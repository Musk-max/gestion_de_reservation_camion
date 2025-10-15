
public class Site {

/* Constantes communes à tous les sites */

static final int STOCK_INIT = 5;
static final int STOCK_MAX = 10;
static final int BORNE_SUP = 8;
static final int BORNE_INF = 2;
//34 d'après la ligne 20 de SystemeEmprunt le site doit etre instancié avec un id
int id;

//53 de nouvelles variables pour faire attendre les clients lorsque le camion est en action
boolean chargement_en_cours;
boolean dechargement_en_cours;


//49 creons une liste de clients pour enregistrer les clients qui sont sur un meme site
//on choisit 20 clients par defaut car notre systeme en simule pour 20 mais on va plus tard choisir une arraylist comme data structure
//aller dans SystemeEnmprunt en 49' pour voir comment est utilisé ce tableau
Client[] clients= new Client[20];
//1) le nb_velo courant du parking
int nb_velo= STOCK_INIT;

//10 variable pour savoir lorsqu'un emprun est en cours
boolean emprunt_en_cours; 
//20 variable pour savoir lorsqu'une restitution est en cours
boolean restitution_en_cours; 

// 16 creons une variable pour décompter le nombres d'emprunteurs ca peut nous etre utile mais c'est souvent fait en td donc j'ai fait ca
int nb_emprunteurs=0;

//17  creons une variable pour s'assurer que le dernier restitueur ait restitué avant d'emprunter
int nb_restitueurs=0;

//33 construteur d'après la ligne 20 dans SystemeEmprunt pour l'instanciation des sites
public Site(int id){
     this.id=id;
}
//35 creation d'un getter pour l'id
public int getId(){
  return this.id;
}


/* 
//2) I- emprunter
Synchronised void emprunter(){
 
 //11 rendons sequentiel la methode emprunter lorsqu'un emprunt est déja en cours

  while (emprunt_en_cours) {  try { wait();} catch( Exception e){} } 
  
  while(nb_velo==0){
      //4 plus de velo disponible
      try { wait();} catch( Exception e){}
  }
   //12 nous avons débuté notre emprunt
   emprunt_en_cours = true; 
  //3 prendre le velo
  nb_velo--;
  //5
  System.out.println("nb_velo : "+ nb_velo);

  //13 fin emprunt
  emprunt_en_cours=false;

}
  */

  //14 J'ai fini ma méthode emprunter en 13 mais je vais l'organiser tel en TD en la séparant en début et fin
  //15 debut emprunt s'assure que lorque un emprunt est en cours ou si il n'y a plus de velo oubien le dernier restitueur n'a pas restitué on soit bloqué
  // 18 je dois bien analyser ou tester pendant plusieurs jours cette condition dans le while de debut emprunt pour l'ameliorer

