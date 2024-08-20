package src.springboot.dto;


import src.springboot.entities.Category;
import src.springboot.entities.Company;

import java.time.LocalDate;
import java.util.Objects;

public class NewCouponRequest {
    private Long companyId;
    private Category category;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private double price;
    private String image;

    public NewCouponRequest() {

    }

    public NewCouponRequest(Long companyId, Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Category getCategory() {
        return category;
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
        NewCouponRequest that = (NewCouponRequest) o;
        return amount == that.amount && Double.compare(price, that.price) == 0 && Objects.equals(companyId, that.companyId) && category == that.category && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, category, title, description, startDate, endDate, amount, price, image);
    }

    @Override
    public String toString() {
        return "NewCouponRequest{" + "companyId=" + companyId + ", category=" + category + ", title='" + title + '\'' + ", description='" + description + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", price=" + price + ", image='" + image + '\'' + '}';
    }
}
