'use strict';

// Users service used for communicating with the users REST endpoint
angular.module('users').factory('Users', ['$resource',
  function ($resource) {
    return $resource('api/users', {}, {
      update: {
        method: 'PUT'
      }
    });
  }
]);

angular.module('users').factory('UsersQuery', ['$resource',
  function ($resource) {
    return $resource('api/users/find/:username', {
      username: '@username'
    }, {
      query: {
        method: 'GET',
        isArray: true
      }
    });
  }
]);

angular.module('users').factory('UsersTest', ['$resource',
  function ($resource) {
    return $resource('api/users/get/:userId', {
      userId: '@_id'
    }, {
      update: {
        method: 'PUT'
      },
      find:{
        method: 'GET'
      }
    });
  }
]);

//TODO this should be Users service
angular.module('users.admin').factory('Admin', ['$resource',
  function ($resource) {
    return $resource('api/users/:userId', {
      userId: '@_id'
    }, {
      update: {
        method: 'PUT'
      }
    });
  }
]);