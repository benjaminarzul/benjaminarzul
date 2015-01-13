'use strict';

angular.module('togafquizzApp')
    .factory('Session', function ($resource) {
        return $resource('api/sessions/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    var debutFrom = data.debut.split("-");
                    data.debut = new Date(new Date(debutFrom[0], debutFrom[1] - 1, debutFrom[2]));
                    var finFrom = data.fin.split("-");
                    data.fin = new Date(new Date(finFrom[0], finFrom[1] - 1, finFrom[2]));
                    return data;
                }
            }
        });
    });
