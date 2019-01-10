package model;

public class BluRay extends Article {

	private boolean troisD;

	public boolean isTroisD() {
		return troisD;
	}

	public void setTroisD(boolean troisD) {
		this.troisD = troisD;
	}

	public BluRay(Integer nbDisques, String type, Film film) {
		super(nbDisques, type, film);
		this.troisD=false;
	}

	public BluRay(Integer nbDisques, String type, Integer idAdherent, Film film) {
		super(nbDisques, type, idAdherent, film);
		this.troisD=false;
	}

	public BluRay(Integer nbDisques, String type, Film film, boolean troisD) {
		super(nbDisques, type, film);
		this.troisD = troisD;
	}

	

}
