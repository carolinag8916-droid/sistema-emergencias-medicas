# Sistema de Gestión de Emergencias Médicas

## Descripción

Aplicación desarrollada en Java que simula la gestión de emergencias médicas mediante programación concurrente. El sistema permite registrar incidentes, establecer prioridades, asignar ambulancias y personal médico, y consultar el estado de las operaciones desde una consola.

El proyecto utiliza hilos de Java para representar las actividades de los operadores, despachadores, equipos de emergencia y monitor del sistema.

## Funcionalidades

- Registro automático de emergencias.
- Clasificación por gravedad: crítica, grave, moderada y leve.
- Organización de emergencias mediante una cola de prioridades.
- Asignación y liberación de ambulancias y médicos.
- Procesamiento concurrente de emergencias.
- Monitorización de estados en consola.
- Registro de estadísticas de atención.
- Recuperación de emergencias ante interrupciones de los equipos.
- Pruebas unitarias y de concurrencia.

## Tecnologías utilizadas

- Java JDK 25.
- Apache Maven 3.10.0.
- JUnit.
- Visual Studio Code.
- Git y GitHub.

## Estructura del proyecto

```text
sistema-emergencias-medicas/
│
├── pom.xml
│
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── emergencias/
    │               ├── modelo/
    │               ├── servicio/
    │               ├── concurrencia/
    │               ├── util/
    │               └── App.java
    │
    └── test/
        └── java/
            └── com/
                └── emergencias/
                    ├── servicio/
                    └── concurrencia/
```

## Requisitos de instalación

Para ejecutar el proyecto se necesita:

- Java JDK 25 o superior compatible con la configuración del proyecto.
- Apache Maven.
- Git (opcional, para clonar el repositorio).

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/carolinag8916-droid/sistema-emergencias-medicas.git
```

Ingresar a la carpeta del proyecto:

```bash
cd sistema-emergencias-medicas
```

## Compilación

Ejecutar el siguiente comando:

```bash
mvn clean compile
```

## Ejecución de pruebas

Para ejecutar las pruebas unitarias y de concurrencia:

```bash
mvn clean test
```

## Ejecución del sistema

Para iniciar la simulación desde la consola:

```bash
mvn exec:java "-Dexec.mainClass=com.emergencias.App"
```

El sistema genera emergencias de manera automática, realiza asignaciones de recursos y muestra periódicamente las estadísticas de funcionamiento.

Para detener la ejecución, presionar `Ctrl + C` en la terminal.

## Concurrencia y sincronización

La aplicación utiliza `Thread` y la interfaz `Runnable` para ejecutar diferentes actividades simultáneamente.

Entre los mecanismos utilizados se encuentran:

- `PriorityBlockingQueue`: administra las emergencias pendientes.
- `synchronized`: protege las operaciones de asignación y liberación de recursos.
- `AtomicInteger`: genera identificadores y mantiene contadores seguros.
- `AtomicLong`: acumula los tiempos de atención.
- `Thread.interrupt()`: permite gestionar interrupciones durante la ejecución de los equipos.

## Pruebas

El proyecto incluye pruebas para verificar:

- El registro y la priorización de emergencias.
- La asignación correcta de ambulancias y médicos.
- La prevención de asignaciones duplicadas.
- La liberación y reutilización de recursos.
- El comportamiento concurrente de varios hilos.
- La recuperación de una emergencia interrumpida.

## Autora

**Liliana Carolina García Agudelo**

## Repositorio

https://github.com/carolinag8916-droid/sistema-emergencias-medicas

---

*Proyecto académico: Sistema de Gestión de Emergencias Médicas con Concurrencia.*
