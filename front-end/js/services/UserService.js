angular.module($APP.name).factory('UserService', UserService);

UserService.$inject = ['$http', '$timeout'];

function UserService($http, $timeout) {
    var service = {
        get: get,
        list: list,
        create: create,
        update: update,
        remove: remove,
        forgotPassword: forgotPassword
    }
    return service;

    function get(id) {
        return $http({
            method: 'GET',
            url: $APP.server + 'api/users',
            headers: {
                'Authorization': "Basic " + window.btoa("admin:admin")
            },
            parms: {
                id: id
            }
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function list() {
        return $http({
            method: 'GET',
            url: $APP.server + 'api/users/',
            headers: {
                'Authorization': "Basic " + window.btoa("admin:admin")
            },
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function create(data) {
        return $http({
            method: 'POST',
            url: $APP.server + 'api/users/',
            headers: {
                'Authorization': "Basic " + window.btoa("admin:admin")
            },
            data: data
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function update(id, data) {
        return $http({
            method: 'PUT',
            url: $APP.server + 'api/users/' + id,
            headers: {
                'Authorization': "Basic " + window.btoa("admin:admin")
            },
            // params: {
            //     userId: id
            // },
            data: data
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function remove(id) {
        return $http({
            method: 'DELETE',
            url: $APP.server + 'api/users/' + id,
            headers: {
                'Authorization': "Basic " + window.btoa("admin:admin")
            },
            // params: {
            //     userId: id
            // }
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }
    
    function forgotPassword(data) {
        return $http({
            method: 'POST',
            url: $APP.server + 'api/users/resetPassword',
            headers: {
            },
            withCredentials: true,
            data: data
        })
            .success(function(result) {
                return result
            })
            .error(function(data, status) {
                return status;
            })
    }


}
