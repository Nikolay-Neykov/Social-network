'use strict';

// Configuring the Articles module
angular.module('groups').run(['Menus',
  function (Menus) {
    // Add the articles dropdown item
    Menus.addMenuItem('topbar', {
      title: 'Groups',
      state: 'groups',
      type: 'uib-dropdown',
      roles: ['*']
    });

    // Add the dropdown list item
    Menus.addSubMenuItem('topbar', 'groups', {
      title: 'List Groups',
      state: 'groups.list'
    });

    // Add the dropdown create item
    Menus.addSubMenuItem('topbar', 'groups', {
      title: 'Favourite Groups',
      state: 'groups.favourite',
      //roles: ['user']
    });

    // Add the dropdown create item
    Menus.addSubMenuItem('topbar', 'groups', {
      title: 'Create Group',
      state: 'groups.create',
      //roles: ['user']
    });
  }
]);
