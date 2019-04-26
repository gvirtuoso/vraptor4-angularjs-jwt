angular.module("listaCompras").config(function (localStorageServiceProvider) {
	
	localStorageServiceProvider.setPrefix("listaCompra")
								.setStorageType("sessionStorage")
								.setStorageCookie(1, '/')
								.setStorageCookieDomain(window.location)
								.setNotify(true, true);
	
});