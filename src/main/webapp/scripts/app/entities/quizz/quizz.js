'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('quizz', {
                parent: 'entity',
                url: '/quizz',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/quizz/quizzs.html',
                        controller: 'QuizzController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('quizz');
                        return $translate.refresh();
                    }]
                }
            })
            .state('quizzDetail', {
                parent: 'entity',
                url: '/quizz/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/quizz/quizz-detail.html',
                        controller: 'QuizzDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('quizz');
                        return $translate.refresh();
                    }]
                }
            });
    });
