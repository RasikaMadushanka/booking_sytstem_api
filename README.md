# Interview Scheduling API

A backend REST API designed to manage **interview scheduling and time slot allocation**.
The system allows interviewers to book available time slots, automatically assigns candidates to interviews, and prevents double-booking.

## 🚀 Overview

This system ensures that **each interview time slot can only be booked once**. When an interviewer schedules a slot, the system automatically assigns a candidate and sends an alert message with the interview details.

## 🛠 Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **ORM:** Hibernate (JPA)
* **Database:** MySQL
* **Architecture:** Layered Architecture (Controller → Service → Repository)
* **Build Tool:** Maven

## ⚙ Core Features

* Interviewers can **book interview time slots**
* **Automatic candidate assignment** when a slot is booked
* Prevents **double booking of time slots**
* Sends **alert messages with interview details**
* Full **CRUD operations for interview scheduling**
* Secure and scalable backend design

## 🏗 System Architecture

### Controller Layer

Handles REST API endpoints for:

* Interviewer booking
* Candidate assignment
* Schedule management

### Service Layer

Contains the core business logic:

* Validates available time slots
* Automatically assigns candidates
* Prevents duplicate bookings

### Repository Layer

Uses **Hibernate/JPA repositories** to interact with the MySQL database.

### Entity Layer

Defines the system models such as:

* Interviewer
* Candidate
* InterviewSchedule
* TimeSlot

## 🔁 Workflow

1. Interviewer selects a **time slot**.
2. The system checks if the slot is **already booked**.
3. If available, the slot is **reserved for that interviewer**.
4. The system **automatically assigns a candidate**.
5. An **alert message** is generated with the interview details.
6. The booked time slot becomes **unavailable for other interviewers**.

## 📌 Key Rule

A **single time slot can only be booked by one interviewer**.
Once reserved, the system blocks the slot to prevent any additional bookings.

## 📫 Author

**Rasika Madushanka**
Software Engineering Student (HND @ ICET)

---

⭐ This project demonstrates building a **real-world interview scheduling backend system using Java, Spring Boot, Hibernate, and MySQL**.
