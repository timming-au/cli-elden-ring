# FIT2099 Assignment (Semester 1, 2023)
# Elden Ring

## Contribution Log
[Link to spreadsheet](https://docs.google.com/spreadsheets/d/1c-rrjRwR8Huk-2wwv6Zpmb_kAb5kch_zlCfuI5olHSo/edit?usp=sharing)

## MA_Lab04_Group8
Team members:
- Kok Tim Ming (32619138)
- Wee Jun Lin (32620861)
- Lau Ka-Kiat (32967136)

## Design Rationale
Our main objective involves designing an extensible codebase around the existing Elden Ring game engine. To achieve this, design goals have been set to be faithfully adhered throughout the whole design and development process.

We strive to:
- Reduce code redundancy (DRY): Achieve through abstractions to reuse code. This will make the codebase more concise and easier to maintain.

- Avoid multiple inheritance (diamond problem): Done by abstracting methods through interfaces (ISP) or utilising composition.

- Create an extensible codebase, accounting for future feature additions and development. Classes and modules should only be made to be extended (OCP).

- Follow the Interface Segregation Principle (ISP). This will prevent unused code and will make adding new features easier as no refactoring is needed.

- Follow Single Responsibility Principle (SRP): each class should only have one responsibility so that it is more focused. This will make the codebase easier to be extended with new functionality and easier to maintain.

