'use strict';

angular.module('togafquizzApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


