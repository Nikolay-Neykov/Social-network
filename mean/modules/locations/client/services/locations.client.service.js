'use strict';

//Locations service used for communicating with the articles REST endpoints
angular.module('locations').factory('Locations', ['$http',
  function ($http) {
    return {
      create: function(location, callback, error){
        var body = JSON.stringify(location);
        $http.post('http://localhost:8080/locations', body).then(callback, error);
      },
      find: function(callback, error){
        $http.get('http://localhost:8080/locations').then(callback, error);
      },
      findByIDs: function(IDs, callback, error){
        $http.get('http://localhost:8080/locations/' + convertIdsToParam(IDs)).then(callback, error);
      },
      findByCriteria: function(criteria, callback, error){
        $http.get('http://localhost:8080/locations?search=' + criteria).then(callback, error);
      },
      remove: function(locationId, callback, error){
        $http.delete('http://localhost:8080/locations/' + locationId).then(callback, error);
      },
      update: function(location, callback, error){
        var body = JSON.stringify(location);
        $http.put('http://localhost:8080/locations/', body).then(callback, error);
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
