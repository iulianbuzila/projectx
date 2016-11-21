angular.module($APP.name).config(appconfig)

function appconfig($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('app', {
            url: "/",
            templateUrl: "templates/main.html",
            // controller: "MainCtrl"
        })
        .state('login', {
            url: "/login",
            templateUrl: "templates/login.html",
            controller: "LoginCtrl as vm"
        })
        .state('app.home', {
            url: "home",
            templateUrl: "templates/home.html",
            controller: "HomeCtrl"
        })
    $urlRouterProvider.otherwise('/login'); //hardcoded for start
}
