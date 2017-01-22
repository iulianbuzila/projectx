angular.module($APP.name).config(appconfig)

function appconfig($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('app', {
            url: "/",
            templateUrl: "templates/main.html",
             controller: "AppCtrl as vm"
        })
        .state('login', {
            url: "/login",
            templateUrl: "templates/login.html",
            controller: "LoginCtrl as vm"
        })
        .state('app.home', {
            url: "home",
            templateUrl: "templates/home.html",
            controller: "HomeCtrl as vm"
        })
        .state('forgotPassword', {
            url: "/forgotPassword",
            templateUrl: "templates/forgotPassword.html",
            controller: "ForgotPasswordCtrl as vm"
        })
        .state('app.roles', {
            url: "roles",
            templateUrl: "templates/roles.html",
            controller: "RolesCtrl as vm"
        })
        // .directive( {
        //     templateUrl: 'templates/document.html',
        //     controller: 'HomeCtrl as vm'
        // })
    $urlRouterProvider.otherwise('/login'); //hardcoded for start
}
