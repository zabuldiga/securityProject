angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
     const contextPath = 'http://localhost:8080/api/v1/';




                    $scope.loadCart = function(){
                    $http.get(contextPath + 'cart')
                       .then(function(response){
                           $scope.cart = response.data;
                       });
                    }


                    $scope.clearCart = function(){
                           $http.get(contextPath + 'cart/clear')
                           .then(function(response){
                           $scope.cart = response.data;
                           });
                    }

                           $scope.deleteProductByIdToCart = function(productId){
                              $http.get(contextPath + 'cart/delete/' + productId)
                              .then(function(response){
                                   $scope.cart = response.data;
                              });
                              }
      $scope.showOrderForm = false;

      $scope.order = {}; // Телефон и адрес

                                                   // Показывает/скрывает форму
      $scope.toggleOrderForm = function () {
      $scope.showOrderForm = true;
      };

      $scope.clearOrders = function() {
      $scope.MyOrders = [];
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
//                    $scope.loadOrders();
                    $scope.clearCart();
                    $scope.showOrderForm = false;
                    }, function (error) {
                        console.error("Ошибка при отправке заказа:", error);
                        alert("Ошибка при отправке заказа.");
                        });
       };

                              $scope.loadCart();
});