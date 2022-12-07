package web.services;

public interface ServiceInterface {
	InterfaceUser interfaceUser = new ImpUser();
	InterfaceAcheteur interfaceAcheteur = new ImpAcheteur();
	InterfaceVendeur interfaceVendeur = new ImpVendeur();
	InterfaceProduct interfaceProduct = new ImpProduct();
	InterfaceEnchere interfaceEnchere = new ImpEnchere();
	InterfaceInscription interfaceInscription = new ImpInscription();
}
