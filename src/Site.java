
public class Site {

/* Constantes communes à tous les sites */

static final int STOCK_INIT = 5;
static final int STOCK_MAX = 10;
static final int BORNE_SUP = 8;
static final int BORNE_INF = 2;
//34 d'après la ligne 20 de SystemeEmprunt le site doit etre instancié avec un id
int id;

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
  synchronized void debut_emprunt(){
   //  21) j'ai enlevé  restitution_en_cours dans le while car elle est redondante avec nb_restituteur !=0 selon moi 
   System.out.println("emprunt en cours: "+emprunt_en_cours);
    System.out.println("nb_velo: "+nb_velo);
    System.out.println("nb_restituteurs: "+nb_restitueurs);
     while (emprunt_en_cours || nb_velo==0 || nb_restitueurs !=0) {  try { wait();} catch( Exception e){} }
     //23
     emprunt_en_cours=true;
     nb_emprunteurs++;
     System.out.println(" je commence à emprunter donc emprunt_en_cours devient : "+emprunt_en_cours + " et nombre emprunteurs devient: "+nb_emprunteurs);
}
//19 action d'emprunter
public void emprunter(){
   int ancien_nb_velo=nb_velo;
   nb_velo-=1;
   System.out.println("j'emprunte 1 velo donc le nombre de vélo passe de :"+ancien_nb_velo+" à "+ nb_velo+" sur le site de départ");
}

synchronized void fin_emprunt(){
      nb_emprunteurs--;
      emprunt_en_cours=false;
      System.out.println(" j'ai fini d'emprunter donc nb_emprunteurs devient: "+nb_emprunteurs+" et emprunt_en_cours devient: "+emprunt_en_cours);
      //22 J'ai choisi un while notify_All pour l'action d'emprunter ou de restituer car il y a plusieurs cas d'attente distincts ici
      System.out.println("et je reveille tous ceux qui sont en attentes pour soit emprunter ou restituer ou oubien le camion qui vient soit charger ou décharger des vélos");
      notifyAll();
    
}

    

//6 II-restituer
synchronized void debut_restituer(){
  System.out.println("je commence à restituer le vélo emprunté je vérifie s'il n'y a pas quelqu'un qui a une restitution en cours");
  System.out.println(" restitution en cours: "+restitution_en_cours);
  System.out.println(" je vérifie que le nombre de vélo n'atteint pas stock max autrement il n'y aurait plus de place pour restituer je devrais attendre");
  System.out.println("nb_velo: "+ nb_velo +" et stock max est égal à :" +STOCK_MAX);
  System.out.println("je vérifie que le dernier emprunteur a fini égalemnt");
  System.out.println("nb_emprunteurs: "+nb_emprunteurs);

    while( nb_velo == STOCK_MAX || restitution_en_cours || nb_emprunteurs !=0){ try { wait();} catch( Exception e){} }
    //24
    restitution_en_cours=true;
    //23 
    nb_restitueurs++;
    //8 restituer le velo

    System.out.println("je commence à restituer donc restitution_en_cours devient : "+restitution_en_cours + " et nb_restitueurs devient: "+nb_restitueurs);
}

// 9 action de restituer

public void restituer(){
     int ancien_nb_velo=nb_velo;
     nb_velo ++;
    System.out.println("je restitue un velon donc nb_velo passe de : "+ ancien_nb_velo+" à "+ nb_velo+" sur le site d'arrivée");
  }
//25

synchronized void fin_restituer(){
  nb_restitueurs--;
  restitution_en_cours=false;
   System.out.println("j'ai fini de restituer donc :");
   System.out.println("nb_restitueurs devient: "+nb_restitueurs+" et restitution_en_cours devient: "+restitution_en_cours);
   System.out.println("et je reveille tous ceux qui sont en attente");
  notifyAll();
}

//26 Test de scenarios pour les méthodes emprunter et restituer 
//27 Pour pouvoir tester ce scenario là on va realiser la classe Client 
  
}




