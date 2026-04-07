# Garage Management System (GMS)

[![Java](https://img.shields.io/badge/Java-11%2B-blue)](https://www.oracle.com/java/)
[![UI](https://img.shields.io/badge/UI-Swing-informational)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Status](https://img.shields.io/badge/Status-Active-success)](#)

A Java Swing application for managing vehicles in a garage, including a table view, full-text search, vehicle creation form, and printing support.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Architecture (Short)](#architecture-short)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [Author](#author)

## Features

- **Clean vehicle table** with the following fields:
  - Brand
  - Model/Type
  - License Plate
  - Consumption
  - Range
  - Fuel Tank Capacity
  - Seat Capacity
  - Speed
- **Dynamic search/filtering** across all columns
- **Add new vehicle** via form input
  - Required fields: Brand, Type, License Plate
  - Optional technical fields
- **Print support** for the table view
- **Exit confirmation dialog** with a small animation
- **Clean object creation using Builder Pattern** (`VehicleBuilder`)

## Project Structure

```text
src/
└─ main/
   └─ java/
      ├─ Main.java
      ├─ GUI.java
      ├─ Vehicle.java
      └─ VehicleBuilder.java
```

## Architecture (Short)

- `Main.java`
  - Application entry point
  - Creates initial sample vehicles
  - Launches the UI on the Swing Event Dispatch Thread

- `GUI.java`
  - Main window and complete UI logic
  - Table model, filtering, form handling, printing, exit dialog

- `Vehicle.java`
  - Domain model for vehicles
  - Uses `Optional` for optional properties

- `VehicleBuilder.java`
  - Fluent builder for readable and controlled object creation

## Requirements

- **Java JDK 11+** (recommended: JDK 17 or newer)
- Windows, macOS, or Linux

## Quick Start

### Windows (PowerShell)

```powershell
javac -d out src/main/java/*.java
java -cp out main.java.Main
```

> Note: The package name is `main.java`, so the main class is `main.java.Main`.

### Start in IDE (VS Code / IntelliJ)

- Run `Main.java` as the startup class
- Ensure the correct JDK is configured

## Usage

On startup, three sample vehicles are loaded. You can then:

1. Search vehicles in the table
2. Add new vehicles via the form
3. Print the table
4. Close the application via `EXIT` → `EXIT2`

## Screenshots

No screenshots are versioned yet.

- Recommendation: Store images in `docs/images/` (e.g., `docs/images/main-window.png`)
- Then embed them like this:

```md
![Main Window](docs/images/main-window.png)
```

## Roadmap

- [ ] Improve numeric input validation with clear error messages
- [ ] Further separate UI and business logic (MVC-style)
- [ ] Add persistence (JSON/CSV/SQLite)
- [ ] Add edit/delete actions for existing vehicles
- [ ] Add export features (CSV/PDF)
- [ ] Add unit tests with JUnit

## Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch
3. Implement your changes
4. Open a pull request

## Author

Repository: **MF-swiss/Garage-Management-System-GMS-**
