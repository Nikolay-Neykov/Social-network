'use strict';

// Configuring the Articles module
angular.module('events').run(['Menus',
  function (Menus) {
    // Add the articles dropdown item
    Menus.addMenuItem('topbar', {
      title: 'Events',
      state: 'events',
      type: 'uib-dropdown',
      roles: ['*']
    });

    // Add the dropdown list item
    Menus.addSubMenuItem('topbar', 'events', {
      title: 'List Events',
      state: 'events.list'
    });

    // Add the dropdown create item
    Menus.addSubMenuItem('topbar', 'events', {
      title: 'Create Event',
      state: 'events.create',
      //roles: ['usera']
    });
  }
]);
