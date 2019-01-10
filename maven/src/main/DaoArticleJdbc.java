package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Article;
import model.BluRay;
import model.DVD;
import model.Film;
import util.Context;

public class DaoArticleJdbc implements DaoArticle {
	
	DaoFilm d = DaoFilmFactory.getInstance();
	
	public List<Article> findAll() {
		List<Article> articles = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		Article art = null;
		Film film = null;
		try {
			st = Context.getInstance().getConnection().createStatement();
			rs = st.executeQuery("select * from article");
				while (rs.next()) {
					if(rs.getString("type").equals("DVD")) {
						//instanciation d'un article DVD
						art = new DVD(0, "DVD", film, rs.getBoolean("bonus"));
						//récupération de toutes les infos à partir du super constructeur
						art.setNoArticle(rs.getInt("noarticle"));
						art.setNbDisques(rs.getInt("nbdisques"));
						art.setType(rs.getString("type"));
						art.setIdAdherent(rs.getInt("id_adherent"));
//						art.getFilm().setId(rs.getInt("film_id"));
						//ajout des infos dans la liste "articles"
						articles.add(art);
						}
					else {
						//instanciation d'un article Blyray
						art = new BluRay(0, "BluRay", film, rs.getBoolean("troisd"));
						//récupération de toutes les infos à partir du super constructeur
						art.setNoArticle(rs.getInt("noarticle"));
						art.setNbDisques(rs.getInt("nbdisques"));
						art.setType(rs.getString("type"));
						art.setIdAdherent(rs.getInt("id_adherent"));
//						art.getFilm().setId(rs.getInt("film_id"));
						//ajout des infos dans la liste "articles"
						articles.add(art);
						}
					}
			}	
		catch (SQLException e) {
			e.printStackTrace();
			}
		return articles;
	}
	
	
	public Article findByKey(Integer key) {
		Article art = null;
		Film film = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = Context.getInstance().getConnection().prepareStatement("select a.noarticle, a.nbdisques,a.type,a.bonus,a.troisd,a.id_adherent from article a left join adherent ad on a.id_adherent=ad.noadherent where a.noarticle=?");
			ps.setInt(1, key);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("type").equals("DVD")) {
					//instanciation d'un article DVD
					art = new DVD( null, "DVD", film, rs.getBoolean("bonus"));
					//récupération de toutes les infos à partir du super constructeur
					art.setNoArticle(rs.getInt("noarticle"));
					art.setNbDisques(rs.getInt("nbdisques"));
					art.setType(rs.getString("type"));
					art.setIdAdherent(rs.getInt("id_adherent"));
//					art.getFilm().setId(rs.getInt("film_id"));
					}
				else {
					//instanciation d'un article Blyray
					art = new BluRay(null, "DVD", film, rs.getBoolean("bonus"));
					//récupération de toutes les infos à partir du super constructeur
					art.setNoArticle(rs.getInt("noarticle"));
					art.setNbDisques(rs.getInt("nbdisques"));
					art.setType(rs.getString("type"));
					art.setIdAdherent(rs.getInt("id_adherent"));
//					art.getFilm().setId(rs.getInt("film_id"));
					}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
			return art;		
		}

	
	public void insert(Article obj) {
		PreparedStatement ps = null;
		
		try {
			if (d.findByFilm(obj.getFilm())==null) {
				ps = Context.getInstance().getConnection().prepareStatement(
					"insert into article (noarticle,nbdisques,type,bonus,troisd,id_adherent,film_id) values(nextval('seq_article'),?,?,?,?,?,nextval('seq_film'))", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,obj.getNbDisques());
			ps.setString(2, obj.getType());
			
			if (obj instanceof DVD) {
				ps.setBoolean(3,((DVD)obj).isBonus());
				ps.setBoolean(4, false);
				}
			if (obj instanceof BluRay) {
				ps.setBoolean(4, ((BluRay)obj).isTroisD());
				ps.setBoolean(3, false);	
				}
			ps.setInt(5, obj.getIdAdherent());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setNoArticle(rs.getInt(1));
				obj.getFilm().setId(rs.getInt(7));;
				}
			d.insert(obj.getFilm());
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			}
		}
	
	
	public void update(Article art) {
		PreparedStatement ps = null;
		try {
			ps = Context.getInstance().getConnection().prepareStatement("update article set nbdisques=?,type=?,bonus=?,troisd=?,id_adherent=?,film_id=? where noarticle=?");
			ps.setInt(1, art.getNbDisques());
			ps.setString(2, art.getType());
			if (art instanceof DVD) {
				ps.setBoolean(3,((DVD)art).isBonus());
				ps.setBoolean(4, false);
				}
			if (art instanceof BluRay) {
				ps.setBoolean(4, ((BluRay)art).isTroisD());
				ps.setBoolean(3, false);	
				}
			ps.setInt(5, art.getIdAdherent());
			ps.setInt(6, art.getFilm().getId());
			ps.setInt(7, art.getNoArticle());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Article obj) {
		deleteByKey(obj.getNoArticle());
	}
	
	
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps = Context.getInstance().getConnection().prepareStatement("delete from article where noarticle=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	public List<Article> findByAdherent(Adherent adherent){//verifier nom variable
//		List<Article> articles = new ArrayList<>();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		Article art = null;
//		try {
//			st = Context.getInstance().getConnection().prepareStatement("select a.noarticle,a.nbdisques,a.type,a.bonus,a.troisd,a.film_id, ad.noadherent from article a join adherent ad on a.id_adherent=ad.noadherent where a.id_adherent=?");
//			st.setString(1, adherent.getNoAdherent());
//			rs = st.executeQuery();
//				while (rs.next()) {
//					articles.add(art);
//				}
//			}
//		catch (SQLException e) {
//			e.printStackTrace();
//			}
//	return articles;
//	}

//	@Override
//	public List<Article> findAllByAdherent(Adherent emprunteur) {
//		List<Article> articles = new ArrayList<>();
//		Statement st = null;
//		ResultSet rs = null;
//		Article art = null;
//		DaoAdherent daoAdherent = DaoAdherentFactory.getInstance(); // nécessite code Thomas
//		try {
//			st = Context.getInstance().getConnection().createStatement();
//			rs = st.executeQuery("select * from article");
//			while (rs.next()){
//				if(rs.getString("type").equals("DVD")) {
//					if(rs.getString("type").equals("DVD")) {
//						//instanciation d'un article DVD
//						art = new DVD(rs.getBoolean("bonus"));
//						//récupération de toutes les infos à partir du super constructeur
//						art.setNoArticle(rs.getInt("noarticle"));
//						art.setNbDisques(rs.getInt("nbdisques"));
//						art.setType(rs.getString("type"));
//						art.setIdAdherent(rs.getInt("idadherent"));
//						art.getFilm().getId(rs.getInt("film_id"));
//						//ajout des infos dans la liste "articles"
//						articles.add(art);
//						}
//					else {
//						//instanciation d'un article Blyray
//						art = new BluRay(rs.getBoolean("troisd"));
//						//récupération de toutes les infos à partir du super constructeur
//						art.setNoArticle(rs.getInt("noarticle"));
//						art.setNbDisques(rs.getInt("nbdisques"));
//						art.setType(rs.getString("type"));
//						art.setIdAdherent(rs.getInt("idadherent"));
//						art.getFilm().getId(rs.getInt("film_id"));
//						//ajout des infos dans la liste "articles"
//						articles.add(art);
//						}
//					}
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//			}
//		return articles;
//	}


		
	
	
	
}//fermeture interface
