package model;

public abstract class Article {
	protected Integer noArticle;
	protected Integer nbDisques;
	protected  String type;
	protected Integer idAdherent;
	protected Film film;
	
	// *** Constructeurs ****
	public Article( Integer nbDisques, String type, Film film) {
		super();
		this.nbDisques = nbDisques;
		this.type = type;
		this.film=film;
	}
	
	
	public Article(Integer nbDisques, String type, Integer idAdherent, Film film) {
		super();
		this.nbDisques = nbDisques;
		this.type = type;
		this.idAdherent=idAdherent;
		this.film=film;
	}


	// *** Methodes ***
	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public Integer getNbDisques() {
		return nbDisques;
	}

	public void setNbDisques(Integer nbDisques) {
		this.nbDisques = nbDisques;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIdAdherent() {
		return idAdherent;
	}

	public void setIdAdherent(Integer idAdherent) {
		this.idAdherent = idAdherent;
	}

	
	public  Film getFilm() {
		return film;
	}

	public  void setFilm(Film film) {
		this.film = film;
	}

	

}//fermeture
