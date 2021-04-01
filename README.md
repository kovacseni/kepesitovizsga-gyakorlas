# Vizsgafeladat

A feladatok megoldásához az IDEA fejlesztőeszközt használd! 
Bármely régebbi osztályt megnyithatsz.

Új repository-ba dolgozz. A `pom.xml` tartalmát nyugodtan át lehet másolni. 
Projekt, könyvtár, repository neve legyen: `kepesitovizsga-gyakorlas`. 
GroupId: `training`, artifactId: `kepesitovizsga-gyakorlas`. Csomagnév: `hu.nive.ujratervezes.kepesitovizsga`.

A feladatok megoldásához ajánlom figyelmedbe a cheet sheetet:

https://github.com/Training360/strukturavalto-java-public/blob/master/cheet-sheet/cheet-sheet.md

Először másold át magadhoz a teszteseteket, majd commitolj azonnal!

A három (de ötnek számító) feladatra 3 órád van összesen!

Oldd meg a feladatokat, minden feladat megoldását egy külön commitban
add be!
Ha letelik az idő az első commitodhoz képest és nem fejezted be, megint commitolj, akkor is,
ha nem vagy kész! Utána nyugodtan folytathatod a megoldást, akár több órát is
ülhetsz felette, ha kész vagy, commitolj!

## Számjegyek

A NumberOfDigits osztályba írj egy `getNumberOfDigits()` metódust, amely egy egész számot vár paraméterül!
A metódus adja vissza azt, hogy ha 1-től kezdve a paraméterként átadott számig leírjuk az összes egész számot,
akkor hány számjegyet írtunk le!

## Oltási sorrend

A koronavírus vakcinák elosztási rendszerét kell modellezni ebben a feladatban attól függően, hogy éppen milyen
típusú vakcina érkezik az országba (mivel ugye egyes típusoknál vannak bizonyos prioritások, egyeseknél pedig
bizonyos kizáró tényezők.)
Írj először egy Person osztályt, melynek attribútumai a következők: String name, int age, ChronicDisease chronic és
Pregnancy pregnant! Ez utóbbi kettő két enum, mindkettőnél két lehetőség közül lehet választani: igen vagy nem.
Kell ezután egy Vaccine nevű osztály, amely kezel egy, az oltásra regisztrált emberekből álló listát.
A listát adatbázisból töltsd be a konstruktorban paraméterként kapott DataSource alapján.
Az osztályban található a `List<Person> getVaccinationList()` metódus, amelynek a későbbiekben háromféle különböző eredményt
kell visszaadnia
A következőkben hozz létre még 3 osztályt, amelyeknek az egyszerűség kedvéért 3 különböző vakcinatípus neveit add:
Pfizer, AstraZeneca és SinoPharm. Mindhárom osztály a Vaccine osztály leszármazottja és a regisztráltak listája alapján
prioritási sorrendet állít fel az emberek között, és így készíti el az adott vakcinatípussal oltandók listáját.
A következők a feltételek:
- Az alapértelmezett sorrend a regisztráció sorrendje mindhárom esetben.
- Pfizer vakcinával elsősorban és soron kívül a várandósok oltandóak, közvetlenül utánuk kell besorolni az időseket
(65 év felettiek) és csak utánuk jöhet mindenki más.
- AstraZeneca vakcinával a várandósok eleve nem olthatóak, úgyhogy őket ki kell venni a regisztráltak listájából.
Cserébe előre kell venni a krónikus betegeket, utánuk közvetlenül pedig az időseket. A végén jöhet mindenki más.
- SinoPharm vakcinával a várandósok és a krónikus betegek nem olthatóak, sőt, még az idősek is módjával. Itt előre kell
venni a fiatalokat és utánuk következhetnek az idősek. 
  
A feladat szabadon bővíthető.

## Kutyák

Ez a feladat a március 16-i vizsga adatbázis tábláját felhasználva készült.

Készíts egy DogManager osztályt, amelyben a dogs.csv fájl felhasználásával megvalósítod a következő metódusokat:

- Készíts egy `getCountryByExactDogSpecies(String name)` metódust, amely paraméterül várja egy konkrét kutyafajta nevét, és visszaadja a konkrét országot,
  ahol az a fajta őshonos! Figyelj arra, hogy a metódus kezelje azt is, ha a beérkező paraméter kis-nagybetűk szempontjából
  máshogyan van leírva, mint ahogyan a kutyafajták nevei nálad szerepelnek! Fontos azt is kezelni, ha a beérkező
  paraméter üres.
- Készíts egy `getDogsInAlphabeticalOrderByName()`metódust, amely egy Dog listában adja vissza az összes kutyafajtát, a neveik alapján abc-sorrendben!
- Írj egy `getDogStatistics()` metódust, amely visszaadja egy újabb adatszerkezetben, hogy országonként hány kutyafajta van felsorolva az eredetiben!

(A dogs.csv az `src/main/resources` könyvtárban található.)