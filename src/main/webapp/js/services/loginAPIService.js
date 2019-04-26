angular.module("listaCompras").factory("loginAPI", function ($http, config) {
	
	var _login = function (segUsuarioJson) {
		return $http.post(config.baseUrl + "/seguranca/usuario/login", segUsuarioJson);
	};
	
	return {
		login: _login
	};
	
});