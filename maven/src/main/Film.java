package model;

import java.util.Date;
import java.util.List;

public class Film {
	private String titre;
	private Date dateSortie;
	private List<Realisateur> realisateurs;
	private int id;

	public Film(String titre, Date dateSortie, List<Realisateur> realisateurs) {
		super();
		this.titre = titre;
		this.dateSortie = dateSortie;
		this.realisateurs = realisateurs;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public List<Realisateur> getRealisateur() {
		return realisateurs;
	}

	public void setRealisateur(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
