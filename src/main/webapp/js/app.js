angular.module("listaCompras", ["ngRoute", "ui.validate", "ng-currency", "LocalStorageModule"]);

angular.module("listaCompras").run(function($rootScope, $templateCache) {
	
	// Evitando memoria ache na camada de visualizacao
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined'){
            $templateCache.remove(current.templateUrl);
        }
    });
	
});