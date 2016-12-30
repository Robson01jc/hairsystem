package com.robsonrocha.webservice;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.robsonrocha.business.CustomerService;
import com.robsonrocha.entity.Customer;

@Path("customer")
public class CustomerWebService {

	@Inject
	private CustomerService customerService;
	
	@GET
	@Path("/allCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> allCustomers() {
		return customerService.loadAll();
	}
	
	@POST
	@Path("/saveCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveCustomer(Customer c) {
		if (Objects.isNull(c.getId())) {
			customerService.create(c);
		} else {
			customerService.update(c);
		}
		return "Customer saved successfully!";
	}
	
	@DELETE
	@Path("/deleteCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteCustomer(Customer c) {
		customerService.delete(c);
		return "Customer deleted successfully!";
	}
}
