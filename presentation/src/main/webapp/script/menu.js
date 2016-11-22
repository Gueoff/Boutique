

    angular.module('shopApp', []).controller('MenuController', function() {
        var menu = this;
        menu.types = [
          {name:'pull'},
          {name:'jean'}];
          
      	}) //END CONTROLLER


	.controller('ShopController', ['$scope', '$http', function(){
		var shop = this;
		var cart = [];
		
		shop.articles = [
			{name: 'pull over', price:3, type:'pull'},
			{name: 'jean magueule', price:6, type:'jean'}
		];



	
    	shop.addToCart = function() {
     		
    	}




	}]) //END CONTROLLER
	
