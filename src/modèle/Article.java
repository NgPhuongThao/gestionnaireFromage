package modèle;

public class Article {

	private Fromage fromage;
	private String clé;
	private float prixTTC;
	private int quantitéEnStock;
	
	public Article(Fromage fromage, String clé, float prixTTC) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixTTC = prixTTC;
		this.quantitéEnStock = 0;
	}
	
	public Fromage getFromage() {
		return this.fromage;
	}

	public float getPrixTTC() {
		return this.prixTTC;
	}
	
	public int getQuantitéEnStock() {
		return quantitéEnStock;
	}
	
	public String getClé() {
		return this.clé;
	}

	public void setQuantitéEnStock(int quantitéEnStock) {
		this.quantitéEnStock = quantitéEnStock;
	}

	public void préempterQuantité(int quantité) {
		this.quantitéEnStock -= quantité;
	}
	
	public void rendreQuantité(int quantité) {
		this.quantitéEnStock += quantité;
	}	
	
	public String toString() {
		if (clé.equals(""))
			return this.fromage.getDésignation() + ", Prix TTC : " + this.getPrixTTC() + " â‚¬";
		else
			return this.fromage.getDésignation() + ", " + this.clé + ", Prix TTC : " + this.getPrixTTC() + " â‚¬";
	}
	
	public String toStringIHM() {
		if (clé.equals(""))
			return "A la pièce, Prix TTC : " + this.getPrixTTC() + " €";
		else
			return this.clé + ", Prix TTC : " + this.getPrixTTC() + " €";
	}
	
	public String toStringAvecStock() {
		return this.toString() + ", Quantité en stock : " + this.quantitéEnStock;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Article)){
			return false;
		}
		Article other = (Article) obj;
		return (this.fromage.equals(other.fromage) && this.clé.equals(other.clé));
	}
	
}
