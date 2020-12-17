**Miniprojektin raportti**

*Ryhmä Scrumbags: Atte, Toni, Sanna, Rasmus, Lauri ja Tuomas*
 

**Sprint 1**

Projekti lähti hyvin käyntiin ja saimme aika nopeasti sovelluksen pohjan aikaan. Sovimme heti alussa että tehdään ensin tekstikäyttöliittymä ja siirrytään graafiseen vasta jos projektin lopussa jää ylimääräistä aikaa. Teknisistä ratkaisuista saimme sovittua että noudatetaan Dao-mallia, käytetään javaa, gradlea ja sqliteä. Tämän enempää arkkitehtuurista ei oikeastaan tarvinnutkaan sopia vaan se rakentui itsekseen kun pohja oli saatu kuntoon. Suurempia refaktorointeja ei ensimmäisessä sprintissä tarvittu vaan sovelluksen pohja rakentui konsistentisti sopimamme perusteella.

Ensimmäisessä sprintissä saimme aikaan ne user storyt jotka oli asiakkaan kanssa sovittukin, eli linkin ja kirjan lisäämisen. Mergeäminen ja gitti yleisesti tuotti vähän hankaluuksia koska teimme projektia kaikki yhdessä samaan aikaan ja keskustelimme samalla zoomin välityksellä. Oli kuitenkin todella kätevää olla puheyhteydessä koodauksen aikana. Monet ongelmatilanteet ratkesivat nopeammin näin kuin yksin bugia etsimällä. Toisaalta mergeäminen oli välillä hyvinkin hidasta. Kokeilimme hieman sekä työskentelyä erillisten lyhyiden feature branchien kanssa että suoraan main branchissa työskentelemistä. Mukavammaksi osoittautui lopulta kuitenkin yhdessä branchissa työskenteleminen, sillä havaitsimme myös erillisten branchien työstämisen tuovan omia haasteitaan mergeämiseen.

Ensimmäinen sprintti opetti että yhteistyö etänä on mahdollista, kunhan muistaa kertoa muille mitä on tekemässä milloinkin. Mergeämisen lisäksi muita teknisiä vaikeuksia ei juuri ollut. Jotkut konfiguroinnit eivät heti toimineet oikein mutta joku aina löysi bugit nopeasti eikä näistä ehtinyt muodostua varsinaista ongelmaa. Kuuden tunnin työaika oli tiukka ja seuraavaan sprinttiin otimme vähän vähemmän tehtävää ettei tulisi niin kiire. Jouduimmekin lopulta tinkimään ensimmäisessä sprintissä automaattitestien kattavuudesta

**Sprint 2**

Ohjelman rungon valmistuttua ensimmäisen sprintin aikana oli toisessa sprintissä helpompi ottaa mukaan enemmän asiakkaalle näkyviä toiminnallisuuksia. Tämä näkyy toteutettujen user storien määrässä: toisessa sprintissä toteutettuja storeja oli 7 kappaletta ja yksi edellistä sprintistä jäänyt story, kun ensimmäisessä saimme toteutettua vain yhden storyn. Tämä osaltaan toki viestii myös siitä, että tulimme paremmiksi pilkkomaan toiminnallisuuksia ja näiden taskeja backlogilla selkeästi pienempiin osiin. testauksen osalta linjasimme keskittyvämme erityisesti end-to-end -testaamiseen Cucumber-kirjastoa hyödyntäen, mikä osoittautui yllättävän käytännölliseksi ja hyödylliseksi.

On huomionarvoista, että toisen sprintin toteutettujen user storien joukossa kolmesta jäi testaukseen liittyviä taskeja seuraavaan sprinttiin. Definition of Donen (DOD) kohtaan
> Toiminnallisuus on testattu kaikilla tasoilla automaattisesti ja kattavasti, minkä lisäksi on suoritettu tarvittava määrä tutkivaa testausta

viitaten kyseiset storyt olisi voinut jättää `WIP`-tilaan vielä sprint 2 loppuun, koska taskeja päätettiin jatkaa vielä sprintissä 3. Toinen vaihtoehto olisi ollut tarkentaa kyseistä DoD:n kohtaa, koska tämä jättää tulkinnanvaraisuutta: mikä on riittävän kattava automaattinen testaus? Toisaalta, tämä korostaa tiimiläisten vapautta käyttää omaa harkintaa ja keskittyä olennaiseen mielivaltaisen 100% rivikattavuuden tavoittelemisen sijaan. 

