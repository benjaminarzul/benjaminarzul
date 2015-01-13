'use strict';

angular.module('togafquizzApp')
    .factory('Quizz', function ($resource) {
        return $resource('api/quizzs/:id', {}, {
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
