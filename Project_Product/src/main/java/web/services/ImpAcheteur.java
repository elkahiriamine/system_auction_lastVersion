package web.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import web.connection.ConnectionWithOracle;
import web.model.Acheteur;
import web.model.Vendeur;

public class ImpAcheteur implements InterfaceAcheteur {

	Connection connection = new ConnectionWithOracle().getConnection();

	InterfaceUser interfaceUser = new ImpUser();

	public ImpAcheteur() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAcheteur(Acheteur acheteur) {
		System.out.println("Test 2");
		Long id_user = interfaceUser.addUser(acheteur);
		System.out.println("Test 3");
		System.out.println("id_user" + id_user);
		String requete = "insert into acheteur(type_user, id_user) values(?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setString(1, acheteur.getTypeUser());
			preparedStatement.setLong(2, id_user);

			preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeAcheteur(Long id) {
		String requete = "delete from acheteur where id_acheteur = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Acheteur> getListAcheteur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acheteur getAcheteur(Long id) {
		Acheteur user = new Acheteur();
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
				user.setTypeUser("Acheteur");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Acheteur getAcheteurByIdUser(Long id) {
		Acheteur user = new Acheteur();
		String requete = "select u.*,a.* from acheteur a,table_user u where a.id_user = ? and u.id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			preparedStatement.setLong(2, id);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				user.setId(res.getLong("ID_ACHETEUR"));
				user.setFirstName(res.getString("firstName"));
				user.setLastName(res.getString("lastName"));
				user.setSexe(res.getString("sexe"));
				user.setPhone(res.getString("phone"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setTypeUser("Acheteur");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public String getEmailByAcheteur(Long id_acheteur) {
		String email = null;
		String requete = "select u.email from table_user u, acheteur a where u.id = a.ID_USER and ID_ACHETEUR = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id_acheteur);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
			     email = res.getString("email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
	

}
