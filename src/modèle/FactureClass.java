package modèle;

public class FactureClass {
	private float totalCommande;
	
	public FactureClass(Panier panier) {
		totalCommande = panier.total()+panier.getPrixLivraison();
	}
	
	public float getTotal() {
		return totalCommande;
	}
}
