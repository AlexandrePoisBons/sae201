# SAE 2.01 & 2.02 :
Explications pour lancer les deux applications.


# Instruction pour la compilation
Afin de compiler rapidement nos programmes, nous avons créé un fichier compile.list permettant de compiler tous nos programmes depuis un seul répertoire. Pour cela, mettez-vous dans le répertoire "sae201" puis exécutez la commande : sous Windows :  javac "@compile.list"  sous Linux : javac @compile.list


# Application n°1
L’application n°1 consiste à créer un fichier texte (.txt) qui va stocker les différentes informations d’une ou plusieurs cuves, des différents tuyaux qui les relient ainsi que le type de structure. Notre Application 1 peut se lancer en mode CUI ou bien en GUI. Pour cela, nous allons aller dans le répertoire parent de "sae201" puis pour lancer l’application, exécuter la commande " java sae201/metier/application1.java " CUI pour lancer l'application 1 en mode console ou bien  "java sae201/metier/application1.java GUI". À la fin de l’exécution de ce programme, le fichier texte (.txt) va être créé dans le dossier "sae201/metier".


# Application n°2
Notre application n°2 va pouvoir s’exécuter de 3 façons différentes. La première est la méthode « Simple » dans laquelle l’application va afficher un réseau de cuves en reprenant les informations du fichier texte (.txt) créé précédemment. Il y a aussi la méthode « Avance » dans laquelle l’utilisateur va pouvoir choisir quel fichier texte (.txt) il souhaite lire sachant que ce fichier peut se trouver dans n’importe quel dossier. Enfin, il y a la méthode « Manuel » qui permet d’ouvrir des champs de saisie pour que l’utilisateur puisse directement créer et afficher son réseau de cuves. Pour lancer cette application, il faut se mettre dans le répertoire parent de sae201 exécuter la commande "java sae201/ihm/application2"
