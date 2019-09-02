package com.sistemes.view;

import com.sistemes.Application;
import com.sistemes.codi.UsersCodi;
import com.sistemes.models.UsersModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registrationView")
@RequestScoped
public class RegistrationView {

	private UsersModel user = null;
	
	public RegistrationView(){
		super();
		user = new UsersModel();
	}
	
	
	public void userRegistration(){
		UsersCodi negoci = new UsersCodi(Application.getConnexio());
		negoci.insert(user);
		try {
			Application.getConnexio().getConnection().commit();
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}


	public UsersModel getUser() {
		return user;
	}


	public void setUser(UsersModel user) {
		this.user = user;
	}
	
	

}
