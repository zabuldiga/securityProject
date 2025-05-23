(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                            templateUrl: 'orders/orders.html',
                            controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springWebUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
        }
          if (!$localStorage.springWebGuestCartId) {
                    $http.get('http://localhost:8080/api/v1/cart/generate')
                        .then(function successCallback(response) {
                            $localStorage.springWebGuestCartId = response.data.value;
                        });
                }
    }
})();


angular.module('market-front').controller('indexController', function ($rootScope,$scope, $http ,$location, $localStorage) {
    const contextPath = 'http://localhost:8080/api/v1/';
    $scope.user = {
        username: '',
        password: ''
    };

                    $scope.clearOrders = function() {
                        $scope.MyOrders = [];
                    };

                 $scope.isUserLoggedIn = function () {
                            return $localStorage.springWebUser && $localStorage.springWebUser.token;
                            };

                             $scope.logout = function () {
                                delete $localStorage.springWebUser;
                                delete $http.defaults.headers.common.Authorization;
                                $scope.clearOrders();
                                $location.path('/');
                                };

                                 $scope.goToLogin = function () {
                                   if ( !$scope.user.username || !$scope.user.password) {
                                     alert("Введите логин и пароль");
                                     return;
                                   }
                                    $http.post( 'login/auth', $scope.user)
                                        .then(function successCallback(response) {
                                            if (response.data.token) {
                                                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                                                $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
                                                $scope.user.username = null;
                                                $scope.user.password = null;

                                                $http.get('http://localhost:8080/api/v1/cart/' + $localStorage.springWebGuestCartId + '/merge')
                                                                        .then(function successCallback(response) {
                                                 });

                                                 $location.path('/');
                                            }
                                        }, function errorCallback(response) {
                                        });
                                 };





// user
//         $scope.createNewUser = function () {
//                                      console.log($scope.user);
//                                      $http.post(contextPath +  'apps/new-user/' , $scope.user)
//                                          .then(function (response) {
//                                               alert('successful registration : ' + $scope.user.username);
//                                              $scope.load();
//                                          });
//         }
//
//          $scope.showCurrentUserInfo = function(){
//          $http.get('/profile', {
//             headers: {
//               'Authorization': 'Bearer ' + $localStorage.springWebUser.token
//             }
//          }).then(function successCallback(response){
//              alert('MY NAME IS: ' + response.data.username);
//           }, function errorCallback(response){
//                  alert('UNAUTHORIZED');
//               });
//         }
//
//
//
//
//


//

});

