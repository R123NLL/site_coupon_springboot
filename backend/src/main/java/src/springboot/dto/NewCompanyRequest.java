package src.springboot.dto;

import src.springboot.entities.Coupon;


import java.util.Set;

public class NewCompanyRequest {
    private Long companyId;
    private String name;
    private String email;
    private String password;
    private Set<Coupon> coupons;

    public NewCompanyRequest() {

    }

    public NewCompanyRequest(Long companyId, String name, String email, String password, Set<Coupon> coupons) {
        this.companyId = companyId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "NewCompanyRequest{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
