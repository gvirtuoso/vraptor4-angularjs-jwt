angular.module("listaCompras").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "view/main.html",
		controller: "mainController"
	});
	
	$routeProvider.when("/login", {
		templateUrl: "view/login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/novoUsuario", {
		templateUrl: "view/novoUsuario.html",
		controller: "novoUsuarioController"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});