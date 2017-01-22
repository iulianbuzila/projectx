/**
 * Created by iulia on 1/22/2017.
 */

angular.module($APP.name).factory('DocumentService', DocumentService);

DocumentService.$inject = ['$http', '$timeout'];

function DocumentService($http, $timeout) {
    var service = {
        get: get,
        list: list,
        create: create,
        update: update,
        remove: remove
    }
    return service;

    function get(id) {
        return $http({
            method: 'GET',
            url: $APP.server + 'api/documents/',
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
            url: $APP.server + 'api/documents/',
            headers: {
                'Authorization': sessionStorage.getItem("token")
            }
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

    function create(data, name, keyword, abstract) {
        return $http({
            method: 'POST',
            url: $APP.server + 'api/documents/',
            headers: {
                'Authorization': sessionStorage.getItem("token"),
                'content-type': 'multipart/formdata;boundary=--gc0p4Jq0M2Yt08jU534c0p'
    },
            data: data,
            name: name,
            keyword: keyword,
            abstract: abstract
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
            url: $APP.server + 'api/documents/{documentId}',
            headers: {
                'Authorization': sessionStorage.getItem("token")
            },
            params: {
                userId: id
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

    function remove(id) {
        return $http({
            method: 'DELETE',
            url: $APP.server + 'api/documents/{documentId}',
            headers: {
                'Authorization': sessionStorage.getItem("token")
            },
            params: {
                documentId: id
            }
        })
            .success(function (result) {
                return result;
            })
            .error(function (data, status) {
                return status;
            })
    }

}
