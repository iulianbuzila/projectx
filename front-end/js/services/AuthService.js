angular.module($APP.name).factory('AuthService', AuthService);

AuthService.$inject = ['$http', '$timeout'];

function AuthService($http, $timeout) {
    var service = {
        login: login
    }
    return service;

    function login(data) {
        return $http({
                method: 'POST',
                url: $APP.server + 'api/login/',
                headers: {
                    'Authorization': "Basic " + window.btoa(data.username + ":" + data.password)
                },
                withCredentials: true,
                data: data
            })
            .success(function(result, status, headers, config) {
                console.log(config);
                sessionStorage.setItem("token", result["token"]);

                return result;
            })
            .error(function(data, status) {
                return status;
            })
    }


}
