# [PROGRAMACIÓN ORIENTADA A OBJETOS] Centro de Convenciones - Sistema de Gestión

## Descripción

Este proyecto implementa un sistema de gestión para un Centro de Convenciones utilizando los principios de la Programación Orientada a Objetos (POO) y varios patrones de diseño. Proporciona funcionalidades para gestionar espacios, reservas, productos y aspectos económicos del centro. En este proyecto conjunto mi compañero y yo obtuvimos una calificación de 9,6/10.

## Autores
- Fabio Rodríguez Gómez
- Alfredo Sobrados González

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

## Persistencia de datos

El sistema serializa objetos a un archivo binario .dat para almacenar información de espacios, reservas y productos, garantizando la persistencia entre sesiones sin necesidad de bases de datos externas.

## Diagrama de Clases

```mermaid
classDiagram
    class Centro_Convenciones {
        - instancia: Centro_Convenciones
        - listaEspacios: ArrayList~Espacio~
        - listaReservas: ArrayList~Reserva~
        - carta: Seccion
        + obtenerInstanciaCC() Centro_Convenciones
        + buscarEspacio(cod: String) Espacio
        + existe(e: Espacio):boolean
        + altaEspacio(e: Espacio) boolean
        + alterarDispEspc(es:Espacio)
        + aniadirPrecioEspc(anio:int, es: Espacio, porcentaje: double)
        + listarInfoEspcDisponible()
        + listarInfoEspcNoDisponible()
        + listarAllEspacios()
        - buscarReserva(id: String) Reserva
        + altaReserva(r: Reserva) boolean
        + bajaReserva(id: String)
        + aniadirConsumicionReserva(idR: String, con: Consumicion)
        + mostrarConsumicionesReserva(idR: String)
        + eliminarConsumicionReserva(idR: String, idC: String)
        + listarInfoReservas()
        + mostrarInfoReserva(id: String)
        + agregarSeccion(c: Carta)
        + agregarProducto(s: Carta, p: Carta)
        + obtenerLista(s: Seccion) ArrayList~Carta~
        + alterarDispProducto(p:Carta)
        + mostrarInfoCarta(cod:String)
        + calcularIngresosEspacio(fI: LocalDate, fF: LocalDate, es:Espacio) double
        + calcularIngresosCC(fI: LocalDate, fF: LocalDate) double
        + getCarta() Seccion
        + getListaEspacios() ArrayList~Espacio~
        + getListaReservas() ArrayList~Reserva~
    }

    class Espacio {
        - codigo: String
        - nombre: String
        - superficie: double
        - capacidad: int
        - listaCostes: ArrayList~Coste~
        - estado: boolean
        + getCodigo() String
        + getNombre() String
        + getSuperficie() double
        + getCapacidad() int
        + getEstado() boolean
        + getListaCostes() ArrayList~Coste~
        + setDisponibilidad(estado: boolean)
        + setListaCostes(c: Coste)
        + buscarCoste(anio: int) Coste
        + calcularIngreso() double
    }

    class Reserva {
        - espacio: Espacio
        - fecha: LocalDate
        - cliente: String
        - telefono: long
        - id: String
        - listaConsumiciones: ArrayList~Consumicion~
        + setFecha(fecha: LocalDate)
        + getEspacioDecorado() Espacio
        + getFecha() LocalDate
        + getCliente() String
        + getTelefono() long
        + getId() String
        + getListaConsumiciones() ArrayList~Consumicion~
        + aniadirConsumicion(consumicion: Consumicion)
        - buscarConsumicion(id: String) Consumicion
        + eliminarConsumicion(id: String)
        + mostrarConsumiciones()
    }

    class Carta {
        - nombre: String
        - id: String
        - descripcion: String
        + getNombre() String
        + getId() String
        + getDescripcion() String
        + getEstado() boolean
        + agregar(c: Carta)
        + mostrarInfo()
        + recuperarCarta(nombre: String) Carta
        + setListaCostes(c: Coste)
        + getLista() ArrayList~Carta~
        + setEstado(estado: boolean)
    }

    class Producto {
        - listaCostes: ArrayList~Coste~
        - estado: boolean
        + mostrarInfo()
        + agregar(e: Carta)
        + setEstado(estado: boolean)
        + setListaCostes(c: Coste)
        + getEstadoImprimir(estado:boolean) String
        + getEstado() boolean
        + getCostes() ArrayList<Coste>
        + recuperarCarta(id: String) Carta
        + getLista() ArrayList~Carta~
        + buscarCoste(anio: int) Coste
    }

    class EquipamientoDecorador {
        - codigoNumerico: int
        - descripcion: String
        - espacio: Espacio
        + calcularIngreso(cod: String) double
        + getCodigoNum() int
        + getDescripcion() String
        + getCodigo() String
        + getNombre() String
        + getSuperficie() double
        + getCapacidad() int
        + getEstado() boolean
        + getDescripcion() String
        + calcularIngreso() double
    }

    class Ornamento {
        - ingreso: double
        + getDescripcion() String
        + getNombre() String
        + calcularIngreso() double
    }

    class Musica {
        - ingreso: double
        + getDescripcion() String
        + getNombre() String
        + calcularIngreso() double
    }

    class Escenario {
        - ingreso: double
        + getDescripcion() String
        + getNombre() String
        + calcularIngreso() double
    }

    class MenuPrincipal {
        - menus: ArrayList~Menus~
        + ejecutarOpciones() String
    }

    class Menus {
        - centConv: Centro_Convenciones
        + getCentConv() Centro_Convenciones
        + ejecutar()
        + ejecutarOpciones() String
    }

    class GestionEspacios {
        + darAltaEspacio()
        + generarCodigoEspacio() String
        + aniadirPrecioEspacio()
        + hacerDisponible()
        + hacerNoDisponible()
        + ejecutarOpciones() String
    }

    class GestionReservas {
        + darAltaReserva()
        + generarCodigoReserva() String
        + generarCodigoConsumicion() String
        + contarNumeroConsumiciones() int
        + darBajaReserva()
        + mostrarInfoReservaConcreta()
        + aniadirConsumicionReserva()
        + eliminarConsumicionReserva()
        + ejecutarOpciones() String
    }

    class GestionCartaProductos {
        + darAltaSeccion()
        + contarNumeroSecciones() int
        + darAltaProducto()
        + contarNumeroProductos() int
        + generarCodigoSeccion() String
        + generarCodigoProducto() String
        + mostrarInfoProducto()
        + mostrarInfoSeccionConProductos()
        + hacerProductoDisponible()
        + hacerProductoNoDisponible()
        + mostrarInfoProductosDisponibles()
        + mostrarInfoProductosNoDisponibles()
        + ejecutarOpciones() String
    }

    class GestionEconomica {
        + mostrarIngresosEspacio()
        + mostrarIngresosCC()
        + ejecutarOpciones() String
    }

    class Coste {
        - precio: double
        - anio: int
        + getPrecio() double
        + getAnio() int
    }

    class Consumicion {
        - producto: Producto
        - cantidad: int
        - id: String
        + getProducto() Producto
        + getCantidad() int
        + getId() String
        + calcularCosteConsumicion() double
    }

    class Seccion {
        - listaProductos: ArrayList~Carta~
        + getEstado() boolean
        + getLista() ArrayList~Carta~
        + mostrarInfo()
        + agregar(c: Carta)
        + recuperarCarta(id: String) Carta
        + setListaCostes(c: Coste)
        + setEstado(estado:boolean)
    }

    Centro_Convenciones "1" *-- "0 .. *" Espacio
    Centro_Convenciones "1" *-- "0 .. 1" Seccion
    Centro_Convenciones <.. Reserva : uso
    Reserva "1" -- "0 .. *" Consumicion : tiene varias
    EquipamientoDecorador --|> Espacio
    EquipamientoDecorador <|-- Ornamento: {no excluyente, total}
    EquipamientoDecorador <|-- Musica: {no excluyente, total}
    EquipamientoDecorador <|-- Escenario: {no excluyente, total}
    Espacio "1" -- "0 .. 1" EquipamientoDecorador : se utiliza en
    Carta <|-- Producto
    Carta <|-- Seccion
    Carta "0..*" -- "1" Seccion
    Espacio "1" -- "0 .. *" Coste
    Reserva "0 .. *" -- "1" EquipamientoDecorador: puede tener varias
    EquipamientoDecorador "1" -- "0 .. 1" Reserva: Permite guardar el objeto Espacio decorado
    EquipamientoDecorador "1" -- "0 .. *" Coste
    Consumicion "0 .. 1" --> "1" Producto: tiene o puede tener
    Coste "0 .. *" -- "1" Producto: tiene

    Menus "0..1" -- "1" Centro_Convenciones: provee de funcionalidades a la estrategia

    MenuPrincipal --|> Menus
    MenuPrincipal "1" -- "0 .. *" Menus: puede tener varios
    Menus <|-- GestionEspacios 
    Menus <|-- GestionReservas 
    Menus <|-- GestionCartaProductos
    Menus <|-- GestionEconomica
```

