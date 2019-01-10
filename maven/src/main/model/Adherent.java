package model;

public class Adherent {
	
	private String civilite;
	private String prenom;
	private String nom;
	private Adresse adresse;
	private int idAdherent;
	
	
	
	public Adherent(String civilite, String prenom, String nom, Adresse adresse) {
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public Adherent() {
	}


	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public int getIdAdherent() {
		return idAdherent;
	}


	public void setIdAdherent(int idAdherent) {
		this.idAdherent = idAdherent;
	}
	
	
	
	
	

}
