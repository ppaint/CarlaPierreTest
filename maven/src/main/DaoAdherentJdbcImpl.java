package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Adherent;
import model.Adresse;
import util.Context;

public class DaoAdherentJdbcImpl implements DaoAdherent {

	@Override
	public List<Adherent> findAll() {
		List<Adherent> adherents = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = Context.getInstance().getConnection().createStatement();
			rs = st.executeQuery("select adh.civilite, adh.prenom, adh.nom, adh.noadherent, adr.numero, adr.rue, adr.ville, adr.codepostal, adr.id_adresse from adherent adh join adresse adr on adh.noadherent=adr.id_adresse");
			while (rs.next()) {
				adherents.add(new Adherent(rs.getString("civilite"), rs.getString("prenom"), rs.getString("nom"),
					new Adresse(rs.getInt("numero"),rs.getString("rue"),rs.getString("ville"),rs.getString("codepostal"))));
			System.out.println(rs.getString("civilite")+" "+rs.getString("prenom")+" "+rs.getString("nom")+" "+rs.getInt("numero")+" "+rs.getString("rue")+" "+rs.getString("ville")+" "+rs.getString("codepostal")+" "+rs.getInt("id_adresse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return adherents;
	}

	@Override
	public Adherent findByKey(Integer key) {
		Adherent adherent = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Context.getInstance().getConnection().prepareStatement("select adh.civilite, adh.prenom, adh.nom, adh.noadherent, adr.numero, adr.rue, adr.ville, adr.codepostal, adr.id_adresse from adherent adh join adresse adr on adh.noadherent=adr.id_adresse where noadherent=?");
			ps.setInt(1, key);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				adherent = new Adherent(rs.getString("civilite"), rs.getString("prenom"), rs.getString("nom"),
						new Adresse(rs.getInt("numero"),rs.getString("rue"),rs.getString("ville"),rs.getString("codepostal")));
				
				System.out.println(rs.getString("civilite")+" "+rs.getString("prenom")+" "+rs.getString("nom")+" "+rs.getInt("numero")+" "+rs.getString("rue")+" "+rs.getString("ville")+" "+rs.getString("codepostal")+" "+rs.getInt("id_adresse"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return adherent;
	}
	
	
	public Adherent findByAdherent(Adherent key) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Context.getInstance().getConnection().prepareStatement("select adh.civilite, adh.prenom, adh.nom, adh.noadherent, adr.numero, adr.rue, adr.ville, adr.codepostal, adr.id_adresse from adherent adh join adresse adr on adh.noadherent=adr.id_adresse where adh.nom=? and adh.prenom = ?");
			ps.setString(1, key.getNom());
			ps.setString(2, key.getPrenom());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println(rs.getString("civilite")+" "+rs.getString("prenom")+" "+rs.getString("nom")+" "+rs.getInt("numero")+" "+rs.getString("rue")+" "+rs.getString("ville")+" "+rs.getString("codepostal")+" "+rs.getInt("id_adresse"));
				key.setIdAdherent(rs.getInt("noadherent"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	@Override
	public void insert(Adherent obj) {

		PreparedStatement ps = null;
		try {
			ps = Context.getInstance().getConnection().prepareStatement(
					"insert into adherent(noadherent,civilite,prenom,nom) values(nextval('seq_adherent'),?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getCivilite());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getNom());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setIdAdherent(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ps = null;
		try {
			ps = Context.getInstance().getConnection().prepareStatement(
					"insert into adresse(numero,rue,ville,codepostal, id_adresse) values(?,?,?,?,?)");
			ps.setInt(1, obj.getAdresse().getNumero());
			ps.setString(2, obj.getAdresse().getRue());
			ps.setString(3, obj.getAdresse().getVille());
			ps.setString(4, obj.getAdresse().getCodePostal());
			ps.setInt(5, obj.getIdAdherent());

			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Adherent obj) {

		PreparedStatement ps = null;
		
		try {
			ps = Context.getInstance().getConnection().prepareStatement(
					"update adherent set civilite=?, prenom=?, nom=? where noadherent=?");
			ps.setString(1, obj.getCivilite());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getNom());
			ps.setInt(4, obj.getIdAdherent());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ps = null;
		
		try {
			ps = Context.getInstance().getConnection().prepareStatement(
					"update adresse set numero=?, rue=?, ville=?, codepostal=? where id_adresse=?");
			ps.setInt(1, obj.getAdresse().getNumero());
			ps.setString(2, obj.getAdresse().getRue());
			ps.setString(3, obj.getAdresse().getVille());
			ps.setString(4, obj.getAdresse().getCodePostal());
			ps.setInt(5, obj.getAdresse().getIdAdresse());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Adherent obj) {
		deleteByKey(obj.getIdAdherent());
	}

	@Override
	public void deleteByKey(Integer key) {

		PreparedStatement ps = null;
		
		try {
			ps = Context.getInstance().getConnection().prepareStatement("delete from adherent where noadherent=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			
			ps = Context.getInstance().getConnection().prepareStatement("delete from adresse where id_adresse=?");
			ps.setInt(1, key);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
