# Centro de Convenciones - Sistema de Gestión

## Descripción

Este proyecto implementa un sistema de gestión para un Centro de Convenciones utilizando los principios de la Programación Orientada a Objetos (POO) y varios patrones de diseño. Proporciona funcionalidades para gestionar espacios, reservas, productos y aspectos económicos del centro.

## Autores
- Fabio Rodríguez Gómez - 21730332R
- Alfredo Sobrados González - 70273340Z

## Características principales

- Gestión de espacios (alta, disponibilidad, y equipamientos).
- Gestión de reservas (creación, asignación de consumiciones, cancelación).
- Gestión de productos (creación de secciones y productos, disponibilidad).
- Gestión económica (cálculo de ingresos por espacio y generales).

## Patrones de diseño utilizados

### 1. Singleton
- **Clase aplicada:** `Centro_Convenciones`.
- **Descripción:** Asegura que exista solo una instancia del Centro de Convenciones.

### 2. Decorator
- **Clase aplicada:** `Espacio` y sus decoradores.
- **Descripción:** Permite agregar equipamientos opcionales (música, ornamentos, escenarios) a los espacios de manera dinámica.

### 3. Composite
- **Clases aplicadas:** `Carta`, `Sección`, y `Producto`.
- **Descripción:** Modela la jerarquía de la carta de productos para una gestión uniforme de componentes simples (productos) y compuestos (secciones).

### 4. Strategy
- **Clases aplicadas:** Clases de menús y submenús de gestión.
- **Descripción:** Permite manejar de forma dinámica los algoritmos de interacción en los menús.

## Estructura del proyecto

- **`Centro_Convenciones`**: Clase principal, gestiona las reservas, espacios y la carta de productos.
- **`Espacio`**: Representa un espacio del centro, que puede decorarse con opciones adicionales.
- **`Reserva`**: Administra la información de las reservas, incluyendo espacios y consumiciones.
- **`Carta`**: Administra la jerarquía de productos disponibles en el centro.
- **`Coste`**: Clase auxiliar que almacena precios específicos para años determinados.
- **`MenuPrincipal` y subclases**: Gestionan la interacción del usuario con las funcionalidades.

## Funcionalidades principales

### Gestión de espacios
1. Alta de nuevos espacios.
2. Modificación de disponibilidad de espacios.
3. Listado de espacios disponibles/no disponibles.

### Gestión de reservas
1. Alta de reservas.
2. Adición y eliminación de consumiciones.
3. Listado de reservas y consulta de una reserva específica.

### Gestión de productos
1. Alta de secciones y productos.
2. Modificación de disponibilidad de productos.
3. Consulta de la carta organizada.

### Gestión económica
1. Cálculo de ingresos por espacio.
2. Cálculo de ingresos totales del Centro de Convenciones.

## Casos de prueba

El proyecto incluye casos de prueba que verifican:
- Creación de espacios y reservas.
- Gestión de consumiciones y productos.
- Cálculos económicos.

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu_usuario/centro-convenciones.git
   ```
2. Compila el proyecto:
    ```bash
   javac -d bin src/*.java
   ```
3. Ejecuta el programa:
   ```bash
   java -cp bin Main
   ```
## Licencia
Este proyecto se distribuye bajo la licencia MIT.
## Agradecimientos
Agradezco a mis profesores y compañeros por su apoyo y orientación durante el desarrollo de esta práctica. Sus aportes han sido fundamentales para el éxito de este proyecto.
## Contribuciones
Si deseas contribuir a este proyecto, no dudes en hacer un fork del repositorio, proponer mejoras o reportar problemas. ¡Toda ayuda es bienvenida!
## Contacto
Para cualquier consulta o comentario sobre este proyecto, puedes contactarme a través de mi perfil de GitHub.
