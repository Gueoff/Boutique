	const config ={
	    urlClient : "http://localhost:8080/application/clientcommand",
	    urlSupplier : "http://localhost:8080/application/suppliercommand",
	    urlConvert : "http://localhost:8080/application/exchangerate",
	    urlAuth : "http://localhost:8080/application/authentification",
	    urlcreditCard : "http://localhost:8080/application/creditCard"
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
   	 	
   	 	$scope.showAdmin = function(){
			if($scope.adminActif){
				$scope.adminActif = false;
			}
			else{
				$scope.adminActif = true;
			}
		}
   	 	
   	 	$scope.showCreditCard = function(){
			if($scope.creditCardActif){
				$scope.creditCardActif = false;
			}
			else{
				$scope.creditCardActif = true;
			}
		};
   	 	  	 	
   	 	   	 
     }]) //END CONTROLLER


     //Controller general de l'app
	.controller('ShopController', ['$scope', 'clientService', 'loginService', 'supplierService', 'creditCardService', function($scope, clientService, loginService, supplierService, creditCardService){
		//Variables d'objets
		$scope.cart = [];
		$scope.articles = [];
        $scope.types = [];
        $scope.supplier=[];
        
        //Variables du convertisseur de money
        $scope.money = 'EUR';
        $scope.signeMoney = '€';
        $scope.oldMoney = '';
        $scope.total = 0;
        $scope.taux = 1;
        
        //Variables de l'user
        $scope.currentUser = '';
        $scope.admin = false;
    	
    	
    	
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
    	};
    	
   	 	
    	

    	
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
    				$scope.currentUser.cart = $scope.cart;
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
    				$scope.currentUser.cart = $scope.cart;
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
    				$scope.currentUser.cart = [];
    			}
    	  	}, function(){
    	  		alert("Something went wrong with clearCart!");
    		});
    	}
    	
    	//Fonction permettant d'acheter tout le panier
    	$scope.buy = function(cardNumber, expDate) {
    		creditCardService.check(cardNumber, expDate).then(function(response){
    			if(response){
    				alert('Votre carte bleue est valide');
    				clientService.buy($scope.currentUser).then(function(response){
    	    			if(response){
    	    				
    	    				clientService.getArticles($scope.typeArticle).then(function(response){
    	    					$scope.articles = angular.fromJson(response);    		   	
    	    				}, function(){
    	    					alert("Something went wrong with getArticles()!");
    	    				});
    	    				$scope.cart=[];
    	    				$scope.currentUser.cart = [];
    	    			}
    	    	  	}, function(){
    	    	  		alert("Something went wrong with buy!");
    	    		});
    				
    			}
    	  	}, function(){
    	  		alert("Erreur avec votre carte bleue!");
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
    			$scope.taux = taux;
    	  	}, function(){
    	  			alert("Something went wrong with convert!");
    		});
    	};
    	
    	
    	//Fonction permettant à un utilisateur de se connecter.
    	//Affiche les produits a acheter au fournisseur si admin.
    	$scope.login = function (email, password) {
			loginService.login(email, password).then(function(response){
    			$scope.currentUser = angular.fromJson(response);
    			if($scope.currentUser.name == 'admin'){
    				$scope.admin = true;
    				
    				supplierService.getSupplierArticles().then(function(response){
    					$scope.supplier = angular.fromJson(response);
    				}, function(){
    					alert("Something went wrong with getSupplierArticles!");
    				})
    					
    			}
    	  	}, function(){
    	  		alert("Something went wrong with login!");
    		});
		};
		
		//Fonction permettant de créer un nouveau client.
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

		//Fonction permettant d'acheter un produit au fournisseur.
    	$scope.buySupplier = function(index) {
    		supplierService.buy($scope.supplier[index]).then(function(response){
    			if(response){
    				$scope.articles.push($scope.supplier[index]);
    			}
    	  	}, function(){
    	  		alert("Something went wrong with buySupplier!");
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
	
	
	//Factory faisant des appels SOAP vers le serveur.
	.factory("supplierService", ['$soap',function($soap){
	    return {
	        buy: function(article){
	        	var articleJson = angular.toJson(article);
	            return $soap.post(config.urlSupplier, "buy", {article : articleJson});
	        },
	        
	        getSupplierArticles: function(){
	            return $soap.post(config.urlSupplier, "getSupplierArticles");
	        } 
	    }
	}])
	
		//Factory faisant des appels SOAP vers le serveur.
	.factory("creditCardService", ['$soap',function($soap){
	    return {
	    	check: function(cardNumber, expDate){
	        	var cardNumberJson = angular.toJson(cardNumber);
	        	var expDateJson = angular.toJson(expDate);
	            return $soap.post(config.urlcreditCard, "check", {cardNumber : cardNumberJson, expDate : expDateJson});
	        }
	    }
	}])
	