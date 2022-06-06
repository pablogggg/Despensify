# Despensify

Despensify es una despensa digital a través de la cual podemos organizar el inventario, control y compra de los productos que utilizamos en nuestra
casa diariamente. Mediante un interfaz sencillo y amigable el usuario puede realizar un registro y login de manera segura y utilizando sus credenciales acceder a su cuenta personal de usuario. Desde la página principal de la aplicación es posible consultar, añadir, editar o eliminar una serie de productos a la despensa digital, que serán almacenados en una base de datos MySQL conectada a nuestra aplicación. Respecto a los usuarios, es posible actualizar la contraseña de cada uno desde el Panel correspondiente y, mediante la gestión de usuarios que hace la base de datos, varias personas podrán utilizar diferentes cuentas en el mismo ordenador sin compartir una misma despensa digital. El programa funciona como una aplicación de escritorio en local.

 ## Guía de instalación y requisitos

Se trata de una aplicación de escritorio pensada para el uso doméstico. No tiene un límite máximo de usuarios y es necesario realizar una serie de pasos para poder ponerla en funcionamiento. En primer lugar, es necesario tener instalada y actualizada la máquina virtual de Java y las variables de entorno. 

Tutorial para descargar e instalar JDK de java:
https://www.campusmvp.es/recursos/post/Paso-a-paso-como-instalar-el-JDK-de-Java-para-empezar-a-programar.aspx

Tutorial para configurar las variables de entorno:
https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=389:configurar-java-en-windows-variables-de-entorno-javahome-y-path-cu00610b&catid=68&Itemid=188

Posteriormente, descargamos el proyecto desde mi página de Github. La versión presentada se encuentra guardada como "Despensify 1.1." y debe dsecargarse en formato .zip, en la dirección siguiente:
https://github.com/pablogggg/Despensify

Una vez descargado el archivo, descomprimimos en una carpeta y abrimos y ejecutamos el archivo SQL con MySQL para cargar la base de datos con las dos tablas necesarias para que el programa funcione. El archivo SQL va a cargarnos también un usuario y contraseña concretos para que la aplicación no de problemas al probarla en diferentes ordenadores.
A continuación, abrimos el proyecto con el IDE Apache Netbeans 13 para evitar errores originados por utilizar diferentes versiones u otro IDE. Dentro del paquete Source Packages encontramos el paquete Application, que solo contiene una clase java llamada AppMain.java, que a su vez contiene el método principal del programa. Clicamos con botón derecho, seguidamente clicamos en “Ejecutar” y el programa se lanza. Ya podemos utilizar Despensify.

## Construido con 

    IDE: Apache Netbeans 13
    Manejador de dependencias: Maven
    Gestor de Bases de Datos: MySQL
    Lenguaje: Java
    
## Autor

    Pablo García Cabrera
