var vivControllers = angular.module('vivControllers', []);
 
vivControllers.controller(
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
    $scope.user = 'Gonzalo';

	});

vivControllers.controller(
  'WorkDetailsCtrl',function ($scope, $modal, $log, services) {

    var ModalInstanceCtrl = function ($scope, $modalInstance, workId) {
      $scope.workId = workId;
      services.getWork(workId).then(function(result) {
           $scope.work=result;
          } );
     
      $scope.ok = function () {
        $modalInstance.close();
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    };

    $scope.open = function (workId) {
      var modalInstance = $modal.open({
        templateUrl: './partials/workDetails.html',
        controller: ModalInstanceCtrl,
        resolve: {
          workId: function () {
            return workId;
          }
        }
      });
    };
});