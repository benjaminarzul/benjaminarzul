'use strict';

angular.module('togafquizzApp')
    .factory('Reponse', function ($resource) {
        return $resource('api/reponses/:id', {}, {
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
