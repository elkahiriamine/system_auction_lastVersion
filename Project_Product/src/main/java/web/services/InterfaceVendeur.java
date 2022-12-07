package web.services;

import java.util.Collection;

import web.model.Vendeur;

public interface InterfaceVendeur {
	
        public void addVendeur(Vendeur vendeur);
        public void removeVendeur(Long id);
        Collection<Vendeur> getListVendeur();
        Vendeur getVendeur(Long id);

}
