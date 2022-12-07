package web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RepaintManager;

import web.model.Acheteur;
import web.model.Enchere;
import web.model.Etat;
import web.model.Inscription;
import web.model.Product;
import web.model.Vendeur;
import web.services.ImpProduct;
import web.services.InterfaceProduct;
import web.services.ServiceInterface;
import web.services.TEST;

@WebServlet(name = "jar", urlPatterns = { "/login", "*.jee" })

public class WebServlet5 extends HttpServlet {

	private ServiceInterface serviceInterface;
	private static HttpSession sessionVendor;
	private HttpSession sessionAcheteur;
	
	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);

		if (path.equals("/login"))
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		else if (path.equals("/LoginAdmin.jee")) {
			req.getRequestDispatcher("LoginAdmin.jsp").forward(req, resp);
		} 
		else if (path.equals("/LoginVendeur.jee")) {
			req.getRequestDispatcher("LoginVendeur.jsp").forward(req, resp);
		} 
		else if (path.equals("/LoginAcheteur.jee")) {
			req.getRequestDispatcher("LoginAcheteur.jsp").forward(req, resp);
		} 
		else if (path.equals("/index.jee")) {
			req.getRequestDispatcher("accueil.jsp").forward(req, resp);
		} 
		else if (path.equals("/listProducts.jee")) {
			Collection<Product> products = serviceInterface.interfaceProduct.getListProduct();
			products.forEach(product -> {
				System.out.println(product);
			});
			req.setAttribute("listProducts", products.toArray());
			req.getRequestDispatcher("listProducts.jsp").forward(req, resp);
		} 
		else if (path.equals("/ajouteProduct.jee")) {
			req.getRequestDispatcher("formulaire.jsp").forward(req, resp);
		}
		else if(path.equals("/ajouteEnchere.jee")) {
			req.getRequestDispatcher("formulaireEnchere.jsp").forward(req, resp);
		}
		else if (path.equals("/listEncheres.jee")) {
			Long id_user = (Long) sessionVendor.getAttribute("id_user");
			Collection<Enchere> encheres = serviceInterface.interfaceEnchere.getListEnchereByIdVendeur(id_user);
			encheres.forEach(enchere -> {
				System.out.println(enchere);
			});
			req.setAttribute("listEncheres", encheres.toArray());
			req.getRequestDispatcher("listEncheres.jsp").forward(req, resp);
		} 
		else if (path.equals("/listEncheresAll.jee")) {
			Collection<Enchere> encheres = serviceInterface.interfaceEnchere.getAllEnchere();
			System.out.println("**********");
			encheres.forEach(enchere -> {
				System.out.println(enchere);
			});
			System.out.println("**********");
			req.setAttribute("listEncheres", encheres.toArray());
			req.getRequestDispatcher("listEncheresAll.jsp").forward(req, resp);
		}
		else if(path.equals("/inscrit.jee")) {
			Long id_enchere = Long.parseLong(req.getParameter("codeEnchere"));
			Long id_user = (Long) sessionAcheteur.getAttribute("id_user");
			Acheteur acheteur = serviceInterface.interfaceAcheteur.getAcheteurByIdUser(id_user);
			Inscription inscription = new Inscription();
			inscription.setId_enchere(id_enchere);
			inscription.setId_user(acheteur.getId());
			System.out.println(inscription);
			System.out.println(serviceInterface.interfaceInscription.verificationScription(inscription));
			if(serviceInterface.interfaceInscription.verificationScription(inscription)) {
			serviceInterface.interfaceInscription.addInscription(inscription);}
			
			resp.sendRedirect("/Project_Product/listDesInscription.jee");
		}
		else if(path.equals("/listDesInscription.jee")) {
			Long id_user = (Long) sessionAcheteur.getAttribute("id_user");
			Acheteur acheteur = serviceInterface.interfaceAcheteur.getAcheteurByIdUser(id_user);
			Collection<Enchere> encheres = serviceInterface.interfaceInscription.getListEnchereByInscription(acheteur.getId());
			encheres.forEach(enchere -> {
				System.out.println(enchere);
			});
			System.out.println("Test ins");
			req.setAttribute("listEncheres", encheres.toArray());
			req.getRequestDispatcher("ListInscription.jsp").forward(req, resp);
		}
		else if(path.equals("/candidature.jee")) {
			Long id_enchere = Long.parseLong(req.getParameter("codeEnchere"));
			Enchere enchere = serviceInterface.interfaceEnchere.getEnchere(id_enchere);
			//Product product = enchere.getProduct();
			req.setAttribute("enchere", enchere);
			req.getRequestDispatcher("formulaire.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println("Actuel : " + path);

		if (path.equals("/verificationLogin.jee")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String typeUser = req.getParameter("type_user");
			System.out.println("type user : " + typeUser);

			if (typeUser.equals("vendeur")) {

				if (serviceInterface.interfaceUser.verificationLogin(email, password)) {
					System.out.println("Connecter");
				    sessionVendor = req.getSession();
					sessionVendor.setAttribute("id_user", serviceInterface.interfaceUser.getUserByEmail(email).getId());
					System.out.println("Session :" + sessionVendor.getAttribute("id_user"));
					req.getRequestDispatcher("accueilVendeur.jsp").forward(req, resp);
				} else {
					System.out.println("Non Connecter");
				}
			}
			
			if (typeUser.equals("acheteur")) {

				if (serviceInterface.interfaceUser.verificationLogin(email, password)) {
					System.out.println("Connecter");
				    sessionAcheteur = req.getSession();
					sessionAcheteur.setAttribute("id_user", serviceInterface.interfaceUser.getUserByEmail(email).getId());
					System.out.println("Session :" + sessionAcheteur.getAttribute("id_user"));
					req.getRequestDispatcher("accueilAcheteur.jsp").forward(req, resp);
				} else {
					System.out.println("Non Connecter");
				}
			}

		} else if (path.equals("/updateProduct.jee")) {
			Product product = new Product();
			Long id_enchere = Long.parseLong(req.getParameter("CodeEnchere"));
			Enchere enchere = serviceInterface.interfaceEnchere.getEnchere(id_enchere);
			product.setId(Long.parseLong(req.getParameter("idProduct")));
			product.setCode(Long.parseLong(req.getParameter("CodeVendeur")));
			product.setName(req.getParameter("name"));
			product.setPrix(Double.parseDouble(req.getParameter("prix")));
			enchere.setProduct(product);
			
			Long id_user = (Long) sessionAcheteur.getAttribute("id_user");
			Acheteur acheteur = serviceInterface.interfaceAcheteur.getAcheteurByIdUser(id_user);
			System.out.println(acheteur.getId());
			String email = serviceInterface.interfaceAcheteur.getEmailByAcheteur(acheteur.getId());
			enchere.setProprietaire(email);
			
			serviceInterface.interfaceProduct.updateProduct(enchere);
			resp.sendRedirect("/Project_Product/listEncheresAll.jee");
		}
		else if(path.equals("/ajouteEnchere.jee")) {
			// declaration
			Enchere enchere = new Enchere();
			Product product = new Product();
			Long id_user = (Long) sessionVendor.getAttribute("id_user");
			
			// recuperation des données 
			product.setName(req.getParameter("name"));
			product.setPrix(Double.parseDouble(req.getParameter("prix")));
			product.setCode(id_user);
			
			// ajouter produit dans la base de données 
			serviceInterface.interfaceProduct.addProduct(product);
			
			product = serviceInterface.interfaceProduct.getProductByNatureCode(id_user);
			enchere.setProduct(product);
			
			System.out.println(product);
			
			// nous allons ajouter vendeur dans enchere
			enchere.setVendeur(serviceInterface.interfaceVendeur.getVendeur(id_user));
			System.out.println(req.getParameter("date_debut"));
			//DateTimeFormatter form = DateTimeFormatter.ofPattern("DD/MM/YYYY");
			
			//enchere.setDateDebut(form.format(req.getParameter("date_debut")));
			// recuperation de la date
			
			LocalDate date1 = LocalDate.parse(req.getParameter("date_debut"));
			enchere.setDateDebut(date1);
			
			//LocalDate date2 = LocalDate.parse(req.getParameter("date_fin"));
			//enchere.setDateFin(date2);
			
			// recuperation d'etat d'enchere
			enchere.setEtat(req.getParameter("state"));
			
			System.out.println(enchere);
			serviceInterface.interfaceEnchere.addEnchere(enchere);
			resp.sendRedirect("/Project_Product/listEncheres.jee");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
