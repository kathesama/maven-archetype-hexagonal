# maven-archetype-hexagonal

Ejecutar esto para instalar el artefacto:

1.- Hacer la instalación:

> mvn clean install

2- Luego hacer la publicación al m2 local:

```agsl
mvn install:install-file \
"-Dfile=target/maven-archetype-hexagonal-1.0-SNAPSHOT.jar" \
"-DgroupId=com.kathesama" \
"-DartifactId=maven-archetype-hexagonal" \
"-Dversion=1.0-SNAPSHOT" \
"-Dpackaging=jar" \
"-DgeneratePom=true" \
"-DcreateChecksum=true"
```

Al momento de usar el archetype tomar en cuenta esto:

```agsl
mvn archetype:generate \
-DgroupId=com.mygroup \
-DartifactId=my-artifactId \
-DarchetypeGroupId=com.kathesama \
-DarchetypeArtifactId=maven-archetype-hexagonal \
-DarchetypeVersion=1.0-SNAPSHOT
```

Una vez generado debería quedar una estructura como la que se muestra acá:

```
com
  └── myproject
      ├── application
      │   ├── port
      │   │   ├── in (Input Ports)
      │   │   └── out (Output Ports)
      │   └── service
      ├── domain
      │   ├── model
      │   └── exception
      └── infrastructure
      │   ├── adapter
      │   │   ├── rest (Input Adapter)
      │   │   │   ├── controller
      │   │   │   ├── dto/model
      │   │   │   └── mapper
      │   │   └── persistence (Output Adapter)
      │   │       ├── entity
      │   │       ├── repository
      │   │       └── mapper
      │   ├── security
      │   │   └── JwtTokenProvider
      │   ├── interceptor/aspect
      │   └── configuration
      │   └── logging
      │       ├── LoggerService
      │       └── LoggerFactory
      └── util/common
```
Aquí hay una explicación de cada uno de los elementos que integran esta estructura de carpetas en un proyecto Java con Spring Boot y la metodología hexagonal:

- `application`: Este paquete contiene la lógica de negocio de tu aplicación.
    - `port`: Define los puertos.
        - `in (Input Ports)`: Define las operaciones que puede realizar.
        - `out (Output Ports)`: Define las operaciones que necesita que se realicen en la infraestructura.
    - `service`: Implementa los puertos de entrada (Input Ports).

- `domain`: Este paquete contiene todas las clases que representan el dominio.
    - `model`: Define las entidades del dominio.
    - `exception`: Define las excepciones personalizadas del dominio.

- `infrastructure`: Este paquete contiene todo el código que no pertenece al dominio.
    - `adapter`: Implementa los adaptadores.
        - `rest (Input Adapter)`: Implementa los controladores REST.
            - `controller`: Define los controladores REST.
            - `dto/model`: Define los objetos de transferencia de datos (DTO).
            - `mapper`: Define los mapeadores.
        - `persistence (Output Adapter)`: Implementa la persistencia de .
            - `entity`: Define las entidades de persistencia.
            - `repository`: Define los repositorios.
            - `mapper`: Define los mapeadores.
    - `security`: Implementa la seguridad.
        - `JwtTokenProvider`: Proporciona tokens JWT para la autenticación.
    - `interceptor/aspects`: Define los interceptores.
    - `configuration`: Define la configuración.
    - `logging`: Este paquete contendría todas las clases relacionadas con el logging.
        - `LoggerService`: Esta clase sería un servicio que proporciona métodos para registrar eventos de diferentes niveles (INFO, DEBUG, WARN, ERROR).
        - `LoggerFactory`: Esta clase sería una fábrica que crea instancias de LoggerService para diferentes clases.

- `util/common`: Este paquete contiene todas las clases de utilidad que se utilizan en toda la aplicación.

## Antes de empezar:

1. Puertos:
    1. Los modelos de dominios deber ser solo POJO
    2. Los puertos son interfaces, es lo que se utiliza para comunicarse con el mundo exterior:
        3. input: métodos relacionados al modelo de dominio
        4. output: métodos necesarios para la persistencia y operar con el modelo de dominio.
5. El service implementa el puerto de entrada y hace uso del puerto de salida.
6. **En Spring boot no es una buena práctica hacer la inyección de dependencia mediante @Autowired
   sino mediante un constructor de la clase, usando lombok se realiza mediante la anotación @RequiredArgsConstructor**
7. En la infraestructura se crea el adapter, permite persistir los datos en la base de datos, requiere un repositorio y un mapper.
8. Para el output se crean las entidades y en mapper
9. El dominio se desacopla de las tablas / base de datos / data
10. Si se quisiera cambiar de base de datos sólo se deberá cambiar entity y repository
11. Las validaciones de campos deben ir en la infraestructura a y su vez en su respectivo DTO/model del input
12. Usar DTO para los request del input.