  synchronized void debut_emprunt(int id_client){
    //50' caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   
    System.out.println("Debut emprunt-1-"+id_client+": je suis le client "+id_client+" je fais debut emprunt sur le site "+ this.getId()+" puis-je emprunter un velo ? ");
     //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
    


   //  21) j'ai enlevé  restitution_en_cours dans le while car elle est redondante avec nb_restituteur !=0 selon moi 
   
   // affichage lorsque rien nous bloque ou bien pour dire la cause du blocage lorsqu'on souhaite emprunter
   if(nb_velo != 0 &&  nb_restitueurs ==0 && !emprunt_en_cours ){
     for (int i=0;i<1; i++){
      System.out.println(" ");
    }
    System.out.println("Debut emprunt-2-"+id_client+": c'est toujours le client"+id_client+" rien ne me bloque sur le site "+this.getId()+" , je peux directement emprunter");
    //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   }else{
       if(emprunt_en_cours){
     System.out.println("Debut emprunt-2-"+id_client+": c'est toujours le client"+id_client+" Emprunt en cours pour le site "+this.getId()+" est" +emprunt_en_cours+" Donc je suis obligé d\'attendre que la personne qui est déjà entrain d\'emprunter finisse");
     //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   }
    if(nb_velo==0){
     System.out.println("Debut emprunt-2-"+id_client+": c'est toujours le client"+id_client+" on a nb_velo sur le site "+ this.getId()+" est" +nb_velo+" Donc je suis obligé d\'attendre que soit un camion oubien un autre client vienne déposer un nouveau vélo");
     //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   } 
    if( nb_restitueurs !=0){
     System.out.println("Debut emprunt-2-"+id_client+": c'est toujours le client"+id_client+" on nb_restituteurs sur le site : "+this.getId()+"est "+nb_restitueurs+ " Donc je suis obligé d\'attendre que les restitueurs finissent et le dernier restituer finis lorsque nb_restituer=0");
     //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   } 
   }
     while (emprunt_en_cours || nb_velo==0 || nb_restitueurs !=0) {  try { wait();} catch( Exception e){} }
     //23
     emprunt_en_cours=true;
     nb_emprunteurs++;
     System.out.println("Debut emprunt-3-"+id_client+": c'est toujours le client"+id_client+"  avant de commencer à emprunter"+" sur le site "+this.getId()+" je verrouille l'accès a ce site donc emprunt_en_cours devient : "+emprunt_en_cours + " et nombre emprunteurs devient: "+nb_emprunteurs);
     //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
}
//19 action d'emprunter
public void emprunter(int id_client){
   int ancien_nb_velo=nb_velo;
   nb_velo-=1;
   System.out.println("emprunter-1-"+id_client+"  c'est toujours le client"+id_client+" j'emprunte 1 velo donc le nombre de vélo passe de :"+ancien_nb_velo+" à "+ nb_velo+" sur le site de depart "+this.getId());
   //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
}

synchronized void fin_emprunt(int id_client){
      nb_emprunteurs--;
      emprunt_en_cours=false;
      System.out.println("fin emprunt-1-"+id_client+" c'est toujours le client"+id_client+" j'ai fini d'emprunter donc nb_emprunteurs devient: "+nb_emprunteurs+" et emprunt_en_cours devient: "+emprunt_en_cours);
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
      //22 J'ai choisi un while notify_All pour l'action d'emprunter ou de restituer car il y a plusieurs cas d'attente distincts ici
      System.out.println("fin emprunt-2-"+id_client+" c'est toujours le client"+id_client+" et je reveille tous ceux qui sont en attentes pour soit emprunter ou restituer ou oubien le camion qui vient soit charger ou décharger des vélos sur le site "+this.getId());
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
      notifyAll();
    
}

    

//6 II-restituer
synchronized void debut_restituer(int id_client){
  System.out.println(" debut restituer-1-"+id_client+" c'est toujours le client "+id_client+" est ce que je peux restituer mon velo sur le site "+this.getId()+ " ?");
  //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
  if(!restitution_en_cours && nb_velo != STOCK_MAX && nb_emprunteurs ==0 ){
         System.out.println("Debut restituer-2-"+id_client+" c'est toujours le client "+id_client+" rien ne me bloque sur le site "+this.getId()+" , je peux directement restituer");
         //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }

  }else{
    if( nb_velo == STOCK_MAX){
        System.out.println("Debut restituer-2-"+id_client+" c'est toujours le client "+id_client+" le nb_velo: sur le site  "+this.getId()+ " est "+ nb_velo +" et stock max est égal à :" +STOCK_MAX+ " donc il n\' y a pour le moment pas de place pour restituer les vélos sur le site "+ this.getId()+" je dois attendre qu'il y en ait ");
        //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
    }
    if(restitution_en_cours){
            System.out.println("Debut restituer-2-"+id_client+" c'est toujours le client "+id_client+" restitution en cours sur le site : "+this.getId()+ " est "+restitution_en_cours+" quelqu\\'un d'autre est déjà entrain de restituer un vélo sur le site "+ this.getId()+" j\'attend que ca se termine !");
            //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }

    }
     if(nb_emprunteurs !=0){
      System.out.println("Debut restituer-2-"+id_client+" c'est toujours le client "+id_client+"il y a déjà "+nb_emprunteurs+"emprunteurs:  sur le site "+ this.getId()+" je suis donc obligé d\'attendre que le dernier finisse");
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
    }
  }

    while( nb_velo == STOCK_MAX || restitution_en_cours || nb_emprunteurs !=0){ try { wait();} catch( Exception e){} }
    //24
    restitution_en_cours=true;
    //23 
    nb_restitueurs++;
    //8 restituer le velo

    System.out.println("Debut restituer-3-"+id_client+" c'est toujours le client "+id_client+"avant de commencer à restituer je verrouille l'accès au site"+this.getId()+" donc restitution_en_cours devient : "+restitution_en_cours + " et nb_restitueurs devient: "+nb_restitueurs+" sur le site "+this.getId());
    //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
}

// 9 action de restituer

public void restituer(int id_client){
     int ancien_nb_velo=nb_velo;
     nb_velo ++;
    System.out.println(" restituer-1-"+id_client+" c'est toujours le client "+id_client+ "je restitue un velon donc nb_velo passe de : "+ ancien_nb_velo+" à "+ nb_velo+" sur le site d'arrivee " +this.getId());
    //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
  }
//25

synchronized void fin_restituer(int id_client){
  nb_restitueurs--;
  restitution_en_cours=false;
   System.out.println("Fin restituer-1-"+id_client+" c'est toujours le client "+id_client+" j'ai fini de restituer donc : nb_restitueurs devient: "+nb_restitueurs+" et restitution_en_cours devient: "+restitution_en_cours);
   //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
   System.out.println("Fin restituer-2-"+id_client+" c'est toujours le client "+id_client+"je reveille tous ceux qui sont en attente pour soit emprunter ou restituer aussi oubien le camion pour soit décharger ou charger des vélos");
   //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
  notifyAll();
}
//45 methode charger execedent qui ramene le stock du site a STOCK_INIT a chaque fois qu'il depasse BORN_SUP et  les velos enleves seront pris par le camion
/*public synchronized void charger_excedent_dans_camion(int excedent) {
      int ancien_nb_velo=nb_velo;
      System.out.println("le  nb_velo sur le site  "+this.getId()+" avant  le passage du camion est de "+ ancien_nb_velo);
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
      nb_velo -= excedent;
      System.out.println("le nouveau nb_velo sur le site  "+this.getId()+" après le passage du camion est de "+ nb_velo);
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
}*/
//48 methode prendre complement dans camion qui ramene le stock du site a STOCK_INIT a chaque fois qu'il est en dessous de BORN_INF 
// enlevant la quantite à completer de velo du camion , et en donnant cette quantite la au site
// mais si la quantite dans le camion ne suffit pas donner tout ce que le camion a au site dans ce cas le stock du camion devient 0
/*public synchronized void prendre_complement_dans_camion(int quantite_a_completer) {
     int ancien_nb_velo=nb_velo;
      System.out.println("le  nb_velo sur le site  "+this.getId()+" avant  le passage du camion est de "+ ancien_nb_velo);
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
      nb_velo += quantite_a_completer;
      System.out.println("le nouveau nb_velo sur le site  "+this.getId()+" après le passage du camion est de "+ nb_velo);
      //50 caractere pour separer les affichage de clients différents
    for (int i=0;i<1; i++){
      System.out.println(" ");
    }
}
*/

// 52 Separons prendre_complement_dans_camion() en debut_prendre_complement_dans_camion, prendre_complement_dans_camion() et fin_prendre_complement_dans_camion()

  // --- 1) DEBUT prendre_complement_dans_camion ---
synchronized void debut_prendre_complement_dans_camion(int quantite_a_completer) {
    // Séparation visuelle
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    System.out.println("Debut camion-1: le camion arrive sur le site " + this.getId() +
        " pour déposer " + quantite_a_completer + " vélos.");

    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    // Ici, on peut imaginer une attente si le site est occupé
    // Exemple : pendant un emprunt ou une restitution
    while (emprunt_en_cours || restitution_en_cours) {
        try {
            System.out.println("Debut camion-2: le camion doit attendre car le site "
                + this.getId() + " est occupé (emprunt_en_cours=" + emprunt_en_cours +
                ", restitution_en_cours=" + restitution_en_cours + ")");
            wait();
        } catch (Exception e) {
        }
    }

    System.out.println("Debut camion-3: le camion peut maintenant déposer ses vélos sur le site " + this.getId());
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }
}

// ACTION prendre_complement_dans_camion ---
public void prendre_complement_dans_camion(int quantite_a_completer) {
    int ancien_nb_velo = nb_velo;

    System.out.println("Camion-1: nb_velo sur le site " + this.getId() +
        " avant le passage du camion est de " + ancien_nb_velo);
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    nb_velo += quantite_a_completer;

    System.out.println("Camion-2: nb_velo sur le site " + this.getId() +
        " après le passage du camion est de " + nb_velo);
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }
}
// --- 3) FIN prendre_complement_dans_camion ---
synchronized void fin_prendre_complement_dans_camion(int quantite_a_completer) {
    System.out.println("Fin camion-1: le camion a fini de déposer " + quantite_a_completer +
        " vélos sur le site " + this.getId());

    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    System.out.println("Fin camion-2: le camion réveille tous ceux qui étaient en attente sur le site " + this.getId());
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    notifyAll();
}


//26 Test de scenarios pour les méthodes emprunter et restituer
//27 Pour pouvoir tester ce scenario là on va realiser la classe Client
//55 separons egalement charger_excedent_dans_camion_en trois
// --- 1) DEBUT charger_excedent_dans_camion ---
synchronized void debut_charger_excedent_dans_camion(int excedent) {
    // Séparation visuelle
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    System.out.println("Debut camion-charger-1: le camion arrive sur le site " + this.getId() +
        " pour CHARGER un excédent de " + excedent + " vélos.");

    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    // Attente si le site est occupé (par exemple, emprunt ou restitution en cours)
    while (emprunt_en_cours || restitution_en_cours) {
        try {
            System.out.println("Debut camion-charger-2: le camion doit attendre car le site "
                + this.getId() + " est occupé (emprunt_en_cours=" + emprunt_en_cours +
                ", restitution_en_cours=" + restitution_en_cours + ")");
            wait();
        } catch (Exception e) {
        }
    }

    System.out.println("Debut camion-charger-3: le camion peut maintenant charger les vélos sur le site " + this.getId());
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }
}


// 
public void charger_excedent_dans_camion(int excedent) {
    int ancien_nb_velo = nb_velo;

    System.out.println("Camion-charger-1: nb_velo sur le site " + this.getId() +
        " avant le passage du camion est de " + ancien_nb_velo);
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    nb_velo -= excedent;

    System.out.println("Camion-charger-2: nb_velo sur le site " + this.getId() +
        " après le passage du camion est de " + nb_velo);
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }
}


// --- 3) FIN charger_excedent_dans_camion ---
synchronized void fin_charger_excedent_dans_camion(int excedent) {
    System.out.println("Fin camion-charger-1: le camion a fini de charger " + excedent +
        " vélos sur le site " + this.getId());
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    System.out.println("Fin camion-charger-2: le camion réveille tous ceux qui sont en attente (clients ou autres camions) sur le site " + this.getId());
    for (int i = 0; i < 1; i++) {
        System.out.println(" ");
    }

    notifyAll();
}
}




