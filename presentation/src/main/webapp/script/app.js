	const config ={
	    urlClient : "http://localhost:8080/application/clientcommand",
	    urlSupplier : "http://localhost:8080/application/suppliercommand",
	    urlConvert : "http://localhost:8080/application/exchangerate",
	    urlAuth : "http://localhost:8080/application/authentification"
	};

    angular.module('shopApp', ['angularSoap'])
    
    //Controller pour les actions graphiques du menu
    .controller('MenuController',['$scope', function($scope) {
    	          
        $scope.showType = function(){
			if($scope.typeActif){
				$scope.typeActif = false;
			}
			else{
				$scope.typeActif = true;
			}
		} 
        
   	 	$scope.showCart = function(){
			if($scope.cartActif){
				$scope.cartActif = false;
			}
			else{
				$scope.cartActif = true;
			}
		}
   	 	   	 
     }]) //END CONTROLLER


     //Controller general de l'app
	.controller('ShopController', ['$scope', 'clientService', 'loginService', function($scope, clientService, loginService){
		//Variables d'objets
		$scope.cart = [];
		$scope.articles = [];
        $scope.types = [];
        
        //Variables du convertisseur de money
        $scope.money = 'EUR';
        $scope.signeMoney = '€';
        $scope.oldMoney = '';
        $scope.total = 0;
        
        //Variables de l'user
        //mock
        $scope.currentUser = '';//{name:'dela', firstname:'geoffrey', age:22, email:'g@d.com', password:'azerty',cart: $scope.cart};

    	
    	
    	
    	//FUNCTIONS
		$scope.getTotal = function(){
			$scope.total = 0;
    		for(var i = 0; i < $scope.cart.length; i++){
        		$scope.total += ($scope.cart[i].price)*1;
    		}
		};
	
    	$scope.showLogup = function(){
    		if($scope.logupActif){
				$scope.logupActif = false;
			}
			else{
				$scope.logupActif = true;
			}
    	}
    	

    	
    	//Fonction permettant de charger tous les types d'articles et les articles dès le lancement de l'app.
    	clientService.getTypesArticle().then(function(response){
			$scope.types = angular.fromJson(response);
			$scope.typeArticle = $scope.types[0].name;
			
			clientService.getArticles($scope.typeArticle).then(function(response){
				$scope.articles = angular.fromJson(response);
			}, function(){
				alert("Something went wrong with getArticles()!");
			});
			
		}, function(){
			alert("Something went wrong with getTypesArticle()!");
		});
    	
    	//Fonction permettant de changer de type d'article
    	$scope.changeType = function(index){
    		$scope.typeArticle = $scope.types[index].name;
    		clientService.getArticles($scope.typeArticle).then(function(response){
				$scope.articles = angular.fromJson(response);
			}, function(){
				alert("Something went wrong with getArticles()!");
			});
    	};
    	
    	//Fonction permettant d'ajouter un article au pannier
    	$scope.addToCart = function(index) {
    		clientService.addToCart($scope.currentUser, $scope.articles[index]).then(function(response){
    			if(response){
    				$scope.cart.push($scope.articles[index]);
    				$scope.getTotal();
    			}
    	  	}, function(){
    	  		alert("Something went wrong with addToCart!");
    		});
    	}
    	
    	//Fonction permettant de retirer un article du pannier
    	$scope.removeToCart = function(index) {
    		clientService.removeToCart($scope.currentUser, $scope.cart[index]).then(function(response){
    			if(response){
    				$scope.cart.pop($scope.cart[index]);
    				$scope.getTotal();
    			}
    	  	}, function(){
    	  		alert("Something went wrong with removeToCart!");
    		});
    	}
    	
    	//Fonction permettant de vider le panier
    	$scope.clearCart = function() {
    		clientService.clearCart($scope.currentUser).then(function(response){
    			if(response){
    				$scope.cart = [];
    				$scope.getTotal();
    			}
    	  	}, function(){
    	  		alert("Something went wrong with clearCart!");
    		});
    	}
    	
    	//Fonction permettant d'acheter tout le panier
    	$scope.buy = function() {
    		clientService.buy($scope.currentUser).then(function(response){
    			alert('on achete');
    	  	}, function(){
    	  		alert("Something went wrong with buy!");
    		});
    	}
    	
    	//Fonction permettant de changer la monnaie, et donc de convertir tous les prix.
    	$scope.convert = function(money) {
			$scope.money = money;
			if(money == 'USD'){
				$scope.oldMoney = 'EUR';
				$scope.signeMoney = '$';
			} else{
				$scope.oldMoney = 'USD';
				$scope.signeMoney = '€';
			}
			var taux = 1;
			
    		clientService.convert(taux, $scope.oldMoney, $scope.money).then(function(response){
    			taux = response;
    				
    			//MAJ des prix des articles
    		   	for(i=0; i< $scope.articles.length; i++){
    				$scope.articles[i].price = ($scope.articles[i].price*taux).toFixed(2);
    			}
    			//MAJ des prix du panier
    			for(i =0; i< $scope.cart.length; i++){
    				$scope.cart[i].price = ($scope.cart[i].price*taux).toFixed(2);
    			}	
    			$scope.getTotal();	
    	  	}, function(){
    	  			alert("Something went wrong with convert!");
    		});
    	};
    	
    	
    	
    	$scope.login = function (email, password) {
			loginService.login(email, password).then(function(response){
    			$scope.currentUser = angular.fromJson(response);
    	  	}, function(){
    	  		alert("Something went wrong with login!");
    		});
		};
		
		$scope.logup = function(email, password, firstname, name, age) {
			var client = {name:name, firstname:firstname, age:age, email:email, password:password,cart: []};
			loginService.logup(client).then(function(response){
				if(response){
					$scope.currentUser = client;
				}
    	  	}, function(){
    	  		alert("Something went wrong with logup!");
    		});
		};
    	
	}]) //END CONTROLLER
	
	
	//Factory faisant des appels SOAP vers le serveur.
	.factory("clientService", ['$soap',function($soap){
	    return {
	        getTypesArticle: function(){
	            return $soap.post(config.urlClient,"getTypesArticle");
	        },
	        
	        getArticles: function(type){
	            return $soap.post(config.urlClient,"getArticles",{type : type});
	        },
	        
	        addToCart: function(client, article){
	        	var clientJson = angular.toJson(client);
	        	var articleJson = angular.toJson(article);
	            return $soap.post(config.urlClient,"addToCart",{client : clientJson, article : articleJson});
	        },
	        
	        removeToCart: function(client, article){
	        	var clientJson = angular.toJson(client);
	        	var articleJson = angular.toJson(article);
	            return $soap.post(config.urlClient,"removeToCart",{client : clientJson, article : articleJson});
	        },
	 
	        clearCart: function(client){
	        	var clientJson = angular.toJson(client);
	            return $soap.post(config.urlClient, "clearCart", {client : clientJson});
	        },
	        
	        buy: function(client){
	        	var clientJson = angular.toJson(client);
	            return $soap.post(config.urlClient, "buy", {client : clientJson});
	        },
	        
	        convert: function(amount, from, to){
	            return $soap.post(config.urlConvert, "convert", {amount : amount, from : from, to : to});
	        }   
	    }
	}])
	
	
	//Factory faisant des appels SOAP vers le serveur.
	.factory("loginService", ['$soap',function($soap){
	    return {
	        login: function(email, password){
	            return $soap.post(config.urlAuth, "login", {email : email, password : password});
	        },
	        
	        logup: function(client){
	        	var clientJson = angular.toJson(client);
	            return $soap.post(config.urlAuth, "logup", {client : clientJson});
	        } 
	    }
	}])
	