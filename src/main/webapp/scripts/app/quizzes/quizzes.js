'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('quizzes', {
                abstract: true,
                parent: 'site',
                url: '/quizzes'
            });
    });
