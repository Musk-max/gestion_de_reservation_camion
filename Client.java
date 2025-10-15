// 27 suite : creation de la classe client
public class Client extends Thread { 
    //29 
    Site siteDep=null;
    Site siteArr=null; 
    int id;

    //28 d'après l'instanciation des clients dans SystemeEmprunt le constructeur de client fait ceci: 
    // le client choisi son site de départ et d'arrivée directement dans son constructeur mais et si il veut changer ?? on verra ca dans les prochaines versions
    public Client(Site siteDep, Site siteArr, int id){
        this.siteDep=siteDep;
        this.siteArr=siteArr;
        this.id=id;
     }
    //31' methode qui simule le trajet du Client
    public void run(){
         //42 afficher le nom du client qui vient de demarrer a decommenter si on en a besoin
         //System.out.println("Client numero "+id+" demarre sa premiere instruction");

        //38 avant de demarrer le client doit emprunter d'abord voir 38'
           this.emprunter();
       
        //32 calcul de la distance entre les sites d'après l'énoncé du sujet c'est la différence des id entre les sites en valeur absolue
        int distance= Math.abs(this.siteDep.getId()-this.siteArr.getId());
        //36 calcul de la duree selon la forumle duree = distance / vitesse et on suppose que les cycliste on une vitesse de 24 km/h
         long duration=(long) distance/1;
         System.out.println(" trajet-1-"+id+" c'est toujours le client "+this.id+" Je fais mon trajet entre le site " + siteDep.getId()+" et le site "+ siteArr.getId()+ "je parcours: "+distance+"km en une durée de "+duration + "ms");
        
         //37 simulation du temps de trajet du cycliste selon l'énoncé
        try{Thread.sleep(duration);}catch(Exception e){}
        //39 Après le trajet il doit restituer le velo voir 39'
          this.restituer();
        
    }
    //38'
    public synchronized void   emprunter(){
       //System.out.println("je suis le client "+id+" je fais debut emprunt sur le site "+ this.siteDep.getId());
       this.siteDep.debut_emprunt(this.id);
       this.siteDep.emprunter(this.id);
       this.siteDep.fin_emprunt(this.id);
    }
    //39'
    public void restituer(){
        this.siteArr.debut_restituer(this.id);
        this.siteArr.restituer(this.id);
        this.siteArr.fin_restituer(this.id);
    }
 }