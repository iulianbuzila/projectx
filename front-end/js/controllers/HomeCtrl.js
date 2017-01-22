var app = angular.module($APP.name);

app.controller('HomeCtrl', HomeCtrl)
    .directive("page1", function(){
        var uniqueId = 1;
        return {
            restricted: 'e',
            transclude: true,
            scope: {
                document: '='
            },
            templateUrl: 'templates/document.html',
            controller: 'HomeCtrl',
            link: function(scope, element, attrs){
                scope.uniqueId = uniqueId++;

                scope.remove = function() {
                    element.html('');
                };

                console.log(scope.uniqueId);
            }
        }
    })
    .directive("fileread", [function () {
        return {
            scope: {
                fileread: "="
            },
            link: function (scope, element, attributes) {
                element.bind("change", function (changeEvent) {
                    var reader = new FileReader();
                    reader.onload = function (loadEvent) {
                        scope.$apply(function () {
                            scope.fileread = loadEvent.target.result;
                        });
                    };
                    reader.readAsDataURL(changeEvent.target.files[0]);
                });
            }
        }
    }]);

HomeCtrl.$inject = ['$rootScope', '$timeout', 'UserService','DocumentService', 'AuthService', '$compile', '$scope'];

function HomeCtrl($rootScope, $timeout, UserService, DocumentService, AuthService, $compile, $scope) {
    var vm = this;
    vm.select = select;
    vm.update = update;
    vm.remove = remove;
    vm.toggleCreate = toggleCreate;
    vm.reload = reload;
    vm.deleteDocument = deleteDocument;
    vm.addDocument = addDocument;
    vm.getDocuments = getDocuments;

    getDocuments();

    $scope.documents = [
        {name:"Fisier 1",date:"11-02-02",status:"1.0"},
        {name:"Fisier 2",date:"11-11-11",status:"1.0"},
        {name:"Fisier 3",date:"11-12-12",status:"2.0"},
        {name:"Fisier 4",date:"11-02-02",status:"2.0"},
        {name:"Fisier 5",date:"11-02-02",status:"1.0"},
        {name:"AFF",date:"11-02-02",status:"1.0"},
        {name:"ADD",date:"11-02-02",status:"2.0"},
        {name:"AEE",date:"11-02-02",status:"1.0"},
        {name:"AFF",date:"11-02-02",status:"1.0"},
        {name:"ADD",date:"11-02-02",status:"2.0"},
        {name:"AEE",date:"11-02-02",status:"1.0"},
        {name:"AFF",date:"11-02-02",status:"1.0"},
        {name:"BBB",date:"TypeBBB",status:"2.0"}];

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

    function deleteDocument(documentid) {
        console.log(documentid);
        DocumentService.remove(vm.document.id)
    }

    function addDocument(){
        var fd = new FormData();
        fd.append('data', vm.document.file);
        fd.append('name', vm.document.tag);
        fd.append('keyword', vm.document.tag);
        fd.append('abstract', vm.document.description);

        // for (var pair of fd.entries()) {
        //     console.log(pair[0]+ ', ' + pair[1]);
        // }
        DocumentService.create(fd, vm.document.tag, vm.document.description, "12312")
            .success(function(result) {
               console.log(result);
            })
            .error(function(status) {
               console.log(status);
            })
    }

    function getDocuments(){
        DocumentService.list().success(function(result) {
            vm.documents = result;
            $scope.documents = result;
            console.log(result);
        });
    }

}