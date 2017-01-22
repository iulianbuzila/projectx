angular.module($APP.name).controller('AppCtrl', AppCtrl)

AppCtrl.$inject = ['$rootScope', '$state'];

function AppCtrl($rootScope, $state) {
    var vm = this;
    vm.Roles = Roles;

    $('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrain_width: true, // Does not change width of dropdown to that of the activator
            gutter: -170, // Spacing from edge
            belowOrigin: false, // Displays dropdown below the button
            alignment: 'right' // Displays dropdown with edge aligned to the left of button
        }
    );

    function Roles(){
        $state.go('app.roles');
    }

}
