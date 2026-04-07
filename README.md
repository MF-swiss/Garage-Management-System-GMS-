# Garage Management System (GMS)

[![Java](https://img.shields.io/badge/Java-11%2B-blue)](https://www.oracle.com/java/)
[![UI](https://img.shields.io/badge/UI-Swing-informational)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Status](https://img.shields.io/badge/Status-Aktiv-success)](#)

Eine Java-Swing-Anwendung zur Verwaltung von Fahrzeugen in einer Garage – inklusive Tabellenansicht, Volltextsuche, Fahrzeug-Erfassung und Druckfunktion.

## Inhaltsverzeichnis

- [Features](#features)
- [Projektstruktur](#projektstruktur)
- [Architektur (kurz)](#architektur-kurz)
- [Voraussetzungen](#voraussetzungen)
- [Schnellstart](#schnellstart)
- [Nutzung](#nutzung)
- [Screenshots](#screenshots)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [Autor](#autor)

## Features

- **Übersichtliche Fahrzeugtabelle** mit folgenden Daten:
  - Marke
  - Typ
  - Kennzeichen
  - Verbrauch
  - Reichweite
  - Tankkapazität
  - Sitzplätze
  - Geschwindigkeit
- **Dynamische Suche/Filterung** über alle Spalten
- **Neues Fahrzeug hinzufügen** über Eingabeformular
  - Pflichtfelder: Marke, Typ, Kennzeichen
  - Optionale technische Werte
- **Druckfunktion** für die Tabellenansicht
- **Bestätigungsdialog beim Beenden** inklusive kleiner Animation
- **Saubere Objekterstellung per Builder-Pattern** (`VehicleBuilder`)

## Projektstruktur

```text
src/
└─ main/
   └─ java/
      ├─ Main.java
      ├─ GUI.java
      ├─ Vehicle.java
      └─ VehicleBuilder.java
```

## Architektur (kurz)

- `Main.java`
  - Einstiegspunkt der Anwendung
  - Erstellt Beispiel-Fahrzeuge
  - Startet die GUI auf dem Swing Event Dispatch Thread

- `GUI.java`
  - Hauptfenster und komplette UI-Logik
  - Tabellenmodell, Suche, Eingabeformular, Druck, Exit-Dialog

- `Vehicle.java`
  - Domänenmodell für Fahrzeuge
  - Verwendet `Optional` für optionale Eigenschaften

- `VehicleBuilder.java`
  - Fluent Builder zur kontrollierten und lesbaren Objekt-Erzeugung

## Voraussetzungen

- **Java JDK 11+** (empfohlen: JDK 17 oder neuer)
- Windows, macOS oder Linux

## Schnellstart

### Windows (PowerShell)

```powershell
javac -d out src/main/java/*.java
java -cp out main.java.Main
```

> Hinweis: Das Package lautet `main.java`, daher ist die Startklasse `main.java.Main`.

### IDE-Start (VS Code / IntelliJ)

- `Main.java` als Startklasse ausführen
- Sicherstellen, dass das korrekte JDK konfiguriert ist

## Nutzung

Beim Start werden drei Beispiel-Fahrzeuge geladen. Danach kannst du:

1. Fahrzeuge in der Tabelle durchsuchen
2. Neue Fahrzeuge über das Formular hinzufügen
3. Die Tabelle drucken
4. Das Programm über `EXIT` → `EXIT2` schließen

## Screenshots

Aktuell sind noch keine Screenshots versioniert.

- Empfehlung: Lege Bilder unter `docs/images/` ab (z. B. `docs/images/main-window.png`)
- Danach kannst du sie so einbinden:

```md
![Hauptfenster](docs/images/main-window.png)
```

## Roadmap

- [ ] Eingabevalidierung für numerische Felder verbessern (klare Fehlermeldungen)
- [ ] Trennung von UI- und Geschäftslogik weiter ausbauen (MVC-Ansatz)
- [ ] Persistenz ergänzen (JSON/CSV/SQLite)
- [ ] Bearbeiten/Löschen bestehender Fahrzeuge
- [ ] Exportfunktionen (CSV/PDF)
- [ ] Unit-Tests mit JUnit

## Contributing

Beiträge sind willkommen.

1. Repository forken
2. Feature-Branch erstellen
3. Änderungen implementieren
4. Pull Request öffnen

## Autor

Projekt im Repository: **MF-swiss/Garage-Management-System-GMS-**
