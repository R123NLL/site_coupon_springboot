package src.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.springboot.dao.CompanyRepository;
import src.springboot.entities.Company;
import src.springboot.service.CompanyService;

import java.util.ArrayList;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ArrayList<Company> getAllCompanies() {
        return null;
    }

    @Override
    public void addCompany() {

    }
}
