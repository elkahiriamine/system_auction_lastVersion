package web.services;

import java.util.Collection;

import web.model.User;

public interface InterfaceUser {
	
     Long addUser(User user);
     void removeUser(Long id);
     Collection<User> getListUser();
     User getUser(Long id);
     boolean verificationLogin(String email, String password);
     User getUserByEmail(String email);
}
