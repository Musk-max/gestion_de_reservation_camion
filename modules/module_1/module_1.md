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
7) revoir ma formule de calcul de la duree