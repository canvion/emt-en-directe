# EMT en Directe 🚌

Aplicació web  que simula el sistema de transport públic de l'EMT de Palma de Mallorca. 

Els busos es mouen automàticament per les rutes reals de la ciutat, avançant punt a punt amb una probabilitat d'aturada que simula possibles atascos. 

L'usuari pot explorar les línies i parades sobre un mapa interactiu, desar les seves parades favorites i seguir la posició dels busos en temps real mitjançant polling automàtic cada 10 segons.

---

## Instal·lació i execució

### Requisits previs
- Java 17
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

## Credencials de prova

| Usuari | Contrasenya |
|--------|-------------|
| usuari1 | password |
---

##  Captures de pantalla

### **Primera vista en accedir a http://localhost:4200**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/88441d45-bff0-496c-b254-11eaa48f88ed" />

### **Pàgina de login quan clickam a _incia sessió_**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/c0581c8c-283e-42ef-9dc0-52686036031c" />


### **Pàgina del registre, quan clickam des de login a _No tens compte? Registra't_**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/9fa38107-1710-422f-a7f9-88afd26ca7b5" />


### **Visualització en fer login de la pàgina principal**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/eac5756f-7651-4cb7-8d72-87f82879a64c" />


### **Visualització de la pestanya de línies**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/460a6f2a-af0b-46d5-b141-5ba3649408f8" />

### **Visualització de la pestanya d'una línia en clickar des de línies, amb la línia dibuixada al mapa**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/e25e10cf-7605-48d6-93bd-f6f25e3bb475" />

### **Visualització de la pestanya de favorits**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/3e3adb26-9137-49e5-ad12-fe8208b98bcb" />

### **Visualització de la popup d'una parada, amb el nom, el codi i el botó d'afegir a favorits**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/e234c83e-4b0c-42fa-a643-979e7c4ff32e" />

### **Visualització de la popup d'un bus, amb el número de la línea del bus**

<img width="618" height="339" alt="image" src="https://github.com/user-attachments/assets/777c3c30-b120-44c9-ac4a-f1dba80bee15" />

---

## Funcionalitats

- Mapa interactiu centrat a Palma amb totes les parades de les línies
- Visualització en temps real dels busos simulats (polling cada 10 segons)
- Filtre de parades amb una línia que les uneix en clickar una línia
- Sistema de parades favorites per a usuaris registrats
- Registre i login d'usuaris amb autenticació JWT

---

## Com funciona la simulació dels busos

Els busos es mouen punt a punt pel recorregut real de la línia.
El backend avança la posició del bus a cada crida, amb una probabilitat d'aturada que simula possibles atascos. 
El frontend fa polling cada 10 segons per actualitzar la posició al mapa automàticament.

---

## Arquitectura Backend

Desenvolupat amb Spring Boot 3 + Java + MySQL, seguint el patró 
Controller-Service-Repository.

### Entitats i Model de Dades
- `Usuario`, `Linea`, `Parada`, `Bus`, `Favorito`
- `LineaParada` — relació N:M entre línies i parades, amb ordre i camp `esParada` per diferenciar parades reals de punts intermedios

### Autenticació JWT
El sistema usa BCrypt per encriptar contrasenyes i JWT amb durada de 24h.  Compost per `JwtAuthenticationFilter`, `JwtTokenUtil` i `CustomUserDetailsService`.

### Seguretat i CORS
CORS configurat per a `localhost:4200`. 
Rutes públiques: auth, register, lineas, paradas, buses. 
Rutes protegides: favoritos (requereixen JWT).

### Dades Inicials
5 línies reals de Palma (1, 5, 23, 33, 35) carregades automàticament a l'inici mitjançant `CargarDatos.java` , amb coordenades reals de parades i punts intermedis.

---

## Disseny i Arquitectura Frontend

El frontend és una SPA desenvolupada amb Angular standalone i TypeScript.

### Interfície
El mapa ocupa el 100% de la pantalla com a vista principal. 
La interfície flotant segueix un estil inspirat en iOS: títol centrat a la part superior, botó de login/logout a la dreta, i barra de navegació inferior amb accés a Línies i Favorits.
Els panells de Línies i Favorits s'obren des de baix amb animació CSS.

### Autenticació al Frontend
El token JWT es guarda a `localStorage` i s'afegeix automàticament a totes les peticions mitjançant un interceptor. 
Un guard protegeix les funcionalitats de favorits, que només són visibles amb sessió iniciada.

