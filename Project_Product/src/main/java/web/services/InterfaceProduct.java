package web.services;

import java.util.Collection;

import web.model.Enchere;
import web.model.Product;

public interface InterfaceProduct {
	
     void addProduct(Product product);
     void removeProduct(Long id);
     Collection<Product> getListProduct();
     Product getProduct(Long id);
     Product getProductByNatureCode(Long id_code);
     void updateProduct(Enchere enchere);
}
