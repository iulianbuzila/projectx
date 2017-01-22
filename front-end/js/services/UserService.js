angular.module($APP.name).factory('UserService', UserService);

UserService.$inject = ['$http', '$timeout'];

function UserService($http, $timeout) {
    var service = {
        get: get,
        list: list,
        create: create,
        update: update,
        remove: remove,
        forgotPassword: forgotPassword,
        userGroups: userGroups
    }
    return service;

    function get(id) {
        return $http({
            method: 'GET',
            url: $APP.server + 'api/users',
            headers: {
                'Authorization': sessionStorage.getItem("token")
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
                'Authorization': sessionStorage.getItem("token")
            },
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function userGroups(userId) {
        return $http({
            method: 'GET',
            url: $APP.server + 'api/users/group/' + userId,
            headers: {
                'Authorization': sessionStorage.getItem("token")
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
                'Authorization': sessionStorage.getItem("token")
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
                'Authorization': sessionStorage.getItem("token")
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
                'Authorization': sessionStorage.getItem("token")
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
