'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('myanswers', {
                parent: 'quizzes',
                url: '/myanswers',
                data: {
                    roles: ['ROLE_STAGIAIRE']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/quizzes/myanswers/myanswers.html',
                        controller: 'MyAnswersController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('myanswers');
                        return $translate.refresh();
                    }]
                }
            });
    });
