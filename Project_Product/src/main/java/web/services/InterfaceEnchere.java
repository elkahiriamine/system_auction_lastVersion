package web.services;

import java.util.Collection;

import web.model.Enchere;

public interface InterfaceEnchere {
     void addEnchere(Enchere enchere);
     void removeEnchere(Long id);
     void updateEnchere(Enchere enchere);
     Enchere getEnchere(Long id);
     Collection<Enchere> getAllEnchere();
	 Collection<Enchere> getListEnchereByIdVendeur(Long id_vendeur);
}
