angular.module($APP.name).controller('HomeCtrl', HomeCtrl)

HomeCtrl.$inject = ['$rootScope', '$timeout', 'UserService', 'AuthService'];

function HomeCtrl($rootScope, $timeout, UserService, AuthService) {
    var vm = this;
    vm.select = select;
    vm.update = update;
    vm.remove = remove;
    vm.toggleCreate = toggleCreate;
    vm.reload = reload;


    vm.user = {};
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
        vm.edit = true;
        vm.user = angular.copy(row)
        $timeout(function() {
            $('select').material_select();
        });
        $('#username').addClass('active');
    }

    function toggleCreate() {
        if (vm.user && vm.user.username) {
            console.log(vm.user.username);
            $('#' + vm.user.username).removeClass('active')
        }
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
        UserService.update(vm.user.id)
        // console.log(vm.user);
    }

    function reload() {
        UserService.list().success(function(result) {
            vm.list = result;
        })
    }
}
