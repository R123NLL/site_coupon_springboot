package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    List<Company> findAll();

}
