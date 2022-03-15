# B-VSA LS 21/22 Cvičenie 1

[![GitHub](https://img.shields.io/github/license/interes-group/b-vsa-cvicenia)](https://unlicense.org)
[![Java](https://img.shields.io/badge/Java-8-red)](https://openjdk.java.net/projects/jdk/8/)
[![EclipseLink](https://img.shields.io/badge/EclipseLink-2.7.10-blue)](https://www.eclipse.org/eclipselink/)

Tento repozitár je určený pre výučbu predmetu B-VSA vyučovaný na FEI STU Bratislava počas letného semestra 2021/2022.
Jednotlivé branches repozitáru demonštrujú problematiku preberanú na jednotlivých cvičeniach.

Cieľom cvičenia 1 je demonštrácia pripojenia na SQL databázu, priblížiť problematiku ORM mapovania a úvod do technológie
JPA. Cvičenie sa odvíja
od [študijných materiálov](https://docs.google.com/document/d/1LAcj__vV2FDfsYaY5DaqIWrhxxAmYZt-h3GbmVIbscY/)
dostupných na [stránke predmetu](https://uim.fei.stuba.sk/predmet/b-vsa/).

Pre demonštráciu problematiky cvičenie využíva databázu MySQL a H2. Jednotlivé triedy aplikácie slúžia výhradne na
demonštráciu problematiky.

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

Pre správne otestovanie funkcionality pripojenia aplikácie na MySQL je potrebné mať nainštalovanú databázy MySQL vo
verzií 5.7 a vyššie. Po spustení databázové servera je potrebné vytvoriť databázu a používateľ pre potreby aplikácie.

Názov databázy a prihlasovacie údaje používateľa musia byť totožné s uvedenými v
súbor [Cvicenie1](src/main/java/sk/stuba/fei/uim/vsa/cv1/Cvicenie1.java). Uvedený SQL skript pracuje s defaultnými
hodnotami.

```sql
CREATE DATABASE IF NOT EXISTS VSA_CV1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'vsa'@'localhost' IDENTIFIED BY 'vsa';
GRANT ALL PRIVILEGES ON VSA_CV1.* TO 'vsa'@'localhost';
FLUSH PRIVILEGES;
```