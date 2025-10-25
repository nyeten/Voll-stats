# Volleyball Statistics Tracker

A JavaFX desktop application for managing volleyball teams, recording live match data, and generating detailed performance reports.  
Designed with modular architecture, persistent data storage, and Excel export functionality using Apache POI.

---

## Overview

This application provides an intuitive interface for recording volleyball statistics in real time. Users can create teams, assign players, and log match events such as attacks, serves, and digs.  
After each game, player and team statistics are automatically aggregated and exported to an Excel file, with each match saved on a separate sheet for long-term tracking.

---

## Key Features

- **Live Match Tracking** – Record detailed player stats (kills, blocks, assists, serves, digs) during gameplay.  
- **Team and Player Management** – Create, edit, and persist teams with player names, positions, and jersey numbers.  
- **Automated Reporting** – Export results to an Excel workbook (`game_history.xlsx`), with each game stored in its own worksheet.  
- **Persistent Storage** – Save and load team data using JSON.  
- **Modular JavaFX UI** – Separate scenes for team management, gameplay, and data review.

---

## Technologies

| Category | Tools & Frameworks |
|-----------|--------------------|
| **Language** | Java 17 |
| **UI Framework** | JavaFX |
| **Data Handling** | JSON (for persistence) |
| **Reporting** | Apache POI (Excel integration) |
| **Build Tool** | Maven |

---

## Installation and Execution

### Prerequisites
- Java 17 or later  
- Maven 3.8+  

### Run Locally
```bash
git clone https://github.com/yourusername/volleyball-stats-tracker.git
cd volleyball-stats-tracker
mvn clean javafx:run
```

## Usage
- **Record Game Score** – Keeps track of score of game. Seperate from stat tracking.
- **Team Setup** – Create or load a team. Add players with name, number, and position.
- **Start Game** – Choose a team and begin tracking live stats for each player.
- **View Stats** – Adjust or view individual player stats mid-game.
- **Save Game** –  Export match results to game_history.xlsx (creates new worksheet per match).

## Dependencies
JavaFX
Apache POI
Gson

## Future Enhancements

Visualization of player stats using JavaFX charts.
Database integration (SQLite or PostgreSQL).
Web interface or cloud synchronization.
User authentication for shared team data.




## Notes for Reviewers

This project was developed independently as a full-stack JavaFX application.
It demonstrates skills in:

Object-oriented design.

Event-driven UI development.

Data persistence (JSON + Excel).

Integration of third-party libraries (Apache POI).

Clean, maintainable architecture with modular controllers and views.

## Author: Nye Tenerelli

nyetenerelli@gmail.com

www.linkedin.com/in/nye-tenerelli-ba546023b


