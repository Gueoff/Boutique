

    angular.module('shopApp', [])
    
    
    .controller('MenuController', ['$scope', '$http', function($scope, $http) {
        $scope.types = [
          {name:'pull'},
          {name:'jean'}];
          
        $scope.showType = function(){
			if($scope.typeActif){
				$scope.typeActif = false;
			}
			else{
				$scope.typeActif = true;
			}
		} 
     }]) //END CONTROLLER


	.controller('ShopController', ['$scope', '$http', function($scope, $http){
		
		$scope.cart= [
            {name:'nom du truc', price:3, type:'pull'},
            {name:'deuxieme truc', price:5, type:'pull'}];
		
		$scope.articles = [
			{name: 'pull over', price:3, type:'pull'},
			{name: 'jean magueule', price:6, type:'jean'}
		];


	 $scope.showCart = function(){
			if($scope.cartActif){
				$scope.cartActif = false;
			}
			else{
				$scope.cartActif = true;
			}
		}
		
		$scope.getTotal = function(){
    		var total = 0;
    		for(var i = 0; i < $scope.cart.length; i++){
        		var product = $scope.cart[i];
        		total += (product.price);
    		}
    		return total;
		}
	
    	$scope.addToCart = function(index) {
     		alert($scope.articles[index].name);
     		$scope.cart.push($scope.articles[index]);
     		
    	}




	}]) //END CONTROLLER
	
