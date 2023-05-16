package fileSystem;

public class File extends Elements{
	private int taille;

	public File(String nom, String path,int taille) {
		super(nom, path);
		this.taille = taille;
	}

	public int getTaille() {
		return taille;
	}

	
}
