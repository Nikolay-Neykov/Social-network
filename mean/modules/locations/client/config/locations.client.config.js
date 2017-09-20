'use strict';

// Configuring the Articles module
angular.module('locations').run(['Menus',
  function (Menus) {
    // Add the articles dropdown item
    Menus.addMenuItem('topbar', {
      title: 'Locations',
      state: 'locations',
      type: 'uib-dropdown',
      roles: ['*']
    });

    // Add the dropdown list item
    Menus.addSubMenuItem('topbar', 'locations', {
      title: 'List Locations',
      state: 'locations.list'
    });

    // Add the dropdown create item
    Menus.addSubMenuItem('topbar', 'locations', {
      title: 'Create Location',
      state: 'locations.create',
      roles: ['admin']
    });
  }
]);
