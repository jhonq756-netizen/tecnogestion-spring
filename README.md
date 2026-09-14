# TecnoGestión S.A.C. — Sistema de Gestión Comercial (Spring Boot)

Proyecto para el curso **Herramientas de Desarrollo** — implementación de control de
versiones con Git y GitHub sobre un sistema de gestión comercial.

Sistema web con 4 módulos: **Login, Dashboard, Clientes, Productos y Ventas**,
construido con **Java 17 + Spring Boot 3 + Thymeleaf**. Los datos se guardan
**en memoria** (no usa base de datos), por lo que no requiere ninguna
instalación adicional para ejecutarse.

## Requisitos

- Java JDK **17** o superior
- Maven (o usar el que trae integrado tu IDE)
- Visual Studio Code con las extensiones **"Extension Pack for Java"** y
  **"Spring Boot Extension Pack"** (o IntelliJ IDEA, que ya trae soporte Spring Boot)

## Cómo ejecutarlo

### Opción 1: Visual Studio Code
1. Abre la carpeta del proyecto en VS Code (`Archivo > Abrir carpeta`).
2. Espera a que VS Code detecte el proyecto Maven e instale las dependencias
   (lo hace automáticamente la primera vez, necesita internet).
3. Abre `src/main/java/com/tecnogestion/TecnogestionApplication.java`.
4. Haz clic en **Run** (▶) sobre el método `main`, o usa el ícono de Spring Boot
   Dashboard en la barra lateral.

### Opción 2: Terminal (Maven)
```bash
mvn spring-boot:run
```

### Opción 3: Generar el .jar y ejecutarlo
```bash
mvn clean package
java -jar target/tecnogestion.jar
```

Luego abre en el navegador: **http://localhost:8080**

**Usuario de prueba:** admin
**Contraseña de prueba:** admin123

## Estructura del proyecto

```
tecnogestion-spring/
├── pom.xml                         # Dependencias Maven (Spring Web + Thymeleaf)
├── src/main/java/com/tecnogestion/
│   ├── TecnogestionApplication.java
│   ├── controller/                 # LoginController, DashboardController,
│   │                                  ClienteController, ProductoController, VentaController
│   ├── model/                      # Usuario, Cliente, Producto, Venta
│   └── service/                    # Almacenamiento en memoria (ArrayList) para cada entidad
└── src/main/resources/
    ├── application.properties
    ├── templates/                  # Vistas Thymeleaf (login, dashboard, clientes, productos, ventas)
    └── static/css/style.css
```

## Notas sobre el proyecto

- **Sin base de datos**: todos los datos (usuarios, clientes, productos, ventas)
  viven en listas en memoria dentro de las clases `*Service`. Se reinician cada
  vez que detienes y vuelves a iniciar la aplicación. Esto simplifica la
  ejecución para efectos de la práctica de Git/GitHub (no hay que instalar
  ni configurar ningún motor de base de datos).
- **Autenticación simple** basada en sesión HTTP (`HttpSession`), sin Spring
  Security, para mantener el proyecto liviano.

## Sugerencia de ramas de Git para este proyecto

- `main` — versión estable
- `develop` — integración de módulos
- `feature/login` — pantalla e integración de inicio de sesión
- `feature/clientes` — módulo de clientes
- `feature/productos` — módulo de productos
- `feature/ventas` — módulo de ventas
