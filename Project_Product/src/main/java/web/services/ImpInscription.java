package web.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import web.connection.ConnectionWithOracle;
import web.model.Enchere;
import web.model.Inscription;
import web.model.Product;
import web.model.User;
import web.model.Vendeur;

public class ImpInscription implements InterfaceInscription {

	Connection connection = new ConnectionWithOracle().getConnection();
	
	public ImpInscription() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addInscription(Inscription inscription) {
		System.out.println("Creation connection to data base");
		String requete = "insert into INSCRIT_ENCHERE(ID_ACHETEUR, ID_ENCHERE) values(?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, inscription.getId_user());
			preparedStatement.setLong(2, inscription.getId_enchere());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Enchere> getListEnchereByInscription(Long id_acheteur) {
		Collection<Enchere> encheres = new ArrayList<Enchere>();
		String requete = "select e.* from enchere e, INSCRIT_ENCHERE i, acheteur ach "
				+ "where e.ID_ENCHERE = i.ID_ENCHERE and ach.ID_ACHETEUR = i.ID_ACHETEUR and ach.ID_ACHETEUR = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id_acheteur);
			System.out.println("Test 1");
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Enchere enchere = new Enchere();
				Product product = ServiceInterface.interfaceProduct.getProduct(res.getLong("ID_PRODUCT"));
				Vendeur vendeur = ServiceInterface.interfaceVendeur.getVendeur(res.getLong("ID_VENDEUR"));
				enchere.setId(res.getLong("ID_ENCHERE"));
				enchere.setProduct(product);
				enchere.setVendeur(vendeur);
				System.out.println("Test 2");
				enchere.setEtat(res.getString("STATE"));
				enchere.setDateDebut(LocalDate.parse(res.getDate("DATEDEBUT")+""));
				//enchere.setDateFin(LocalDate.parse(res.getDate("DATEFIN")+""));
				encheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Test 3");

		return encheres;
	}

	@Override
	public boolean verificationScription(Inscription inscription) {
		Inscription inscription1 = this.getInscription(inscription);
		System.out.println("verificationScription");
		System.out.println(inscription);
        System.out.println(inscription1);
        System.out.println("**********************************");
		if(inscription1.getId_enchere() == null || inscription1.getId_user() == null) {
			return true;}
		else if (inscription1.getId_enchere().equals(inscription1.getId_enchere()) && inscription1.getId_user().equals(inscription.getId_user()) ) {
			System.out.println("Inscription : Test 2");
			return false;
		}

		return false;
	}

	@Override
	public Inscription getInscription(Inscription inscription) {
		Inscription inscription1 = new Inscription();
		String requete = "select * from INSCRIT_ENCHERE where ID_ACHETEUR=? and ID_ENCHERE=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, inscription.getId_user());
			preparedStatement.setLong(2, inscription.getId_enchere());
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				inscription1.setId_user(res.getLong("ID_ACHETEUR"));
				inscription1.setId_enchere(res.getLong("ID_ENCHERE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Test Final Inscription : ");
		System.out.println(inscription1);
		System.out.println("***********");
		return inscription1;
	}

	

}
