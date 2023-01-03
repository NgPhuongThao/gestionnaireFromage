package modèle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFacture {
	
	@Test
	public void testFactureGetTotal() {
		Panier panier = new Panier();
		panier.addLine(new LigneDeCommande("désignation", "clé", 1.2F, 10), new Article(new Fromage("désignation"),"clé",1.2F));
		panier.setPrixLivraison(4F);
		FactureClass facture = new FactureClass(panier);
		assertEquals(16.0F, facture.getTotal(),0.0F);
	}
	
}
