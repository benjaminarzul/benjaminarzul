'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('session', {
                parent: 'entity',
                url: '/session',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/session/sessions.html',
                        controller: 'SessionController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('session');
                        return $translate.refresh();
                    }]
                }
            })
            .state('sessionDetail', {
                parent: 'entity',
                url: '/session/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/session/session-detail.html',
                        controller: 'SessionDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('session');
                        return $translate.refresh();
                    }]
                }
            });
    });
