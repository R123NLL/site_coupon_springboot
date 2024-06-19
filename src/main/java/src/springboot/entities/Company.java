package src.springboot.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    @Column(name = "company_name", nullable = false, unique = true, length = 40)
    private String name;
    @Column(name = "company_email", nullable = false, unique = true, length = 40)
    private String email;
    @Column(name = "company_password", nullable = false, length = 40)
    private String password;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Coupon> coupons;

    public Company() {
    }

    public Company(String name, String email, String password, List<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(password, company.password) && Objects.equals(coupons, company.coupons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, coupons);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                "}\n";
    }
}
