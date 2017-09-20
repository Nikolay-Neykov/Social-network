'use strict';

/*global google */

// Groups controller
angular.module('locations').controller('LocationsController', ['$scope', '$http', '$state', '$stateParams', '$location', 'Authentication', 'Locations', 'NgMap', 'Categories',
  function ($scope, $http, $state, $stateParams, $location, Authentication, Locations, NgMap, Categories) {
    $scope.authentication = Authentication;

    $scope.categoriesEvents = {
      onSelectionChanged: $scope.onItemSelect
    };

    // Create new Location
    $scope.create = function (isValid) {
      var location = {};
      location.name = this.name;
      location.description = this.description;
      location.latitude = this.latitude;
      location.longitude = this.longitude;
      location.categories = $scope.selectedCategories;

      Locations.create(location, function(response){
        $location.path('locations');
      },function(response){
        $scope.error = response[0];
      });
    };

    // Remove Location
    $scope.remove = function (locationId) {
      Locations.remove($stateParams.locationId, function(response){
        $location.path('locations');
      },function(response){
        $scope.error = response;
      });
    };

    // Find existing Location
    $scope.findOne = function () {
      Locations.findByIDs([$stateParams.locationId], function(response){
        $scope.location = response.data[0];
        Categories.find(function (response) {
          $scope.categories = response.data;
          for (var i = 0; i < $scope.location.categories.length; i++) {
            $scope.location.categories[i] = $scope.categories.find(x => x.id === $scope.location.categories[i].id);
          }
        }, function (response) {
          $scope.error = response;
        });
      },function(response){
        $scope.error = response;
      });
    };

    // Update existing Location
    $scope.update = function (isValid) {
      Locations.update(this.location, function(response){
        $location.path('locations');
      },function(response){
        $scope.error = response[0];
      });
    };

    $scope.find = function () {
      // If successful we assign the response to the global user model
      Locations.find(function (response) {
        $scope.locations = response.data;
        //for (var i = 0; i < $scope.locations.length; i++) {
        //  $scope.location.categories[i] = $scope.categories.find(x => x.id === $scope.location.categories[i].id);
        //}
        addPositionToLocations();
      }, function (response) {
        $scope.error = response;
      });
      // And redirect to the previous or home page
      //$state.go($state.previous.state.name || 'home', $state.previous.params);
    };

    $scope.findCategories = function(){
      Categories.find(function (response) {
        $scope.categories = response.data;

        $scope.selectedCategories = [];
        $scope.selectedCategories.push = function() { Array.prototype.push.apply(this, arguments);  $scope.onCategoriesSelect();};
        $scope.selectedCategories.splice = function() { Array.prototype.splice.apply(this, arguments);  $scope.onCategoriesSelect();};
      }, function (response) {
        $scope.error = response;
      });
    };

    $scope.onCategoriesSelect = function(){
      var categoriesQuery = '';
      for (var i = 0; i < $scope.selectedCategories.length; i++) {
        categoriesQuery += $scope.selectedCategories[i].id + '$';
      }

      Locations.findByCriteria('categories~' + categoriesQuery ,function (response) {
        $scope.locations = response.data;
        addPositionToLocations();
      }, function (response) {
        $scope.error = response;
      });
    };

    $scope.search = function(){
      if($scope.searchQuery){
        Locations.findByCriteria('name:' + $scope.searchQuery,function (response) {
          $scope.locations = response.data;
          addPositionToLocations();
        }, function (response) {
          $scope.error = response;
        });
      } else {
        $scope.find();
      }
    };

    $scope.showDetails = function (event, location) {
      $scope.selectedLocation = location;
      $scope.objMapa.showInfoWindow('details', location.id);
    };

    $scope.$on('mapInitialized', function (event, map) {
      $scope.objMapa = map;
    });

    function addPositionToLocations() {
      $scope.locations.forEach(function (location) {
        var image = location.events.length > 0 ? 'modules/locations/client/img/markers/event.png': 'modules/locations/client/img/markers/location.png';
        location.position = [location.latitude, location.longitude];
        location.id = "" + location.id;
        location.icon = {url: image, scaledSize:[32, 32]};
      });

      addCurrentLocation();
    }

    function addCurrentLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          $scope.$apply(function () {
            $scope.currentLocation = {
              'id': '0',
              'name': 'You Are Here',
              'animation': google.maps.Animation.BOUNCE,
              '': {url: 'modules/locations/client/img/markers/current_location.png', scaledSize: [30, 30]},
              'position': [position.coords.latitude, position.coords.longitude]
            };
            $scope.locations.push($scope.currentLocation);
          });
        });
      }
    }

    //String.prototype.format = function() {
    //  var formatted = this;
    //  for( var arg in arguments ) {
    //    formatted = formatted.replace("{" + arg + "}", arguments[arg]);
    //  }
    //  return formatted;
    //};
  }
]);