## Ejecución
Puedes usar tu IDE favorito a continuación se muestran los pasos con dos IDEs

### **Usando IntelliJ IDEA**

#### **1. Abrir el proyecto en IntelliJ**
1. Abre IntelliJ IDEA.
2. Ve a **File > Open** y selecciona la carpeta del repositorio que clonaste de GitHub.
3. IntelliJ detectará automáticamente si el proyecto es de Java. Si no, te pedirá que configures un **SDK de Java**.

   - Si es la primera vez, selecciona un JDK en tu máquina o descárgalo desde IntelliJ. Asegúrate de usar JDK 8 o superior.

#### **2. Verificar la estructura del proyecto**
1. Asegúrate de que tu archivo fuente (`Main.java`) (Clase principal de cada proyecto con el método main) esté dentro de una carpeta llamada `src` o algo similar. La estructura típica sería:

   ```
   repositorio/
   ├── src/
   │   └── com/
   │       └── ejemplo/
   │           └── Main.java
   ```

2. Si no tienes esta estructura, puedes configurar el directorio como "fuente" (`Source Root`):
   - Haz clic derecho en la carpeta `src`.
   - Selecciona **Mark Directory as > Sources Root**.

#### **3. Ejecutar el programa**
1. Abre el archivo `Main.java` en IntelliJ.
2. Busca el botón verde con forma de triángulo ▶ junto al método `main`.
3. Haz clic en ese botón y selecciona **Run 'Main'**.
4. IntelliJ compilará automáticamente el programa y lo ejecutará en la consola integrada.

