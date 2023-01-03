package modèle;

public enum TypeLait {
	VACHE("Vache"), CHEVRE("Chèvre"), BREBIS("Brebis");

	private String typeDeLait;
	
	private TypeLait(String typeDeLait) {
		this.typeDeLait = typeDeLait;
	}
	
	public String getTypeDeLait() {
		return this.typeDeLait;
	}
}
