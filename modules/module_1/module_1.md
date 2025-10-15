# Création de l'environnement de développement

```bash
git init
git add *
Si le compte n’est pas encore connecté à la machine, configurez votre identité Git :

bash
Copier le code
git config --global user.email "hervelagouesh@gmail.com"
git config --global user.name "musk-max"
Ensuite :

bash
Copier le code
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Musk-max/gestion_de_reservation_camion.git
git push -u origin main
📝 Note
Il est possible que Git affiche des avertissements comme ceux-ci :

pgsql
Copier le code
warning: in the working copy of 'src/Site.java', LF will be replaced by CRLF the next time Git touches it
warning: in the working copy of 'src/SystemeEmprunt.java', LF will be replaced by CRLF the next time Git touches it
➡️ Ces avertissements peuvent être ignorés, car ils n’affectent ni les fichiers ni le code.

Copier le code
# Dimanche 12/10/2025
## Suivi des changements de classes *
1) de num 1 à 27 site 
2) de 27 à  29    Client
3) 30 à     31      SystemeEmprunt
4) 31 à 32 Client
5) 33    à 35        Site
6) 36   37   Client
7) revoir ma formule de calcul de la duree car elle trop petite a mon gout ( le thread doit dormir au moins 1 seconde ...)
# Lundi 13/10/2025
8) 38 à 39' dans Client
9) Comprendre pour quoi le nombre de vélo affiché dans debut restituer est différent du nombre de vélo dans emprunter
    ( C'est à dire quand on execute emprunter on passe de 5 à 4 vélo; cette valeur de 4 la est sensée persister également dans la méthode
       debut restituer, mais quand on affiche le nombre de vélo récupérer par début restituer il est de 5 comme au début ), est ce parceque nb_velo devrait etre manipulé en section critique ?
# Mardi 14/10/25
10) En fait ce qui se passe en 9 est tout à fait normal car le site de depart lui a maintenant 4 velo mais le site d'arrivée lui en 5+1 maintenant car c'est laba que le client restitue le velo qu'il a pris sur le site de départ'
11) 40 à 41' SystemeEmprunt'
12) Faison en sorte  que chaque thread qui attend affiche les raisons de son attente afin que nous puissions bien suivre la trame d\'execution
13) Idem pour les notifications faisons en sorte que chaque process qui réveille les autres dise pourquoi il les a réveillé
14) Me debrouiller pour afficher le nom  du client qui vient de demarrer (j'ai donc modifier nos constructeurs de clients dans tous les fichiers pour que a l'initialisation chaque client ait un numero)
15) Normalement de pas son contenu la méthode emprunter de client devrait etre atomique sauf si la premiere instruction c\'est l\'ordo qui choisit qui la joue
16) question 1 : pourquoi les starts ne sont pas séquentiels dans l\'execution de leur premiere methode en tout cas c\'est un constat que je viens de faire
# Mercredi 15/10/2025
17) 42 et 42\' dans  camion
18) 43 dans SystemeEmprunt
19)44 à 44\' et 46 et 47 et 47\' dans camion
20) 45 et 48 Site
21) 46 testons la methode charger de camion
22)49 site et 49\' SystemeEmprunt
23)50 et 50\'dans site au niveau de debutEmprunt( boucle pour afficher des caracteres separateurs) et plusieurs copie de 50 partout dans le code de site ou il y a un affichage pour creer des espaces
24) 51 dans  SystemeEmprunt pour juste informer sur les legendes de nos affichages
25) 52 et 53 dans Site 
26) 54 dans camion
27) 55 dans site
28)56 , 56\', 56 suites dans camion et aussi 56 suite dans SystemeEmprunt
