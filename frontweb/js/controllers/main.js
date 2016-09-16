'use strict';

/**
 * @ngdoc function
 * @name cloudunitDemoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the cloudunitDemoApp
 */
angular.module('cloudunitDemoApp').controller('mainCtrl', function($scope, $stomp, $log, CONFIG) {
			
	var self = this;
	$scope.sumValue = 0;
	$scope.sumShares = 0;
	
	// graph compagny
	$scope.compagniesTab = [];
  	$scope.sharesTab = [];
	  
	  
	$scope.compagniesTab = [];
  	$scope.sharesTab = [];
  
	// graph value
	$scope.labels = ["n-6", "n-5", "n-4", "n-3", "n-2", "n-1", "n"];

	$scope.data = [
		[0, 0, 0, 0, 0, 0, 0]
	];
	
  
	function updateGraph() {
		angular.forEach($scope.compagnies, function(data, key) {
			$scope.sumValue += data.value;
			$scope.sumShares += data.share;
			$scope.sharesTab.push(data.share);
			$scope.compagniesTab.push(data.name);
		});
		
		var length = $scope.data[0].length;
		for(var i = 1; i < length; i++) {
			$scope.data[0][i-1] = $scope.data[0][i];
		}
		$scope.data[0][length -1] = $scope.sumValue;
	}


	$stomp
	.connect(CONFIG.baseUrl + '/my-socket')

	.then(function (frame) {
		var subscription = $stomp.subscribe('/topic/results', function (payload, headers, res) {
			$scope.sumValue = $scope.sumShares = 0;
			$scope.compagniesTab = [];
			$scope.sharesTab = [];
			
			$scope.compagnies = JSON.parse(res.body);
			

			updateGraph();
			$scope.$apply();
		})
		
		/*
		// Unsubscribe
		subscription.unsubscribe()

		// Send message
		$stomp.send('/dest', {
		message: 'body'
		}, {
		priority: 9,
		custom: 42 // Custom Headers
		})

		// Disconnect
		$stomp.disconnect(function () {
			$log.info('disconnected')
		})*/
	})
	
});
