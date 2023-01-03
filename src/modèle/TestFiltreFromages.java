package modèle;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestFiltreFromages {

    @Test
    public void testFiltreChevre() {
        Fromage fromageChevre = new Fromage("description");
        fromageChevre.updateTypeFromage(TypeLait.CHEVRE);
        assertEquals(fromageChevre.getTypeFromage(), TypeLait.CHEVRE);
    }
    
    @Test
    public void testFiltreBrebis() {
        Fromage fromageChevre = new Fromage("description");
        fromageChevre.updateTypeFromage(TypeLait.BREBIS);
        assertEquals(fromageChevre.getTypeFromage(), TypeLait.BREBIS);
    }
    
    @Test
    public void testFiltreVache() {
        Fromage fromageChevre = new Fromage("description");
        fromageChevre.updateTypeFromage(TypeLait.VACHE);
        assertEquals(fromageChevre.getTypeFromage(), TypeLait.VACHE);
    }
        
    @Test
    public void testArticle() {
        Fromage fromage = new Fromage("description");
        fromage.addArticle("500g", 7.0F);
        assertEquals(fromage.nombreArticles(), 1);
    }
}