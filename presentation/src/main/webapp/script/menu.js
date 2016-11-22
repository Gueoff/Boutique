

    angular.module('shopApp', [])
    
    
    .controller('MenuController', ['$scope', '$http', function($scope, $http) {
        $scope.types = [
          {name:'pull'},
          {name:'jean'}];
          
          
        $scope.derouler = function(){
			if($scope.actif){
				$scope.actif = false;
			}
			else{
				$scope.actif = true;
			}
			
		}
          
      	}]) //END CONTROLLER


	.controller('ShopController', ['$scope', '$http', function($scope, $http){
		$scope.cart = [];
		
		$scope.articles = [
			{name: 'pull over', price:3, type:'pull'},
			{name: 'jean magueule', price:6, type:'jean'}
		];



	
    	$scope.addToCart = function() {
     		 articles.push({name:'hey', price:90, type:'lol'});
    	}




	}]) //END CONTROLLER
	
