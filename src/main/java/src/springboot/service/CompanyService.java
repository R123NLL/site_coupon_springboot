package src.springboot.service;

import src.springboot.entities.Company;

import java.util.ArrayList;

public interface CompanyService {
    ArrayList<Company> getAllCompanies();
    void addCompany();
}
