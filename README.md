## Introducción

En este documento describiremos la estructura de un proyecto fullstack con el objetivo de entender su funcionamiento y como se ejecuta.

La aplicación cuenta con lo siguiente: 

- DocumentIngester: Backend desarrollado en Spring Boot con GraphQL para la API.
- CandidatesWeb: Frontend desarrollado en Angular que consume la API del backend.
- Colección de Postman que facilita la funcionalidad de subida de archivos. 

## DocumentIngester

Aplicación desarrollada en Spring boot que utiliza Postgresql como base de datos.
Permite a los usuarios subir archivos docx o pdf y extraer su contenido registrando el mismo en la base de datos junto con un identificador único, nombre del archivo y su extensión.
También permite realizar búsquedas sobre los archivos mediante un criterio de búsqueda, devolviendo la lista de archivos que contienen dicho criterio.

- Estructura del proyecto y schema graphql:

  
![estructura](https://github.com/user-attachments/assets/71b2226f-1064-42dd-8ddf-ff6577a3da32)
![graphql](https://github.com/user-attachments/assets/0b419bfb-0634-48e7-a8b6-7fa6e2eea14d)

Separamos claramente la lógica por paquetes para mantener el entorno mas organizado.

## Correr la app

> [!IMPORTANT]
La Aplicación corre en el puerto 8080 y la BD en el 5432!

Para correr la aplicación debe estar definido el server de postgresql y la db, de lo contrario el ide no nos permitirá correrla.

Para administrar la base de datos utilizamos pgAdmin4.

Debemos crear el servidor en pgAdmin4 con el nombre BDDocumentIngester.

Para registrar el server debemos seguir los siguientes pasos:

![server1](https://github.com/user-attachments/assets/fc01ac8e-d713-4c21-8c59-cdd52e45ca61)
![server](https://github.com/user-attachments/assets/3ad810e4-faf9-4262-bed3-eefe57f834ce)

En el campo password pondremos "123" aunque este valor puede ser cambiado desde el application.properties en el proyecto.

Con el server creado, creamos la DB con nombre BDDocumentIngester:

![creardb](https://github.com/user-attachments/assets/11c65c30-c93c-4938-9aaf-d6233f2a9efa)


> [!NOTE]
> Debes tener PostgreSQL instalado para poder conectar el server.

Listo! Ya deberia correr correctamente.

### Funcionalidades con Postman

Proporcionamos una colección de postman para probar las funcionalidades del backend.

- Subir un archivo

  Primero debemos importar la colección que está incluida en el repositorio.

  Luego, con el backend corriendo, seleccionamos la request POST Upload File. 

  Nos vamos a Body > form-data y seleccionamos el archivo docx o pdf que queramos en la sección File como lo indica la siguiente imagen.


  ![guiaPostman](https://github.com/user-attachments/assets/da665d1b-e9ad-466c-83a2-a6c2e0383c9a)

Para terminar le damos a Send y listo! Ya tenemos un nuevo archivo registrado en la base de datos.

- Buscar por palabras claves

  Este método es mas simple, solo tenemos que dirigirnos a la request POST Search In Documents, seleccionar body > raw y cambiar la palabra "CRITERIO" por la búsqueda deseada.

  ![guiaPostman2](https://github.com/user-attachments/assets/ea186ade-59c8-4701-9796-31dc8100ea70)

  Le damos a Send y devolverá los archivos con el contenido que incluye el criterio.


## CandidatesWeb

Aplicación desarrollada en Angular que consume las APIs provistas por DocumentIngester.

Permite visualizar todos los datos de la BD en forma de tabla paginada y realizar una búsqueda sobre los documentos por su contenido.

- Estructura del proyecto Angular

  ![angular](https://github.com/user-attachments/assets/4fdfc9ed-d6ee-4fbe-b7c5-ca5140a26653)

  Creamos dos componentes nuevos para manejar las nuevas funcionalidades.

  > ExpandTextComponent

    Sirve para los casos en los que el contenido del archivo es muy largo. Agrega un Read More al lado del contenido que al tocarlo expande el mismo permitiendo ver todo.

   > FilesComponent

    Es donde traemos todos los archivos de la base de datos. Además, en su html se crea la tabla y también cuenta con un pipe que proporciona el resto de funcionalidades.

  - Vista web

    ![Screenshot 2024-10-11 152343](https://github.com/user-attachments/assets/ba0e9675-8add-4034-86a0-993ceff42c4b)


## Correr la app

> [!IMPORTANT]
La Aplicación corre en el puerto 4200!

Para comenzar nos tenemos que parar en la carpeta del proyecto: ..\CandidatesWeb

Luego debemos ejecutar estos comandos:

Comando para instalar dependencias:

> npm install

Comando para correr la aplicacion

> ng serve -o

### Funcionalidades

- Filtrar por contenido

  La aplicación permite realizar un filtro sobre los documentos, devolviendo aquellos documentos que su contenido coincida o contenga lo que escribimos en el textbox.

  La tabla de documentos se actualiza automáticamente.

- Tabla Paginada

  La tabla puede mostrar un número determinado de items. (esto se puede cambiar en el código).

  No presenta problemas al actualizarse con la búsqueda de contenido.
  
