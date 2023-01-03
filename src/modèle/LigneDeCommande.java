package modèle;

public class LigneDeCommande {
	
	private String désignation;
	private float prix;
	private int quantité;
	private String clé;
	
	public LigneDeCommande(String désignation, String clé, float prix, int quantité) {
		if (clé == "") {
			this.désignation = désignation + " - A la pièce";
		} else {
			this.désignation = désignation + " - " + clé;
		}
		this.clé = clé;
		this.prix = prix;
		this.quantité = quantité;
	}
	
	public String getDésignation() {
		return this.désignation;
	}
	
	public float getPrix() {
		return this.prix;
	}
	
	public int getQuantité() {
		return this.quantité;
	}
	
	public String getClé() {
		return this.clé;
	}
	
	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	public float prixTotal() {
		return this.quantité*this.prix;
	}
	
    public String[] toArray() {
        String[] result = new String[4];
        result[0] = this.désignation;
        result[1] = String.format("%.2f", this.prix);
        result[2] = String.format("%d", this.quantité);
        result[3] = String.format("%.2f", this.prixTotal());
        return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LigneDeCommande)){
			return false;
		}
		LigneDeCommande ligne = (LigneDeCommande) obj;
		return ligne.getDésignation().equals(this.getDésignation());
		}

}