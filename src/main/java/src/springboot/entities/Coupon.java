package src.springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private int id;
    private int companyID;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "title",nullable = false,length = 40)
    private String title;
    @Column(name = "description",nullable = false,length = 40)
    private String description;
    @Column(name = "start_date",nullable = false,length = 40)
    private LocalDate startDate;
    @Column(name = "end_date",nullable = false,length = 40)
    private LocalDate endDate;
    @Column(name = "amount",nullable = false,length = 40)
    private int amount;
    @Column(name = "price",nullable = false,length = 40)
    private double price;
    @Column(name = "image",nullable = false,length = 40)
    private String image;

    // We also have constructor by default,
    // but we required to create constructor.
    // We will pass arguments to it if it will be required in the future.
    public Coupon() {
    }

    public Coupon(int companyID, Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public Category getCategory() {
        return category;
    }

    public int getCategoryId() {
        return category.ordinal()+1;
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
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
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
