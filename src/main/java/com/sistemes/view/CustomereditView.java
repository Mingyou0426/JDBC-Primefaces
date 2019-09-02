package com.sistemes.view;

import com.sistemes.models.CustomersModel;
import com.sistemes.Application;
import com.sistemes.codi.CustomersCodi;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="customereditView")
@RequestScoped
public class CustomereditView {

	private CustomersModel customers = null;
	
	public CustomereditView(){
		super();
		customers = new CustomersModel();
	}
	
	
	public void customersSave(){
		CustomersCodi negoci = new CustomersCodi(Application.getConnexio());
		negoci.update(customers);
		try {
			Application.getConnexio().getConnection().commit();
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}


	public CustomersModel getCustomers() {
		return customers;
	}


	public void setCustomers(CustomersModel customers) {
		this.customers = customers;
	}
	
	

}
