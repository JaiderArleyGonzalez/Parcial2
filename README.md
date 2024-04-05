# Parcial Segundo Tercio
El proyecto consiste en un conjunto de aplicaciones Java que proporcionan servicios matemáticos, incluyendo búsqueda lineal y búsqueda binaria, a través de endpoints HTTP. Además, cuenta con un proxy que actúa como intermediario para redirigir las solicitudes a los servicios correspondientes.
## Funcionamiento

## Prerrequisitos

1. Java Development Kit (JDK) 17: La aplicación está configurada para compilar y ejecutarse en Java 17. Asegúrate de tener JDK 17 instalado en tu sistema.
2. Apache Maven instalado y configurado.
3. Conexión a Internet para descargar las dependencias de Maven.
4. Git: Para trabajar con el control de versiones, necesitarás tener Git instalado en tu sistema.

## Instalación
Crearemos tres instancias de EC2 en AWS, bajo los siguientes nombres: proxyservice, mathservice1, mathservice2.

![](/media/instancias.png)

Editamos las reglas de entrada para la seguridad según los puertos a usar.

![](/media/seguridad.png)

En cada una de las máquinas realizamos las siguientes instalaciones.
### Git
Para la instalación de Git (con el objetivo de clonar el repositorio para correr cada servicio respectivo) ejecutamos el siguiente comando:

```
    sudo yum install -y git
```

Git es un sistema de control de versiones distribuido que permite gestionar cambios en el código fuente de manera eficiente y colaborativa. Permite rastrear, revertir y fusionar cambios realizados por diferentes colaboradores en un proyecto de software, lo que facilita el trabajo en equipo y la administración del código a lo largo del tiempo.

### Java
Para la instalación de Java, usaremos la versión 17 que es la que se estableció en el POM

```
    sudo yum install -y java-17-amazon-corretto-devel
```

Java es un lenguaje de programación de propósito general y orientado a objetos, desarrollado por Sun Microsystems (adquirida por Oracle Corporation). Es conocido por su portabilidad, lo que significa que los programas escritos en Java pueden ejecutarse en diferentes plataformas sin necesidad de recompilación. Java es ampliamente utilizado en el desarrollo de aplicaciones empresariales, aplicaciones móviles (Android), aplicaciones web, sistemas embebidos, y más. Ofrece características como la gestión automática de memoria a través del recolector de basura, seguridad robusta, y una gran cantidad de bibliotecas estándar y frameworks que facilitan el desarrollo de software.
### Apache-Maven
```
    sudo wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
```

```
    sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
```

```
    sudo yum install -y apache-maven
```
Estas líneas de comandos son instrucciones para instalar Apache Maven en un sistema CentOS o Fedora utilizando el gestor de paquetes yum:

1. Descarga del archivo de repositorio de Maven: Utiliza el comando wget para descargar el archivo de repositorio de Apache Maven desde el repositorio de Fedora People y lo guarda en el directorio /etc/yum.repos.d/ con el nombre epel-apache-maven.repo.

2. Modificación del archivo de repositorio: Utiliza el comando sed para modificar el archivo de repositorio descargado, reemplazando la variable $releasever por 6. Esto se hace para especificar la versión de CentOS o Fedora que estás utilizando, ya que la variable $releasever se expandirá a la versión del sistema operativo.

3. Instalación de Apache Maven: Utiliza el comando yum para instalar Apache Maven junto con todas sus dependencias, utilizando la opción -y para confirmar automáticamente todas las preguntas de instalación con "sí".

Maven es una herramienta de gestión de proyectos y construcción de software ampliamente utilizada en el desarrollo de aplicaciones Java. Permite automatizar el proceso de compilación, gestión de dependencias, empaquetado y distribución de software. Maven utiliza un archivo de configuración llamado POM (Project Object Model) para describir cómo se construye el proyecto, incluyendo sus dependencias, plugins, y configuraciones. Facilita la gestión de proyectos complejos al proporcionar una estructura uniforme y coherente, además de integrarse fácilmente con repositorios de artefactos como Maven Central.

## Ejecución

Para cada máquina lo primero que haremos será clonar el repositorio

```
    git clone https://github.com/JaiderArleyGonzalez/Parcial2.git
```

Ahora compilamos el proyecto con maven

```
    mvn compile
```

