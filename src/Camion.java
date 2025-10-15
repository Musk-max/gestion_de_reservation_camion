
public class Camion extends Thread {
     
  //56
  // drapeau pour savoir si le camion doit continuer
    private  boolean en_service = true;
  //56'
   public void arreter() {
        en_service = false;
    }

    //46
    static int nb_velo=20;
    // 42 attribut sites  du camion d'après son instanciation dans SystemeEmprunt
    Site[] sites;
   // 42' Constructeur  du camion d'après son instanciation dans SystemeEmprunt
    public Camion(Site[] sites){
        this.sites=sites;
    }
    //43'
    public void run(){ 
        System.out.print("debut du camion");
       //le camion se deplace de site en site dans l'ordre des numeros puis reprend à 0
       int i=0;
       //while(i<=sites.length){
        while (en_service) { // 56 suite au lieu de while(i <= sites.length) pour permettre l'arret du camion quand tous les threads ont finis
        //duree du deplacement entre deux sites
       try{Thread.sleep(1000);}catch(Exception e){ break;}
        System.out.println(i);
        //44 charger l'execedent sur le camion
        if( sites[i].nb_velo > Site.BORNE_SUP ){
          System.out.println(" le nombre de vélo du site numero "+i+"  parcouru par le camion: le site "+ sites[i].getId()+ " est de "+sites[i].nb_velo +
          " velos il depasse donc la borne supérieure des sites qui est de "+ Site.BORNE_SUP+ " on va donc ramener le nombre de velo au stock initial des sites qui est de "+Site.STOCK_INIT+
          " en chargeant l\'excedent dans le camion" );
           int excedent=sites[i].nb_velo-Site.STOCK_INIT;
           this.enlever_sur_le_site(i, excedent);
           
        }
        //47 le camion reapprovisionne le site de la quantite dont il a besoin pour atteindre son stock initial dans la mesure du possible
        // Dans la mesure du possible signifie que si le camion n'a pas assez de velos pour ravitailler le site il se contente de donner tout ce qu'il a et donc son stock passe à 0
        if(sites[i].nb_velo < Site.BORNE_INF){
            System.out.println(" le nombre de vélo du site numero "+i+"  parcouru par le camion: le site "+ sites[i].getId()+ " est de "+sites[i].nb_velo +
          " velos il est  donc inférieure à  la borne inferieur des sites qui est de "+ Site.BORNE_INF+ " on va donc ramener le nombre de velo au stock initial des sites qui est de "+Site.STOCK_INIT+
          "  dans la mesure du possible en dechargeant la quantite a completer du camion " );
          if(nb_velo > Site.STOCK_INIT-sites[i].nb_velo){
             int quantite_a_completer=Site.STOCK_INIT-sites[i].nb_velo;
             this.reapprovisionner_site(i,quantite_a_completer);
          }else{
            //sinon la quantite a completer est le nb_velo du camion, il donne tout ce qu'il a 
            int quantite_a_completer=nb_velo;
            this.reapprovisionner_site(i,quantite_a_completer);
          }
          
          
        }
        i=(i+1) % sites.length;
       }
       //56 suite 
       System.out.println("Fin du camion car tous les clients ont terminé !");
    }
    //44' creation de la methode charger du camion conjointement avec celle de site
    //cette methode prend l'exces de velos sur le site i et le mets dans le camion
    public  void enlever_sur_le_site(int i, int excedent){
        int ancien_nb_velo=nb_velo;
        System.out.println(" le nombre de velos du camion etait de "+ancien_nb_velo +" avant d\'arriver sur le site  : "+sites[i].getId());
        this.reprendre_du_site(i, excedent);
        nb_velo +=excedent;
        System.out.println(" le nouveau nombre de velos du camion après avoir enlevé "+excedent+" velos sur le site"+sites[i].getId()+" est "+nb_velo);

    }
   
    

    //47' creation de la méthode  reapprovisionner_site du camion conjointement avec celle de site
    // Cette methode calcul le deficit de velo sur Site et la quantite de velo dont le site a besoin pour revenir a son stock initial du camion pour le donner au site
    public void reapprovisionner_site(int i,int quantite_a_completer ){
        int ancien_nb_velo=nb_velo;
        System.out.println(" le nombre de velos du camion etait de "+ancien_nb_velo +" avant d\'arriver sur le site  : "+sites[i].getId());
       this.donner_au_site(i, quantite_a_completer);
        nb_velo -=quantite_a_completer;
        System.out.println(" le nouveau nombre de velos du camion après avoir deposé "+quantite_a_completer+" velos sur le site"+sites[i].getId()+" est "+nb_velo);

    }
    //54 methode qui rassemple le process pour reapprovisionner le site
    public void donner_au_site( int i, int quantite_a_completer){

       sites[i].debut_prendre_complement_dans_camion(quantite_a_completer);
       sites[i].prendre_complement_dans_camion(quantite_a_completer);
       sites[i].fin_prendre_complement_dans_camion(quantite_a_completer);
    }
    //56 methode qui rassemple le process pour enlever le surplus des sites
    public void reprendre_du_site(int i, int excedent) {
    sites[i].debut_charger_excedent_dans_camion(excedent);
    sites[i].charger_excedent_dans_camion(excedent);
    sites[i].fin_charger_excedent_dans_camion(excedent);
}
}
