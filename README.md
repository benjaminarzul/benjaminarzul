README for togafQuizz
=====================

==============
INSTALLATION :
==============

 - Pré-requis :
   * Un serveur MySQL
   * Maven 3.1.1
   * JHipster :suivre les instructions sur https://jhipster.github.io/installation.html avec notamment l'installation des produits suivants :
     - JAVA Oracle JDK 7
     - Maven
     - Git
     - Node.js
     - Yeoman : npm install -g yo
     - Bower : npm install -g bower
     - Grunt : npm install -g grunt-cli
     - JHipster : npm install -g generator-jhipster
     
 - Base de données :
  * lancer le script de création du schéma 'togafQuizz', le script est sous src/main/resources/config/mysql/0_togafQuizz_create_schema_user.sql
  * le script initialise un schéma togafQuizz avec un utilisateur togafQuizz (mot de passe t0g@gQu1zz)

============================  
LANCEMENT DE L'APPLICATION :
============================

En phase de développement, l'application se lance de deux manières :
 - en ligne de commande (sous la racine du projet ) : mvn spring-boot:run
 - sous un IDE (Spring Tool Suite) en lançant la classe src/main/java/fr/softeam/togafquizz/Application.java ("Run As -> Java Application" ou "Run As -> Spring Boot App" si l'IDE est Spring Tool Suite).
   (possibilité de lancer l'application en mode DEBUG avec "Debug As -> Java Application" ou "Debug As -> Spring Boot App" )
 
L'application est accessible via l'URL : http://localhost:8080

Si les travaux effectués sont essentiellement sur le Front End (AngularJS, javascript, html), vous pouvez lancer l'outil Grunt qui va détecter automatiquement les modifications sur les fichiers JS, HTML, JSON et relancer à chaud l'application.
Pour lancer Grunt, exécuter en ligne de commande (sour la racine du projet) : grunt serve

L'application sera dans ce cas accessible via l'URL suivante : http://localhost:9000; toute modification sera immédiatement visible dans votre navigateur grâce à Grunt.

=================
JEUX DE DONNEES :
=================

Au premier lancement de l'application, LiquiBase initialise la base de données en créant les tables et met à jour un jeux de données de test (voir les fichiers csv sous /src/main/resources/config/liquibase).

Voici les utilisateurs de l'application créés pour les tests en développement ainsi que leur rôle :
 - Administrateur (login : admin, mot de passe : admin, rôles : ROLE_ADMIN, ROLE_USER)
 - Marc GARAGNON (login : mgaragnon, mot de passe : mgaragnon, rôle : ROLE_FORMATEUR)
 - Jean DUPONT (login : jdupont, mot de passe : jdupont, rôle : ROLE_STAGIAIRE)
 - Marc DURAND (login : mdurand, mot de passe : mdurand, rôle : ROLE_STAGIAIRE)
 - Joël LEBLOND (login : jleblond, mot de passe : jleblond, rôle : ROLE_STAGIAIRE)

============== 
LIENS UTILES :
==============

JHipster : https://jhipster.github.io/
LiquiBase : http://www.liquibase.org/index.html
AngularJS : https://angularjs.org/
Twitter Bootstrap : http://getbootstrap.com/2.3.2/index.html
ui-router : https://github.com/angular-ui/ui-router/wiki