Ejecutamos las clases correspondientes según la máquina

```
    mvn exec:java '-Dexec.mainClass=edu.arep.math.MathService'
```

o

```
    mvn exec:java '-Dexec.mainClass=edu.arep.proxy.ProxyService'
```

## Arquitectura

![](/media/arquitectura.png)

La arquitectura del sistema consiste en una infraestructura distribuida en la nube de Amazon Web Services (AWS), compuesta por tres máquinas virtuales EC2.

La primera máquina, llamada proxyservice, actúa como un punto de entrada para los clientes y ejecuta un servicio conocido como proxyService. Este servicio funciona en el puerto 8080 y tiene la responsabilidad de distribuir la carga de trabajo entre las otras dos máquinas virtuales.

Las otras dos máquinas, denominadas mathservice1 y mathservice2, ejecutan un servicio denominado mathService. Estos servicios están configurados para funcionar en el puerto 36000. Ambos servicios proporcionan funcionalidades matemáticas, incluyendo búsquedas lineales y binarias.

El servicio proxyService implementa un algoritmo de balanceo de carga conocido como Round Robin. Este algoritmo distribuye equitativamente las solicitudes entrantes entre las máquinas mathservice1 y mathservice2, asegurando una distribución uniforme de la carga de trabajo y mejorando la escalabilidad y la disponibilidad del sistema.

## Componentes

1. **MathService:** Esta clase proporciona servicios matemáticos, incluyendo búsqueda lineal y búsqueda binaria. Se encarga de procesar las solicitudes relacionadas con operaciones matemáticas. Sus métodos principales son:
    * **main:** Inicia el servidor Spark para manejar las solicitudes HTTP.
    * **linearSearch:** Realiza una búsqueda lineal en una lista dada y devuelve el resultado en formato JSON.
    * **binarySearch:** Realiza una búsqueda binaria en una lista dada y devuelve el resultado en formato JSON.
    * **getPort:** Obtiene el puerto del entorno o utiliza un puerto predeterminado si no está configurado. 
2. **ProxyService:** Esta clase actúa como un proxy para los servicios de búsqueda lineal y búsqueda binaria. Se comunica con el servicio MathService para realizar las búsquedas. Sus métodos principales son:

   * **main:** Inicia el servidor Spark para manejar las solicitudes HTTP. 
   * **getPort:** Obtiene el puerto del entorno o utiliza un puerto predeterminado si no está configurado. 
3. **HttpConnectionExample:** Esta clase proporciona métodos para realizar conexiones HTTP GET. Su principal método es:
   * **Invoke:** Realiza una solicitud GET a un servidor HTTP especificado y devuelve la respuesta como una cadena.
## Testing

Los tests en la clase MathServiceTest están diseñados para probar los métodos de la clase MathService, que proporciona servicios matemáticos como búsqueda lineal y búsqueda binaria.

- **testLinearSearchValuePresent**: Verifica si el método `linearSearch` devuelve el resultado esperado cuando el valor está presente en la lista.
- **testLinearSearchValueNotPresent**: Verifica si el método `linearSearch` devuelve el resultado esperado cuando el valor no está presente en la lista.
- **testLinearSearchValueFirstElement**: Verifica si el método `linearSearch` devuelve el resultado esperado cuando el valor es el primer elemento de la lista.
- **testLinearSearchValueLastElement**: Verifica si el método `linearSearch` devuelve el resultado esperado cuando el valor es el último elemento de la lista.
- **testBinarySearchValuePresent**: Verifica si el método `binarySearch` devuelve el resultado esperado cuando el valor está presente en la lista.
- **testBinarySearchValueNotPresent**: Verifica si el método `binarySearch` devuelve el resultado esperado cuando el valor no está presente en la lista.
- **testBinarySearchValueFirstElement**: Verifica si el método `binarySearch` devuelve el resultado esperado cuando el valor es el primer elemento de la lista.
- **testBinarySearchValueLastElement**: Verifica si el método `binarySearch` devuelve el resultado esperado cuando el valor es el último elemento de la lista.

Para probarlos se ejecuta el siguiente comando:

```
    mvn test
```

Se obtendrá lo siguiente:

![](/media/test.png)

## Consideraciones
* El funcionamiento del programa depende de que la instancia en AWS se encuentre corriendo.
