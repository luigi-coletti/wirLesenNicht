# 📖 Bibliothekssoftware Demo: wirLesenNicht (Vaadin UI)

## 🌟 Überblick

Diese Anwendung ist eine **Demo-Implementierung einer Bibliotheksverwaltungssoftware**, die als Vaadin-Webanwendung realisiert wurde. Sie ermöglicht es registrierten Nutzern, verschiedene Medientypen – **Bücher**, **Filme** und **Zeitschriften** – auszuleihen und den Überblick über die Rückgabetermine zu behalten.

Das Design folgt einem klaren Objektmodell, wie im beigefügten UML-Klassendiagramm dargestellt.

---

## ✨ Funktionen

* **Medienkatalog:** Verwaltung und Anzeige aller verfügbaren Medien (`Buch`, `Film`, `Zeitschrift`).
* **Ausleihe:** Nutzer können Medien ausleihen (verwaltet durch die `Controller`-Klasse).
* **Rückgabetermine:** Anzeige, bis wann ein ausgeliehenes Medium zurückgegeben werden muss.
* **Nutzerverwaltung:** Einfache Verwaltung von `Person`-Objekten.
* **Vaadin UI:** Eine moderne, responsive Benutzeroberfläche, die im Browser läuft.

---

## 🛠️ Systemarchitektur

Die Anwendung basiert auf dem folgenden objektorientierten Design:

### 1. Kernobjekte

* **`Controller`**: Die zentrale Steuerungslogik. Sie verwaltet alle Medien (`allMedia: HashMap<String, Media>`) und alle Nutzer (`users: ArrayList<Person>`).
* **`Media`** (Abstract): Die abstrakte Basisklasse für alle ausleihbaren Objekte. Sie definiert gemeinsame Attribute wie **Titel**, **ID** und das **Ausleihdatum** (`lentDate`).
* **`Buch`**, **`Zeitschrift`**, **`Film`**: Konkrete Subklassen von `Media`, die jeweils spezifische Attribute (wie `author`, `editor`, `regisseur`) hinzufügen.
* **`Person`**: Repräsentiert einen Nutzer der Bibliothek mit Attributen wie **Vorname**, **Nachname** und einer Liste der aktuell ausgeliehenen Medien (`medias: HashMap<String, Media>`).

### 2. Beziehungen

* Der **`Controller`** aggregiert alle **`Media`**-Objekte und **`Person`**-Objekte.
* Die **`Person`**-Objekte halten eine Referenz auf die von ihnen ausgeliehenen **`Media`**-Objekte.


---

## 🚀 Erste Schritte

### Voraussetzungen

Stellen Sie sicher, dass folgende Software auf Ihrem System installiert ist:

* **Java Development Kit (JDK) 17** oder neuer.
* **Maven** (wird oft mit modernen IDEs wie IntelliJ gebündelt).
* **IntelliJ IDEA** (Empfohlen für eine einfache Ausführung).

### Installation & Start

1.  **Repository klonen:**
    ```bash
    git clone [https://github.com/luigi-coletti/wirLesenNicht.git](https://github.com/luigi-coletti/wirLesenNicht.git)
    cd wirLesenNicht
    ```

2.  **Projekt in IntelliJ öffnen:**
    * Öffnen Sie IntelliJ IDEA.
    * Wählen Sie **Open** und navigieren Sie zum geklonten `wirLesenNicht`-Ordner.
    * IntelliJ sollte das Maven-Projekt automatisch erkennen und initialisieren.

3.  **Anwendung starten:**
    * Navigieren Sie zur Datei `src/main/java/.../Application.java`.
    * Klicken Sie mit der rechten Maustaste auf die Datei und wählen Sie **"Run 'Application.main()'"** oder verwenden Sie die `Run`-Taste in der IDE.

---

## 🌐 Nutzung

Nach dem Start der Anwendung ist die Web-Oberfläche unter folgender Adresse verfügbar:

---
## License
Whaat? We don't need that.

➡️ **http://localhost:8080**

Die Vaadin-Anwendung wird im Browser geladen und Sie können mit der Demo-Bibliotheksverwaltung interagieren.
