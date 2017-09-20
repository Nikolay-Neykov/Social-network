'use strict';

/*global google */

// Groups controller
angular.module('groups').controller('GroupsController', ['$scope', '$http', '$state', '$stateParams', '$location', 'Authentication', 'Groups', 'Events', 'UsersTest', 'UsersQuery',
  function ($scope, $http, $state, $stateParams, $location, Authentication, Groups, Events, Users, UsersQuery) {
    $scope.authentication = Authentication;

    // Create new Group
    $scope.create = function (isValid) {
      var group = {};
      group.name = this.name;
      group.description = this.description;
      group.events = $scope.selectedEvents;
      for (var i = 0; i < group.events.length; i++) {
          delete group.events[i].userGroups;
          delete group.events[i].location;
      }
      group.owner = {'id': $scope.authentication.user._id};

      Groups.create(group, function(response){
        $location.path('groups');
      },function(response){
        $scope.error = response[0];
      });
    };

    // Remove Group
    $scope.remove = function (groupId) {
      Groups.remove($stateParams.groupId, function(response){
        $location.path('groups');
      },function(response){
        $scope.error = response;
      });
    };

    // Find existing Group
    $scope.findOne = function (loadDependencies) {
      Groups.findByIDs([$stateParams.groupId], function(response){
        $scope.group = response.data[0];

        $scope.displayUsers = [];
        for (var i = 0; i < $scope.group.users.length; i++) {
          var displayUser = Users.get({
            userId: $scope.group.users[i].id
          });
          $scope.displayUsers.push(displayUser);
        }
        if(loadDependencies){
          Events.find(function (response) {
            $scope.events = response.data;

            for (var i = 0; i < $scope.group.events.length; i++) {
              $scope.group.events[i] = $scope.events.find(x => x.id === $scope.group.events[i].id);
            }
          }, function (response) {
            $scope.error = response;
          });
        }
      },function(response){
        $scope.error = response;
      });
    };

    $scope.search = function () {
      Groups.findByCriteria('users~' + $scope.authentication.user._id, function (response) {
        $scope.groups = response.data;
      }, function (response) {
        $scope.error = response;
      });
    };

    // Update existing Group
    $scope.update = function (isValid) {
      for (var i = 0; i < this.group.events.length; i++) {
        delete $scope.group.events[i].userGroups;
        delete $scope.group.events[i].location;
      }
      Groups.update(this.group, function(response){
        $location.path('groups');
      },function(response){
        $scope.error = response.data;
      });
    };

    $scope.find = function () {
      Groups.find(function (response) {
        $scope.groups = response.data;
      }, function (response) {
        $scope.error = response;
      });
    };

    $scope.findEvents = function(){
      Events.find(function (response) {
        $scope.events = response.data;

        $scope.selectedEvents = [];
        $scope.selectedEvents.push = function() { Array.prototype.push.apply(this, arguments);  $scope.onEventsSelect();};
        $scope.selectedEvents.splice = function() { Array.prototype.splice.apply(this, arguments);  $scope.onEventsSelect();};
      }, function (response) {
        $scope.error = response;
      });
    };

    $scope.onEventsSelect = function(){
      //var eventsQuery = '';
      //for (var i = 0; i < $scope.selectedCategories.length; i++) {
      //  eventsQuery += $scope.selectedCategories[i].id + '$';
      //}
      //
      //Locations.findByCriteria('categories~' + categoriesQuery ,function (response) {
      //  $scope.locations = response.data;
      //  addPositionToLocations();
      //}, function (response) {
      //  $scope.error = response;
      //});
    };

    $scope.onSelectUser = function(event){
      $scope.open = "open";
      $scope.foundUsers = UsersQuery.query({
        username: $scope.selectedUser.username
      });
    };

    $scope.onUserClick = function(user){
      $scope.open = "";
      $scope.selectedUser = user;
    };

    $scope.onInviteUser = function(){
      for (var i = 0; i < this.group.events.length; i++) {
        delete $scope.group.events[i].userGroups;
        delete $scope.group.events[i].location;
      }
      $scope.group.users.push({"id": $scope.selectedUser._id});
      Groups.update($scope.group, function(response){
        $scope.displayUsers.push($scope.selectedUser);
      },function(response){
        $scope.error = response.data;
      });
    };
  }
]);
