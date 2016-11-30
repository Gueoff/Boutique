	const config ={
	    urlClient : "http://localhost:8080/application/clientcommand",
	    urlSupplier : "",
	    urlConvert : "	http://localhost:8080/application/exchangerate"
	};

    angular.module('shopApp', ['angularSoap'])
    
    
    .controller('MenuController',['$scope', function($scope) {
    	
    	$scope.message = "menuC";
          
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


	.controller('ShopController', ['$scope', '$location', 'USER_ROLES', 'AuthService', 'clientService', function($scope, $location, USER_ROLES, AuthService, clientService){
		//VARIABLES
		$scope.cart = [];
		
		$scope.articles = [
			{name: 'pull over',   description:'ptite description', price:3, available:true, type:'pull'},
			{name: 'jean magueule',  description:'', price:6, available:true, type:'jean'}
		];
		
        $scope.types = [
            {name:'tshirt'},
            {name:'pantalon'}
        ];
        
        $scope.money = 'EUR';
        $scope.signeMoney = '€';
        $scope.oldMoney = '';
        $scope.total = 0;
        

        
        //$scope.typeArticle = $location.search();
        $scope.typeArticle = 'pull';
        
        //mock
        $scope.currentUser = {name:'desbrosses', firstname:'geoffrey', age:22, email:'g@d.com',cart: $scope.cart};
    	//$scope.currentUser = null;
    	$scope.userRoles = USER_ROLES;
    	$scope.isAuthorized = AuthService.isAuthorized;
    	
    	
    	
    	//FUNCTIONS
		$scope.getTotal = function(){
			$scope.total = 0;
    		for(var i = 0; i < $scope.cart.length; i++){
        		$scope.total += ($scope.cart[i].price)*1;
    		}
		}
	

    	$scope.setCurrentUser = function (user) {
    		$scope.currentUser = user;
    	};
    	
    	
    	
    	
    	//Fonctions de service
    	clientService.getTypesArticle().then(function(response){
			$scope.types = angular.fromJson(response);
		}, function(){
			alert("Something went wrong with getTypesArticle()!");
		});
	
		if($scope.typeArticle != ''){
			clientService.getArticles($scope.typeArticle).then(function(response){
				$scope.articles = angular.fromJson(response);
			}, function(){
				alert("Something went wrong with getArticles()!");
			});
		}
    	
    	
    	$scope.addToCart = function(index) {
    		clientService.addToCart($scope.currentUser, $scope.articles[index]).then(function(response){
    			$scope.cart.push($scope.articles[index]);
    			$scope.getTotal();
    	  	}, function(){
    	  		alert("Something went wrong with addToCart!");
    		});
    	}
    	
    	$scope.removeToCart = function(index) {
    		clientService.removeToCart($scope.currentUser, $scope.cart[index]).then(function(response){
    			$scope.cart.pop($scope.cart[index]);
    			$scope.getTotal();
    	  	}, function(){
    	  		alert("Something went wrong with removeToCart!");
    		});
    	}
    	
    	$scope.clearCart = function() {
    		clientService.clearCart($scope.currentUser).then(function(response){
    			$scope.cart = [];
    			$scope.getTotal();
    	  	}, function(){
    	  		alert("Something went wrong with clearCart!");
    		});
    	}
    	
    	$scope.buy = function() {
    		clientService.buy($scope.currentUser).then(function(response){
    			alert('on achete');
    	  	}, function(){
    	  		alert("Something went wrong with buy!");
    		});
    	}
    	
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
    	}
    	
    	


	}]) //END CONTROLLER
	
	
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
	        },
	       
	    }
	}])

	
	
	
	
	

	.controller('LoginController', ['$scope', '$rootScope','AUTH_EVENTS','AuthService', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
		$scope.credentials = {
				username: '',
				password: ''
		};
		
		$scope.login = function (credentials) {
			AuthService.login(credentials).then(function (user) {
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$scope.setCurrentUser(user);
			}, function () {
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
			});
		};
	}])
	
	.constant('AUTH_EVENTS', {
		loginSuccess: 'auth-login-success',
		loginFailed: 'auth-login-failed',
		logoutSuccess: 'auth-logout-success',
		sessionTimeout: 'auth-session-timeout',
		notAuthenticated: 'auth-not-authenticated',
		notAuthorized: 'auth-not-authorized'
	})
	
	.constant('USER_ROLES', {
		all: '*',
		admin: 'admin',
		editor: 'editor',
		guest: 'guest'
	})
	
	.factory('AuthService', function ($http, Session) {
		var authService = {};
 
		authService.login = function (credentials) {
			return $http.post('/login', credentials)
						.then(function (res) {
							Session.create(res.data.id, res.data.user.id, res.data.user.role);
							return res.data.user;
						});
			};
 
			authService.isAuthenticated = function () {
				return !!Session.userId;
			};
 
			authService.isAuthorized = function (authorizedRoles) {
				if (!angular.isArray(authorizedRoles)) {
					authorizedRoles = [authorizedRoles];
				}
				return (authService.isAuthenticated() && authorizedRoles.indexOf(Session.userRole) !== -1);
			};

			return authService;
	})


	.service('Session', function () {
		this.create = function (sessionId, userId, userRole) {
			this.id = sessionId;
			this.userId = userId;
			this.userRole = userRole;
		};
		
		this.destroy = function () {
			this.id = null;
			this.userId = null;
			this.userRole = null;
		};
	})
	/*
	.config(function ($stateProvider, USER_ROLES) {
		$stateProvider.state('dashboard', {
			url: '/dashboard',
			templateUrl: 'dashboard/index.html',
			data: {
				authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
			}
		});
	})
	
	
	.run(function ($rootScope, AUTH_EVENTS, AuthService) {
		$rootScope.$on('$stateChangeStart', function (event, next) {
			var authorizedRoles = next.data.authorizedRoles;
			if (!AuthService.isAuthorized(authorizedRoles)) {
				event.preventDefault();
				if (AuthService.isAuthenticated()) {
					// user is not allowed
					$rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
				} else {
					// user is not logged in
					$rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
				}
			}
		});
	})
	
	.config(function ($httpProvider) {
		$httpProvider.interceptors.push([
	    '$injector',
	    function ($injector) {
	    	return $injector.get('AuthInterceptor');
	    	}
	    ]);
	})
	
	.factory('AuthInterceptor', function ($rootScope, $q,  AUTH_EVENTS) {
	  return {
	    responseError: function (response) { 
	      $rootScope.$broadcast({
	        401: AUTH_EVENTS.notAuthenticated,
	        403: AUTH_EVENTS.notAuthorized,
	        419: AUTH_EVENTS.sessionTimeout,
	        440: AUTH_EVENTS.sessionTimeout
	      }[response.status], response);
	      return $q.reject(response);
	    }
	  };
	})
		*/
