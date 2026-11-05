# 🎬 CHILL — A Text-Based Streaming Service

> **SP3 · Datamatiker, 1st semester**
> An object-oriented streaming platform in the spirit of Netflix, HBO and Viaplay, built in Java as a console application.

![Java](https://img.shields.io/badge/Java-26-orange?logo=openjdk&logoColor=white)
![Interface](https://img.shields.io/badge/UI-Text--based-informational)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

---

## 📖 Table of Contents

- [About the Project](#-about-the-project)
- [Domain](#-domain)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [Data](#-data)
- [Team](#-team)

---

## 🧭 About the Project

The goal of SP3 is to carry out an **object-oriented analysis** of a domain, turn that analysis into an **object-oriented design**, and then **implement the solution in Java**.

The solution is built on core Java concepts:

- Classes, methods and interfaces
- Loops and conditionals
- `ArrayList`s and other data structures
- File I/O and exception handling

The first version is **purely text-based**. A graphical interface is an optional extension.

---

## 🌐 Domain

The system models a streaming service with **media** that users can play.

| Concept      | Description                                                                         |
|--------------|-------------------------------------------------------------------------------------|
| **Media**    | Anything playable. All media have a **title**, a **release year** and a **rating**. |
| **Movie**    | A single media item belonging to one or more categories.                            |
| **Series**   | A media item made up of **seasons**, each containing a number of **episodes**.      |
| **Category** | A genre such as *Crime*, *War*, *Drama*, *Family*, *Romance* or *Sci-Fi*.           |
| **User**     | Has a list of **watched** media and a list of **saved** (watch later) media.        |

---

## ✨ Features

### Core requirements

#### Start menu
- [ ] Create a new user
- [ ] Log in as an existing user

#### Main menu
- [ ] Search for a specific movie
- [ ] Browse all movies in a category
- [ ] View the list of watched movies
- [ ] View the list of saved movies

#### Media actions
When a user selects a movie from a list (category, watched or saved), they can:
- [ ] **Play** it (simulated, e.g. *"Titanic is now playing..."*). The movie is then added to the watched list.
- [ ] **Save** it to their watch-later list
- [ ] **Remove** it from their watch-later list

#### Persistence and robustness
- [ ] User credentials (username and password) are stored in a file and loaded on startup
- [ ] Errors are handled sensibly, and the user is informed when relevant

### Possible extensions

- [ ] Search for movies with a rating above **8.5**
- [ ] Search for movies from a specific decade (e.g. the **80s**)
- [ ] Easily add or remove categories (e.g. *Christmas*, *Halloween*)
- [ ] **Admin** users can add movies, series, seasons and episodes
- [ ] Save users' lists to file when the program closes
- [ ] **Child** profiles restricted to the *Family* category
- [ ] Support for new media types (e-books, audiobooks, games)
- [ ] Graphical user interface (Processing, Java Swing or JavaFX)

---


## 💾 Data

Movie and series data are loaded from semicolon-separated files in [`data/`](data/) into `ArrayList`s on startup.

**`filmData.csv`** (100 movies)

```
Title; Year; Categories; Rating;
The Godfather; 1972; Crime, Drama; 9,2;
```

**`seriesData.csv`** (100 series)

```
Title; Years; Categories; Rating; Season-Episodes;
The Sopranos; 1999-2007; Crime, Drama; 9,2; 1-13, 2-13, 3-13, 4-13, 5-13, 6-21;
```

> **Note:** Ratings use a decimal comma (`9,2`). A series that is still running has an open-ended year range (e.g. `2011-`). Seasons are written as `season-episodeCount`.

Poster images for every title are in [`assets/`](assets/) for use in an optional GUI.

---

## 👥 Team

| Name | GitHub |
|------|--------|
|Rasmus Schubert|Gubbi099|
|Patrick Rosenkrantz|Patrick-Rosenkrantz|
|Nikolaj Christensen|NikolajEsperChristensen|
|Frederik Camilo Dahl|FrederikCamiloDahl|

---

<sub>Developed as part of the 1st semester of the Datamatiker (Computer Science AP) programme.</sub>
