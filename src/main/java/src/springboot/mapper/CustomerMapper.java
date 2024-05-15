package src.springboot.mapper;

import src.springboot.dto.NewCustomerRequest;
import src.springboot.entities.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(NewCustomerRequest ncr){
        Customer customer=new Customer(ncr.getFirstName(), ncr.getLastName(),
                ncr.getEmail(), ncr.getPassword(), ncr.getCoupons());
        return customer;
    }
}
