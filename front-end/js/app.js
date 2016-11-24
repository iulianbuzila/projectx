var $APP = $APP || {};
$APP.server = 'http://localhost:8081/';
$APP.name = 'projectx'

Storage.prototype.setObject = function(key, value) {
    this.setItem(key, JSON.stringify(value));
}

Storage.prototype.getObject = function(key) {
    var value = this.getItem(key);
    return value && JSON.parse(value);
}

angular.module($APP.name, [
    'ui.router'
]);

angular.module($APP.name).run(apprun);

function apprun() {

}
