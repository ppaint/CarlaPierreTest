package jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import dao.DaoAdherent;
//import dao.DaoAdherentFactory;
import dao.DaoArticle;
import dao.DaoArticleFactory;
import dao.DaoFilm;
import dao.DaoFilmFactory;
//import model.Adherent;
//import model.Adresse;
import model.Article;
import model.DVD;
import model.Film;
import model.Realisateur;

public class App {

	public static void main(String[] args) {
		// DaoAdherent daoAdherent = DaoAdherentFactory.getInstance();
		DaoFilm daoFilm = DaoFilmFactory.getInstance();
		DaoArticle daoArticle = DaoArticleFactory.getInstance();
		List<Film> films = new ArrayList<>();
		List<Realisateur> realisateurs = new ArrayList<>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, Calendar.DECEMBER, 18);
		
		//Adresse loc = new Adresse(1, "Rue Saint Paul", "DeadTown", "666");
		//Adherent Paul = new Adherent("M", "Paul", "More", loc);
		
		realisateurs.add(new Realisateur("Wan", "James"));
		
		Film f;
		f = new Film("Aquaman", calendar.getTime(), realisateurs);
		
		films.add(f);
		
		//daoAdherent.insert(Paul);
		Article a = new DVD(1, "DVD", 1, f, false);
		//daoArticle.insert(a);
		daoArticle.insert(a);
		System.out.println(daoFilm.findByFilm(f)==null);
		

		//System.out.println(daoFilm.findByKey(10));
		

}
}