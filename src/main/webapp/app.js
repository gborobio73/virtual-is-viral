var vivApp = angular.module('viv', ['ui.router', 'ui.bootstrap', 'vivControllers', 'vivServices']);

vivApp.config(function($stateProvider, $urlRouterProvider) {
  //
  // For any unmatched url
  //$urlRouterProvider.otherwise("/Board");
  //
  // Now set up the states
  $stateProvider
    .state('Board', {
      url: '/Board',
      templateUrl: 'partials/Board.html',
      controller: 'boardController'
    })
    .state('NewBoard', {
      url: '/NewBoard',
      templateUrl: 'partials/NewBoard.html',
      controller: 'boardController'
    })
    .state('Tiles', {
      url: '/Tiles',
      templateUrl: 'Tiles.html',
      controller: 'boardController'
    })
});