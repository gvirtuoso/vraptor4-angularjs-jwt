angular.module("listaCompras").config(function ($httpProvider) {
	
	$httpProvider.interceptors.push(['$q', '$location', 'localStorageService', function($q, $location, localStorageService) {
		
		return {
			
			request : function (config) {
				
				var _isLogado = false;
				var _token = "";
				var _email = "";
				
				if(localStorageService.isSupported) {
					_token = localStorageService.get("app_token");
					_email = localStorageService.get("app_usuario_email");
					_isLogado = _token != null;
				}
				
				config.headers = config.headers || {};
				
				if(_isLogado) {
					config.headers.Authorization = _email + ' ' + _token;
				}
                
                return config;
                
			}
		};
		
	}]);
	
});