angular.module('api', ['ngStorage']).controller('indexController', function ($scope, $http , $localStorage) {
    const contextPath = 'http://localhost:8080/api/v1/';
    $scope.currentPage = 1;
    $scope.user = {
        username: '',
        password: ''
    };






//   page
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








//   auth
                 $scope.isUserLoggedIn = function () {
                            return $localStorage.springWebUser && $localStorage.springWebUser.token;
                            };

                             $scope.logout = function () {
                                delete $localStorage.springWebUser;
                                delete $http.defaults.headers.common.Authorization;
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
                                            }
                                        }, function errorCallback(response) {
                                        });
                                 };





// user
         $scope.createNewUser = function () {
                                      console.log($scope.user);
                                      $http.post(contextPath +  'apps/new-user/' , $scope.user)
                                          .then(function (response) {
                                               alert('successful registration : ' + $scope.user.username);
                                              $scope.load();
                                          });
         }

          $scope.showCurrentUserInfo = function(){
          $http.get('/profile')
          .then(function successCallback(response){
              alert('MY NAME IS: ' + response.data.username);
           }, function errorCallback(response){
                  alert('UNAUTHORIZED');
               });
         }





// load
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







//  products
                    $scope.deleteProduct = function(productId){
                      $http.delete(contextPath + 'products/' +  productId)
                      .then(function(response){
                       $scope.load();
                       });
                    }

                       $scope.deleteProductInBasket = function(productId){
                           $http.delete(contextPath +'products/basket/'+ productId)
                           .then(function(response){
                           $scope.loadBasket();
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





// cart
                $scope.addToCart =  function(productId){
                        $http.get(contextPath + 'carts/add/' + productId)
                             .then(function (response) {
                              $scope.loadCart();
                        });

                 }


                $scope.loadCart = function(){
                $http.get(contextPath + 'carts')
                   .then(function(response){
                       $scope.cart = response.data;
                   });
                }


                $scope.clearCart = function(){
                       $http.get(contextPath + 'carts/clear')
                       .then(function(response){
                       $scope.cart = response.data;
                       });
                }


               // Переменная-флаг
               $scope.showOrderForm = false;
               $scope.order = {}; // Телефон и адрес

               // Показывает/скрывает форму
               $scope.toggleOrderForm = function () {
                   $scope.showOrderForm = true;
               };

               $scope.submitOrder = function () {
                   if (!$scope.order.phone || !$scope.order.address) {
                       alert("Введите телефон и адрес");
                       return;
                   }

                   const orderDto = {
                       username: $localStorage.springWebUser.username,
                       product_title: $scope.cart.items.length > 0 ? $scope.cart.items[0].productTitle : '',
                       total_price: $scope.cart.totalPrice,
                       phone: $scope.order.phone,
                       address: $scope.order.address,
                       items: $scope.cart.items.map(item => ({
                           productTitle: item.productTitle,
                           quantity: item.quantity,
                           pricePerProduct: item.pricePerProduct,
                           price: item.price
                       }))
                   };

                   $http.post(contextPath + 'order/', orderDto)
                       .then(function (response) {
                           alert("Заказ успешно отправлен!");
                           console.log(orderDto);
                           $scope.order = {};
                           $scope.showOrderForm = false;
                       }, function (error) {
                           console.error("Ошибка при отправке заказа:", error);
                           alert("Ошибка при отправке заказа.");
                       });
               };












        $scope.load();
        $scope.loadCart();
});

