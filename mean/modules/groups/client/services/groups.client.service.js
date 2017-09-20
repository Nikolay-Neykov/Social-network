'use strict';

//Groups service used for communicating with the articles REST endpoints
angular.module('groups').factory('Groups', ['$http',
  function ($http) {
    return {
      create: function(group, callback, error){
        var body = JSON.stringify(group);
        $http.post('http://localhost:8080/userGroups', body).then(callback, error);
      },
      find: function(callback, error){
        $http.get('http://localhost:8080/userGroups').then(callback, error);
      },
      findByIDs: function(IDs, callback, error){
        $http.get('http://localhost:8080/userGroups/' + convertIdsToParam(IDs)).then(callback, error);
      },
      findByCriteria: function(criteria, callback, error){
        $http.get('http://localhost:8080/userGroups?search=' + criteria).then(callback, error);
      },
      remove: function(groupId, callback, error){
        $http.delete('http://localhost:8080/userGroups/' + groupId).then(callback, error);
      },
      update: function(group, callback, error){
        var body = JSON.stringify(group);
        $http.put('http://localhost:8080/userGroups/', body).then(callback, error);
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
