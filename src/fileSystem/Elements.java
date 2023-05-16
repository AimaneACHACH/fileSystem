package fileSystem;

public class Elements {
	private String nom;
	private String path;
	public Elements(String nom, String path) {
		super();
		this.nom = nom;
		this.path = path;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getTaille() {
		return 0;
	}
	
}
