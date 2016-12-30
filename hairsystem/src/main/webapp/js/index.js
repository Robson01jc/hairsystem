var INDEX = (function() {
	
	var index = {};
	
	index.customer = {};
	
	index.customers = [];
	
	index.loadTemplates = function() {
		
		$.ajax({
			method: "GET",
			url: "template/table-tr-customer-template.html",
			dataType: "html",
			cache: false,
			success: function(markup) {
				$.template("customerTemplate", markup);
			},
			error: function(error) {
				console.log(error);
			}
		});
		
	}
	
	index.getCustomersFromServer = function() {
		
		$.ajax({
			method: "GET",
			url: "rest/customer/allCustomers",
			contentType: "application/json",
			success: function(data) {
				index.customers = data;
				index.buildCustomersTable();
			},
			error: function(error) {
				console.log(error.status);
			}
		});
		
	}
	
	index.saveCustomerOnServer = function() {
		
		$.ajax({
			method: "POST",
			url: "rest/customer/saveCustomer",
			contentType: "application/json",
			data: index.customer,
			success: function(response) {
				alert(response);
				index.customer = {};
				index.clearCustomerForm();
				index.getCustomersFromServer();
			},
			error: function(error) {
				console.log(error);
			}
		});
		
	}
	
	index.deleteCustomerOnServer = function(customer) {
		
		$.ajax({
			method: "DELETE",
			url: "rest/customer/deleteCustomer",
			contentType: "application/json",
			data: JSON.stringify(customer),
			success: function(response) {
				alert(response);
				index.getCustomersFromServer();
			},
			error: function(error) {
				console.log(error);
			}
		});
		
	}
	
	index.buildCustomersTable = function() {
		
		$("#table_customer_body").empty();
		
		$.tmpl("customerTemplate", index.customers).appendTo("#table_customer_body");
		
	}
	
	index.formToJSON = function() {
		
		index.customer.name = $("input[name='name']").val();
		index.customer.phone = $("input[name='phone']").val();
		index.customer.address = $("input[name='address']").val();
		
		return JSON.stringify(index.customer);
	}
	
	index.clearCustomerForm = function() {
		
		$("input[name='name']").val("");
		$("input[name='phone']").val("");
		$("input[name='address']").val("");
		
	}
	
	index.initTriggers = function() {
		$('#customer_form').on("submit", function(e) {
			index.customer = index.formToJSON();
			index.saveCustomerOnServer();
			e.preventDefault();
		});
		$('#newCustomer').on("click", function() {
			index.customer = {};
			index.clearCustomerForm();
		})
		
	}
	
	index.editCustomer = function(object) {
		index.customer = object;
		index.fillOutCustomerForm();
	}
	
	index.deleteCustomer = function(object) {
		index.deleteCustomerOnServer(object);
	}
	
	index.fillOutCustomerForm = function() {
		
		$("input[name='name']").val(index.customer.name);
		$("input[name='phone']").val(index.customer.phone);
		$("input[name='address']").val(index.customer.address);
		
	}
	
	index.init = function() {
		index.loadTemplates();
		index.initTriggers();
		index.getCustomersFromServer();
	}
	
	return {
		init: index.init,
		editCustomer: index.editCustomer,
		deleteCustomer: index.deleteCustomer
	}
	
})();