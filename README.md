# Unified Java Project (projects)

This is a unified Java project that brings together three individual object-oriented systems:
- **Mission Management System**
- **Land Management System**
- **Nursery School Management System**

Each system is developed using Object-Oriented Programming (OOP) principles with classes, inheritance, abstraction, encapsulation, and validations.

## 📁 Project Structure

```
unified-java-project/
│
├── mission/         # Mission Management System classes
├── land/            # Land Management System classes
├── nursery/         # Nursery School Management classes
├── main/            # Main entry point
├── Dockerfile       # Docker setup for building and running
└── README.md        # Project documentation
```

## 🚀 Features

### ✅ Mission Management System
- Abstract `Mission` class with concrete classes:
  - `ReconMission`
  - `RescueMission`
  - `CombatMission`
  - `HumanitarianMission`
- Resource and Personnel management
- Mission tracking and report generation

### ✅ Land Management System
- Manage different land types (e.g., Agricultural, Residential, Commercial)
- Validate land size, type, and ownership
- Generate reports on land registry

### ✅ Nursery School Management System
- Manage student registration
- Validate age and class-level
- Generate simple performance and enrollment reports

## 🛠️ Technologies Used
- Java (JDK 17+)
- Git & GitHub for version control
- Docker for containerization

## 🐳 Run with Docker

Build the Docker image:
```bash
docker build -t unified-java-project .
```

Run the compiled Java project:
```bash
docker run unified-java-project
```

## 💻 Running Locally (Without Docker)
Compile:
```bash
javac main/Main.java mission/*.java land/*.java nursery/*.java
```

Run:
```bash
java main.Main
```

## 👤 Author

**Elie Nshimyumuremyi**  
GitHub: [hha22bba](https://github.com/hha22bba)

---

Feel free to contribute or suggest improvements to this project!
