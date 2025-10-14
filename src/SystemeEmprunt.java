
import java.util.Random;

class SystemeEmprunt {

  /* Constantes de la simulation */

	static final int NB_SITES = 5;
	static final int NB_CLIENTS = 20;

	private Site[] sites = new Site[NB_SITES];
	private Client[] clients = new Client[NB_CLIENTS];
	private Camion camion = null;

	/* Constructeur du systeme d'emprunt */
	SystemeEmprunt() {

		/* Instanciation des sites */
		for(int i = 0; i < NB_SITES; i++)
			sites[i] = new Site(i);

		/* Instanciation des clients */
    Random r = new Random();
	  int siteDep=0;
	  int siteArr=0;
    for(int i = 0; i < NB_CLIENTS; i++) {
		    
			do{
				 siteDep = r.nextInt(NB_SITES);
			     siteArr = r.nextInt(NB_SITES);
			}
			while(siteDep == siteArr);//30  ajout de l'instanciation des site dans un do while pour qu'ils soient réinstanciés tant qu'ils sont différents
			clients[i] = new Client(sites[siteDep], sites[siteArr],i);
		}
		clients[1].start();

		/* Instanciation du camion */
		//camion = new Camion(sites);

    /* Démarrage du camion et des clients  bref des threads dans une methode fonctionner*/
    /* TODO */
	//31 Demarrage d'un client
	//31' D'abord on va faire la méthode run d'un client vu que c'est un thread

	//40 puis 40'
	// on créé juste des objets clients hors de la boucle prévue par le TP pour tester que nos methodes emprunter et restituer de clients fonctionnent bien correctement
	// et le scénario est que le client 1 va de son site de départ vers son site d'arrivée et que les clients 2 et 3 sont en  sur le site d'arrivée du clients 1 pour emprunter
	//des vélos mais on a malheureusement initialisé les stocks de vélos à 1 donc l'ordananceur va permettre seulement à l'un  des deux d'emprunter et de se déplacer
	//et l'autre est censé etre en attente de l'arrivée du client 1 donc on veut vérifier que l'attente fonctionne bien
	//Puis on a choisi comme site d'arrivée des 2 autres clients site de départ du client 1 car c'est un moyen sur de s'assurer que leur site de départ est différent de leur site d'arrivée sinon on aurait pu choisir d'autres valeurs
	Client c2=new Client(clients[1].siteArr, clients[1].siteDep,2);
	Client c3=new Client(clients[1].siteArr, clients[1].siteDep,3);
	
      
	  //41 puis 41'
	  c2.start();
	  c3.start();
	 
  }
  
  public static void main(String[] args) {
    new SystemeEmprunt();
	
  }

} // SystemeEmprunt
