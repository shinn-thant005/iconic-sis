# 🎓 ICONIC SIS (School Information System)

ICONIC SIS is a robust, production-grade School Information System built using a modern, decoupled full-stack architecture. Designed specifically to modernize school logistics and administrative overhead, the platform transitions traditional educational models into automated digital ecosystems.

The application features a decoupled operation architecture featuring a **Template-Instance Pattern** for schedule tracking, high-performance database normalization, and optimized workflows for student onboarding, transaction security, and automated schedule generation.

---

## 🛠️ Tech Stack & Architecture

- **Backend:** Java 21, Spring Boot 3.x, Spring Data JPA, Spring Security, Jakarta Validation
- **Frontend:** React (Vite), Tailwind CSS
- **Database:** MySQL (Normalized to 3rd Normal Form)
- **Object Storage:** Cloudinary / Supabase Storage (External asset pipeline for optimized media resolution)
- **Hosting Target:** Railway (Backend & DB), Vercel (Frontend CDN)

---

## 📐 System Architecture & Core Design Patterns

### 1. Template-Instance Pattern (Time Logistics)
To decouple static planning rules from day-to-day changes (such as holidays or substitute teachers), scheduling is split into two layers:
* **The Blueprint (`TimetableTemplate`):** Holds the master weekly schedule. It is immutable under normal operations and represents what *should* happen.
* **The Instance (`ClassSession`):** A background service weekly/daily "explodes" the templates into specific `LocalDate` records. Attendance and structural updates are tracked against these instances, leaving the core master blueprint safe and uncorrupted.

### 2. Centralized Contact Utility
To enforce **Third Normal Form (3NF)** database purity, all mobile numbers across students, teachers, parents, and employees are stored inside a centralized `PhoneContact` utility table. This table lacks a public controller layer, ensuring full data encapsulation and protection against direct endpoint access.

### 3. High-Concurrency & Optimization
* **Decoupled Asset Pipeline:** Image files are intercepted via Spring's `MultipartFile` arrays, compressed into highly optimized `.webp` formats to preserve network bandwidth, and uploaded to external cloud buckets. The MySQL database stores only lightweight public URL strings.
* **Atomic Transactions:** Multi-service saves (such as creating a student, their parents, and their phone records simultaneously) are guarded by `@Transactional` barriers to guarantee zero orphaned rows on database failure.
* **Many-to-One Normalization:** Relationships (such as Student-to-Parent, Student-to-Grade, and Student-to-Session) utilize `@ManyToOne` configurations to support multi-child households and shared cohorts without database deadlocks.

---

## 🚀 Key Database Relations

```text
       [PhoneContact] (Centralized 3NF Utility Table)
             ▲
             │ (Unified via String IDs)
    ┌────────┼────────┐
    │        │        │
[Student] [Parent] [Teacher]
    │        │
    ▼        ▼
[Enrollment] ──► [AcademicYear]
    │
    ▼
 [Grade] ◄── [Session] (Student Cohort Groups)
                 │
                 ▼
               [Room] (Physical Floor Allocations)
