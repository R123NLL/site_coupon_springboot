package src.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.springboot.entities.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByEmailAndPassword(String email, String password);
    Company findByEmail(String email);

}
