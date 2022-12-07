package web.services;

import java.util.Collection;

import web.model.Acheteur;

public interface InterfaceAcheteur {
      public void addAcheteur(Acheteur acheteur);
      public void removeAcheteur(Long id);
      Collection<Acheteur> getListAcheteur();
      Acheteur getAcheteur(Long id);
      Acheteur getAcheteurByIdUser(Long id);
      String getEmailByAcheteur(Long id_acheteur);
}
