# EMT en Directe 🚌

Aplicació web  que simula el sistema de transport públic de l'EMT de Palma de Mallorca. 

Els busos es mouen automàticament per les rutes reals de la ciutat, avançant punt a punt amb una probabilitat d'aturada que simula possibles atascos. 

L'usuari pot explorar les línies i parades sobre un mapa interactiu, desar les seves parades favorites i seguir la posició dels busos en temps real mitjançant polling automàtic cada 10 segons.

---

## Instal·lació i execució

### Requisits previs
- Java 21
- Maven
- Angular 
- MySQL

### 1. Clona el repositori
```bash
git clone https://github.com/canvion/emt-en-directe.git
cd emt-en-directe
```

### 2. Configura la base de dades
Obre MySQL i executa:
```sql
CREATE DATABASE emt_directe;
CREATE USER 'emt_user'@'localhost' IDENTIFIED BY 'emt_password';
GRANT ALL PRIVILEGES ON emt_directe.* TO 'emt_user'@'localhost';
FLUSH PRIVILEGES;
```
La configuració ja està preparada a `application.properties`, no cal modificar res més.

### 3. Arrenca el backend
```bash
cd backend
mvn spring-boot:run
```

### 4. Arrenca el frontend
```bash
cd frontend
npm install
ng serve
```
L'aplicació s'obre a `http://localhost:4200`.

---

##  Credencials de prova

Per accedir a l'aplicació cal crear un usuari des del formulari de registre.

---

##  Captures de pantalla





##  Funcionalitats

-  Mapa interactiu centrat a Palma amb totes les parades de les línies
-  Visualització en temps real dels busos simulats (polling cada 10 segons)
-  Els busos fan el recorregut d'anada i tornada, amb possibilitat d'aturada simulant atascos
-  Filtre de parades per línia
-  Sistema de parades favorites per a usuaris registrats
-  Registre i login d'usuaris amb autenticació JWT

---

##  Documentació de l'API

Consulta el fitxer [API.md](./API.md) per veure tots els endpoints disponibles.
