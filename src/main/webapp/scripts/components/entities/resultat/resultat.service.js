'use strict';

angular.module('togafquizzApp')
    .factory('Resultat', function ($resource) {
        return $resource('api/resultats/:id', {}, {
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
