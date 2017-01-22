/**
 * Created by iulia on 1/22/2017.
 */

angular.module($APP.name).controller('RolesCtrl', RolesCtrl)

RolesCtrl.$inject = ['$rootScope', '$timeout', 'UserService', 'AuthService'];

function RolesCtrl($rootScope, $timeout, UserService, AuthService) {
    var vm = this;
    vm.select = select;
    vm.update = update;
    vm.remove = remove;
    vm.toggleCreate = toggleCreate;
    vm.reload = reload;

    vm.user = {
        username:''
    };
    vm.edit = false;
    reload();

    $timeout(function() {
        $('select').material_select();
    });

    function select(row) {
        console.log(row);
        if (vm.user.username) {
            $('#' + vm.user.username).removeClass('active')
        }
        $('#' + row.username).addClass('active');

        // if (vm.user.email) {
        //     $('#' + vm.user.email).removeClass('active')
        //     console.log(vm.user.email);
        // }
        // $('#' + row.email).addClass('active');

        vm.edit = true;
        vm.user = angular.copy(row);
        $timeout(function() {
            $('select').material_select();
        });
        $('#username').addClass('active');
        $('#email').addClass('active');
        $('#firstName').addClass('active');
        $('#lastName').addClass('active');

    }

    function toggleCreate() {
        if (vm.user && vm.user.username) {
            console.log(vm.user.username);
            $('#' + vm.user.username).removeClass('active')
        }
        // if (vm.user && vm.user.email) {
        //     console.log(vm.user.email);
        //     $('#' + vm.user.email).removeClass('active')
        // }

        vm.edit = false;
        delete vm.user;
        $timeout(function() {
            $('select').material_select();
        });
    }

    function update() {
        UserService.update(vm.user.id, vm.user)
        // console.log(vm.user);
    }
    function remove() {
        UserService.remove(vm.user.id)
        // console.log(vm.user);
    }

    function userGroups() {
        UserService.userGroups(vm.user.id).success(function(result) {
            // vm.list = result;
            console.log(result);
        })
    }

    function reload() {
        UserService.list().success(function(result) {
            vm.list = result;
            userGroups(vm.user.id);
            console.log(result);
        })
    }
}
