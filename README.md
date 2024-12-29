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

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

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
git clone <url-du-dépôt>
cd TestJava2
```

### Étape 2 : Compiler le Projet

Utilisez Gradle pour compiler le projet.

Avec le wrapper Gradle :

Sur Linux/Mac :
```
./gradlew build
```
Sur Windows :
```
gradlew.bat build
```
Avec une installation locale de Gradle :
```
gradle build
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

##Détails du Script d'Exécution

Script Linux/Mac (run.sh)
```
#!/bin/bash

# Compiler si nécessaire
if [ ! -d "build" ]; then
  echo "Compilation en cours..."
  ./gradlew build
fi

# Exécuter le jeu
./gradlew lwjgl3:run
```

Script Windows (run.bat)
```
@echo off

:: Compiler si nécessaire
if not exist "build" (
  echo Compilation en cours...
  gradlew.bat build
)
:: Exécuter le jeu
gradlew.bat lwjgl3:run
```

## Licence

Ce projet est sous licence MIT. Veuillez consulter le fichier LICENSE pour plus de détails.


