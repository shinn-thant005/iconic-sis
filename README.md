Here is a complete, professional, and production-ready `README.md` file designed for your **ICONIC SIS** project repository. It is written using standard GitHub Markdown syntax, so you can easily copy and paste it directly into your project.

```markdown
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

```

---

## 📅 Automatic Scheduling Algorithm

The system incorporates a custom **Constrained Greedy Backtracking (CGB)** algorithm designed to resolve school scheduling puzzles automatically:

1. **Heuristic Priority Queue:** Prioritizes scheduling highly constrained teachers first, core subjects with intensive weekly period allocations second, and maps elective variants (e.g., STEAM-1 vs. STEAM-2) into parallel blocks.
2. **Global Conflict Checker:** Enforces database constraints by parsing structural arrays via a comprehensive transactional verification query before finalizing any template slot:
```sql
SELECT COUNT(*) FROM timetable_template 
WHERE (teacher_id = ? OR room_id = ?) 
AND time_slot_id = ? AND day_of_week = ?;

```


3. **Validation Guards:** Audits total available periods against mandatory weekly subject allocations, issuing UI warnings and halting generation if data imbalances are found.

---

## 🛣️ API Endpoints Preview

All endpoints follow strict RESTful conventions using Query Parameters (`@RequestParam`) for modular filtering, sorting, and pagination.

### Student Management

* `POST /api/student/add` - Onboards a new student using an immutable Java 21 Record payload (`StudentCreationDTO`). Returns a `201 Created` status with a context `Location` header pointer.
* `GET /api/student` - Retrieves filtered student structures.

### Academic Architecture

* `GET /api/grade` - Lists all grade tiers.
* `POST /api/grade/add` - Adds a new grade tier.
* `GET /api/session/get` - Pulls active student sections.
* `GET /api/room` - Fetches physical classroom listings.
* `GET /api/academic` - Fetches historical academic timelines.

---

## 💻 Installation & Local Setup

### Prerequisites

* Java 21 JDK
* Node.js (v18+)
* MySQL Server 8.x

### Backend Setup

1. Clone the repository and navigate to the backend folder:
```bash
git clone [https://github.com/your-username/iconic-sis.git](https://github.com/your-username/iconic-sis.git)
cd iconic-sis

```


2. Configure your database details inside `src/main/resources/application.properties` (or use a local properties file configuration):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/iconic_sis
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

```


3. Compile and run the Spring Boot application:
```bash
./mvnw spring-boot:run

```



### Frontend Setup

1. Navigate to the frontend UI subdirectory:
```bash
cd ../frontend

```


2. Install the necessary node modules:
```bash
npm install

```


3. Run the development server:
```bash
npm run dev

```



---

## 📌 Development Roadmap

* [x] Baseline 3NF Relational Database Schema & Architecture Mappings
* [x] Secure Student Onboarding Architecture using Java 21 Records & DTOs
* [x] Atomic Transaction Pipelines (`@Transactional` Boundary Mappings)
* [ ] Teacher & Subject Data Feature Entry Setup
* [ ] Automatic Scheduling Algorithm (CGB) Integration
* [ ] Decoupled Media Upload Pipeline (.webp Compression Filters)
* [ ] Student History Advancement and Academic Promotion Engine
* [ ] Spring Security Role-Based Access Control (`ROLE_ADMIN`, `ROLE_USER`)

```

```
