const config ={
    urlClient : "http://localhost:8080/application/clientcommand",
    urlSupplier : "",
};


angular.module('myApp', ['angularSoap'])

.factory("testService", ['$soap',function($soap){

    return {
        HelloWorld: function(){
 
            return $soap.post(config.urlClient,"addToCart",{},{});
        }
    }
}])

.controller('MainCtrl', function($scope, testService) {

  testService.HelloWorld().then(function(response){

	  $scope.response = response;
  }, function(){
	  alert("Something went wrong!");
	});

})