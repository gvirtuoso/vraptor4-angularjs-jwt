angular.module("listaCompras").factory("usuarioAPI", function ($http, config, localStorageService) {
	
	var SegUsuario = function(nomeCompleto, email, senha) {
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
	};
	
	var _newSegUsuarioJson = function (json) {
		return angular.toJson({objeto : new SegUsuario(json.nomeCompleto, json.email, json.senha)});
	}
	
	var _saveUsuario = function (segUsuarioJson) {
		return $http.post(config.baseUrl + "/seguranca/usuario/salvar", segUsuarioJson);
	};
	
	var _usuarioLogado = function () {
		return {id: localStorageService.get(config.storageUsuarioId), 
			nomeCompleto: localStorageService.get(config.storageUsuarioNomeCompleto), 
			email: localStorageService.get(config.storageUsuarioEmail),
			token: localStorageService.get(config.storageTokenKey)};
	};
	
	var _doLogout = function () {
		localStorageService.remove(config.storageTokenKey);
		localStorageService.remove(config.storageUsuarioId);
		localStorageService.remove(config.storageUsuarioNomeCompleto);
		localStorageService.remove(config.storageUsuarioEmail);
	};

	return {
		newSegUsuarioJson: _newSegUsuarioJson,
		saveUsuario: _saveUsuario,
		usuarioLogado : _usuarioLogado,
		doLogout : _doLogout
	};
	
});