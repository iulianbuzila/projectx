var app = angular.module($APP.name);

app.controller('HomeCtrl', HomeCtrl)
    .directive("page1", function(DocumentService){
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
                scope.uniqueId = document.id;

                scope.remove = remove;
                scope.download = download;
                function remove(id) {
                    console.log(id);
                    DocumentService.remove(id)
                        .success(function (result) {
                            element.html('');
                            console.log(result);
                        })
                        .error(function (status) {
                            console.log(status);
                        });
                }
                function download(id) {
                    console.log(id);
                    DocumentService.download(id)
                        .success(function (result) {
                            try {
                                var b = new Blob([result], {type: "application/octet-stream"});
                                console.log(scope.document.name);
                                saveAs(b, scope.document.name);
                            } catch (e) {
                                window.open("data:application/octet-stream" + "," + encodeURIComponent(result), '_blank', '');
                            }
                            console.log(result);
                        })
                        .error(function (status) {
                            console.log(status);
                        });
                }
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

app.config(['$compileProvider',
    function ($compileProvider) {
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|tel|file|blob):/);
    }]);

HomeCtrl.$inject = ['$rootScope', '$timeout', 'UserService','DocumentService', 'AuthService', '$compile', '$scope'];

function HomeCtrl($rootScope, $timeout, UserService, DocumentService, AuthService, $compile, $scope) {
    var vm = this;
    vm.addDocument = addDocument;
    vm.getDocuments = getDocuments;

    getDocuments();

    // $scope.documents = [
    //     {name:"Fisier 1",date:"11-02-02",status:"1.0"},
    //     {name:"Fisier 2",date:"11-11-11",status:"1.0"},
    //     {name:"Fisier 3",date:"11-12-12",status:"2.0"},
    //     {name:"Fisier 4",date:"11-02-02",status:"2.0"},
    //     {name:"Fisier 5",date:"11-02-02",status:"1.0"},
    //     {name:"AFF",date:"11-02-02",status:"1.0"},
    //     {name:"ADD",date:"11-02-02",status:"2.0"},
    //     {name:"AEE",date:"11-02-02",status:"1.0"},
    //     {name:"AFF",date:"11-02-02",status:"1.0"},
    //     {name:"ADD",date:"11-02-02",status:"2.0"},
    //     {name:"AEE",date:"11-02-02",status:"1.0"},
    //     {name:"AFF",date:"11-02-02",status:"1.0"},
    //     {name:"BBB",date:"TypeBBB",status:"2.0"}];

    vm.user = {};
    vm.document = {};
    vm.edit = false;

    $timeout(function() {
        $('select').material_select();
    });

    function addDocument(){
        var fd = new FormData();
        fd.append('data', vm.document.file);
        fd.append('name', vm.document.name);
        fd.append('keyword', vm.document.name);
        fd.append('abstract', vm.document.abstract);

        DocumentService.create(fd, vm.document.name, vm.document.name, vm.document.abstract)
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