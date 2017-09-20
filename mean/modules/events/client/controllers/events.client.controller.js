'use strict';

// Groups controller
angular.module('events').controller('EventsController', ['$scope', '$http', '$state','$stateParams', '$location', 'Authentication', 'Events', 'NgMap', 'Categories', 'Locations', 'Groups',
  function ($scope, $http, $state, $stateParams, $location, Authentication, Events, NgMap, Categories, Locations, Groups) {
    $scope.authentication = Events;

    // Create new Event
    $scope.create = function (isValid) {
      var event = {};
      event.name = this.name;
      event.description = this.description;
      event.time = this.time;
      event.location = $scope.selectedLocation[0];
      delete event.location.events;
      event.categories = $scope.selectedCategories;
      event.imageUrl = $scope.imageUrl;

      Events.create(event, function(response){
        $location.path('events');
      },function(response){
        $scope.error = response[0];
      });
    };

    $scope.onImageUpload = function (image) {
      $scope.imageUrl = image.name;
    };

    // Create new Event
    $scope.remove = function (eventId) {
      Events.remove($stateParams.eventId, function(response){
        $location.path('events');
      },function(response){
        $scope.error = response;
      });
    };

    // Find a list of Events
    $scope.find = function () {
      Events.find(function(response){
        $scope.isOpen = false;
        $scope.events = response.data;
        $scope.example1model = [];
      },function(response){
        $scope.error = response;
      });
    };

    // Find existing Event
    $scope.findOne = function (loadDependencies) {
      Events.findByIDs([$stateParams.eventId], function(response){
        $scope.event = response.data[0];

        Groups.findByIDs($scope.event.userGroups, function(response){
          $scope.event.userGroups = response.data;
        },function(response){
          $scope.error = response;
        });

        if(loadDependencies){
          Locations.find(function (response) {
            $scope.locations = response.data;
            $scope.event.location = $scope.locations.find(x => x.id === $scope.event.location);
            $scope.categories = $scope.event.location.categories;
            for (var i = 0; i < $scope.event.categories.length; i++) {
              $scope.event.categories[i] = $scope.categories.find(x => x.id === $scope.event.categories[i].id);
            }
          }, function (response) {
            $scope.error = response;
          });
        }
      },function(response){
        $scope.error = response;
      });
    };

    // Update existing Event
    $scope.update = function (isValid) {
      delete this.event.userGroups;
      delete this.event.location.events;
      Events.update(this.event, function(response){
        $location.path('events');
      },function(response){
        $scope.error = response[0];
      });
    };

    $scope.openCalendar = function(e){
      e.preventDefault();
      e.stopPropagation();

      $scope.isOpen = true;
    };

    $scope.findLocations = function(){
      Locations.find(function (response) {
        $scope.locations = response.data;

        $scope.selectedLocation = [];
        $scope.selectedLocation.push = function () { Array.prototype.push.apply(this, arguments); $scope.onLocationSelect(); };
        $scope.selectedLocation.splice = function () { Array.prototype.splice.apply(this, arguments); $scope.onLocationSelect(); };
      }, function (response) {
        $scope.error = response;
      });
    };

    $scope.onLocationSelect = function(){
      $scope.categories = $scope.selectedLocation[0].categories;

      $scope.selectedCategories = [];
    };
  }
]);
