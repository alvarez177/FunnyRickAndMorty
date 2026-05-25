# 🛸 Funny Rick & Morty

## ¿Qué es Funny Rick & Morty?

**Funny Rick & Morty** es una aplicación Android que permite visualizar el **algunos personajes** de la serie animada **Rick & Morty**.

Cuenta con una **interfaz de usuario simple, limpia e intuitiva**, pensada para que cualquier persona pueda interactuar fácilmente con la aplicación.  


---

## 🛠️ Tecnologías utilizadas

La aplicación fue desarrollada utilizando las siguientes tecnologías y herramientas:

- **Android**
- **Kotlin**
- **Jetpack Compose**
- **Hilt** (inyección de dependencias)
- **Retrofit** (consumo de servicios REST)
- **Corrutinas**
- **MockK** (pruebas unitarias)
- **Coil** para la carga de imagenes

---
## ⚙️ Decisiones técnicas tomadas

## 🏗️ Arquitectura

Funny Rick & Morty está construida siguiendo los principios de **Clean Architecture**, organizada en **tres capas principales**:

- **Domain**
- **Data**
- **Framework (App)**

Estas capas respetan estrictamente la **regla de dependencias**, donde:

- La capa **Domain** es la más interna y **no tiene conocimiento** de ninguna otra capa.
- La capa **Data** solo tiene visibilidad hacia **Domain**.
- La capa **Framework (App)** tiene visibilidad hacia **Data** y **Domain**.

## 💉 Uso de Hilt

Se eligió Hilt para gestionar la inyección de dependencias y reducir el acoplamiento entre componentes, permitiendo una arquitectura más limpia, modular y fácil de mantener


Esto permite una arquitectura **escalable, mantenible y fácil de testear**.

## 📸 Uso de Coil

- Se implementó Coil como librería de carga de imágenes por su integración nativa con Jetpack Compose, gestión eficiente de caché, soporte para placeholders y optimización en listas para evitar renderizados y recargas innecesarias durante el scroll.

---

### Especificación de visibilidad – Framework (App)

<img width="414" height="84" alt="Screenshot 2026-02-05 at 3 19 20 AM" src="https://github.com/user-attachments/assets/8655c36b-bb0d-4d97-8342-3ecfcc73d326" />

### Especificación de visibilidad – Data

<img width="414" height="84" alt="Screenshot 2026-02-05 at 3 19 53 AM" src="https://github.com/user-attachments/assets/dd9ac2c9-5ea3-46b4-bb56-a293def1dbd5" />

### Especificación de visibilidad – Domain

<img width="414" height="84" alt="Screenshot 2026-02-05 at 3 21 59 AM" src="https://github.com/user-attachments/assets/08316959-6304-47ee-a61f-3d13138e8226" />


## 🧠 Patrón de presentación

Luego de definir la arquitectura, se implementó el patrón de presentación **MVI (Model–View–Intent)**.

Este patrón permite:

- Tener una **única fuente de verdad** para el estado de la UI.
- Manejar los eventos de usuario/eventos de la aplicación de forma clara y predecible.
- Separar de manera explícita:
  - **Estado**
  - **Intents (Eventos)**
  - **Efectos secundarios(Effects)**

Gracias a esto, la aplicación es más **robusta**, **fácil de depurar** y **sencilla de mantener**.

---

## 📱 Características principales

- Listar personajes de la serie animada Rick & Morty
- Pantalla de error con politica de re-intentos


## 🤖 ¿ Se hizo uso de la inteligencia artificial ?
No, para esta prueba no me fue necesario hacer uso de la inteligencía artificial


## ⏳ Qué quedó fuera por falta de tiempo

- Implementar paginación para cargar progresivamente todos los personajes de la API.
- Usar mappers para pasar de objeto de api a objeto de domain

## 🚀 Qué mejoraría con más tiempo

- Crear mapper para convertir objeto de API a objeto de domain.
- Refinar el manejo de errores, porque actualmente solo muestra una pantalla en general con diferentes textos segun el error.
- Mejorar aspectos visuales y de experiencia de usuario.
- Agregar pruebas unitarias faltantes


## 📱 Pantallas de la aplicación

Escenario exitoso:


https://github.com/user-attachments/assets/b5ea3aab-2d4d-4c31-aeee-b52fa45ca371


Escenario sin conexión a internet:

https://github.com/user-attachments/assets/b862e052-8576-47d4-b048-2a8d885935cd






