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

### Eclipse

1. Instalar [Eclipse Modeling Tools](http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/mars2)
2. Instalar el plugin [m2e](http://www.eclipse.org/m2e/m2e-downloads.html)
3. Instalar el plugin   [m2e connector for antlr ]()
4. Instalar el plugin   [m2e connector for the Maven Dependency Plugin]()
Opcional:
1. Instalar el plugin [Xtext](https://eclipse.org/Xtext/download.html). La versión _debe_ ser la 2.7.2.
2. Instalar el plugin [ANTLR 4 IDE](https://github.com/jknack/antlr4ide)

Alternativamente, todos los plugins anteriores (m2e) pueden instalarse en forma automática utilizando el archivo de instalación P2F que se encuentra en `_RUTA_`.

1. Abrir menú `File->Import->Install->Install Software Items from File`
2. Hacer click en `Browse`
3. Buscar y seleccionar el archivo antlr.p2f
4. Seleccionar todos los plugins que aparezcan en la lista 
5. _Deseleccionar_ la opción `Install latest version of selected software`. Esto es porque el plugin ANTLR 4 IDE funciona exclusivamente con la versión 2.7.2 de Xtext.

### Maven


## Configuración de un proyecto en ANTLR 4

1. Instalar el arquetipo `antlr4-archetype` que se encuentra en `_RUTA_`.

```
cd _RUTA_
mvn install
```

2. Utilizar Eclipse para crear un nuevo proyecto Maven y seleccionar el arquetipo `antlr4-archetype` como base para crear el proyecto.

Este tutorial provee un arquetipo de maven, llamado `antlr4-archetype`, para crear proyectos ANTLR4. Los proyectos creados a partir de este arquetipo están preconfigurado para comenzar a desarrollar sin mayores inconvenientes (asumiendo que los pasos anteriores de instalación hayan sido ejecutados correctamente).



