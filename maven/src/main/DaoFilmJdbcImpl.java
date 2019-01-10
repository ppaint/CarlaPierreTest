package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import model.Film;
import model.Realisateur;
import util.Context;

public class DaoFilmJdbcImpl implements DaoFilm {

	@Override
	public List<Film> findAll() {
		List<Film> films = new ArrayList<>();
		List<Realisateur> realisateurs = new ArrayList<>();

		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null, rs2 = null;

		Film film = null;
		Realisateur realisateur = null;

		try {
			st = Context.getInstance().getConnection().createStatement();
			rs = st.executeQuery("select f.id_film, f.titre, f.datesortie, f.id_film from film f");
			while (rs.next()) {
				ps = Context.getInstance().getConnection().prepareStatement(
						"select r.nom, r.prenom from realisateur r join filmographie fg on r.id_realisateur = ?");
				ps.setInt(1, rs.getInt("id_film"));
				rs2 = ps.executeQuery();
				realisateurs = new ArrayList<>();
				while (rs2.next()) {
					realisateur = null;
					realisateur = new Realisateur(rs2.getString("nom"), rs2.getString("prenom"));
					realisateurs.add(realisateur);
				}

				film = new Film(rs.getString("titre"), (java.util.Date)rs.getDate("datesortie"), realisateurs);
				films.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public Film findByKey(Integer key) {
		List<Realisateur> realisateurs = new ArrayList<>();

		PreparedStatement ps, ps2 = null;
		ResultSet rs = null, rs2 = null;

		Film film = null;
		Realisateur realisateur = null;

		try {
			ps = Context.getInstance().getConnection()
					.prepareStatement("select f.id_film, f.titre, f.datesortie from film f where id_film = ?");
			ps.setInt(1, key);
			rs = ps.executeQuery();

			if (rs.next()) {
				ps2 = Context.getInstance().getConnection().prepareStatement(
						"select r.nom, r.prenom from realisateur r join filmographie fg on r.id_realisateur = ?");
				ps2.setInt(1, rs.getInt("id_film"));
				rs2 = ps2.executeQuery();
				realisateurs = new ArrayList<>();
				while (rs2.next()) {
					realisateur = null;
					realisateur = new Realisateur(rs2.getString("nom"), rs2.getString("prenom"));
					realisateurs.add(realisateur);
				}

				film = new Film(rs.getString("titre"), (java.util.Date)rs.getDate("datesortie"), realisateurs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	public Film findByFilm(Film key) {
		List<Realisateur> realisateurs = new ArrayList<>();

		PreparedStatement ps, ps2 = null;
		ResultSet rs = null, rs2 = null;
		Date datesortie = new java.sql.Date(0);
		datesortie.setTime(key.getDateSortie().getTime());

		Film film = null;
		Realisateur realisateur = null;

		try {
			ps = Context.getInstance().getConnection()
					.prepareStatement("select f.id_film, f.titre, f.datesortie from film f where titre = ? and datesortie = ?");
			ps.setString(1, key.getTitre());
			ps.setDate(2, datesortie);
			rs = ps.executeQuery();

			if (rs.next()) {
				ps2 = Context.getInstance().getConnection().prepareStatement(
						"select r.nom, r.prenom from realisateur r join filmographie fg on r.id_realisateur = ?");
				ps2.setInt(1, rs.getInt("id_film"));
				rs2 = ps2.executeQuery();
				realisateurs = new ArrayList<>();
				while (rs2.next()) {
					realisateur = null;
					realisateur = new Realisateur(rs2.getString("nom"), rs2.getString("prenom"));
					realisateurs.add(realisateur);
				}

				film = new Film(rs.getString("titre"), (java.util.Date)rs.getDate("datesortie"), realisateurs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
			return film;
		
	}

	@Override
	public void insert(Film obj) {
		PreparedStatement ps = null;
		Date datesortie = new java.sql.Date(0);
		datesortie.setTime(obj.getDateSortie().getTime());
		
		try {
				ps = Context.getInstance().getConnection().prepareStatement(
					"insert into film(titre,datesortie,id_film) values(?,?,?)");
				ps.setString(1, obj.getTitre());
				ps.setDate(2, datesortie);
				ps.setInt(3, obj.getId());
				ps.executeUpdate();
			
				ps = null;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		for(int i=0; i<obj.getRealisateur().size() ;i++) {
			try {
				ps = Context.getInstance().getConnection().prepareStatement(	
					"insert into filmographie(realisateur_id,film_id) values(?,?)"); 
				ps.setInt(1, obj.getRealisateur().get(i).getId());
				ps.setInt(2 ,obj.getId());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				ps= null;
				
				ps = Context.getInstance().getConnection().prepareStatement(	
						"insert into realisateur(nom,prenom,id_realisateur) values(?,?,?)"); 
				ps.setString(1 , obj.getRealisateur().get(i).getNom());
				ps.setString(2 , obj.getRealisateur().get(i).getPrenom());
				ps.setInt(3, obj.getRealisateur().get(i).getId());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void update(Film obj) {

	}

	@Override
	public void delete(Film obj) {

	}

	@Override
	public void deleteByKey(Integer key) {

	}

}
