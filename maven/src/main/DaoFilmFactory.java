package dao;

public class DaoFilmFactory {
	
	private static DaoFilm singleton = null;

	public static DaoFilm getInstance() {
		if (singleton == null) {
			singleton = new DaoFilmJdbcImpl();
		}
		return singleton;
	}
}
