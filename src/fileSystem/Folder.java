package fileSystem;
import java.util.*;

public class Folder extends Elements{
	private ArrayList<Elements> elementsList;
	public Folder(String nom, String path) {
		super(nom, path);
		this.elementsList = new ArrayList<Elements>();
		
	}
	
	public int getTaille() {
		int taille=0;
		for(Elements x : elementsList) {
			taille+=x.getTaille();
		}
		return taille;
	}
	
	public void createElement(int type,int taille,String nom) { //type1:folder,type0:file,taille defaut:0
		if(type==1) {
			Folder newElement= new Folder(nom,this.getPath()+"/"+this.getNom());
			elementsList.add(newElement);
		}
		if(type==0) {
			File newElement= new File(nom,this.getPath()+"/"+this.getNom(),taille);
			elementsList.add(newElement);
		}
	}
	public void editElement(Elements e,String newName) {
		e.setNom(newName);
	}
	public void removeElement(Elements e) {
		this.elementsList.remove(e);
	}

	public ArrayList<Elements> getElementsList() {
		return elementsList;
	}
	
}
