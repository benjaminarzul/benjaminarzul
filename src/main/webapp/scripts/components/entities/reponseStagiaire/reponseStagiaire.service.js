'use strict';

angular.module('togafquizzApp')
    .factory('ReponseStagiaire', function ($resource) {
        return $resource('api/reponseStagiaires/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
