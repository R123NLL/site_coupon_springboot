package src.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.dao.CouponRepository;
import src.springboot.dao.CustomerRepository;
import src.springboot.dto.NewCompanyRequest;
import src.springboot.entities.Company;
import src.springboot.mapper.CompanyMapper;
import src.springboot.service.CompanyService;
import src.springboot.service.impl.CompanyServiceImpl;
import src.springboot.service.impl.CustomerServiceImpl;


import java.util.Arrays;

@Service
public class CompanyTester {
    private CompanyRepository companyRepository;
    private CompanyServiceImpl companyService;
    private static final Logger logger = LoggerFactory.getLogger(CompanyTester.class);

    @Autowired
    public CompanyTester(CompanyRepository companyRepository,
                          CustomerRepository customerRepository,
                          CouponRepository couponRepository) {
        this.companyService = new CompanyServiceImpl(companyRepository, customerRepository, couponRepository);
        this.companyRepository=companyRepository;
    }

    public void test() {
        logger.info("******************* Company Tester *******************");

        // creating 5 companies
        logger.info("------ Creating Companies c1-c5 ------");
        createAndSave5Companies();

        //show all companies
        logger.info("All the companies before testing: \n " + companyRepository.getAllCompanies());

        //isExists
        logger.info("Company Testing: is company exists? (coleman-true) " +
                companyRepository.isCompanyExists("office@Coleman.com", "12345"));
        logger.info("Company Testing: is company exists? (google-false) " +
                companyRepository.isCompanyExists("office@google.com", "12345"));

        //deleting and checking
        logger.info("------ Company Testing: deleting non existing company ------");
        logger.info(("is company with id 10 exists? " + companyRepository.existsById(10L)));
        logger.info("Deleting company with id 10");
        companyRepository.deleteCompany(10);
        logger.info("Company Testing: checking if company is deleted");
        logger.info("" + companyRepository.getOneCompany(10));

        logger.info("------ Company Testing: deleting existing company ------");
        logger.info(("is company with id 5 exists? " + companyRepository.existsById(5L)));
        logger.info("Deleting company with id 5");
        companyRepository.deleteCompany(5);
        logger.info("Company Testing: checking if company is deleted");
        logger.info("" + companyRepository.getOneCompany(5));

        //adding and checking
        logger.info("Company Testing: adding new company (El-AL) ");
        companyRepository.addCompany(new Company("El-Al", "office@elal.co.il", "0123456", null));
        logger.info("" + companyRepository.getOneCompanyByEmail("office@elal.co.il"));

        //testing login
        //TODO logger.info("Company Testing: login to company:");

        //coupon testing for company
        //TODO

        //show all companies
        logger.info("All the companies after testing: \n " + companyRepository.getAllCompanies());
    }

    private void createAndSave5Companies() {
        companyRepository.saveAll(Arrays.asList(CompanyMapper.mapToCompany(
                        new NewCompanyRequest("Coleman", "office@Coleman.com", "12345", null))
                , CompanyMapper.mapToCompany(new NewCompanyRequest("ViewSonic", "office@Viewsonic.com", "123123", null))
                , CompanyMapper.mapToCompany(new NewCompanyRequest("Logitech", "office@Logitech.com", "54321", null))
                , CompanyMapper.mapToCompany(new NewCompanyRequest("Oxford", "office@Oxford.com", "122333", null))
                , CompanyMapper.mapToCompany(new NewCompanyRequest("Jbl", "office@Jbl.com", "132213", null))));
        logger.info("created 5 companies");
    }
}
