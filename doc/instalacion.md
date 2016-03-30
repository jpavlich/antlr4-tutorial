# Instalación y configuración de ANTLR v4

Existen varias alternativas para [utilizar ANTLR4](http://www.antlr.org/tools.html). En este tutorial nos enfocaremos en utilizar [Eclipse](http://www.eclipse.org/) y [Maven](https://maven.apache.org/). A pesar de ello, este tutorial debería servir, con cambios mínimos, para cualquiera de las demás herramientas disponibles.

Se asume que el lector tiene las siguientes competencias:

* Eclipse
    - Instalar Eclipse
    - Instalar plugins
    - Importar proyectos
    - Desarrollar en Java con Eclipse

* Maven
    - Crear un proyecto Maven
    - Manejo de archivos `pom.xml`
        - Configurar repositorios
        - Configurar dependencias
        - Configurar proceso de construcción (build)

## Instalación de entorno de desarrollo

1. Instalar Eclipse. Para este tutorial se utilizó [Eclipse Modeling Tools](http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/mars2)
2. Instalar el plugin [Xtext](https://eclipse.org/Xtext/download.html)
3. Instalar el plugin [m2e](http://www.eclipse.org/m2e/m2e-downloads.html)
4. (Opcional) Instalar el plugin [ANTLR 4 IDE](https://github.com/jknack/antlr4ide)

## Configuración de un proyecto en ANTLR 4

Desde este [repositorio], descargar el esqueleto de proyecto ANTLR 4. Dicho proyecto está preconfigurado para comenzar a desarrollar sin mayores inconvenientes (asumiendo que los pasos anteriores hayan sido ejecutados correctamente).



