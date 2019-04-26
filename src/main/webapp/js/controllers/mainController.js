angular.module("listaCompras").controller("mainController", function($route, $scope, $location, config, usuarioAPI, listaAPI, listaItemAPI, itemAPI, unidadeMedidaAPI) {

	//Verificando se o usuario possui permissao de acesso
	$scope.usuario = usuarioAPI.usuarioLogado();
	if($scope.usuario.token === undefined || $scope.usuario.token == null) {
		$location.path("/login");
	}
	
	$scope.subtitulo = "Lista de Compras";
	$scope.listaSelecionada = 0;
	$scope.listas = [];
	$scope.listaSelecionadaItens = [];
	$scope.valorTotalLista = 0.00;
	$scope.modoEdicaoDescricaoLista = false;
	$scope.itemListaParaEdicao = [];
	$scope.unidadesMedidas = [];
	
	$scope.logout = function() {
		usuarioAPI.doLogout();
		$location.path("/login");
	};
	
	$scope.selecionarLista = function(id) {
		$scope.modoEdicaoDescricaoLista = false;
		$scope.listaSelecionada = id;
		carregarInformacoesLista(id);
		carregaItensDaLista(id);
	};
	
	$scope.$watchCollection('listaSelecionadaItens',function() {
		// Função disparada sempre que o objeto $scope.listaSelecionadaItens sofrer alterações
		$scope.valorTotalLista = 0;
		angular.forEach($scope.listaSelecionadaItens, function(item, key) {
			$scope.valorTotalLista += item.cadItem.valor * item.quantidade;
		});
	});
	
	$scope.criarLista = function(novaLista) {
		
		listaAPI.criarLista(listaAPI.newComListaJson(novaLista)).success(function(data) {
			delete $scope.novaLista;
			$scope.formNovaLista.$setPristine();
			carregarListas();
			$scope.selecionarLista(data.id);
			$scope.mensagemErro = "";
		}).error(function(data) {
			$scope.mensagemErro = data.errors[0].message;
		});
		
	};
	
	$scope.habilitarEdicaoDescricaoLista = function() {
		$scope.modoEdicaoDescricaoLista = true;
	};
	
	$scope.cancelarEdicaoLista = function(id) {
		carregarListas();
		$scope.selecionarLista(id);
	};
	
	$scope.alterarLista = function(lista) {
		listaAPI.alterarLista(listaAPI.newComListaJson(lista)).success(function(data) {
			carregarListas();
			$scope.selecionarLista(data.id);
			$scope.mensagemErro = "";
		}).error(function(data) {
			$scope.mensagemErro = data.errors[0].message;
		});
	};
	
	$scope.excluirLista = function(lista) {
		if (confirm("Confirma a exclusão da lista e de todos os seus itens?")) {
			
			listaItemAPI.excluirItensPorLista(lista.id).success(function(data){
				listaAPI.excluirLista(listaAPI.newComListaJson(lista)).success(function(data){
					$scope.listaSelecionada = 0;
					carregarListas();
					$scope.mensagemErro = "";
				}).error(function(data){
					$scope.mensagemErro = data.errors[0].message;
				});
			}).error(function(data){
				$scope.mensagemErro = data.errors[0].message;
			});
		}
	};
	
	$scope.adicionarItemNaLista = function(newComListaItem) {
		itemAPI.criarItem(itemAPI.newCadItemJson(newComListaItem.cadItem)).success(function(data){
			newComListaItem.cadItem = {id:data.id};
			newComListaItem.comLista = {id:$scope.listaSelecionada};
			listaItemAPI.criarItemLista(listaItemAPI.newComListaItem(newComListaItem)).success(function(data){
				delete $scope.newComListaItem;
				carregaItensDaLista($scope.listaSelecionada);
				$scope.mensagemErro = "";
			}).error(function(data){
				$scope.mensagemErro = data.errors[0].message;
			});
			
		}).error(function(data){
			$scope.mensagemErro = data.errors[0].message;
		});
	};
	
	$scope.alterarIsComprado = function(itemLista) {
		listaItemAPI.alterarItemLista(itemLista).success(function(data){
			$scope.mensagemErro = "";
			carregaItensDaLista($scope.listaSelecionada);
		}).error(function(data){
			$scope.mensagemErro = data.errors[0].message;
		});
	};
	
	$scope.alterarItemLista = function(itemLista) {
		
		if(itemLista.cadItem.valor === undefined || itemLista.cadItem.valor == null || isNaN(itemLista.cadItem.valor)) {
			itemLista.cadItem.valor = 0;
		}
		
		itemAPI.alterarItem(itemLista.cadItem).success(function(data){
			listaItemAPI.alterarItemLista(itemLista).success(function(data){
				$scope.mensagemErro = "";
				$scope.itemListaParaEdicao = [];
				carregaItensDaLista($scope.listaSelecionada);
			}).error(function(data){
				$scope.mensagemErro = data.errors[0].message;
			});
		}).error(function(data){
			$scope.mensagemErro = data.errors[0].message;
		});
	};
	
	$scope.excluirItemLista = function(itemLista) {
		
		if (confirm("Confirma a exclusão do item \"" + itemLista.cadItem.descricao + "\" ? \nEsta operação não pode ser desfeita")) {
			listaItemAPI.excluirItemLista(itemLista).success(function(data){
				$scope.mensagemErro = "";
				carregaItensDaLista($scope.listaSelecionada);
			}).error(function(data){
				$scope.mensagemErro = data.errors[0].message;
			});
		}
	};
	
	$scope.habilitarEdicaoItemLista = function(itemLista) {
		$scope.itemListaParaEdicao = itemLista;
	};
	
	$scope.cancelarEdicaoItemLista = function() {
		$scope.itemListaParaEdicao = [];
		carregaItensDaLista($scope.listaSelecionada);
	};
			
	var carregaItensDaLista = function(id) {
		listaItemAPI.carregaItensDaLista(id).success(function(data) {
			$scope.listaSelecionadaItens = data;
		});
	};
	
	var carregarInformacoesLista = function(id) {
		listaAPI.carregaInformacoesLista(id).success(function(data) {
			$scope.listaSelecionadaDetail = data;
		});
	};
	
	var carregarListas = function() {
		listaAPI.getListas().success(function(data) {
			$scope.listas = data;
		});
	};
	
	var carregarUnidadesMedida = function() {
		unidadeMedidaAPI.getUnidadesMedida().success(function(data) {
			$scope.unidadesMedidas = data;
		});
	};
	
	carregarListas();
	carregarUnidadesMedida();

});