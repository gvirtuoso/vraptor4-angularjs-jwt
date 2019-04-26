angular.module("listaCompras").factory("unidadeMedidaAPI", function ($http, config) {
	
	var _getUnidadesMedida = function() {
		return $http.get(config.baseUrl + "/cadastro/unidademedida/listarTodos");
	};
	
	return {
		getUnidadesMedida: _getUnidadesMedida
	};
	
});