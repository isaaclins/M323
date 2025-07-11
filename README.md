# Gilded Rose Kata – Legacy Refactoring (Java + Maven)

Dieses Repository enthält die Legacy-Version der bekannten [Gilded Rose Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata) in Java, vorbereitet für den Einsatz im Unterricht.

## 🧪 Hinweise zur Aufgabe

- Der Code ist absichtlich unübersichtlich gestaltet, um ein realitätsnahes Refactoring zu ermöglichen.
- Ziel ist es, das neue Feature „Conjured Items“ zu implementieren, welches doppelt so schnell an Qualität verliert wie normale Items.
- Tipp: Mit Pin-Down-Tests beginnen, vor der Durchführung von Refactorings.

## 🧾 Anforderungen im Detail

- [Deutsch](GildedRoseRequirements_de.md)
- [Englisch](GildedRoseRequirements.md)

## 🛠️ Weiterführende Schritte

- Einführung von ItemUpdater-Strategien (z.B. durch Vererbung oder Delegation)
- Integration zusätzlicher Features (z.B. Simulation, Konfigurierbarkeit)

## 📦 Projektstruktur (Maven)

``` yaml
GildedRose/
├── pom.xml
└── src
  ├── main
  │ └── java
  │   └── com/gildedrose
  │     ├── GildedRose.java
  │     └── Item.java
  └── test
    └── java
      └── com/gildedrose
         └── GildedRoseTest.java
```

## ▶️ Build & Test (mit Maven)

Voraussetzung: Java 17+ und Maven

``` bash
mvn clean install
```

Tests ausführen:

``` bash
mvn test
```

## 📚 Quellen

- Original-Kata: [GildedRose-Refactoring-Kata by Emily Bache](https://github.com/emilybache/GildedRose-Refactoring-Kata)
- Lizenz der Original-Kata: MIT License