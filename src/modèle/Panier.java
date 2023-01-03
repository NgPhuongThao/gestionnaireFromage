package modèle;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	private float prixLivraison;

	private List<LigneDeCommande> panier;
	
	public Panier() {
		this.panier = new ArrayList<LigneDeCommande>();
	}

	public int getLine(LigneDeCommande ligne) {
		for (int i = 0; i < this.panier.size(); i++) {
			if (this.panier.get(i).equals(ligne)) {
				return i;
			}
		}
		return -1;
	}

	public void addLine(LigneDeCommande ligne,Article article) {
		article.préempterQuantité(ligne.getQuantité());
		boolean add = true;
		for (LigneDeCommande l : this.panier) {
			if (l.equals(ligne)) {
				LigneDeCommande ligneRecherchée = this.panier.get(this.getLine(ligne));
				ligneRecherchée.setQuantité(ligne.getQuantité()+ligneRecherchée.getQuantité());
				add = false;
			}
		}
		if (add) {
			this.panier.add(ligne);
		}
	}
	
	public void removeLigne(int nbLigne) {
		this.panier.remove(nbLigne);
	}
	
	public String[][] toArray() {
		String[][] result = new String[this.panier.size()][4];
		for (int i = 0; i<this.panier.size(); i++) {
			result[i]=panier.get(i).toArray();
		}
		return result;
	}
	
	public float total() {
		float total = 0.0F;
		for (LigneDeCommande ligne : panier) {
			total += ligne.getPrix()*ligne.getQuantité();
		}
		return total;
	}
	
    public float getPrixLivraison() {
        return this.prixLivraison;
    }
    
    public void setPrixLivraison(float prixLivraison) {
        this.prixLivraison = prixLivraison;
    }
    
    public void vider(Articles articles) {
    	for (LigneDeCommande l : this.panier) {
    		articles.getArticle(l.getDésignation(),l.getClé()).rendreQuantité(l.getQuantité());
    	}
    	this.panier.clear();
    }
    
    public List<LigneDeCommande> getPanier() {
        return this.panier;
    }
}