# A szállítók - specifikáció

## Közös app feature-ök
- Regisztráció / Bejelentkezés
- Értesítés küldése, ha megjelenik új releváns lehetőség
- Profil:
  - Név
  - Telefonszám
  - Hely
  - Fuvarozónál: kocsi

## Ügyfelek alkalmazása (Olivér)
- kívánt fuvar felvitele
  - maximum ár
  - méret & súly
  - felvétel határidő: -tól, -ig
  - lerakás határidő: -tól, -ig
  - honnan & hova (cím)
  - megjegyzés
- az adott fuvarra jelentkező fuvarozók listázása
  - el kell valamelyik fuvarozót fogadni; ekkor a többi automatikusan elutasításra kerül
- előzmények
- esetleg: értékelő form a fuvar után a fuvarozóról
- fuvar állapota: friss/vannak jelentkezők/elvállalva/épp történik a szállítás/kész
- térkép nézet

## Fuvarozók alkalmazása (Ádám)
- Előzmények
- Kocsi felvétele
  - max méret
  - max súly
  - telephely
  - mennyiért
- profil beállítások alapján készít egy tervet, a lehető legnagyobb profit - eléréséhez vállalt munkákkal
- Fuvar státusz adminisztrálás: Elvállal/Folyamatban/Befejezte
- esetleg: Értékeli a megrendelőt a fuvar után
- Lehetőségek szűrése
- Listázza a lehetséges munkákat
- Munkák megjelenítése térképen

## Webes frontend admin felület (Samu & Levi)
- statisztikák
  - legtöbbet fuvarozók toplistája
  - diagramok fuvarok típusa & egyebek alapján
- fuvarok megtekintése, törlése, státuszának szerkesztése
- felhasználók kezelése (pl. törlés, admin jog adása)
- esetleg: fuvarok térképes nézet (szűréssel)
