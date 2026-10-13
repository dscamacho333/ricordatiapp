# Ricordati Frontend

Frontend en React + Vite para la API Spring Boot expuesta en `http://localhost:8060`.

## Funcionalidades

- Login con JWT (`POST /auth`)
- Registro (`POST /user`)
- Consulta y actualización del perfil (`GET /user`, `PUT /user`)
- CRUD de tareas (`/task`)
- CRUD de categorías (`/task-category`)
- Carga de estados, prioridades y tiempos de recordatorio
- Protección de rutas mediante token JWT
- Interceptor Axios para enviar `Authorization`

## Ejecutar

```bash
npm install
cp .env.example .env
npm run dev
```

En Windows puedes crear manualmente `.env` copiando el contenido de `.env.example`.

## CORS en Spring Boot

El navegador ejecutará el frontend normalmente en `http://localhost:5173`, por lo que tu API debe permitir ese origen.

Ejemplo global en Spring Boot:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

Si Spring Security administra CORS, asegúrate además de habilitar `cors()` en tu `SecurityFilterChain`.

## Nota sobre `tokeType`

La respuesta Swagger muestra la propiedad `tokeType` (sin la `n`). El frontend soporta tanto `tokeType` como `tokenType` y usa `Bearer` como respaldo.

## Nota sobre actualización de contraseña

`UserUpdateRequestDTO` contiene `account.password`. El formulario envía una cadena vacía cuando el usuario no escribe una nueva contraseña. Si tu backend interpreta `""` como una contraseña nueva, ajusta el backend para ignorarla o modifica `ProfilePage.jsx` para conservar/omitir ese campo según tu lógica.
