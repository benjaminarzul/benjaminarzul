README for togafQuizz
=====================

==============
INSTALLATION :
==============

 - Pr�-requis :
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
     
 - Base de donn�es :
  * lancer le script de cr�ation du sch�ma 'togafQuizz', le script est sous src/main/resources/config/mysql/0_togafQuizz_create_schema_user.sql
  * le script initialise un sch�ma togafQuizz avec un utilisateur togafQuizz (mot de passe t0g@gQu1zz)

============================  
LANCEMENT DE L'APPLICATION :
============================

En phase de d�veloppement, l'application se lance de deux mani�res :
 - en ligne de commande (sous la racine du projet ) : mvn spring-boot:run
 - sous un IDE (Spring Tool Suite) en lan�ant la classe src/main/java/fr/softeam/togafquizz/Application.java ("Run As -> Java Application" ou "Run As -> Spring Boot App" si l'IDE est Spring Tool Suite).
   (possibilit� de lancer l'application en mode DEBUG avec "Debug As -> Java Application" ou "Debug As -> Spring Boot App" )
 
L'application est accessible via l'URL : http://localhost:8080

Si les travaux effectu�s sont essentiellement sur le Front End (AngularJS, javascript, html), vous pouvez lancer l'outil Grunt qui va d�tecter automatiquement les modifications sur les fichiers JS, HTML, JSON et relancer � chaud l'application.
Pour lancer Grunt, ex�cuter en ligne de commande (sour la racine du projet) : grunt serve

L'application sera dans ce cas accessible via l'URL suivante : http://localhost:9000; toute modification sera imm�diatement visible dans votre navigateur gr�ce � Grunt.

=================
JEUX DE DONNEES :
=================

Au premier lancement de l'application, LiquiBase initialise la base de donn�es en cr�ant les tables et met � jour un jeux de donn�es de test (voir les fichiers csv sous /src/main/resources/config/liquibase).

Voici les utilisateurs de l'application cr��s pour les tests en d�veloppement ainsi que leur r�le :
 - Administrateur (login : admin, mot de passe : admin, r�les : ROLE_ADMIN, ROLE_USER)
 - Marc GARAGNON (login : mgaragnon, mot de passe : mgaragnon, r�le : ROLE_FORMATEUR)
 - Jean DUPONT (login : jdupont, mot de passe : jdupont, r�le : ROLE_STAGIAIRE)
 - Marc DURAND (login : mdurand, mot de passe : mdurand, r�le : ROLE_STAGIAIRE)
 - Jo�l LEBLOND (login : jleblond, mot de passe : jleblond, r�le : ROLE_STAGIAIRE)

============== 
LIENS UTILES :
==============

JHipster : https://jhipster.github.io/
LiquiBase : http://www.liquibase.org/index.html
AngularJS : https://angularjs.org/
Twitter Bootstrap : http://getbootstrap.com/2.3.2/index.html
ui-router : https://github.com/angular-ui/ui-router/wiki