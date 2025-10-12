
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
			clients[i] = new Client(sites[siteDep], sites[siteArr]);
			System.out.println(siteDep+"à "+siteArr);
		}

		/* Instanciation du camion */
		//camion = new Camion(sites);

    /* Démarrage du camion et des clients  bref des threads dans une methode fonctionner*/
    /* TODO */
	//31 Demarrage d'un client
	//31' D'abord on va faire la méthode run d'un client vu que c'est un thread
	
      clients[1].start();
  }
  
  public static void main(String[] args) {
    new SystemeEmprunt();
	
  }

} // SystemeEmprunt
