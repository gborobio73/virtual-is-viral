var viv = angular.module('vivControllers', []);
 
viv.controller(
	'boardController', function ($scope, services) {

		$scope.getAllWorks = function() {
      services.getAllWorks().then(
				function(result) {
					$scope.works = result;
   		 		});	
    };

    $scope.getUserWorks = function(user) {
    		services.getUserWorks(user).then(
				function(result) {
					$scope.works = result;
   		 		});	
    };

		$scope.getAllWorks();
	});

viv.controller(
  'userDataController', function ($scope, services) {
    var user = 'Borobio';
    var getUnreadNotificationsAmount = function(user){
      services.getUnreadNotificationsAmount(user).then(
        function(result) {
          $scope.unreadNotifications = result;
          }); 
    }

    var getNotifications = function(user){
      services.getNotifications(user).then(
        function(result) {
          console.log(result);
          $scope.notifications = result;
          }); 
    }

    getUnreadNotificationsAmount(user);
    getNotifications(user);
    $scope.user = user;
  });


viv.controller(
  'detailsController', function ($scope, $stateParams, services) {
    
    var getWork = function(workId) {
      services.getWork(workId).then(
        function(result) {
          $scope.workDetails = result;
          console.log ('getWork() returns: ' + JSON.stringify(result));
          }); 
    };
    var workId = $stateParams.workId;
    getWork(workId);
    
  });

viv.directive('navMenu', function() {
    return {
      templateUrl: 'shared/nav-menu.html'
    };
  });
