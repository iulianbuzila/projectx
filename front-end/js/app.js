var $APP = $APP || {};
// $APP.server = '';
$APP.name = 'projectx'

angular.module($APP.name, [
    'ui.router'
]);

angular.module($APP.name).run(apprun);

function apprun() {
}
