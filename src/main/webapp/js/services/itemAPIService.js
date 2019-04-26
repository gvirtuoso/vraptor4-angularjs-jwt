angular.module("listaCompras").factory("itemAPI", function ($http, config, usuarioAPI) {
	
	var CadItem = function(id, descricao, valor, cadUnidadeMedida, SegUsuarioProprietario, UltimoUsuario) {
		
		if(valor === undefined || valor == null || isNaN(valor)) {
			valor = 0;
		}
		
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.cadUnidadeMedida = cadUnidadeMedida;
		this.segUsuarioProprietario = SegUsuarioProprietario;
		this.ultimoUsuario = UltimoUsuario;
		
	};
	
	var _newCadItemJson = function(json) {
		return angular.toJson({objeto : new CadItem(json.id, json.descricao, json.valor, json.cadUnidadeMedida, usuarioAPI.usuarioLogado(), usuarioAPI.usuarioLogado())});
	};
	
	var _criarItem = function (itemJson) {
		return $http.post(config.baseUrl + "/cadastro/item/salvar", itemJson);
	};
	
	var _alterarItem = function(itemJson) {
		return $http.put(config.baseUrl + "/cadastro/item/alterar", itemJson);
	};
	
	return {
		newCadItemJson : _newCadItemJson,
		criarItem: _criarItem,
		alterarItem: _alterarItem
	};
	
});