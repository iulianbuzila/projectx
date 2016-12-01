angular.module($APP.name).controller('ForgotPasswordCtrl', ForgotPasswordCtrl)

ForgotPasswordCtrl.$inject = ['$rootScope', '$state', 'UserService', '$timeout'];

function ForgotPasswordCtrl($rootScope, $state, UserService, $timeout) {
    var vm = this;
    vm.user = {
        email: ''
    }

    vm.forgotPassword = forgotPassword;

    function forgotPassword() {
        UserService.forgotPassword(vm.user)
            .success(function(result) {
                localStorage.setObject('px.user.current', result);
                localStorage.setObject('px.user.hack', vm.user);
                $state.go('login');
            })
            .error(function(status) {
                $timeout(function() {
                    $('#email').addClass('invalid')
                });
            })
    }
}
