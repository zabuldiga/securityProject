angular.module('market-front').controller('ordersController', function ($scope, $http, $location, $localStorage) {
     const contextPath = 'http://localhost:8080/api/v1/';




                  $scope.loadOrders = function() {
                     $http.get(contextPath + 'order/', {
                                 headers: {
                                         'Authorization': 'Bearer ' + $localStorage.springWebUser.token
                                     }
                                 }).then(function(response) {
                                     $scope.MyOrders = response.data;
                                 });
                                 };


                    $scope.loadOrders();
     });