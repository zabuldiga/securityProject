angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/api/v1/';
    $scope.currentPage = 1;


    $scope.load = function(pageIndex){
             console.log($scope.currentPage);
             currentPage = pageIndex;
             $http({
                                  url: contextPath + 'products/',
                                  method: 'get',
                                      params: {
                                          minCost: $scope.minCost,
                                          maxCost: $scope.maxCost,
                                          page: $scope.currentPage
                                  }
                              }).then(function (response) {
                                 $scope.ProductsList = response.data.content;
                                 $scope.totalPages = response.data.page.totalPages; // Доступ к totalPages внутри page

                                 console.log("Текущая страница:", pageIndex, "Всего страниц:", $scope.totalPages);
                              });
            }

   $scope.deleteProduct = function(productId){
                         $http.delete(contextPath + 'products/' +  productId)
                         .then(function(response){
                          $scope.load();
                          });
                       }


                          $scope.changeCost = function(id,delta){
                                     $http({
                                       url: contextPath + 'products/change_cost' ,
                                         method: 'PATCH',
                                           params:{
                                              id: id,
                                              delta: delta
                                                  }
                                 }).then(function(response){
                                       $scope.load();
                                 });
                                 }

                           $scope.createProductDtoJson = function () {
                              console.log($scope.newProductJson);
                                  $http.post(contextPath + 'products/' , $scope.newProductDtoJson)
                                  .then(function (response) {
                                  $scope.newProductDtoJson.name ='';
                                  $scope.newProductDtoJson.cost ='';
                                  $scope.load();
                             });
                           }

                            $scope.previousPage = function () {
                                                     if ($scope.currentPage > 1) {
                                                         $scope.currentPage--;
                                                         $scope.load($scope.currentPage);
                                                     }
                                                 };

                                                 $scope.nextPage = function () {
                                                     $scope.currentPage++;
                                                     $scope.load($scope.currentPage);
                                                 };
//   $scope.addToCart =  function(productId){
//                            $http.get(contextPath + 'cart/add/' + productId)
//                                 .then(function (response) {
//                            });
//                     };
$scope.addToCart = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.springWebGuestCartId + '/add/' + productId)
            .then(function (response) {
            });
    };
      $scope.load();
});