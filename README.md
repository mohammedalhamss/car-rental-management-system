# Car Rental Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)

## Overview
[cite_start]The Car Rental Management System is a desktop application designed to streamline the car rental process[cite: 5, 6]. [cite_start]Developed for the COM232 Visual Programming for Software Design course [cite: 1][cite_start], this system allows users to browse available vehicles, calculate rental costs based on duration and vehicle year, and process rentals seamlessly[cite: 7, 8]. 

[cite_start]The application features a dedicated administrative panel for managing the vehicle fleet and reviewing sales data[cite: 9, 10, 11]. [cite_start]It is built using Java (Swing for the graphical user interface) and relies on a MySQL database for secure data management[cite: 12].

## Key Features
* [cite_start]**Customer Booking Interface:** Users can select a car from the available inventory and specify the rental duration (3 Days, a Week, or a Month)[cite: 7, 42].
* [cite_start]**Dynamic Pricing:** The system calculates the total rental cost dynamically based on the specific manufacturing year of the selected car[cite: 8, 432].
* [cite_start]**Admin Dashboard:** Secure login for administrators to manage the platform[cite: 262].
* [cite_start]**Fleet Management:** Administrators can add new cars to the database, detailing the brand, model, and year[cite: 11, 498].
* [cite_start]**Sales Tracking:** A detailed view of rental transactions, including start dates, durations, and total costs[cite: 10, 359].

## Technologies Used
* [cite_start]**Frontend:** Java Swing (AWT, JFrame, JPanel) [cite: 12]
* [cite_start]**Backend:** Java (Application Logic & JDBC) [cite: 12]
* [cite_start]**Database:** MySQL [cite: 12]

## Database Schema
[cite_start]The system requires three primary tables[cite: 14]:
1.  [cite_start]`cars`: Stores vehicle details (`car_id`, `car_brand`, `model`, `year`, `status`)[cite: 491].
2.  [cite_start]`rented`: Records transaction history (`car_id`, `start_date`, `duration`, `cost`)[cite: 469].
3.  [cite_start]`user`: Stores administrator credentials (`username`, `password`)[cite: 480].

## Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yourusername/car-rental-management-system.git](https://github.com/yourusername/car-rental-management-system.git)
    cd car-rental-management-system
    ```
2.  **Database Configuration:**
    * Install MySQL and start the local server.
    * Create a database named `rent`.
    * Execute the necessary SQL commands to create the `cars`, `rented`, and `user` tables.
    * *Important:* Open `src/Mangement.java` and update the database connection string, username, and password to match your local MySQL configuration:
        ```java
        DriverManager.getConnection("jdbc:mysql://localhost:3306/rent", "your_username", "your_password");
        ```
3.  **Compile and Run:**
    * Ensure you have the Java Development Kit (JDK) and the MySQL JDBC Driver (`mysql-connector-java.jar`) added to your classpath.
    * Compile the Java files and run `workframe.java` to launch the application.

## Usage
* [cite_start]**Renting a Car:** From the main interface, select an "available" car from the table, enter a start date (`yyyy-MM-dd`), choose a duration, and click "Confirm" to process the rental[cite: 161, 162].
* **Admin Access:** Click the "Admin" button on the top menu. [cite_start]Enter your administrator username and password to log in[cite: 261]. [cite_start]From here, you can click "Add car" to insert new inventory or "Details" to view all active rentals[cite: 228, 260].

## Future Improvements
[cite_start]Future updates planned for this system include[cite: 500]:
* [cite_start]User Registration System [cite: 501]
* [cite_start]Payment Gateway Integration [cite: 502]
* [cite_start]Car Return and Availability Status Updates [cite: 503]
* [cite_start]Responsive UI and Mobile Support [cite: 504]
* [cite_start]Notification System [cite: 505]
* [cite_start]Vehicle Health Monitoring [cite: 506]
* [cite_start]Geofencing Alerts [cite: 507]

## Author
**Mohammed Akram Al-Hams**
