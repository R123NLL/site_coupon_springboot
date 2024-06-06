package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.springboot.entities.Company;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Company c WHERE c.email = :email AND c.password = :password")
    boolean isCompanyExists(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("INSERT INTO Company (id, name, email, password) VALUES (:#{#company.id}, :#{#company.name}, :#{#company.email}, :#{#company.password})")
    void addCompany(@Param("company") Company company);

    @Modifying
    @Transactional
    @Query("DELETE FROM Company c WHERE c.id = :companyId")
    void deleteCompany(@Param("companyId") int companyId);

    @Query("SELECT c FROM Company c WHERE c.id = :companyId")
    Company getOneCompany(@Param("companyId") int companyId);

    @Query("SELECT c FROM Company c")
    ArrayList<Company> getAllCompanies();

    @Query("SELECT c FROM Company c WHERE c.email = :email")
    Company getOneCompanyByEmail(@Param("email") String email);
}
