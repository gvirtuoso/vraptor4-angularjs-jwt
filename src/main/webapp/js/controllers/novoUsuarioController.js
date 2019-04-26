angular.module("listaCompras").controller("novoUsuarioController", function ($scope, usuarioAPI) {
	
	$scope.subtitulo = "Cadastro de Novo Usuário";
	
	$scope.cadastrarUsuario = function (usuario) {
		
		usuarioAPI.saveUsuario(usuarioAPI.newSegUsuarioJson(usuario)).success(function(data) {
			delete $scope.usuario;
			$scope.cadastroForm.$setPristine();
			$scope.goToFormLogin = true;
			
			$scope.mensagemErro = "";
			$scope.mensagemSucesso = "Usuário cadastrado com sucesso!";
			
		}).error(function(data) {
			$scope.mensagemErro = data.errors[0].message;
		});
		
	};
	
});