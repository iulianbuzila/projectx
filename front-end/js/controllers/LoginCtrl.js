angular.module($APP.name).controller('LoginCtrl', LoginCtrl)

LoginCtrl.$inject = ['$rootScope','$state'];

function LoginCtrl($rootScope, $state) {
    var vm = this;

    vm.login = login;

    function login() {
        $state.go('app.home');
    }
}
