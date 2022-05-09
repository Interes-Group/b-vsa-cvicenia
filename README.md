# B-VSA LS 21/22 Cvičenie 11

[![GitHub](https://img.shields.io/github/license/interes-group/b-vsa-cvicenia)](https://unlicense.org)
[![Java](https://img.shields.io/badge/Java-8-red)](https://openjdk.java.net/projects/jdk/8/)
[![EclipseLink](https://img.shields.io/badge/EclipseLink-2.7.10-blue)](https://www.eclipse.org/eclipselink/)

Tento repozitár je určený pre výučbu predmetu B-VSA vyučovaný na FEI STU Bratislava počas letného semestra 2021/2022.
Jednotlivé branches repozitáru demonštrujú problematiku preberanú na jednotlivých cvičeniach.

Cieľom cvičenia 11 je ukážka definovanie REST špecifikácie pomocou štandardu OpenAPI3 (viď
súbor [b-vsa-openapi.yml](src/main/resources/b-vsa-openapi.yaml)) a demonštrovať implementáciu REST webových služieb
pomocou JAX-RS (framework jersey), nastavenia aplikačného servera a testovanie HTTP požiadavok.
Projekt taktiež zahŕňa ukážku práce s HTTP hlavičkami a autentifikáciou používateľa cez Basic Auth.

Pre demonštráciu problematiky cvičenie využíva databázu MySQL a JPA implementáciu EclipseLink, Jersey a HTTP server
Grizzly2. Jednotlivé triedy aplikácie slúžia výhradne na demonštráciu problematiky.

## Inštalácia a spustenie

Cvičenie je implementované, ako [Maven](https://maven.apache.org/) projekt
pre [Java 1.8](https://openjdk.java.net/install/). Nakoľko cvičenie demonštruje prácu s databázou je potrebné mať
nainštalovanú databázu [MySQL 5.7+](https://www.mysql.com/).

### Nastavenie projektu

Projekt je možné otvoriť v ľubovolnom modernom IDE (testované na IntelliJ Idea a Visual Studio Code), podporujúci Maven
projekt manažment.

Pre kompiláciu projektu do JAR archívu je možné použiť príkaz:

```shell
mvn clean package verify
```

### Vytvorenie databázy

Pre správne otestovanie funkcionality aplikácie je potrebné mať nainštalovanú databázy MySQL vo verzií 5.7 a vyššie. Po
spustení databázové servera je potrebné vytvoriť databázu a používateľ pre potreby aplikácie.

Názov databázy a prihlasovacie údaje používateľa musia byť totožné s uvedenými v
súbor [persistence.xml](src/main/resources/META-INF/persistence.xml). Uvedený SQL skript pracuje s defaultnými
hodnotami.

```sql
CREATE DATABASE IF NOT EXISTS VSA_CV11 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'vsa'@'localhost' IDENTIFIED BY 'vsa';
GRANT ALL PRIVILEGES ON VSA_CV11.* TO 'vsa'@'localhost';
FLUSH PRIVILEGES;
```