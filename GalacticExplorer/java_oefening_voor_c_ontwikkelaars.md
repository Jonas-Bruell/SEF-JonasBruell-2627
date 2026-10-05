# 🚀 Galactic Explorer — Java voor C#-programmeurs

## Situering

Je werkt voor **Galactic Explorer**, een fictief ruimtevaartagentschap dat ruimtemissies organiseert.

Je hebt al ervaring met **C#**, maar leert nu Java.

In deze oefening bouw je stap voor stap een programma waarmee Galactic Explorer zijn ruimtemissies kan beheren.

> **Belangrijk:** elk deel bouwt verder op het vorige deel. Bewaar je oplossing na elk deel.


# ⭐ Deel 1 — Java-syntax en methodes

Maak een Java-project met een klasse `Main`.

De klasse moet de methode `main` bevatten met exact deze signatuur:

    public static void main(String[] args)

## 1.1 Variabelen

Maak in `main` exact deze variabelen:

    String missionName = "Mars Explorer";
    String destination = "Mars";
    int duration = 210;
    double distance = 225.0;
    boolean successful = true;
    char missionCode = 'M';

## 1.2 Gegevens afdrukken

Druk vervolgens deze informatie af:

    ========================================
           GALACTIC EXPLORER
    ========================================

    Mission: Mars Explorer
    Code: M
    Destination: Mars
    Duration: 210 days
    Distance: 225.0 million km
    Successful: true

Gebruik `System.out.println()` en `printf()`.

## 1.3 Voorwaarde

Voeg een `if/else` toe.

Als `successful` gelijk is aan `true`, druk je af:

    Mission status: SUCCESS

Als `successful` gelijk is aan `false`, druk je af:

    Mission status: FAILED

## 1.4 Bemanning

Maak een `String`-array met exact deze drie namen:

    Alice
    Bob
    Charlie

Druk de bemanning als volgt af:

    Crew:
    - Alice
    - Bob
    - Charlie

Gebruik hiervoor een `for`-lus.

## 1.5 Countdown

Maak een variabele:

    int countdown = 5;

Gebruik een `while`-lus om af te drukken:

    Countdown:
    5
    4
    3
    2
    1
    Liftoff!

## 1.6 Methode `calculateReturnDistance`

Maak buiten `main` de methode:

    public static double calculateReturnDistance(double distance)

De methode geeft het dubbele van `distance` terug.

Roep de methode vanuit `main` aan met de waarde `225.0`.

Druk af:

    Estimated return distance: 450.0 million km

## 1.7 Methode `printCrew`

Maak buiten `main` de methode:

    public static void printCrew()

De methode moet zelf de array met de drie bemanningsleden bevatten en de bemanning afdrukken.

Roep deze methode vanuit `main` aan.

Maak in dit deel nog geen eigen klasse behalve `Main`.

---

# ⭐ Deel 2 — Klasse `Mission`

Maak een bestand `Mission.java`.

## 2.1 Velden

De klasse `Mission` heeft exact deze vijf private velden:

| Veld | Type |
|---|---|
| `name` | `String` |
| `destination` | `String` |
| `duration` | `int` |
| `distance` | `double` |
| `successful` | `boolean` |

## 2.2 Constructor

Maak exact deze constructor:

    public Mission(
        String name,
        String destination,
        int duration,
        double distance,
        boolean successful
    )

Initialiseer de velden met `this`.

## 2.3 Getters

Maak exact deze getters:

    public String getName()
    public String getDestination()
    public int getDuration()
    public double getDistance()
    public boolean isSuccessful()

## 2.4 Setters

Maak exact deze setters:

    public void setName(String name)
    public void setDestination(String destination)
    public void setDuration(int duration)
    public void setDistance(double distance)
    public void setSuccessful(boolean successful)

## 2.5 Gebruik de klasse

Verwijder de losse missievariabelen uit `main`.

Maak deze twee objecten.

### Mission 1

- name: `Mars Explorer`
- destination: `Mars`
- duration: `210`
- distance: `225.0`
- successful: `true`

### Mission 2

- name: `Moon Base`
- destination: `Moon`
- duration: `30`
- distance: `384400.0`
- successful: `false`

## 2.6 Getter testen

Druk met getters af:

    Mission 1: Mars Explorer
    Destination: Mars
    Duration: 210

## 2.7 Setter testen

Verander met `setSuccessful()` de status van `Moon Base` van `false` naar `true`.

Druk daarna af:

    Moon Base successful: true

De velden mogen niet rechtstreeks vanuit `Main` aangesproken worden.

Dit mag dus niet:

    marsMission.name

---

# ⭐ Deel 3 — Overerving

