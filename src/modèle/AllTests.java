package modèle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestFacture.class, TestFiltreFromages.class, testPanier.class, TestSaisieFromage.class })
public class AllTests {

}
