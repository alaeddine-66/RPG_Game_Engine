# Projet de Moteur de Jeu en Java avec libGDX

## Description

Ce projet est un moteur de jeu extensible écrit en Java, utilisant la bibliothèque libGDX. Il est structuré pour permettre aux utilisateurs de créer facilement des jeux multiplateformes. Le projet inclut une application de démarrage simple.

## Platforms
Le projet prend en charge plusieurs plateformes :

- `core` : Module principal contenant la logique de l'application partagée par toutes les plateformes.
- `lwjgl3` : Plateforme principale pour les ordinateurs de bureau, utilisant LWJGL3.

## Prérequis
Avant de compiler et d'exécuter le projet, assurez-vous d'avoir :
  Java Development Kit (JDK) version 17 ou supérieure.
  Gradle (ou utilisez le wrapper inclus gradlew).

## Structure du Projet
```
├── core                # Code source du moteur de jeu
├── lwjgl3              # Code source pour la plateforme de bureau
├── concreate_game      # Exemple de jeu utilisant le moteur
├── build.gradle        # Fichier de configuration Gradle
├── settings.gradle     # Fichier de configuration Gradle
├── gradlew             # Script Gradle Wrapper (Linux/Mac)
├── gradlew.bat         # Script Gradle Wrapper (Windows)
└── README.md           # Ce fichier
```

## Instructions d'Exécution

### Étape 1 : Cloner le Dépôt

Option 1 : Cloner le Dépôt

Vous pouvez cloner le projet en utilisant git :
```
git clone https://github.com/alaeddine-66/RPG_Game_Engine.git
cd RPG_Game_Engine
```
Option 2 : Télécharger Directement

Si vous n'avez pas git, vous pouvez télécharger le projet directement en tant que fichier ZIP depuis GitHub. Une fois téléchargé, extrayez le contenu et naviguez dans le dossier RPG_Game_Engine

### Étape 2 : Configurer le Répertoire de Travail

Après avoir téléchargé le projet, configurez votre répertoire de travail pour qu'il pointe vers le dossier concreate_game.

+ Pour IntelliJ IDEA :

1. Ouvrez le projet dans IntelliJ.

2. Accédez aux Configurations d'Exécution (Run Configurations).

3. Modifiez la Working Directory pour qu'elle pointe vers RPG_Game_Engine/concreate_game.

+ Pour Eclipse :

1. Cliquez avec le bouton droit sur la configuration de lancement dans Eclipse.

2. Accédez à l'onglet Arguments.

3. Changez le champ Working Directory pour RPG_Game_Engine/concreate_game.

+ Pour Ligne de Commande :

Avant de lancer le jeu, positionnez-vous dans le dossier concreate_game :
```
cd concreate_game
```


### Étape 3 : Exécuter un Jeu Exemple

Un jeu exemple est inclus dans le dossier concreate_game. Vous pouvez le lancer directement pour voir le moteur en action.

Sur Linux/Mac :
```
./run.sh
```
Sur Windows :
```
run.bat
```

Pour créer votre propre jeu, ajoutez vos fichiers au dossier concreate_game en suivant l'exemple fourni.

## Gradle

Le projet utilise Gradle pour gérer les dépendances. Le wrapper Gradle est inclus, ce qui permet d'exécuter les tâches Gradle avec les commandes gradlew.bat ou ./gradlew.

Tâches Utiles

- `--continue` : Les erreurs n'interrompent pas l'exécution des tâches.
- `--daemon` : Utilise le démon Gradle pour accélérer les exécutions.
- `--offline` : Utilise les archives de dépendances mises en cache.
- `--refresh-dependencies` : Valide toutes les dépendances, utile pour les versions snapshot.
- `build` : Compile les sources et génère les archives pour chaque projet.
- `clean` : Supprime les dossiers de build contenant les classes compilées et les archives générées.
- `lwjgl3:jar` : Génère un jar exécutable dans lwjgl3/build/libs.
- `lwjgl3:run` : Démarre l'application sur la plateforme de bureau.
- `test`: runs unit tests (if any).

## Licence

Ce projet est sous licence MIT. Veuillez consulter le fichier LICENSE pour plus de détails.


