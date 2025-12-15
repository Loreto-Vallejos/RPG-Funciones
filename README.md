# 🧙 RPG Funciones (Java Console)

---

## 📘 Descripción
**RPG Funciones** es una aplicación de consola desarrollada en Java que simula un juego de rol básico.
Este laboratorio corresponde a la refactorización del RPG anterior, incorporando el uso de **funciones (métodos)** para organizar el código, mejorar su legibilidad y evitar duplicación, manteniendo una sola clase y sin utilizar Programación Orientada a Objetos.

---

## 🎯 Objetivos de Aprendizaje
- Comprender y aplicar funciones (`static`) en Java
- Modularizar un programa dividiendo responsabilidades
- Mejorar la legibilidad y mantenimiento del código
- Reutilizar lógica mediante métodos
- Preparar el código para futuros proyectos más complejos

---

## ⚙️ Tecnologías Utilizadas
- Java
- Scanner
- Git
- GitHub

---

## 🧩 Funcionalidades
- Menú principal implementado con `do-while`
- Creación de personaje usando una función dedicada
- Sistema de entrenamiento con `while`
- Simulación de batalla por turnos con `for`
- Visualización de inventario mediante `foreach`
- Consulta del estado del personaje usando `if / else`
- Validaciones para evitar entradas inválidas

---

## 🧠 Uso de Funciones
El programa divide su lógica en funciones específicas, entre ellas:
- `mostrarMenuPrincipal()`
- `crearPersonaje()`
- `entrenar()`
- `batalla()`
- `mostrarInventario()`
- `mostrarEstado()`
- `leerEntero()`

Cada función cumple una sola responsabilidad y se declara como `static`, de acuerdo con la consigna del laboratorio.

---

## 🧪 Validaciones Implementadas
- Control de ingreso de datos no numéricos
- Prevención de valores negativos
- Validación de opciones del menú
- Bloqueo de acciones si el personaje no ha sido creado

---

👤 Autor: Loreto Vallejos
