'use strict';

// Articles controller
angular.module('categories').controller('CategoriesController', ['$scope', '$stateParams', '$location', 'Authentication', 'Categories',
  function ($scope, $stateParams, $location, Authentication, Categories) {
    $scope.authentication = Authentication;

    // Create new Category
    $scope.create = function (isValid) {
      var category = {'name': this.name, 'description': this.description};
      Categories.create(category, function(response){
        $location.path('categories');
      },function(response){
        $scope.error = response[0];
      });
    };

    // Remove new Category
    $scope.remove = function (categoryId) {
      Categories.remove($stateParams.categoryId, function(response){
        $location.path('categories');
      },function(response){
        $scope.error = response;
      });
    };

    // Find a list of Categories
    $scope.find = function () {
      Categories.find(function(response){
        $scope.categories = response.data;
      },function(response){
        $scope.error = response;
      });
    };

    // Find existing Category
    $scope.findOne = function () {
      Categories.findByIDs([$stateParams.categoryId], function(response){
        $scope.category = response.data[0];
      },function(response){
        $scope.error = response;
      });
    };

    // Update existing Category
    $scope.update = function (isValid) {
      Categories.update(this.category, function(response){
        $location.path('categories');
      },function(response){
        $scope.error = response[0];
      });
    };
  }
]);
