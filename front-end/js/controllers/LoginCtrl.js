angular.module($APP.name).controller('LoginCtrl', LoginCtrl)

LoginCtrl.$inject = ['$rootScope', '$state', 'AuthService', '$timeout'];

function LoginCtrl($rootScope, $state, AuthService, $timeout) {
    var vm = this;
    vm.user = {
        username: '',
        password: '',
        remember: false
    }

    vm.login = login;
    vm.forgotPassword = forgotPassword;

    if(localStorage.getObject('px.user.remember')){
      vm.user = localStorage.getObject('px.user.remember');
      $('.input-field label').addClass('active');
    }

    function login() {
        AuthService.login(vm.user)
            .success(function(result) {
                localStorage.setObject('px.user.current', result);
                localStorage.setObject('px.user.hack', vm.user);
                if(vm.user.remember){
                  localStorage.setObject('px.user.remember', vm.user);
                }
                else{
                  localStorage.removeItem('px.user.remember');
                }
                $state.go('app.home');
            })
            .error(function(status) {
                $timeout(function() {
                    $('#user').addClass('invalid')
                    $('#password').addClass('invalid')
                });
            })
    }

    function forgotPassword() {
        $state.go('app.forgotPassword');
    }
}
