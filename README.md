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

## Instructions d'Exécution

### Étape 1 : Cloner le Dépôt

Clonez ce projet dans votre répertoire local :
```
git clone https://github.com/alaeddine-66/RPG_Game_Engine.git
cd RPG_Game_Engine
```

### Étape 2 : Exécuter un Jeu Exemple

Un jeu exemple est inclus dans le dossier concreate_game. Vous pouvez le lancer directement pour voir le moteur en action.

Sur Linux/Mac :
```
./run.sh
```
Sur Windows :
```
run.bat
```
Ou utilisez directement Gradle :

Sur Linux/Mac :
```
./gradlew lwjgl3:run
```
Sur Windows :
```
gradlew.bat lwjgl3:run
```

Pour créer votre propre jeu, ajoutez vos fichiers au dossier concreate_game en suivant l'exemple fourni.

## Licence

Ce projet est sous licence MIT. Veuillez consulter le fichier LICENSE pour plus de détails.


