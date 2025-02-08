# ETFBL_IP
## Overview
ETFBL_IP is a system developed for a fictional company that rents electric cars, bikes, and scooters. The system is implemented using Spring Boot for the backend, with a focus on user management, vehicle rental, maintenance tracking, and promotional activities.

The application also provides a user interface for employees (administrators, operators, and managers) to manage vehicles, users, maintenance issues, and rental statistics, as well as a separate client-side interface for renting vehicles and managing their profiles.

## Features

### Backend (Spring Boot Application)
- **Vehicle Management**: Stores information about electric cars, bikes, and scooters, including unique identifiers, purchase date, price, manufacturer, model, and additional specific attributes (autonomy, speed, etc.).
- **Maintenance Tracking**: Records information about vehicle malfunctions, including reasons, timestamps, and whether the vehicle is currently in a malfunctioning state.
- **User Management**: Manages employees (administrators, operators, and managers) and clients, with roles and profiles for each user.
- **Rental Process**: Tracks vehicle rentals, including the rental date, location, and rental duration, as well as payment details. Generates invoices as PDF files.
- **Manufacturer Management**: Stores manufacturer details like name, country, address, and contact information.
- **Promotions and Announcements**: Allows for the creation of marketing promotions and announcements.
- **RESTful Services**: Implements RESTful APIs for vehicle management, user management, and rental processes.
- **RSS Feed**: Exposes an RSS feed for announcements and promotions.

### Employee Application
- **Administrator Pages**:
  - Manage vehicles: Add, remove, and view vehicle details.
  - Upload vehicle data from CSV files.
  - View vehicle details, including all rentals and malfunctions.
  - Manage manufacturers (CRUD).
  - User management: Block or unblock client accounts and manage employee accounts.
- **Operator Pages**:
  - View vehicle rentals.
  - View vehicle locations on a map.
  - Register malfunctions for vehicles.
  - Manage client accounts.
- **Manager Pages**:
  - Access to all administrator and operator pages.
  - View rental statistics (e.g., revenue per day, faults per vehicle).
  - Manage rental prices.

### Client Application
- **User Registration**: Clients can register with their personal details (name, email, phone number, avatar, etc.).
- **Vehicle Rental**: Clients can rent vehicles (scooters, bikes, cars) by selecting the vehicle, location, and payment method.
- **Rental Management**: Clients can view and manage their rental history, including the duration and total amount to pay.
- **Profile Management**: Clients can change their password, deactivate their account, and view their rental history.

### Promotion Creation Application
- **Manager Access Only**: Allows managers to create new promotions and announcements.
- **Promotions and Announcements**: Create, view, and search for promotions and announcements by content.
