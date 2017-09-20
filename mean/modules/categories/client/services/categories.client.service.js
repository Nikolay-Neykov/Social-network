'use strict';

//Categories service used for communicating with the articles REST endpoints
angular.module('categories').factory('Categories', ['$http',
  function ($http) {
    return {
      create: function(category, callback, error){
        var body = JSON.stringify(category);
        $http.post('http://localhost:8080/categories', body).then(callback, error);
      },
      find: function(callback, error){
        $http.get('http://localhost:8080/categories').then(callback, error);
      },
      findByIDs: function(IDs, callback, error){
        $http.get('http://localhost:8080/categories/' + convertIdsToParam(IDs)).then(callback, error);
      },
      remove: function(categoryId, callback, error){
        $http.delete('http://localhost:8080/categories/' + categoryId).then(callback, error);
      },
      update: function(category, callback, error){
        var body = JSON.stringify(category);
        $http.put('http://localhost:8080/categories/', body).then(callback, error);
      }
    };

    function convertIdsToParam(IDs){
      var idsParam = "";

      IDs.forEach(function(ID){
        idsParam += ID + ',';
      });

      return idsParam.slice(0, -1);
    }
  }
]);
