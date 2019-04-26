angular.module("listaCompras").factory("listaItemAPI", function ($http, config, usuarioAPI) {
	
	var ComListaItem = function(id, quantidade, comLista, cadItem, UltimoUsuario) {
		this.id = id;
		this.quantidade = quantidade;
		this.comLista = comLista;
		this.cadItem = cadItem;
		this.ultimoUsuario = UltimoUsuario;
	};
	
	var _newComListaItem = function(json) {
		return angular.toJson({objeto : new ComListaItem(json.id, json.quantidade, json.comLista, json.cadItem , usuarioAPI.usuarioLogado())});
	};
	
	var _carregaItensDaLista = function(id) {
		return $http.get(config.baseUrl + "/compra/listaitem/listarTodos/" + id);
	};
	
	var _excluirItensPorLista = function (id) {
		return $http.post(config.baseUrl + "/compra/listaitem/excluirItensPorLista/" + id);
	};
	
	var _criarItemLista = function(listaItemJson) {
		return $http.post(config.baseUrl + "/compra/listaitem/salvar", listaItemJson);
	};
	
	var _alterarItemLista = function(listaItemJson) {
		return $http.put(config.baseUrl + "/compra/listaitem/alterar", listaItemJson);
	};
	
	var _excluirItemLista = function(listaItemJson) {
		return $http.post(config.baseUrl + "/compra/listaitem/excluir", listaItemJson);
	};
	
	return {
		newComListaItem: _newComListaItem,
		carregaItensDaLista: _carregaItensDaLista,
		excluirItensPorLista: _excluirItensPorLista,
		criarItemLista: _criarItemLista,
		alterarItemLista: _alterarItemLista,
		excluirItemLista: _excluirItemLista
	};
	
});