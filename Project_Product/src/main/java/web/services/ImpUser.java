package web.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import web.connection.ConnectionWithOracle;
import web.model.Product;
import web.model.User;
import web.model.Vendeur;

public class ImpUser implements InterfaceUser {

	Connection connection = new ConnectionWithOracle().getConnection();

	public ImpUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long addUser(User user) {

		Long id_user = null;
		String requete = "insert into table_user(firstName, lastName, sexe, phone, email, password) values(?,?,?,?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			System.out.println("Test 4");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getSexe());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.executeQuery();
			System.out.println("Test 5");
			requete = "select max(id) as id from table_user";
			try (PreparedStatement preparedStatement2 = connection.prepareStatement(requete);) {
				ResultSet res;
				res = preparedStatement2.executeQuery();
				res.next();
				id_user = res.getLong("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id_user;
	}

	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<User> getListUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Long id) {
		User user = new User();
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
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public boolean verificationLogin(String email, String password) {
		User user = this.getUserByEmail(email);
        System.out.println(user);
		if(user.getEmail() == null || user.getPassword() == null)
			return false;
		else if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return true;
		}

		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = new User();
		String requete = "select * from table_user where email = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setString(1, email);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				user.setId(res.getLong("id"));
				user.setFirstName(res.getString("firstName"));
				user.setLastName(res.getString("lastName"));
				user.setSexe(res.getString("sexe"));
				user.setPhone(res.getString("phone"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}

// @Override
/*
 * public void removeUser(Long id) { String requete =
 * "delete from table_user where id = ?"; try (PreparedStatement
 * preparedStatement = connection.prepareStatement(requete);) {
 * preparedStatement.setLong(1, id); preparedStatement.executeQuery(); //
 * connection.setAutoCommit(true); } catch (SQLException e) {
 * e.printStackTrace(); } }
 * 
 * @Override public Collection<User> getListUser() { Collection<User> users =
 * new ArrayList<User>(); String requete =
 * "select u.*,r.name from table_user u, role r, user_role ur where ur.id_user = u.id and ur.id_role = r.id"
 * ; try (PreparedStatement preparedStatement =
 * connection.prepareStatement(requete);) { ResultSet res =
 * preparedStatement.executeQuery(); while (res.next()) { User user = new
 * User(); user.setId(res.getLong("id"));
 * user.setFirstName(res.getString("firstName"));
 * user.setLastName(res.getString("lastName"));
 * user.setSexe(res.getString("sexe")); user.setPhone(res.getString("phone"));
 * user.setEmail(res.getString("email"));
 * user.setPassword(res.getString("password")); users.add(user);
 * 
 * } } catch (SQLException e) { e.printStackTrace(); }
 * 
 * netoyageCollection((List<User>) users); return users; }
 * 
 * @Override public User getUser(Long id) { Collection<User> users = new
 * ArrayList<User>(); String requete =
 * "select u.*,r.name from table_user u, role r, user_role ur where ur.id_user = u.id and ur.id_role = r.id and u.id = ?"
 * ; try (PreparedStatement preparedStatement =
 * connection.prepareStatement(requete);) { preparedStatement.setLong(1, id);
 * ResultSet res = preparedStatement.executeQuery(); while (res.next()) { User
 * user = new User(); user.setId(res.getLong("id"));
 * user.setFirstName(res.getString("firstName"));
 * user.setLastName(res.getString("lastName"));
 * user.setSexe(res.getString("sexe")); user.setPhone(res.getString("phone"));
 * user.setEmail(res.getString("email"));
 * user.setPassword(res.getString("password")); user.setTypeUser(new String[] {
 * res.getString("name") }); users.add(user); }
 * 
 * } catch (SQLException e) { e.printStackTrace(); }
 * 
 * return netoyageUser((List<User>) users); }
 * 
 * private void netoyageCollection(List<User> users) { for (int i = 0; i <
 * users.size(); i++) { for (int j = i + 1; j < users.size(); j++) { if
 * (users.get(i).getId() == users.get(j).getId()) {
 * (users.get(i)).setTypeUser(new String[] { "Vendeur", "Acheteur" });
 * users.remove(j); } } } }
 * 
 * private User netoyageUser(List<User> users) { if(users.size()>1) { if
 * (users.get(0).getId() == users.get(1).getId()) {
 * (users.get(0)).setTypeUser(new String[] { "Vendeur", "Acheteur" });
 * users.remove(1); } } return users.get(0); }
 * 
 * }
 */

/*
 * user = new User(res.getLong("ID"), res.getString("FIRSTNAME"),
 * res.getString("LASTNAME"), res.getString("SEXE"), res.getString("PHONE"),
 * res.getString("EMAIL"), res.getString("PASSWORD"));
 */