### Serveis
- `AuthService` — login, registre i logout  
- `ParadaService` — càrrega de parades  
- `LineaService` — línies i detall per id  
- `BusService` — posicions simulades dels busos  
- `FavoritoService` — gestió de favorits

---

##  Estructura projecte

### Backend

```

backend/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── emt.directe.emt_en_directe/
        │       ├── config/
        │       │   ├── CargarDatos.java
        │       │   └── CorsConfig.java
        │       ├── controller/
        │       │   ├── AuthController.java
        │       │   ├── BusController.java
        │       │   ├── FavoritoController.java
        │       │   ├── LineaController.java
        │       │   ├── ParadaController.java
        │       │   └── UsuarioController.java
        │       ├── dto/
        │       │   ├── BusResponseDTO.java
        │       │   ├── FavoritoRequestDTO.java
        │       │   ├── FavoritoResponseDTO.java
        │       │   ├── JwtResponse.java
        │       │   ├── LineaResponseDTO.java
        │       │   ├── LoginRequest.java
        │       │   ├── ParadaResponseDTO.java
        │       │   ├── UsuarioRequestDTO.java
        │       │   ├── UsuarioResponseDTO.java
        │       │   └── UsuarioUpdateDTO.java
        │       ├── model/
        │       │   ├── Bus.java
        │       │   ├── Favorito.java
        │       │   ├── Linea.java
        │       │   ├── LineaParada.java
        │       │   ├── Parada.java
        │       │   └── Usuario.java
        │       ├── repository/
        │       │   ├── BusRepository.java
        │       │   ├── FavoritoRepository.java
        │       │   ├── LineaParadaRepository.java
        │       │   ├── LineaRepository.java
        │       │   ├── ParadaRepository.java
        │       │   └── UsuarioRepository.java
        │       ├── security/
        │       │   ├── JwtAuthenticationFilter.java
        │       │   ├── JwtTokenUtil.java
        │       │   └── SecurityConfig.java
        │       ├── service/
        │       │   ├── AuthService.java
        │       │   ├── BusService.java
        │       │   ├── CustomUserDetailsService.java
        │       │   ├── FavoritoService.java
        │       │   ├── LineaService.java
        │       │   ├── ParadaService.java
        │       │   └── UsuarioService.java
        │       └── EmtEnDirecteApplication.java
        └── resources/
            └── application.properties

```

### Frontend

```

frontend/
└── src/
    ├── app/
    │   ├── components/
    │   │   ├── login/
    │   │   │   ├── login.css
    │   │   │   ├── login.html
    │   │   │   ├── login.spec.ts
    │   │   │   └── login.ts
    │   │   ├── mapa/
    │   │   │   ├── mapa.css
    │   │   │   ├── mapa.html
    │   │   │   ├── mapa.spec.ts
    │   │   │   └── mapa.ts
    │   │   └── register/
    │   │       ├── register.css
    │   │       ├── register.html
    │   │       ├── register.spec.ts
    │   │       └── register.ts
    │   ├── config/
    │   │   └── api.config.ts
    │   ├── guards/
    │   │   └── auth.guard.ts
    │   ├── interceptors/
    │   │   └── Jwt.Interceptor.ts
    │   ├── models/
    │   │   ├── bus.model.ts
    │   │   ├── favorito.model.ts
    │   │   ├── linea.model.ts
    │   │   ├── parada.model.ts
    │   │   └── usuario.model.ts
    │   ├── services/
    │   │   ├── AuthService.ts
    │   │   ├── BusService.ts
    │   │   ├── FavoritoService.ts
    │   │   ├── LineaService.ts
    │   │   └── ParadaService.ts
    │   ├── app.config.ts
    │   ├── app.css
    │   ├── app.html
    │   ├── app.routes.ts
    │   └── app.ts
    ├── index.html
    ├── main.ts
    └── styles.css

  ```

---

## Tecnologies
- **Backend:** Spring Boot + Spring Security + JWT
- **Base de dades:** MySQL + Spring Data JPA
- **Frontend:** Angular + Angular Material
- **Mapes:** Leaflet.js

---

##  Documentació de l'API

Consulta el fitxer [DocumentaciónApi.md](./DocumentaciónAPI.md) per veure tots els endpoints disponibles.
