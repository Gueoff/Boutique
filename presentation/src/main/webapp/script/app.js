	const config ={
	    urlClient : "http://localhost:8080/application/clientcommand",
	    urlSupplier : "",
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


	.controller('ShopController', ['$scope', '$http', 'USER_ROLES', 'AuthService', 'clientService', function($scope, $http, USER_ROLES, AuthService, clientService){
		//VARIABLES
		$scope.cart = [
		    {name: 'coyote', description:'', price:3, available:true, type:'pull'},
		    {name: 'pantalon', description:'', price:6, available:true, type:'jean'}
		];
		
		$scope.articles = [
			{name: 'pull over',   description:'ptite description', price:3, available:true, type:'pull'},
			{name: 'jean magueule',  description:'', price:6, available:true, type:'jean'}
		];
		
        $scope.types = [
            {name:'tshirt'},
            {name:'pantalon'}
        ];
        
        //mock
        $scope.currentUser = {name:'desbrosses', firstname:'geoffrey', age:22, email:'g@d.com',cart: $scope.cart};
    	//$scope.currentUser = null;
    	$scope.userRoles = USER_ROLES;
    	$scope.isAuthorized = AuthService.isAuthorized;
    	
    	//FUNCTIONS
		$scope.getTotal = function(){
    		var total = 0;
    		for(var i = 0; i < $scope.cart.length; i++){
        		var product = $scope.cart[i];
        		total += (product.price);
    		}
    		return total;
		}
	

    	$scope.setCurrentUser = function (user) {
    		$scope.currentUser = user;
    	};
    	
    	
    	$scope.addToCart = function(index) {
    		clientService.addToCart($scope.currentUser, $scope.articles[index])
    			.then(function(response){
    				$scope.response = response;
    				$scope.cart.push($scope.articles[index]);
    	  	}, function(){
    	  			alert("Something went wrong!");
    		});
    	}
    	
    	clientService.getTypesArticle()
			.then(function(response){
				console.log($scope.types);
				$scope.types = angular.fromJson(response);
				console.log($scope.types);
			}, function(){
				alert("Something went wrong!");
			});


	}]) //END CONTROLLER
	
	
	.factory("clientService", ['$soap',function($soap){

	    return {
	        addToCart: function(client, article){
	            var params = new SOAPClientParameters();
	            params.add('client', client);
	            params.add('article', article);
	            return $soap.post(config.urlClient,"addToCart",params);
	        },
	    
	        getTypesArticle: function(){
	            return $soap.post(config.urlClient,"getTypesArticle");
	        },
	        
	        getArticles: function(type){
	            return $soap.post(config.urlClient,"getArticles", type);
	        }
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