package modèle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testPanier {
	private Panier panier;
	private LigneDeCommande ligne;
	private Article article;
	
	@Before
	public void setUp() {
        this.ligne = new LigneDeCommande("description", "250g", 2.99F, 2);
        this.panier = new Panier();
        this.article = new Article(new Fromage("description"),"250g",2.99F);
	}
	
	@After
	public void tearDown() {
		this.ligne = null;
		this.panier = null;
		this.article = null;
	}	
    @Test
    public void testAjoutPanier() {
        this.panier.addLine(this.ligne,this.article);
        assertTrue(this.panier.getPanier().contains(this.ligne));
    }
    
    @Test
    public void testSupressionPanier() {
        this.panier.addLine(this.ligne,this.article);
        this.panier.removeLigne(0);
        assertFalse(panier.getPanier().contains(ligne));
    }
    
    @Test
    public void testModificationQuantiteAjoutLigne() {
    	int quantitéAvantAjout = this.article.getQuantitéEnStock();
    	this.panier.addLine(this.ligne, this.article);
    	assertEquals(2,quantitéAvantAjout-this.article.getQuantitéEnStock());
    }
    
    @Test
    public void testModificationQuantiteViderPanier() {    	
    	Fromage fromage = new Fromage("description");
    	fromage.addArticle(this.article.getClé(), this.article.getPrixTTC());

    	Articles articles = new Articles();
    	List<Fromage> listeFromages = new LinkedList<Fromage>();
    	listeFromages.add(fromage);
    	articles.addFromages(listeFromages);
    	Article articleSélectionné = articles.getArticle("description", "250g");
    	articleSélectionné.setQuantitéEnStock(10);
    	
    	
    	this.panier.addLine(this.ligne, articleSélectionné);
    	this.panier.vider(articles);
    	
    	assertEquals(10,articleSélectionné.getQuantitéEnStock());
    }
}
