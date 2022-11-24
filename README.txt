ADD_P1.2_GABRIEL_ZAFRA_LALLANA

repo https://github.com/gabzafra/ADD_P1.2_GABRIEL_ZAFRA_LALLANA

La aplicación funciona de la siguiente manera:

El usuario entrara en el menú de LOGIN en el cual se le ofrece la opción de autenticarse o
de salir de la aplicación.

Si elige entrar se comprobará que su nombre de usuario y clave coinciden con las almacenadas
en users.txt.

Si es el usuario admin se le dirige a la zona de administración ADMIN. Donde se le muestra un menú
con opciones de añadir una nueva pregunta (introduciendo los datos por consola), importar
las preguntas de un archivo preguntas.xls o volver al menú de LOGIN.

Si un usuario no administrador se autentica. Pasa al menú de juego GAME. Aquí debe elegir entre
iniciar una partida, ver los récords almacenados en records.txt, recibir instrucciones de
como se juega o volver al menú LOGIN.

Después de contestar a las preguntas se le pide al usuario su nombre y apellidos para después
actualizar el archivo de puntuaciones más altas. Luego se le ofrece la oportunidad de
ver un resumen de su partida partida.pdf. A continuación se le muestra la tabla de mejores
puntuaciones y por último se le devuelve al menú GAME.

RESUMEN DE CLASES

MainP12.java -> Controla el flujo principal del programa mediante un switch que muestra uno de
los menús según la navegación del usuario. Se apoya en los controladores de LoginControler,
AdminController y Partida para ir ejecutando los casos de uso. Además utiliza MenuViews para
generar las entradas y salidas de los menús. Tiene un atributo constante NUM_PREGUNTAS que
se pasa por parámetro en la creación de las partidas para definir cuantas preguntas se tomarán
del pool.

models

Answer.java -> POJO que mantiene los datos de las respuestas que da el jugador.
Jugador.java -> POJO que mantiene los datos del jugador. Sus iniciales y puntuación máxima.
Pregunta.java -> POJO que mantiene los datos de una pregunta. Tiene un id único, un enunciado,
un array con las opciones de respuesta y el indice de la respuesta correcta. 
PreguntaDAO.java -> DAO para manejar el pool de preguntas preguntas.xml.
RecordsDAO.java -> DAO para manejar el archivo de puntuaciones máximas records.txt.
Usuario.java -> POJO con los datos del usuario para la autenticación.
UsuariosDAO.java -> DAO para manejar el archivo de usuarios users.txt.

Todos los DAO crean archivos con datos por defecto si los encuentran vacíos.

services

AdminController.java -> Controlador con los casos de uso del administrador. Se apoya en el DAO de
preguntas para crear nuevas preguntas y en la utilidad de importación de archivos xls
para obtener datos del excel de importación preguntas.xls.
También usa algunos métodos de GameViews para mostrar mensajes de éxito/error.
LoginController.java -> Controla la autenticación apoyándose en el DAO de usuarios.
También usa GameViews para mostrar mensajes.
Partida.java -> Permite instanciar una partida del juego de trivial con preguntas aleatorias del pool
de preguntas preguntas.xml. Esta partida se juega llamando a su método start(). Según el rendimiento 
del jugador sus preguntas se guardan en un ArrayList de Answers y su puntuación en un objeto Jugador.
Este controlador utiliza los DAO de preguntas y de récords. Y hace uso de GameViews para la interacción
con el usuario. Además cuenta con métodos para los casos de uso del menú GAME, como son el mostrar la tabla
de puntuaciones máximas, guardar la puntuación del jugador, pedir sus iniciales al jugador,
generar el informe de la partida partida.pdf. Para mostrar este ultimo informe usa la vista PdfView. 

views

GameViews.java -> Contiene métodos para mostrar vistas por consola, relacionados con la partida. También
tiene algunos métodos para mostrar mensajes por consola. 
MenuViews.java -> Contiene métodos para mostrar vistas por consola, relacionados con el menús. de navegación.
PdfView.java -> Contiene un método para generar un pdf resumen de la partida

util

XLSImporter.java -> Permite importar preguntas desde el archivo preguntas.xls.

ARCHIVOS DE PERSISTENCIA Y AUXILIARES

En la raíz del proyecto hay una carpeta \ficheros que contienen los archivos usados en el programa. Estos ficheros,
menos preguntas.xls que es una plantilla, se crearán con valores por defecto si no se encuentran o están vacíos.

preguntas.xls -> Mantiene el pool de preguntas.
records.txt -> Guarda las iniciales y las puntuaciones máximas.
users.txt -> Guarda algunos pares de username/pass de ejemplo
adam:aaaa
betty:bbbb
cecil:cccc
admin:admin
preguntas.xml -> Es un archivo de plantilla para la importación de varias preguntas.
partida.pdf -> Es el pdf con el resumen de la ultima partida.

