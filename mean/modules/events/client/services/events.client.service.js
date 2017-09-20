'use strict';

//Events service used for communicating with the articles REST endpoints
angular.module('events').factory('Events', ['$http',
  function ($http) {
    return {
      create: function(event, callback, error){
        var body = JSON.stringify(event);
        $http.post('http://localhost:8080/events', body).then(callback, error);
      },
      find: function(callback, error){
        $http.get('http://localhost:8080/events').then(callback, error);
      },
      findByIDs: function(IDs, callback, error){
        $http.get('http://localhost:8080/events/' + convertIdsToParam(IDs)).then(callback, error);
      },
      remove: function(eventId, callback, error){
        $http.delete('http://localhost:8080/events/' + eventId).then(callback, error);
      },
      update: function(event, callback, error){
        var body = JSON.stringify(event);
        $http.put('http://localhost:8080/events/', body).then(callback, error);
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
