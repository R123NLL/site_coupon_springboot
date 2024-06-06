package src.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Company;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findAll();
    boolean isCompanyExists(String email, String password);
    void addCompany(Company company);
    boolean updateCompany(Company company);
    boolean deleteCompany(int companyId);
    Company getOneCompany(int companyId);
    ArrayList<Company> getAllCompanies();
    Company getOneCompanyByEmail(String email);
}
