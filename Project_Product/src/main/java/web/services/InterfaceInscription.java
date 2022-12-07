package web.services;

import java.util.Collection;

import web.model.Enchere;
import web.model.Inscription;

public interface InterfaceInscription {

	void addInscription(Inscription inscription);
	Collection<Enchere> getListEnchereByInscription(Long id_acheteur);
	boolean verificationScription(Inscription inscription);
	Inscription getInscription(Inscription inscription); 
}
