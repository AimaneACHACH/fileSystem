package fileSystem;

public class User {
	private String username;
	private String password;
	private Folder home ;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	    this.home = new Folder(this.username,"");
	}

	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Folder getHome() {
		return home;
	}
	
	
	
	public int calcTaille() {
		return home.getTaille();
	}
	
	public void createElement(int type,int taille,String nom) {
		home.createElement(type, taille, nom);
	}
	public void removeElement(Elements e) {
		home.removeElement(e);
	}
	public void editElement(Elements e,String newName) {
		home.editElement(e, newName);
	}
	
}
