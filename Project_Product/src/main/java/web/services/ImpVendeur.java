package web.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import web.connection.ConnectionWithOracle;
import web.model.User;
import web.model.Vendeur;

public class ImpVendeur implements InterfaceVendeur {

	Connection connection = new ConnectionWithOracle().getConnection();

	InterfaceUser interfaceUser = new ImpUser();
	
	public ImpVendeur() {
	   
	}

	@Override
	public void addVendeur(Vendeur vendeur) {
		System.out.println("Test 2");
		Long id_user = interfaceUser.addUser(vendeur);
		System.out.println("Test 3");
		System.out.println("id_user" + id_user);
		String requete = "insert into vendeur(type_user, id_user) values(?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setString(1, vendeur.getTypeUser());
			preparedStatement.setLong(2, id_user);

			preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeVendeur(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Vendeur> getListVendeur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vendeur getVendeur(Long id) {
		Vendeur user = new Vendeur();
		String requete = "select * from table_user where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				user.setId(res.getLong("id"));
				user.setFirstName(res.getString("firstName"));
				user.setLastName(res.getString("lastName"));
				user.setSexe(res.getString("sexe"));
				user.setPhone(res.getString("phone"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setTypeUser("Vendeur");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}
