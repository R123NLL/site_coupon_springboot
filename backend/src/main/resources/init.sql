
-- Inserting data into companies
INSERT INTO site_coupons_springboot.companies (company_name, company_email, company_password) VALUES
    ('TechCorp', 'tech@corp.com', 'password123'),
    ('Foodies Inc', 'foodies@inc.com', 'delicious456'),
    ('TravelNow', 'travel@now.com', 'vacay789'),
    ('FashionHub', 'fashion@hub.com', 'style101'),
    ('GadgetWorld', 'gadget@world.com', 'innovate2024');

-- Inserting data into customers
INSERT INTO site_coupons_springboot.customers (first_name, last_name, customer_email, customer_password) VALUES
    ('John', 'Doe', 'john.doe@example.com', 'password123'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'password456'),
    ('Michael', 'Johnson', 'michael.johnson@example.com', 'password789'),
    ('Emily', 'Davis', 'emily.davis@example.com', 'pass2024'),
    ('David', 'Williams', 'david.williams@example.com', 'pass5678'),
    ('Sarah', 'Brown', 'sarah.brown@example.com', 'pass3456');

-- Inserting data into coupons
INSERT INTO site_coupons_springboot.coupons (company_id, category, title, description, start_date, end_date, amount, price, image) VALUES
    (1, 'ELECTRICITY', 'Discount on Laptops', 'Get 20% off', '2024-09-01', '2024-10-30', 100, 299.99, 'laptop.jpg'),
    (1, 'ELECTRICITY', '50% off Headphones', 'Best audio experience', '2024-09-15', '2024-10-25', 50, 49.99, 'headphones.jpg'),
    (2, 'FOOD', '10% off all meals', 'Delicious meals at a discount', '2024-09-01', '2024-10-10', 200, 19.99, 'meals.jpg'),
    (2, 'FOOD', 'Free Dessert', 'Get a free dessert with every meal', '2024-09-05', '2024-10-15', 100, 0.00, 'dessert.jpg'),
    (3, 'VACATION', 'Flight Discounts', 'Save big on flights', '2024-09-01', '2024-10-01', 50, 399.99, 'flight.jpg'),
    (3, 'VACATION', 'Hotel Stay Offers', '50% off hotel stays', '2024-09-15', '2024-10-30', 20, 199.99, 'hotel.jpg'),
    (4, 'CLOTHING', 'Summer Collection Sale', 'Up to 70% off', '2024-09-01', '2024-10-20', 300, 49.99, 'clothes.jpg'),
    (4, 'CLOTHING', 'Shoes Discount', 'Buy one get one free', '2024-09-10', '2024-10-30', 100, 79.99, 'shoes.jpg'),
    (5, 'ELECTRICITY', '50% off Smartphones', 'Latest smartphones on sale', '2024-10-01', '2024-09-25', 75, 599.99, 'smartphone.jpg'),
    (5, 'ELECTRICITY', 'Tablet Sale', 'Best prices on tablets', '2024-09-05', '2024-10-20', 40, 299.99, 'tablet.jpg');

-- Assigning coupons to customers in customers_vs_coupons table
INSERT INTO site_coupons_springboot.customers_vs_coupons (customer_id, coupon_id) VALUES
    (1, 1),
    (1, 3),
    (2, 2),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (6, 10);