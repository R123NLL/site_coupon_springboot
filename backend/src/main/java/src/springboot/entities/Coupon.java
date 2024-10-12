package src.springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "title", nullable = false, length = 250)
    private String title;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name = "start_date", nullable = false, length = 40)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false, length = 40)
    private LocalDate endDate;
    @Column(name = "amount", nullable = false, length = 40)
    private int amount;
    @Column(name = "price", nullable = false, length = 40)
    private double price;
    @Column(name = "image", nullable = false, length = 40)
    private String image;
    @ManyToMany(mappedBy = "coupons")
    private Set<Customer> customers;

    public Coupon() {
    }

    public Coupon(Company company, Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
        this.company = company;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id=id;}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public int getCategoryId() {
        return category.ordinal() + 1;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return amount == coupon.amount && Double.compare(price, coupon.price) == 0 && Objects.equals(id, coupon.id) && Objects.equals(company, coupon.company) && category == coupon.category && Objects.equals(title, coupon.title) && Objects.equals(description, coupon.description) && Objects.equals(startDate, coupon.startDate) && Objects.equals(endDate, coupon.endDate) && Objects.equals(image, coupon.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, category, title, description, startDate, endDate, amount, price, image);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", company=" + company +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
