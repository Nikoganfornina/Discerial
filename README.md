# **Discerial** 🎮

**Proyecto TFG Realizado con Alberto Bernet**

**Discerial** es un juego de **trivial** con temáticas latinas y griegas, desarrollado en **JavaFX** y utilizando **Hibernate** para la persistencia de datos. El juego incluye una serie de funcionalidades que mejoran la experiencia de usuario y ofrecen un desafío en diversas áreas del conocimiento. Este proyecto fue realizado como parte de nuestro Trabajo Final de Grado (TFG).

## 🚀 Características del Proyecto

**Discerial** tiene una serie de características clave que permiten a los usuarios disfrutar de una experiencia interactiva y educativa:

### 🎮 **Modo de Juego**
- **Temáticas**: Los usuarios pueden elegir entre varias categorías, como:
  - **Historia**
  - **Filosofía**
  - **Literatura**
  - **Biología**
  - **Mixta**: Una categoría que mezcla preguntas de todas las anteriores.

### 🔐 **Gestión de Usuario**
- **Registro y Login**: Los usuarios pueden registrarse y acceder a su cuenta para guardar su progreso.
- **Edición de perfil**: Los usuarios pueden modificar su información personal y cambiar su imagen de perfil.

### 🕹️ **Sistema de Preguntas y Respuestas**
- **CRUD de Preguntas**: Los administradores tienen la capacidad de crear, leer, actualizar y eliminar preguntas.
- **Contador de Preguntas Correctas y Erróneas**: El juego lleva un registro de las respuestas correctas y erróneas para cada sesión.
- **Contador de Tiempo Jugado**: El tiempo total que el usuario ha jugado se registra y se muestra al final de cada partida.

### 📊 **Sistema de Puntajes**
- Los usuarios pueden ver su puntaje total y comparar sus resultados con otros jugadores.

## 🛠️ **Tecnologías Utilizadas**

- **JavaFX**: Para la creación de la interfaz gráfica de usuario (GUI).
- **Hibernate**: Para la gestión de la base de datos y la persistencia de datos (preguntas, usuarios, puntajes, etc.).
- **MySQL/MariaDB**: Base de datos utilizada para almacenar información de los usuarios, preguntas y estadísticas.

## 📋 **Requisitos del Proyecto**
Para ejecutar este proyecto, es necesario tener instalados los siguientes programas:

- **Java 21 o superior**.
- **JavaFX**.
- **Hibernate**.
- **MySQL/MariaDB** o cualquier base de datos compatible con Hibernate.
- **IDE** como **IntelliJ IDEA**, **NetBeans** o cualquier otro editor compatible con Java.

## 📋 **Inicializar la base de datos**
- A la hora de crear la base de datos y poder entrar en discerial, lo mas optimo para que funcionase de la manera correcta es a traves de esta query de Mysql

```mysql
DROP DATABASE IF EXISTS discerial;
CREATE DATABASE IF NOT EXISTS discerial;
USE discerial;
SHOW VARIABLES LIKE 'max_connections';
SET GLOBAL max_connections = 2500;
```

## ⚙️ **Configurar conexión a la base de datos (Hibernate)**

- Antes de iniciar el proyecto, asegúrate de que el puerto de conexión a MySQL sea el correcto.
- Por defecto, se ha configurado el puerto **3307**. Si estás usando otro puerto (como el clásico **3306**), **debes modificar estas líneas en el archivo** `hibernate.cfg.xml`:

```xml
<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="hibernate.connection.url">jdbc:mysql://localhost:3307/discerial?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```


