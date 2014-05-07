var vivServices = angular.module('vivServices',[]);
 
vivServices.factory('services', function($http) {
	var services = new Object();

	services.getAllWorks = function() {
             //return the promise directly.
             return $http.get('/rest/works/all')
                       .then(function(result) {
                            //resolve the promise as the data
                            return result.data;
                        });
        }

    services.getUserWorks = function(user) {
             //return the promise directly.
             return $http.get('/rest/works/user/'+user)
                       .then(function(result) {
                            //resolve the promise as the data
                            return result.data;
                        });
        }
    services.getWork = function(workId) {
             //return the promise directly.
             return $http.get('/rest/works/work/'+workId)
                       .then(function(result) {
                            //resolve the promise as the data
                            return result.data;
                        });
        }
    return services;
});