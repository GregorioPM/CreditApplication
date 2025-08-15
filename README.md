# Credit Application

Aplicación desarrollada con **Spring Boot**, utilizando base de datos en memoria **H2** y lista para ejecutarse en **Docker**.  
Incluye endpoints documentados con **Swagger**.

---


## 🚀 Clonar el repositorio:
    Gitbash o consola preferencia
    git clone https://github.com/GregorioPM/CreditApplication.git
    cd CreditApplication
    
---
## 🚀 Cómo Ejecutar la Aplicación

Se encuentran dos opciones para ejecutar esta aplicación: usando Docker o directamente desde el IDE con Java 21.

### Opción 1: Ejecutar con Docker (Recomendado)

1.  **Tener Docker instalado:** Docker Desktop [Docker Desktop](https://www.docker.com/products/docker-desktop/).

2.  **Construye la imagen Docker:**
    ```bash
    docker build -t credits-app:1.0 .
    ```

3.  **Ejecuta el contenedor Docker:**
    ```bash
    docker run -p 8080:8080 credits-app:1.0
    ```
    La aplicación estará disponible en `http://localhost:8080`.

### Opción 2: Ejecutar desde tu IDE (con Java 21)

1.  **Asegúrate de tener Java Development Kit (JDK) 21 instalado:** Tener la version de Oracle o de OpenJDK.

2.  **Abre el proyecto en tu IDE favorito** (como IntelliJ IDEA, Eclipse, o VS Code con la extensión de Spring Boot).

3.  **Importa las dependencias de Maven:** Tu IDE debería hacerlo automáticamente al abrir el proyecto. Si no, busca la opción para "Importar" o "Recargar" proyectos Maven.

4.  **Ejecuta la clase principal de la aplicación:** Busca el archivo `src/main/java/com/demo/credits/CreditsApplication.java` y ejecuta el método `main`.

    La aplicación se iniciará y estará accesible en `http://localhost:8080`.

---

## 🧪 Cómo Ejecutar las Pruebas

Para ejecutar las pruebas de la aplicación, puedes hacerlo directamente desde el proyecto Spring Boot.

1.  **Asegúrate de tener Maven instalado:** Si no lo tienes, puedes seguir las instrucciones en la [documentación de Maven](https://maven.apache.org/install.html).

2.  **Navega al directorio raíz del proyecto.**

3.  **Ejecuta las pruebas con Maven:**
    ```bash
    mvn clean test
    ```
 Después de ejecutar las pruebas, **JaCoCo** generará un informe de cobertura de código. Puedes encontrar este informe en `target/site/jacoco/index.html`.

---

## 💡 Probar Endpoints (con cURL y Swagger)

Esta aplicación incluye **Swagger UI** para una fácil interacción con los endpoints. Puedes acceder a ella en `http://localhost:8080/swagger-ui/index.html#/`.

Para probar tu endpoint POST, puedes usar `curl` de la siguiente manera:

**Ejemplo de Comando cURL:**

Suponiendo que tienes un endpoint POST en `/api/recurso` que espera un JSON con `nombre` y `descripcion`:


cURL para el endpoint get
```
curl --location "http://localhost:8080/api/v1/applications/1"
```

cURL para el endpont post

```
curl --location "http://localhost:8080/api/v1/applications" --header "Content-Type: application/json" --data "{\"applicantName\":\"Jose Perez\",\"loanAmount\":3999.00,\"purpose\":\"buy car\",\"monthlyIncome\":1000.00,\"creditScore\":750}"
```
