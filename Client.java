// 27 suite : creation de la classe client
public class Client extends Thread { 
    //29 
    Site siteDep=null;
    Site siteArr=null; 

    //28 d'après l'instanciation des clients dans SystemeEmprunt le constructeur de client fait ceci: 
    // le client choisi son site de départ et d'arrivée directement dans son constructeur mais et si il veut changer ?? on verra ca dans les prochaines versions
    public Client(Site siteDep, Site siteArr){
        this.siteDep=siteDep;
        this.siteArr=siteArr;
     }
    //31' methode qui simule le trajet du Client
    public void run(){
        //32 calcul de la distance entre les sites d'après l'énoncé du sujet c'est la différence des id entre les sites en valeur absolue
        int distance= Math.abs(this.siteDep.getId()-this.siteArr.getId());
        //36 calcul de la duree selon la forumle duree = distance / vitesse et on suppose que les cycliste on une vitesse de 24 km/h
         long duration=(long) distance/1;
         System.out.println(distance+"km et "+duration + "ms");
         //37 simulation du temps de trajet du cycliste selon l'énoncé
        try{Thread.sleep(duration);}catch(Exception e){}
    }
 }