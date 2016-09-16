'use strict';

/**
 * @ngdoc overview
 * @name cloudunitDemoApp
 * @description
 * # cloudunitDemoApp
 *
 * Main module of the application.
 */
angular
  .module('cloudunitDemoApp', [
      'ngRoute', 'ngMaterial', 'ngStomp', 'ngMdIcons', 'data-table', 'chart.js'
  ])
	.constant('CONFIG', {
     baseUrl: 'http://CHEMIN_BACKEND'

  })
  .config(function ($routeProvider, $mdThemingProvider) {
	/*	$mdThemingProvider.theme('default')
    	.primaryPalette('light-blue')
    	.accentPalette('light-blue');*/
		
	// Extend the red theme with a few different colors
  var customPalette = $mdThemingProvider.extendPalette('light-blue', {
    '500': '29b6f6',
		    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)

  });
  // Register the new color palette map with the name <code>neonRed</code>
  $mdThemingProvider.definePalette('custom-light-blue', customPalette);
  // Use that theme for the primary intentions
  $mdThemingProvider.theme('default')
    .primaryPalette('custom-light-blue')
		
		$routeProvider.when('/', {
			templateUrl : 'views/main.html',
			controller : 'mainCtrl',
			controllerAs: 'controller'
	}).otherwise('/');

	//$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

  });
