# 🧪 Prueba Técnica - Desarrollador Junior

Este proyecto corresponde a la prueba técnica para un rol de **Desarrollador Junior**, la cual consiste en la implementación de un sistema **CRUD completo** para la gestión de ventas, productos y vendedores.

El proyecto está desarrollado con una arquitectura de tres capas:
- **Frontend** en Angular 19
- **Backend** en Java (JDK 21) con arquitectura MVC y servicios RESTful
- **Base de Datos** en SQL Server

---

## 📁 Estructura del Proyecto

```
/prueba-tecnica-ventas/
│
├── frontend-angular/        → Proyecto Angular 19 (interfaz gráfica)
├── backend-java/            → Proyecto Java REST (JDK 21)
├── base-de-datos/           → Scripts SQL y diagrama relacional
│   ├── crear_tablas.sql
│   ├── insertar_datos.sql
└── README.md                → Este archivo
```

---

## 🚀 Tecnologías Utilizadas

| Componente  | Tecnología      |
|-------------|-----------------|
| Frontend    | Angular 19, TypeScript, Bootstrap |
| Backend     | Java 21, Spring Boot, Maven |
| Base de Datos | SQL Server |
| Comunicación | RESTful APIs (JSON) |
| Control de versiones | Git + GitHub |

---

## 🎯 Funcionalidad

El sistema permite gestionar:

### 📦 Productos
- Crear, editar, eliminar y listar productos disponibles para la venta.

### 👨‍💼 Vendedores
- Registro y gestión de los vendedores encargados de cada venta.

### 🧾 Ventas
- Registrar ventas asignando un producto y un vendedor con fecha, cantidad y totales calculados.

---

## 🛠️ Instrucciones de Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/TU_USUARIO/prueba-tecnica-ventas.git
cd prueba-tecnica-ventas
```

### 2. Base de Datos (SQL Server)

1. Crea una base de datos llamada `VentasDB`.
2. Ejecuta los scripts en el siguiente orden:

```sql
-- Crear estructura
CREATE DATABASE VentasDB;

CREATE TABLE Producto (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

CREATE TABLE Vendedor (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre NVARCHAR(100) NOT NULL,
    correo NVARCHAR(100) NOT NULL
);


CREATE TABLE Venta (
    id INT PRIMARY KEY IDENTITY(1,1),
    producto_id INT NOT NULL,
    vendedor_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES Producto(id),
    FOREIGN KEY (vendedor_id) REFERENCES Vendedor(id)
);

INSERT INTO Producto (nombre, precio) VALUES
('Teclado Mecánico', 150.00),
('Mouse Inalámbrico', 80.00),
('Monitor 24 pulgadas', 900.00),
('Laptop Core i5', 2500.00),
('Headset Gamer', 200.00);

INSERT INTO Vendedor (nombre, correo) VALUES
('Laura Torres', 'laura.torres@empresa.com'),
('Carlos Ríos', 'carlos.rios@empresa.com'),
('Marta González', 'marta.gonzalez@empresa.com'),
('Juan Pérez', 'juan.perez@empresa.com'),
('Camila Gómez', 'camila.gomez@empresa.com');

INSERT INTO Venta (producto_id, vendedor_id, cantidad, fecha) VALUES
(1, 1, 2, '2025-08-01'),
(2, 2, 1, '2025-08-02'),
(3, 3, 3, '2025-08-03'),
(4, 4, 1, '2025-08-04'),
(5, 5, 2, '2025-08-05');



### 3. Backend (Java)

```bash
cd backend-java
./mvnw clean install
./mvnw spring-boot:run
```

> Asegúrate de configurar el archivo `application.properties` para apuntar a tu instancia de SQL Server.

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=VentasDB
spring.datasource.username=sa
spring.datasource.password=1234
```

### 4. Frontend (Angular)

```bash
cd frontend-angular
npm install
ng serve
```

> El proyecto debe abrir automáticamente en `http://localhost:4200`

---

## 🔄 Endpoints RESTful

| Entidad   | Método | URL Base             | Descripción                  |
|-----------|--------|----------------------|------------------------------|
| Producto  | GET    | `/api/productos`     | Listar productos             |
| Producto  | POST   | `/api/productos`     | Crear producto               |
| Vendedor  | GET    | `/api/vendedores`    | Listar vendedores            |
| Venta     | GET    | `/api/ventas`        | Listar ventas                |
| Venta     | POST   | `/api/ventas`        | Registrar una nueva venta    |

> Estos endpoints son consumidos por el frontend para mostrar y gestionar los datos.

---

## 🧪 Datos de Prueba

Cada tabla contiene **al menos 5 registros** de prueba generados en el script `insertar_datos.sql`.

---

## 📸 Diagrama Relacional

![Diagrama Relacional](base-de-datos/diagrama-relacional.png)

- `Producto`: ID, nombre, precio
- `Vendedor`: ID, nombre
- `Venta`: ID, producto_id (FK), vendedor_id (FK), cantidad, fecha

---

## 📌 Notas

- Tiempo límite de desarrollo: **24 horas**
- El repositorio fue subido cumpliendo con las instrucciones proporcionadas.
- Si no se tiene acceso a la organización oficial, el proyecto fue cargado en un repositorio personal.

---

## 👨‍💻 Autor

**Daniel Iván Gallo Charry**

- Email: daniel.i.gallo@gmail.com
- GitHub: [github.com/danielgallo](https://github.com/danielgallo)
- Bogotá, Colombia

---

## 💡 Dedicados a la INNOVACIÓN y al DESARROLLO TECNOLÓGICO