Aiheesta heräsi edellä olevan lisäksi jatkokysymyksiä; mitä, jos toteamme esimerkiksi testikattavuuden sittenkin riittämättömäksi vasta storyn sulkemisen jälkeen? Miten tämä olisi hyvä dokumentoida backlogilla, koska pelkkä testikattavuuden lisääminenhän ei ole mitään käyttäjälle näkyvää toiminnallisuutta ja siten oman storyn arvoista? Sama pätee refaktoroinnille. Ratkaisimme tämän tekemällä pelkästään sprinttiä varten storyn "Toteutettujen toimintojen testikattavuuden parantaminen ja refaktorointi", jota ei siis luonnollisesti näytetty product backlogilla. Pidimme samaa lähestymistapaa myös esimerkiksi projektinhallinnalle (sisältäen mm. backlogin päivitys, releasen teko, demoon valmistautuminen), jota käytimme jo sprint 1 aikaan. Projektinhallintaan liittyviä taskeja todennäköisesti voisi jatkossa karsia, jos a) Scrum-viitekehyksen toimintatavat tulisi ruutiiniksi tiimiläisille ja b) tiimissä olisi selkeästi oma Scrum-master ja Product owner, jotka tekisivät hallinnan tehtäviä päivätöinään.


Havaitsimme sprintin aikana, että storyille suunniteltuja tehtäviä voi olla vaikea tunnistaa ennalta kattavasti ja päädyimmekin lisäämään ja tarkentamaan tehtäviä sprintin aikana. Kaksi taskia merkitsimme vanhentuneeksi - `Obsolete` - tehtävien täytyttyä muiden listattujen tehtävien myötä. Toisaalta prioriteettisyistä ja edellä mainitun oman harkinnan käyttämisen pohjalta päädyimme hylkäämään yhden testaustaskeista merkitsemällä sen `Rejected`. Kaikki tämä on toisaalta täysin luonnollista oppimista prosessin edetessä ja onnistuimme alkusuunnittelussa korkealla tasolla varsin hyvin.

Mielenkiintoisena havaintona vielä sprintin 1 ja sprintin 2 työmääriin liittyen on todettava, että vaikka toteutettujen storien määrässä on selkeä ero, on taskien story pointteihin verrattuna työmääräarvio hyvin yhtenevä; molempien sprinttien velositeetti oli likipitäen 30 storypistettä.

**Sprint 3**

Kolmas sprintti oli mahdollisesti kaikista helpoin taskien suunnittelun kannalta, koska ohjelmaan laajennettavat uudet toiminnallisuudet perustuivat jo aiemmin lisättyihin toiminnallisuuksiin, eikä ohjelmaan enää tullut mitään suuria tietorakenteisiin vaikuttavia muutoksia. Tosin edelleen oli hieman vaikeuksia saada hahmotettua erilaisten taskien kokonaisuutta, ja taskeja päädyttiin lisäämään kesken sprintin kuten aikaisemmassakin sprintissä. Tästä huolimatta sprintin user storyt saatiin ajallaan valmiiksi.

Viimeisessä sprintissä laajennettiin vielä testausta koko ohjelman osalta kun kaikki user storyt oltiin saatu tehtyä. Huomasimme että ohjelman rakenteen huolellinen suunnittelu on kannattavaa, koska se helpottaa tulevien toiminnallisuuksien lisäämisen ja varsinkin testaamisen ilman turhaa toisteisuutta. Kohtasimme loppua kohti lieviä copy/paste ongelmia koodissa jotka oltaisiin voitu mahdollisesti välttää. Tosin koska kyseessä on pikkuhiljaa product ownerin toiveiden mukaan laajennettava ohjelmisto, on vaikeaa alussa ohjelman rakennetta laatiessa tietää täsmällisesti mitä ominaisuuksia ohjelmaan tullaan lisäämään myöhemmin.

Kolmannessa sprintissä merge-konflikteja syntyi vähemmän koska olimme tarkemmin määritelleet taskien ja tapaamisten avulla kuka tekee mitä ja milloin. Kaikin puolin työskentely sujui melko jouhevasti, sillä ryhmä oli ehtinyt jo hitsautua yhteen ja omaksuneet ryhmän käytänteet
________________________
RAPORTIN OHJEET 

Vertaispalautteen lisäksi ryhmä laatii projektin kulusta pienen raportin (noin 2 sivua)

   - kerrataan jokaisen sprintin aikana kohdatut ongelmat (prosessiin-, projektityöskentelyyn- ja teknisiin asioihin liittyvät)
   - mikä sujui projektissa hyvin, mitä pitäisi parantaa seuraavaa kertaa varten
   - mitä asioita opitte, mitä asioita olisitte halunneet oppia, mikä tuntui turhalta
   - jos raportti puuttuu, vähennetään ryhmältä 2 pistettä
   - raportti palautetaan lisäämällä raporttiin linkki projektin GitHubin Readme:hen
   - raportista tulee ilmetä jokaisen projektiin osallistuneen nimi
   - raportin deadline lauantaina 19.12. klo 23:59


