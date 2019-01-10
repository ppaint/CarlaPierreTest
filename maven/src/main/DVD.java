package model;

public class DVD extends Article {
	private boolean bonus;

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}


	public DVD(Integer nbDisques, String type, Film film, boolean bonus) {
		super(nbDisques, type, film);
		this.bonus = bonus;
	}
	
	public DVD(Integer nbDisques, String type, Film film) {
		super(nbDisques, type, film);
		this.bonus = false;
	}

	public DVD(Integer nbDisques, String type, Integer idAdherent, Film film, boolean bonus ) {
		super(nbDisques, type, idAdherent, film);
		this.bonus= bonus;
	}
	
	public DVD(Integer nbDisques, String type, Integer idAdherent, Film film ) {
		super(nbDisques, type, idAdherent, film);
		this.bonus= false;
	}
	

	
	
	
	
}
