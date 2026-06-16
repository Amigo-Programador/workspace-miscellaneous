───────────────────────────── Prueba tecnica - Michael Page ─────────────────────────────


───────────────────────────── Guia de Uso ─────────────────────────────
# Levantar el proyecto
1. Entrar a la ruta del proyecto \core-pedidos-v1
2. Abrir una consola de comandos en esa ruta
3. Ejecutar el comando: docker-compose up --build

# Obtener el JWT 
4. Abrir el postman del proyecto → resources\files\Michael Page.postman_collection.json

# Procesar el archivo de pedidos
5. Ejecutar el request para obtener el JWT → POST generador-toke-jwt
6. Entrar al request "cargar-pedidos", ir a la pestaña body y cargar el archivo de pedidos que debera procesar
7. Entrar a la pestaña Body y cargar el archivo de pedidos que elija, por defecto he dejado uno en la ruta → resources\files\pedidos.csv
8. Obtendra un Json de respuesta con el resumen de los pedidos procesados

![Arquitectura](src/main/resources/files/captura-respuesta-ok.png)

{
    "mensaje": "Procesamiento por lotes finalizado.",
    "totalProcesados": 100,
    "totalGuardados": 67,
    "pedidosConError": 33,
    "erroresPorTipo": {
        "CLIENTE_NO_ENCONTRADO": 3,
        "SIN_SOPORTE_REFRIGERACION": 8,
        "ERROR_FORMATO": 16,
        "ZONA_INVALIDA": 3,
        "FECHA_ENTREGA_INVALIDA": 3
    },
    "detallesDescartados": [
        {
            "numeroPedido": "PED-1904",
            "tipoError": "ERROR_FORMATO",
            "motivoError": "No enum constant michael.page.domain.model.EstadoPedido.CANCELADO"
        }
    ]
}

───────────────────────────── Requisitos ─────────────────────────────
1. Docker
2. Postman

───────────────────────────── Detalle Técnico ─────────────────────────────

1. Lenguaje usado Java 17 y Framework Spring Boot 3.5.0
2. Se uso Arquitectura Hexagonal para la estructuracion del proyecto.
    Capa aplicacion → Procesos de negocio.
    Capa dominio → Modelos de datos y reglas de validación.
    Capa infraestructura → Logica del negocio, configuracion de proceso batch, persistencia y seguridad.
3. Para la persistencia de datos se utilizo Postgresql (ejecutado en tiempo de ejecucion como un contenedor en docker).
   Tambien use Flyway para el manejo de los esquemas y su versionamiento.
4. Se creo un proceso batch para la lectura y manejo del archivo csv que contiene los pedidos.
    - El archivo se encuentra en el archivo → resources\files\pedidos.csv
5. Se creo un caso de uso para la validacion de Idempotencia.
    - Se valida si el Header "Idempotency-Key" + "Hash SHA-256 del archivo" ya existe en la tabla "cargas_idempotencia"
    - Asi evitar procesar un mismo archivo, evitando el reproceso del mismo.
6. Se implemento OAuth2 Resource Server (JWT), excluyendo unicamente la rutas relacionadas a Swagger
    - Para generar el token, ejecutar el request "generador-toke-jwt" dentro del la collecion Postman → resources\files\Michael Page.postman_collection.json
    - Luego el JWT se guardar como una variable de entorno.
    - Luego en la peticion "cargar-pedidos",  en el Body → form-data → file = "archivo de pedidos" (Adjuntar resources\files\pedidos.csv).
    - Luego ejecutar peticion "cargar-pedidos", esta ya tiene configurada la variable de entorno del JWT en su parametro Bearer token.
    - El JWT tiene una duracion de 5 minutos, luego de eso tendra que volver a generar el token.

7. Se utilizo logs para la trazabilidad.
8. Puede validar en la ruta OpenAPI los endpoints del microservicio.
9. Por el momento no he podido generar los test usando Mockito.
10. README adjunto.
    