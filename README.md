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

### **Primera vista en accedir a http://localhost:4200, es login**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 16 53 24" src="https://github.com/user-attachments/assets/1c94374e-b0c7-4fae-8c2d-bdd23c2e8068" />

### **Pàgina del registre, quan clickam des de login a _No tens compte? Registra't_**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 04 28" src="https://github.com/user-attachments/assets/afe30360-2a73-470b-8041-14410b70e1cf" />

### **Primera visualització en fer login, de la pàgina principal**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 04 55" src="https://github.com/user-attachments/assets/3bbb0a3b-fb28-4168-ad14-59408ef59795" />

### **Visualització de la pestanya de línies**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 05 01" src="https://github.com/user-attachments/assets/685f139f-41d1-4165-b047-9aa7f2b2c902" />

### **Visualització de la pestanya d'una línia en clickar des de línies, amb la línia dibuixada al mapa**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 05 07" src="https://github.com/user-attachments/assets/fa445e3a-85f8-4ec4-86da-fc5ea08a0e83" />

### **Visualització de la pestanya de favorits**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 05 31" src="https://github.com/user-attachments/assets/398b2239-347f-4996-bacd-8fa9beb8887d" />

### **Visualització de la popup d'una parada, amb el nom, el codi i el botó d'afegir a favorits**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 09 10" src="https://github.com/user-attachments/assets/595ebc6e-92d3-47e0-89c6-aed4ed7f2bea" />

### **Visualització de la popup d'un bus, amb el número de la línea del bus**

<img width="618" height="339" alt="Captura de pantalla 2026-02-20 a las 17 09 17" src="https://github.com/user-attachments/assets/2352469a-5ba3-4690-be94-da9f83f04777" />


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
