package web.services;

import java.util.Collection;

import web.model.Acheteur;
import web.model.Enchere;
import web.model.Etat;
import web.model.Inscription;
import web.model.Product;
import web.model.User;

public class TEST {

	private static ServiceInterface serviceInterface;
	
	public TEST() {
		ex13();
	}
	
	void ex01() {
		InterfaceUser interfaceUser = new ImpUser();
		/*
		 * System.out.println("add Product"); interfaceProduct.addProduct(new
		 * Product("pc",567)); Collection<Product> collection =
		 * interfaceProduct.getListProduct(); collection.forEach(product -> {
		 * System.out.println(product); });
		 */
		//interfaceUser.addUser(new Acheteur("Salim","El","Homme","0654323456","amine@gmail.com","123",new String[]{"Vendeur","Acheteur"}));
		//System.out.println(interfaceProduct.getProduct(3L));
		
		//interfaceUser.removeUser(3L);
		
		System.out.println("***********Collection**********");
		Collection<User> users = interfaceUser.getListUser();
		users.forEach(user->{
			System.out.println(user);
		});
		System.out.println("*************GetUser*************");
		System.out.println(interfaceUser.getUser(13L));
	}
	
	void ex02() {
		System.out.println("Test 1");
		serviceInterface.interfaceAcheteur.addAcheteur(new Acheteur("Salma", "Bass", "FEMME", "23456", "welcome@gmail.com", "11111","Acheteur"));
	}
	
	void ex03() {
		serviceInterface.interfaceAcheteur.removeAcheteur(1L);
	}
	
	public static Collection<Product> ex04() {
		Collection<Product> products = serviceInterface.interfaceProduct.getListProduct();
		products.forEach(product->{
			System.out.println(product);
		});
		return products;
	}
	
	public void ex05() {
		Enchere enchere = new Enchere();
		enchere.setEtat("started");
		System.out.println("State : "+enchere.getEtat());
	}
	
	public void ex06() {
	System.out.println(serviceInterface.interfaceProduct.getProductByNatureCode(1L));
	}
	
	public void ex07() {
		for(Enchere en : serviceInterface.interfaceEnchere.getListEnchereByIdVendeur(1L)) {
			System.out.println(en);
		}
	}
	public void ex08() {
		System.out.println(serviceInterface.interfaceAcheteur.getAcheteurByIdUser(5L));
	}
	public void ex09() {
		for(Enchere en : serviceInterface.interfaceInscription.getListEnchereByInscription(1L)) {
			System.out.println(en);
			System.out.println("Test 5");
		}
	}
	public void ex10() {
		System.out.println(serviceInterface.interfaceEnchere.getEnchere(1L));
	}
	public void ex11() {
		System.out.println(serviceInterface.interfaceAcheteur.getEmailByAcheteur(3L));
	}
	public void ex12() {
		System.out.println(serviceInterface.interfaceInscription.getInscription(new Inscription(1L,1L)));
	}
	public void ex13() {
		System.out.println(serviceInterface.interfaceInscription.verificationScription(new Inscription(4L,9L)));
	}
	public static void main(String[] args) {
		new TEST();
	}

}
