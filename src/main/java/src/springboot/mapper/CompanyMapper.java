package src.springboot.mapper;

import src.springboot.dto.NewCompanyRequest;
import src.springboot.entities.Company;

public class CompanyMapper {
    public static Company mapToCompany(NewCompanyRequest ncr){
        Company company= new Company(ncr.getName(), ncr.getEmail(), ncr.getPassword(),ncr.getCoupons());
        return company;
    }
}