Maak twee gespecialiseerde missietypes:

- `ResearchMission`
- `CargoMission`

## 3.1 `ResearchMission`

Maak `ResearchMission.java`.

De klasse erft van `Mission`.

Voeg het private veld toe:

    String researchTopic

Maak deze constructor:

    public ResearchMission(
        String name,
        String destination,
        int duration,
        double distance,
        boolean successful,
        String researchTopic
    )

Gebruik `super(...)` voor de eigenschappen van `Mission`.

Maak:

    public String getResearchTopic()
    public void setResearchTopic(String researchTopic)

## 3.2 `CargoMission`

Maak `CargoMission.java`.

De klasse erft van `Mission`.

Voeg het private veld toe:

    double cargoWeight

Maak deze constructor:

    public CargoMission(
        String name,
        String destination,
        int duration,
        double distance,
        boolean successful,
        double cargoWeight
    )

Gebruik `super(...)`.

Maak:

    public double getCargoWeight()
    public void setCargoWeight(double cargoWeight)

## 3.3 Objecten maken

Maak in `Main` deze twee objecten.

### ResearchMission

- name: `Mars Life`
- destination: `Mars`
- duration: `210`
- distance: `225.0`
- successful: `true`
- researchTopic: `Search for microbial life`

### CargoMission

- name: `Supply Run 7`
- destination: `Moon`
- duration: `30`
- distance: `384400.0`
- successful: `false`
- cargoWeight: `12500`

Druk voor `Mars Life` af:

    === Research Mission ===
    Name: Mars Life
    Destination: Mars
    Research topic: Search for microbial life

Druk voor `Supply Run 7` af:

    === Cargo Mission ===
    Name: Supply Run 7
    Destination: Moon
    Cargo weight: 12500.0 kg

---

# ⭐ Deel 4 — Abstracte klasse en overriding

## 4.1 Maak `Mission` abstract

Maak van `Mission` een abstracte klasse.

## 4.2 Abstracte methode

Voeg aan `Mission` toe:

    public abstract void printDetails();

Deze methode heeft geen body.

## 4.3 Override in `ResearchMission`

Implementeer `printDetails()` in `ResearchMission`.

Gebruik `@Override`.

De uitvoer moet zijn:

    === Research Mission ===
    Name: Mars Life
    Destination: Mars
    Duration: 210 days
    Distance: 225.0 million km
    Successful: true
    Research topic: Search for microbial life

Gebruik de getters van `Mission` voor de geërfde eigenschappen.

## 4.4 Override in `CargoMission`

Implementeer `printDetails()` in `CargoMission`.

Gebruik `@Override`.

De uitvoer moet zijn:

    === Cargo Mission ===
    Name: Supply Run 7
    Destination: Moon
    Duration: 30 days
    Distance: 384400.0 km
    Successful: false
    Cargo weight: 12500.0 kg

## 4.5 Polymorfisme

Maak in `Main` exact twee variabelen met het type `Mission`:

    Mission mission1 = new ResearchMission(...);
    Mission mission2 = new CargoMission(...);

Roep daarna aan:

    mission1.printDetails();
    mission2.printDetails();

Controleer dat de juiste `printDetails()` wordt uitgevoerd.

## 4.6 Test

Probeer tijdelijk een object van het type `Mission` te maken.

Dit moet niet compileren omdat `Mission` abstract is.

Verwijder deze test daarna.

---

# ⭐ Deel 5 — Interface en meerdere implementaties

## 5.1 Interface `EnergyCalculable`

Maak `EnergyCalculable.java`.

Definieer de interface `EnergyCalculable`.

De interface bevat exact één methode:

    double calculateEnergy();

## 5.2 `ResearchMission` implementeren

Laat `ResearchMission` `EnergyCalculable` implementeren.

Implementeer:

    public double calculateEnergy()

Gebruik deze formule:

    duration * 1500

Voor `Mars Life` moet het resultaat `315000.0` zijn.

## 5.3 `CargoMission` implementeren

Laat `CargoMission` eveneens `EnergyCalculable` implementeren.

Gebruik:

    duration * 1000 + cargoWeight * 0.5

Voor `Supply Run 7` moet het resultaat `36250.0` zijn.

## 5.4 Gebruik via de interface

Maak in `Main` exact deze twee variabelen met het type `EnergyCalculable`:

    EnergyCalculable researchMission = new ResearchMission(...);
    EnergyCalculable cargoMission = new CargoMission(...);

Roep `calculateEnergy()` aan en druk af:

    Mars Life energy: 315000.0
    Supply Run 7 energy: 36250.0

Voor deze test mag je de objecten dus niet declareren als `ResearchMission` en `CargoMission`.

