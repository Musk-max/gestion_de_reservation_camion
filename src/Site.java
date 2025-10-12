
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
     while (emprunt_en_cours || nb_velo==0 || nb_restitueurs !=0) {  try { wait();} catch( Exception e){} }
     //23
     emprunt_en_cours=true;
     nb_emprunteurs++;
}
//19 action d'emprunter
public void emprunter(){
   nb_velo--;
   System.out.println(nb_velo);
}

synchronized void fin_emprunt(){
      nb_emprunteurs--;
      emprunt_en_cours=false;
      //22 J'ai choisi un while notify_All pour l'action d'emprunter ou de restituer car il y a plusieurs cas d'attente distincts ici
      notifyAll();
    
}

    

//6 II-restituer
synchronized void debut_restituer(){
    while( nb_velo == STOCK_MAX || restitution_en_cours || nb_emprunteurs !=0){ try { wait();} catch( Exception e){} }
    //24
    restitution_en_cours=true;
    //23 
    nb_restitueurs++;
    //8 restituer le velo
}

// 9 action de restituer

public void restituer(){
     nb_velo ++;
    System.out.println("nb_velo : "+ nb_velo);
  }
//25

synchronized void fin_restituer(){
  nb_restitueurs--;
  restitution_en_cours=false;
  notifyAll();
}

//26 Test de scenarios pour les méthodes emprunter et restituer 
//27 Pour pouvoir tester ce scenario là on va realiser la classe Client 
  
}




