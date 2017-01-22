var app = angular.module($APP.name);

app.controller('HomeCtrl', HomeCtrl)
    .directive("page1", function(){
        return {
            restricted: 'a',
            templateUrl: 'templates/document.html',
            controller: 'HomeCtrl'
        }
    });

HomeCtrl.$inject = ['$rootScope', '$timeout', 'UserService', 'AuthService', '$compile', '$scope'];
// HomeDirective.$inject = ['$scope'];

function HomeCtrl($rootScope, $timeout, UserService, AuthService, $compile, $scope) {
    var vm = this;
    vm.select = select;
    vm.update = update;
    vm.remove = remove;
    vm.toggleCreate = toggleCreate;
    vm.reload = reload;
    vm.deleteDocument = deleteDocument;
    vm.documents = {};

    vm.user = {};
    vm.document = {};
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
        UserService.remove(vm.user.id)
        // console.log(vm.user);
    }

    function reload() {
        UserService.list().success(function(result) {
            vm.list = result;
        })
    }

    function deleteDocument(documentname) {
        console.log(documentname);
        // DocumentService.remove(vm.document.id)
    }

}