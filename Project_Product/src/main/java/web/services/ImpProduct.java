package web.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import web.connection.ConnectionWithOracle;
import web.model.Enchere;
import web.model.Product;

public class ImpProduct implements InterfaceProduct {

	Connection connection = new ConnectionWithOracle().getConnection();

	public ImpProduct() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addProduct(Product product) {
		System.out.println("Creation connection to data base");
		String requete = "insert into product(name, prix,CODE_PRODUCT) values(?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrix());
			preparedStatement.setLong(3, product.getCode());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeProduct(Long id) {
		String requete = "delete from product where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Product> getListProduct() {
		Collection<Product> products = new ArrayList<Product>();
		String requete = "select * from product";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Product product = new Product();
				product.setId(res.getLong("id"));
				product.setName(res.getString("name"));
				product.setPrix(res.getDouble("prix"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public Product getProduct(Long id) {
		Product product = new Product();
		String requete = "select * from product where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				product.setId(res.getLong("id"));
				product.setName(res.getString("name"));
				product.setPrix(res.getDouble("prix"));
				product.setCode(res.getLong("CODE_PRODUCT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product getProductByNatureCode(Long id_code) {
		Product product = new Product();
		String requete = "select * from product where CODE_PRODUCT = ? and id = (select max(id)  from product where CODE_PRODUCT = ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id_code);
			preparedStatement.setLong(2, id_code);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				product.setId(res.getLong("id"));
				product.setName(res.getString("name"));
				product.setPrix(res.getDouble("prix"));
				product.setCode(res.getLong("CODE_PRODUCT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void updateProduct(Enchere enchere) {
		Product oldProduct = ServiceInterface.interfaceProduct.getProduct(enchere.getProduct().getId());
		Product product = enchere.getProduct();
		if(product.getPrix() > oldProduct.getPrix()) {
			System.out.println("Creation connection to data base");
			String requete = "update product set prix = ? where id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
				preparedStatement.setDouble(1, product.getPrix());
				preparedStatement.setLong(2, product.getId());
				preparedStatement.executeQuery();
				ServiceInterface.interfaceEnchere.updateEnchere(enchere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		
	}
		
	}

}
