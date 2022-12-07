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
import web.model.Product;
import web.model.Vendeur;

public class ImpEnchere implements InterfaceEnchere {

	Connection connection = new ConnectionWithOracle().getConnection();
	
	public ImpEnchere() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEnchere(Enchere enchere) {
		String requete = "insert into enchere(ID_PRODUCT, ID_VENDEUR, DATEDEBUT, STATE) values(?,?,to_date(?,'YYYY/MM/DD'),?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, enchere.getProduct().getId());
			preparedStatement.setLong(2, enchere.getVendeur().getId());
			preparedStatement.setString(3,  enchere.getDateDebut()+"");
			//preparedStatement.setString(4,  enchere.getDateFin()+"");
			preparedStatement.setString(4, enchere.getEtat());
			preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeEnchere(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnchere(Enchere enchere) {
     
	String requete = "update enchere set PROPRIETAIRE = ? where ID_ENCHERE = ?";
		
	try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
		preparedStatement.setString(1, enchere.getProprietaire());
		preparedStatement.setLong(2, enchere.getId());
		preparedStatement.executeQuery();
		//ServiceInterface.interfaceEnchere.updateEnchere(enchere);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
}
		
	}

	@Override
	public Collection<Enchere> getListEnchereByIdVendeur(Long id_vendeur) {
		Collection<Enchere> encheres = new ArrayList<Enchere>();
		String requete = "select * from enchere where ID_VENDEUR = ? and STATE = 'started'";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id_vendeur);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Enchere enchere = new Enchere();
				Product product = ServiceInterface.interfaceProduct.getProduct(res.getLong("ID_PRODUCT"));
				Vendeur vendeur = ServiceInterface.interfaceVendeur.getVendeur(res.getLong("ID_VENDEUR"));
				enchere.setId(res.getLong("ID_ENCHERE"));
				enchere.setProduct(product);
				enchere.setVendeur(vendeur);
				enchere.setEtat(res.getString("STATE"));
				enchere.setDateDebut(LocalDate.parse(res.getDate("DATEDEBUT")+""));
				enchere.setProprietaire(res.getString("PROPRIETAIRE") == null?"X":res.getString("PROPRIETAIRE"));
				//enchere.setDateFin(LocalDate.parse(res.getDate("DATEFIN")+""));
				encheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return encheres;
	}

	@Override
	public Enchere getEnchere(Long id) {
		Enchere enchere = new Enchere();
		String requete = "select * from Enchere where ID_ENCHERE = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			preparedStatement.setLong(1, id);
			ResultSet res = preparedStatement.executeQuery();
			while(res.next()) {
				Product product = ServiceInterface.interfaceProduct.getProduct(res.getLong("ID_PRODUCT"));
				Vendeur vendeur = ServiceInterface.interfaceVendeur.getVendeur(res.getLong("ID_VENDEUR"));
				enchere.setId(res.getLong("ID_ENCHERE"));
				enchere.setProduct(product);
				enchere.setVendeur(vendeur);
				enchere.setEtat(res.getString("STATE"));
				enchere.setDateDebut(LocalDate.parse(res.getDate("DATEDEBUT")+""));
				enchere.setProprietaire(res.getString("PROPRIETAIRE") == null?"X":res.getString("PROPRIETAIRE"));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public Collection<Enchere> getAllEnchere() {
		Collection<Enchere> encheres = new ArrayList<Enchere>();
		String requete = "select * from enchere where STATE = 'started'";
		try (PreparedStatement preparedStatement = connection.prepareStatement(requete);) {
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Enchere enchere = new Enchere();
				Product product = ServiceInterface.interfaceProduct.getProduct(res.getLong("ID_PRODUCT"));
				Vendeur vendeur = ServiceInterface.interfaceVendeur.getVendeur(res.getLong("ID_VENDEUR"));
				enchere.setId(res.getLong("ID_ENCHERE"));
				enchere.setProduct(product);
				enchere.setVendeur(vendeur);
				enchere.setEtat(res.getString("STATE"));
				enchere.setDateDebut(LocalDate.parse(res.getDate("DATEDEBUT")+""));
				enchere.setProprietaire(res.getString("PROPRIETAIRE") == null?"X":res.getString("PROPRIETAIRE"));
				//enchere.setDateFin(LocalDate.parse(res.getDate("DATEFIN")+""));
				encheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return encheres;
	}

}
