# Scrumbags
Ohjelmistotuotanto miniprojekti

Lukuvinkkikirjasto<br>

[Backlogit ja muut dokumentit](https://docs.google.com/spreadsheets/d/1cjemOe1V6ia_IiacJ4TIb77QLeIoD4wPJo6hCqYEEDg/edit?usp=sharing).
  
[![codecov](https://codecov.io/gh/Latelaukki/Scrumbags/branch/main/graph/badge.svg)](https://codecov.io/gh/Latelaukki/Scrumbags)

![Java CI with Gradle](https://github.com/Latelaukki/Scrumbags/workflows/Java%20CI%20with%20Gradle/badge.svg)

## Käyttöohjeet

Käy lataamassa uusin [release](https://github.com/Latelaukki/Scrumbags/releases/tag/v2) ja käynnistä se komentoriviltä

```
java -jar Scrumbags_v2.jar
```


Voit myös tehdä jar-tiedoston itse:

* Lataa projekti koneellesi ja mene kansioon Scrumbags
* Luo shadow jar-tiedosto komennolla

```
./gradlew shadowJar
```

* Jar-tiedosto *Scrumbags-all.jar* luodaan kansioon Scrumbags/build/libs/. Jos et löydä sitä niin suorita komento *tree*.
* Mene kansioon jossa tiedosto sijaitsee ja aja se komennolla

```
java -jar Scrumbags-all.jar
```

* Jacoco-testikattavuusraportti luodaan projektin juurikansiossa suorittamalla komento. Huom. suositeltavaa on suorittaa prokjektille ensin komennot ./gradlew clean sekä ./gradlew build

```
./gradlew test jacocotestReport
```

* Tämän jälkeen jacoco-testikattavuusraporttia voi tarkastella juurikansiossa esimerkiksi komennolla

```
firefox build/reports/jacoco/test/html/index.html
```

### Sovelluksen toiminnot

Sovelluksen käynnistymisen jälkeen sovellus listaa käytettävissä olevan toiminnot:

```
komennot:
q) Poistu ohjelmasta
1) Lisää kirja
2) Lisää linkki
3) Lisää podcast
4) Hae kirjamerkkejä
5) Listaa kaikki lukuvinkit
6) Poista lukuvinkki
```

Tämän jälkeen käyttäjää pyydetään valitsemaan jokin yllä listatuista toiminnoista:

```
Anna komennon numero:

```

#### Kirjan lisääminen

Syötä komento `1`:

```
Anna komento:
1
```
ja paina `Enter`.

Tämän jälkeen anna yksi kerrallaan:
- kirjan nimi
- kirjailijan nimi
- kirjan ISBN
- kirjan sivumäärä
- kirjan julkaisuvuosi

Esimerkiksi:

```
Anna kirjan nimi.
Testikirja
Anna kirjailijan nimi.
Testi Kirjailija
Anna ISBN.
123-456
Anna kirjan sivumäärä
500
Anna kirjan julkaisuvuosi
2020
```

Tämän jälkeen käyttäjältä varmistetaan vielä, onko annetut tiedot oikein:

```
LISÄTÄÄN KIRJA: 
NIMI: Testikirja
KIRJAILIJA: Testi Kirjailija
ISBN: 123-456
SIVUMÄÄRÄ: 500
JULKAISUVUOSI: 2020
ONKO OK? [k/e]
```

Jos syötteet ovat oikein, syötä `k` ja paina `Enter`. Muussa tapauksessa syötä `e` ja paina `Enter`.

Jos kirjan lisääminen tapahtui onnistuneesti, käyttöliittymä ilmoittaa seuraavasti:

```
Kirja lisätty onnistuneesti.
```
#### Linkin lisääminen
Syötä komento `2`:

```
Anna komento:
2
```
ja paina `Enter`.

Tämän jälkeen anna yksi kerrallaan:
- Linkin nimi
- Linkin osoite (url)

Esimerkiksi:

```
Anna Linkin nimi.
Ohjelmistotuotanto 2020
Anna URL.
https://ohjelmistotuotanto-hy.github.io/
```
Tämän jälkeen käyttäjältä varmistetaan vielä, onko annetut tiedot oikein:

```
LISÄTÄÄN URL: 
NIMI: Ohjelmistotuotanto 2020
URL: https://ohjelmistotuotanto-hy.github.io/
ONKO OK? [k/e]
```

Jos syötteet ovat oikein, syötä `k` ja paina `Enter`. Muussa tapauksessa syötä `e` ja paina `Enter`. 

Jos linkin lisääminen tapahtui onnistuneesti, käyttöliittymä ilmoittaa seuraavasti:

```
Linkki lisätty onnistuneesti.
```

#### Podcastin lisääminen

Syötä komento `3`:

```
Anna komento:
3
```
ja paina `Enter`.

Tämän jälkeen anna yksi kerrallaan:
- Podcastin nimi
- Julkaisijan nimi
- Podcastin osoite (url)
- Podcastin rss

Esimerkiksi:

```
Anna podcastin nimi (pakollinen tieto).
BBC Newscast
Anna julkaisijan nimi (ohita syöttämällä "q").
BBC
Anna url (ohita syöttämällä "q").
https://www.bbc.co.uk/programmes/p05299nl/episodes/downloads
Anna podcastin rss (ohita syöttämällä "q")
https://podcasts.files.bbci.co.uk/p05299nl.rss
```

Tämän jälkeen käyttäjältä varmistetaan vielä, onko annetut tiedot oikein:

```
LISÄTÄÄN PODCAST: 
NIMI: BBC Newscast
julkaisija: BBC
url: https://www.bbc.co.uk/programmes/p05299nl/episodes/downloads
rss: https://podcasts.files.bbci.co.uk/p05299nl.rss
ONKO OK? [k/e]
```

Jos syötteet ovat oikein, syötä `k` ja paina `Enter`. Muussa tapauksessa syötä `e` ja paina `Enter`. 

Jos podcastin lisääminen tapahtui onnistuneesti, käyttöliittymä ilmoittaa seuraavasti:

```
Podcast lisätty onnistuneesti.
```

#### Hakeminen, poistaminen ja listaaminen

Hakeminen, poistaminen ja listaaminen toimivat kaikki samalla periaatteella:
 - Syötetään haettavan/poistettavan/listattavan kohteen parametrit
 - Poistettaessa varmistetaan vielä poistettava kohde 

#### Sovelluksen sulkeminen
Syötä komento `q`:

```
Anna komento:
q
```
ja paina `Enter`.

## Definition of Done

* User story on määritelty ja sen merkitys analysoitu tarkasti
* Toiminnallisuus on toteutettu kooditasolla
* Toiminnallisuus on testattu kaikilla tasoilla automaattisesti ja kattavasti, minkä lisäksi on suoritettu tarvittava määrä tutkivaa testausta
* Toiminnallisuus on integroitu onnistuneesti osaksi muuta sovellusta
* Sovelluksen toiminta on dokumentoitu