Het doel is expliciet te tonen dat beide implementaties via de interface gebruikt kunnen worden.

---

# ⭐ Deel 6 — `equals`, `hashCode` en collections

## 6.1 `equals()`

Override in `Mission`:

    public boolean equals(Object obj)

Twee missies zijn gelijk als ze dezelfde:

- `name`
- `destination`

hebben.

Alle andere eigenschappen worden genegeerd door `equals()`.

Maak twee afzonderlijke `ResearchMission`-objecten met:

### Object A

- name: `Mars Life`
- destination: `Mars`
- duration: `210`
- distance: `225.0`
- successful: `true`
- researchTopic: `Search for microbial life`

### Object B

- name: `Mars Life`
- destination: `Mars`
- duration: `500`
- distance: `999.0`
- successful: `false`
- researchTopic: `Different research`

Deze uitdrukking moet `true` opleveren:

    missionA.equals(missionB)

## 6.2 `hashCode()`

Override in `Mission`:

    public int hashCode()

Gebruik exact dezelfde eigenschappen als in `equals()`:

- `name`
- `destination`

Als twee missies volgens `equals()` gelijk zijn, moeten hun hashcodes gelijk zijn.

Gebruik hiervoor `Objects.hash(...)`.

## 6.3 Array

Maak deze drie missies:

1. `Mars Life`
2. `Supply Run 7`
3. `Lunar Research`

Maak exact deze array:

    Mission[] missionArray = {
        marsLife,
        supplyRun,
        lunarResearch
    };

Doorloop de array met een enhanced `for`-lus.

Verwachte uitvoer:

    ARRAY
    Mars Life
    Supply Run 7
    Lunar Research

## 6.4 `List`

Maak:

    List<Mission> missions = new ArrayList<>();

Voeg de drie missies toe in deze volgorde:

1. `marsLife`
2. `supplyRun`
3. `lunarResearch`

Gebruik `add()`.

Doorloop de lijst met een enhanced `for`-lus.

Verwachte uitvoer:

    LIST
    Mars Life
    Supply Run 7
    Lunar Research

## 6.5 `Map`

Maak:

    Map<String, Mission> missionMap = new HashMap<>();

Gebruik de naam van de missie als key.

Voeg toe:

    "Mars Life" -> marsLife
    "Supply Run 7" -> supplyRun
    "Lunar Research" -> lunarResearch

Gebruik `put()`.

Zoek daarna `"Mars Life"` op met `get()`.

Roep op de gevonden missie `printDetails()` aan.

## 6.6 `Set`

Maak:

    Set<Mission> uniqueMissions = new HashSet<>();

Voeg toe:

- `marsLife`
- `supplyRun`
- `lunarResearch`

Maak daarna een nieuw `ResearchMission`-object met:

- name: `Mars Life`
- destination: `Mars`
- duration: `999`
- distance: `999.0`
- successful: `false`
- researchTopic: `Completely different research`

Voeg dit object toe aan de `Set`.

Omdat dit object volgens `equals()` gelijk is aan `marsLife`, moet:

    uniqueMissions.size()

de waarde `3` opleveren.

Druk af:

    SET
    Number of unique missions: 3

---

# Java versus C# — belangrijke aandachtspunten

| C# | Java |
|---|---|
| `string` | `String` |
| `bool` | `boolean` |
| `Console.WriteLine()` | `System.out.println()` |
| `:` voor overerving | `extends` |
| `:` voor interfaces | `implements` |
| `base(...)` | `super(...)` |
| properties | getters/setters |
| `override` | `@Override` |
| `List<T>` | `List<T>` |
| `Dictionary<K,V>` | `Map<K,V>` |
| `HashSet<T>` | `HashSet<T>` |
| `Count` | `size()` |
| `Equals()` | `equals()` |
| `GetHashCode()` | `hashCode()` |
| `foreach` | `for (... : ...)` |

## Extra aandachtspunt: `==` versus `equals()`

Gebruik voor objecten niet:

    missionA == missionB

om inhoudelijke gelijkheid te testen.

Gebruik:

    missionA.equals(missionB)

`==` controleert bij objecten of beide variabelen naar hetzelfde object verwijzen.

`equals()` controleert de gelijkheid volgens de implementatie van `equals()`.

## Extra uitdaging

Voeg een derde type missie toe:

    ExplorationMission

De klasse:

- erft van `Mission`;
- implementeert `EnergyCalculable`;
- heeft een veld `numberOfPlanets`;
- override `printDetails()`;
- implementeert `calculateEnergy()`.

Gebruik:

    duration * 2000 + numberOfPlanets * 5000

Voeg de nieuwe missie ook toe aan de `List`, `Map` en `Set`.