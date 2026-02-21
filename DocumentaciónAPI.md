#  Documentació de l'API — EMT en Directe

Base URL: `http://localhost:8080`

---

## Autenticació

### POST `/api/auth/login`
Login d'usuari. Retorna un token JWT.

- **Auth requerida:** No
- **Body:**
```json
{
  "username": "usuari1",
  "password": "password"
}
```
- **Resposta 200:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "usuari1",
  "email": "usuari1@email.com",
  "rol": "USER"
}
```
- **Resposta 401:** Credencials incorrectes

---

## Usuaris

### POST `/api/usuarios/register`
Registre d'un nou usuari.

- **Auth requerida:** No
- **Body:**
```json
{
  "username": "usuari1",
  "email": "usuari1@email.com",
  "password": "password"
}
```
- **Resposta 201:** Usuari creat
- **Resposta 400:** Username o email ja existeix

---

### GET `/api/usuarios`
Llista tots els usuaris.

- **Auth requerida:** Sí (JWT)
- **Resposta 200:**
```json
[
  {
    "id": 1,
    "username": "usuari1",
    "email": "usuari1@email.com",
    "rol": "USER",
    "createdAt": "2025-01-01T10:00:00",
    "updatedAt": "2025-01-01T10:00:00"
  }
]
```

---

### GET `/api/usuarios/{id}`
Obté un usuari per ID.

- **Auth requerida:** Sí (JWT)
- **Resposta 200:** Usuari trobat
- **Resposta 404:** Usuari no trobat

---

### PUT `/api/usuarios/{id}`
Actualitza email i/o contrasenya d'un usuari.

- **Auth requerida:** Sí (JWT)
- **Body:**
```json
{
  "email": "nou@email.com",
  "password": "novacontrasenya"
}
```
- **Resposta 200:** Usuari actualitzat
- **Resposta 400:** Email ja existeix

---

### DELETE `/api/usuarios/{id}`
Elimina un usuari.

- **Auth requerida:** Sí (JWT)
- **Resposta 204:** Eliminat correctament
- **Resposta 404:** Usuari no trobat

---

## Línies

### GET `/api/lineas`
Llista totes les línies amb les seves parades.

- **Auth requerida:** No
- **Resposta 200:**
```json
[
  {
    "id": 1,
    "numero": "35",
    "nombre": "Aquarium - Portitxol",
    "color": "#FF0000",
    "createdAt": "2025-01-01T10:00:00",
    "updatedAt": "2025-01-01T10:00:00",
    "paradas": [
      {
        "id": 1,
        "codigo": "P001",
        "nombre": "Aquarium",
        "orden": 1,
        "esParada": true
      }
    ]
  }
]
```

---

### GET `/api/lineas/{id}`
Obté una línia per ID.

- **Auth requerida:** No
- **Resposta 200:** Línia trobada
- **Resposta 404:** Línia no trobada

---

##  Parades

### GET `/api/paradas`
Llista totes les parades amb les línies que hi passen.

- **Auth requerida:** No
- **Resposta 200:**
```json
[
  {
    "id": 1,
    "nombre": "Aquarium",
    "codigo": "P001",
    "latitud": 39.531322,
    "longitud": 2.729162,
    "esParada": true,
    "createdAt": "2025-01-01T10:00:00",
    "updatedAt": "2025-01-01T10:00:00",
    "lineas": [
      {
        "id": 1,
        "numero": "35",
        "nombre": "Aquarium - Portitxol",
        "color": "#FF0000",
        "orden": 1
      }
    ]
  }
]
```

---

### GET `/api/paradas/{id}`
Obté una parada per ID.

- **Auth requerida:** No
- **Resposta 200:** Parada trobada
- **Resposta 404:** Parada no trobada

---

##  Busos (Simulació)

### GET `/api/buses/linea/{lineaId}`
Retorna la posició actualitzada dels busos d'una línia. Cada crida mou el bus al següent punt del recorregut (polling cada 10-15 segons des del frontend).

- **Auth requerida:** No
- **Resposta 200:**
```json
[
  {
    "id": 1,
    "lineaId": 1,
    "lineaNumero": "35",
    "lineaNombre": "Aquarium - Portitxol",
    "lineaColor": "#FF0000",
    "latitud": 39.532123,
    "longitud": 2.727730,
    "updatedAt": "2025-01-01T10:00:05"
  }
]
```

---

## Favorits

> Tots els endpoints de favorits requereixen el header:
> `Authorization: Bearer {token}`

---

### GET `/api/favoritos`
Llista les parades favorites de l'usuari autenticat.

- **Auth requerida:** Sí (JWT)
- **Resposta 200:**
```json
[
  {
    "id": 1,
    "usuarioId": 1,
    "usuarioUsername": "usuari1",
    "paradaId": 1,
    "paradaNombre": "Aquarium",
    "paradaCodigo": "P001",
    "paradaLatitud": 39.531322,
    "paradaLongitud": 2.729162,
    "createdAt": "2025-01-01T10:00:00",
    "lineas": ["35 - Aquarium - Portitxol"]
  }
]
```

---

### POST `/api/favoritos`
Afegeix una parada als favorits de l'usuari autenticat.

- **Auth requerida:** Sí (JWT)
- **Body:**
```json
{
  "paradaId": 1
}
```
- **Resposta 201:** Favorit creat
- **Resposta 400:** La parada ja és als favorits

---

### DELETE `/api/favoritos/{id}`
Elimina un favorit de l'usuari autenticat.

- **Auth requerida:** Sí (JWT)
- **Resposta 204:** Eliminat correctament
- **Resposta 400:** No tens permisos per eliminar aquest favorit

---

##  Com usar el token JWT

1. Fes login a `POST /api/auth/login`
2. Copia el camp `token` de la resposta
3. Afegeix-lo a la capçalera de les peticions protegides:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```
