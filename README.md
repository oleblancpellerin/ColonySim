# Cloner le projet

git clone https://github.com/djeezuss/ColonySim.git

Importer le fichier build.gradle

Cliquer auto-import

Cliquer OK

Attendre que Gradle finisse ses processus

Cliquer sur "Add configuration"

1. Ajouter une nouvelle "Android App"
2. Nom    : "android"
3. Module : android
4. Cliquer "Apply"
---
1. Ajouter une nouvelle "Application"
2. Nom : desktop
3. Main Class : net.ddns.djeezuss.desktop.DesktopLauncher
4. Working Directory : $PROJECT_DIR$\android\assets
5. Module : desktop
6. Cliquer "OK"

CTRL+ALT+S

Dans "Build, Execution, Deployment" > Gradle-Android Compiler

1. Décliquer "Configure on demand"
2. Cliquer OK

File > Invalidate Caches / Restart...

Sélectionner "Invalidate and Restart"

Laisser Intellij finir d'indexer ce qu'il a besoin

Partir la configuration "desktop" 2 fois (la première fois ne fonctionnera pas, mais la deuxième vas fonctionner)
