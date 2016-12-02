# Boutique
projet de service alma m2

#Rapport
Le rapport est dans le dossier doc/ de boutique/

#Eclipse
Pour lancer l'apllication, éxecuter les commandes suivantes:
mvn install (sur la Boutique)

puis sur la couche application, faire mvn build en précisant dans le goal "tomcat7:run" pour lancer le serveur.

#Ligne de commande
mvn install
cd application
mvn tomcat7:run

#Accès à l'ihm
Ensuite, il suffit d'ouvrir le fichier index.html présent dans la couche présentation et utiliser l'application.

#Doc
La documentation de la boutique est généré dans les dossiers doc/ de chaque couche.

#Génération de la javadoc
Pour générer la javadoc, éxecuter la commande suivante : mvn javadoc:javadoc

#note
pour avoir accès au compte admin, saisir les informations suivantes lors de la connexion:

email : admin@admin.com
mot de passe : admin