#### **4. Configurar parámetros de ejecución (si son necesarios)**
Si tu programa requiere argumentos de entrada, puedes configurarlos fácilmente:
1. Ve a **Run > Edit Configurations**.
2. Selecciona la configuración de tu clase principal (`Main`).
3. En el campo **Program Arguments**, escribe los argumentos separados por espacios (por ejemplo, `arg1 arg2`).
4. Haz clic en **OK** y vuelve a ejecutar el programa.

### **Usando Eclipse**

#### **1. Abrir el proyecto en Eclipse**
1. Abre Eclipse.
2. Ve a **File > Import > Existing Projects into Workspace**.
3. Selecciona la carpeta del repositorio clonado de GitHub y haz clic en **Finish**.

#### **2. Verificar la estructura del proyecto**
1. Asegúrate de que tu archivo `Main.java` (Clase principal de cada proyecto con el método main) esté dentro de una carpeta marcada como **src**. Por ejemplo:

   ```
   repositorio/
   ├── src/
   │   └── com/
   │       └── ejemplo/
   │           └── Main.java
   ```

2. Si Eclipse no reconoce la carpeta como fuente, haz clic derecho en el proyecto:
   - Ve a **Build Path > Configure Build Path**.
   - En la pestaña **Source**, añade la carpeta `src` como fuente si no está configurada.

#### **3. Ejecutar el programa**
1. Haz doble clic en el archivo `Main.java` para abrirlo en el editor.
2. Haz clic derecho en cualquier parte del archivo y selecciona **Run As > Java Application**.
3. Eclipse compilará automáticamente el programa y lo ejecutará en la consola de Eclipse.

#### **4. Configurar parámetros de ejecución**
Si necesitas agregar argumentos de entrada:
1. Haz clic derecho sobre el archivo `Main.java`.
2. Ve a **Run As > Run Configurations**.
3. En la pestaña **Arguments**, escribe los argumentos en el campo **Program Arguments**.
4. Haz clic en **Apply** y luego en **Run**.

## Conclusiones
El proyecto "Centro de Convenciones - Sistema de Gestión" destaca por su implementación efectiva de los principios de la Programación Orientada a Objetos (POO) y patrones de diseño como Singleton, Decorator, Composite y Strategy, que aportan flexibilidad, escalabilidad y reutilización al código. Su estructura modular y jerárquica, con una clara separación de responsabilidades entre clases como `Espacio`, `Reserva` y `Carta`, facilita el mantenimiento y futuras ampliaciones. Además, el sistema cubre áreas clave de gestión, como espacios, reservas, productos y aspectos económicos, haciéndolo práctico y aplicable a escenarios reales. La documentación incluye un detallado diagrama de clases que describe las relaciones entre los componentes, proporcionando una referencia clara para desarrolladores. Los casos de prueba aseguran la fiabilidad del sistema, validando funciones críticas como los cálculos económicos. Por último, el reconocimiento del esfuerzo colaborativo y la calificación sobresaliente de 9,6/10 reflejan tanto la calidad técnica como el compromiso del equipo.

## Agradecimientos
Agradezco a mis profesores y compañeros por su apoyo y orientación durante el desarrollo de esta práctica. Sus aportes han sido fundamentales para el éxito de este proyecto.
## Contribuciones
Si deseas contribuir a este proyecto, no dudes en hacer un fork del repositorio, proponer mejoras o reportar problemas. ¡Toda ayuda es bienvenida!
## Contacto
Para cualquier consulta o comentario sobre este proyecto, puedes contactarme a través de mi perfil de GitHub.
