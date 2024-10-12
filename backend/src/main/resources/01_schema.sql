CREATE DATABASE IF NOT EXISTS `site_coupons_springboot`;

-- Creating the customers table
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    customer_email VARCHAR(40) NOT NULL UNIQUE,
    customer_password VARCHAR(40) NOT NULL
);

-- Creating the companies table
CREATE TABLE IF NOT EXISTS companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(40) NOT NULL UNIQUE,
    company_email VARCHAR(40) NOT NULL UNIQUE,
    company_password VARCHAR(40) NOT NULL
);

-- Creating the coupons table
CREATE TABLE IF NOT EXISTS coupons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_id BIGINT,
    category VARCHAR(250),  -- Assuming Category is an ENUM type
    title VARCHAR(250) NOT NULL,
    description VARCHAR(40) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    amount INT NOT NULL CHECK (amount >= 0),
    price DOUBLE NOT NULL,
    image VARCHAR(40) NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);

-- Creating the customers_vs_coupons join table
CREATE TABLE IF NOT EXISTS customers_vs_coupons (
    customer_id BIGINT,
    coupon_id BIGINT,
    PRIMARY KEY (customer_id, coupon_id),
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (coupon_id) REFERENCES coupons(id) ON DELETE CASCADE
);
