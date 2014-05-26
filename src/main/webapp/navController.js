angular.module('vivControllers').controller(
  'userDataController', function ($scope, $cookies, $location, services) {
    
    var getUnreadNotificationsAmount = function(user){
      services.getUnreadNotificationsAmount(user).then(
        function(result) {
          $scope.unreadNotifications = result;
          }); 
    }

    var getNotifications = function(user){
      services.getNotifications(user).then(
        function(result) {
          $scope.notifications = result;
          }); 
    }

    $scope.user = JSON.parse(sessionStorage.getItem('user'));
    getUnreadNotificationsAmount($scope.user.id);
    getNotifications($scope.user.id);

    
    $scope.markAllNotificationsAsRead = function(user) {
        console.log("markAllNotificationsAsRead for user:" +user);
        $scope.unreadNotifications = 0;
    };

    $scope.logout = function() {
        console.log("logout.");
        gapi.auth.signOut();
        sessionStorage.removeItem('user');
        window.location.replace("/Logout.html");
    };
    $scope.testStuff = function() {
        console.log("testStuff.");
        
        
        
        var request = gapi.client.plus.people.get(
        {
          'userId' : 'me'
        }
        );
        request.execute(function(resp) {
          console.log('SCOPE: ' + JSON.stringify($scope.user));
          $scope.user=resp.displayName;
          $scope.$apply();
          console.log('RESP: ' + JSON.stringify(resp));
          console.log('ID: ' + resp.id);
          console.log('Display Name: ' + resp.displayName);
          console.log('Image URL: ' + resp.image.url);
          console.log('Profile URL: ' + resp.url);
          console.log('SCOPE: ' + JSON.stringify($scope.user));
          
        });     
    };

    $scope.fullgapiauthorize = function() {
        console.log("testStuff.");
        gapi.client.load('plus', 'v1', function() { 
          console.log('loaded.'); 

          gapi.auth.authorize(
            {
              client_id: '17792696780-egbqbeqkdbamg2aojs0e7otgvq1i2p06.apps.googleusercontent.com', 
              scope: 'https://www.googleapis.com/auth/plus.login', 
              immediate: false
            }, 
            function(){
              var request = gapi.client.plus.people.get(
              {
                'userId' : 'me'
              }
              );
              request.execute(function(resp) {
                console.log('RESP: ' + JSON.stringify(resp));
                console.log('ID: ' + resp.id);
                console.log('Display Name: ' + resp.displayName);
                console.log('Image URL: ' + resp.image.url);
                console.log('Profile URL: ' + resp.url);
              });      
          });

            
        });

        
    };
  });