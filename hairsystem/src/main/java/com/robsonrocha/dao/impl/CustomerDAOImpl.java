package com.robsonrocha.dao.impl;

import com.robsonrocha.dao.CustomerDAO;
import com.robsonrocha.dao.generic.GenericDAOImpl;
import com.robsonrocha.entity.Customer;
import com.robsonrocha.util.JPADAO;

@JPADAO
public class CustomerDAOImpl extends GenericDAOImpl<Customer, Long> implements CustomerDAO {

}
