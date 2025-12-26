# Backend con Arquitectura Orientada a Servicios

Este proyecto es un **backend desarrollado en Java con Spring Boot**, diseñado bajo un **enfoque orientado a servicios** y comunicación mediante **APIs REST**, aplicando buenas prácticas de diseño, modularidad y desacople.

El sistema integra múltiples fuentes de información, centraliza la lógica de negocio y expone sus capacidades a un frontend completamente desacoplado.

---

## 🧠 Visión General

La arquitectura se basa en **servicios independientes**, cada uno con una responsabilidad clara.  
Estos servicios actúan como **fuentes de datos**, las cuales son consumidas y procesadas por un **servicio agregador**, encargado de aplicar la lógica de negocio y exponer la información de forma segura y eficiente.

---

## 🧩 Arquitectura de Servicios

### 🔹 Servicio de Fuente Dinámica
- Permite la **carga manual de información**.
- Expone endpoints REST para el ingreso de datos dinámicos.
- Representa fuentes internas o datos ingresados por usuarios.

### 🔹 Servicio de Fuente Estática
- Encargado de la **carga de datos desde archivos CSV**.
- Incluye un **lector de archivos** para procesar información estructurada.
- Ideal para datasets históricos o fuentes predefinidas.

### 🔹 Servicio de Fuente Proxy
- Permite el **consumo de APIs externas**.
- Actúa como intermediario para desacoplar dependencias de terceros.
- Facilita la adaptación y control sobre datos externos.

### 🔹 Servicio Agregador
- Núcleo del sistema.
- **Centraliza la información** proveniente de todas las fuentes.
- Implementa los **procesamientos y la lógica de negocio**.
- Orquesta y consolida los datos para su posterior exposición.

---

## 🔗 Comunicación entre Servicios
- Comunicación basada en **APIs REST**.
- Servicios desacoplados y autónomos.
- Arquitectura preparada para escalar y extender nuevas fuentes sin impactar el sistema existente.

---

## 🔐 Seguridad
El sistema se encuentra **securizado mediante Spring Security**, utilizando:

- **Autenticación basada en JWT (JSON Web Tokens)**.
- Protección de endpoints según roles y permisos.
- Manejo de sesiones stateless.
- Separación clara entre autenticación, autorización y lógica de negocio.

---

## 🧬 Exposición de Datos
- El **Servicio Agregador expone una interfaz GraphQL**.
- Permite consultas flexibles y eficientes según las necesidades del cliente.
- Reduce el overfetching y underfetching de datos.

---

## 🖥️ Frontend
- El backend provee información a un **frontend desarrollado en un proyecto independiente**.
- Totalmente **desacoplado del backend**.
- Comunicación mediante GraphQL y autenticación por JWT.
- Facilita el desarrollo, despliegue y escalabilidad independiente de cada capa.

---

## 🛠️ Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Web (REST)
- Spring Security
- JWT (JSON Web Tokens)
- GraphQL
- Procesamiento de archivos CSV
- Consumo de APIs externas
- Arquitectura orientada a servicios

---

## 🎯 Objetivos del Proyecto
- Aplicar principios de **arquitectura limpia y modular**.
- Diseñar un backend **seguro y escalable**.
- Integrar múltiples fuentes de información de forma desacoplada.
- Centralizar la lógica de negocio en un servicio agregador.
- Exponer datos de manera eficiente mediante **GraphQL**.
- Demostrar un backend preparado para integrarse con frontends modernos.

---

Este repositorio busca reflejar **criterios de diseño, organización, seguridad y buenas prácticas** aplicables a sistemas backend reales y de nivel profesional.
