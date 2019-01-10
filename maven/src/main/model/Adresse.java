package model;

public class Adresse {
	
	private int numero;
	private String rue;
	private String ville;
	private String codePostal;
	private int idAdresse;
	
	
	public Adresse(int numero, String rue, String ville, String codePostal) {
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	public Adresse() {
		
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public int getIdAdresse() {
		return idAdresse;
	}


	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	
	
	
	
	
	

}
