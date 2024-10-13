import React from "react";
import "../components/style/AboutPage.css"; // Ensure this path is correct

export default function AboutPage() {
    return (
        <div className="aboutBackground"> {/* This div applies the background */}
            <div className="about-page">
                <h3 className="title">About This Project</h3>
                <p>
                This project aims to build a user-friendly web application for a coupon management site that allows customers to buy and manage coupons.
                 It lets companies add, modify, and delete coupons, and enables an administrator to manage all the companies and add, modify, and delete companies. 
                </p>
                <p>
                This full-stack project was built on a Java Spring Boot server-side, MySQL database, and React.js client-side, using GitHub for version control.
                 We employed various development techniques such as version control, responsive design, component-based architecture, RESTful APIs, database management,
                  and security practices.
                </p>
                <p>
                This project was developed by Denis Shatik, Alexey Koloty, and Olga Zakharova (during the first stage).
                </p>
                <p>
                We hope we achieved the desired results and created a great project! :D
                </p>
            </div>
            <div className="spinning-cat" />
        </div>
    );
}
