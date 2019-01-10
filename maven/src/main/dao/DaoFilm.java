package dao;

import model.Film;

public interface DaoFilm extends DaoGeneric<Film, Integer> {
	public Film findByFilm(Film key);

	
}
