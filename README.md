# 🌱 Green Garden - Ejercicios de Programación Java

## Descripción del Proyecto

Este proyecto contiene la implementación de **3 ejercicios de programación en Java** como parte de un trabajo académico. Los ejercicios simulan diferentes sistemas para una empresa ficticia llamada "Green Garden" que se prepara para su IPO.

## 📚 Contexto Académico

**Asignatura**: Programación Orientada a Objetos / Patrones de Diseño  
**Objetivo**: Aplicar patrones de diseño y principios SOLID en Java  
**Herramientas**: Java 17, Maven, JUnit 5  

## 🚀 Ejecución Rápida - Demo Completa

Para ver **TODAS las funcionalidades** de los 3 ejercicios de forma automática:

\`\`\`bash
# Compilar y ejecutar demo completa
mvn clean compile exec:java

# O alternativamente
mvn clean compile
mvn exec:java -Dexec.mainClass="com.greengardens.MainDemo"

# O crear JAR y ejecutar
mvn clean package
java -jar target/green-garden-system-1.0.0.jar
\`\`\`

La **clase `MainDemo`** ejecuta automáticamente:
- ✅ **Ejercicio 1**: Sistema de sensores con alarmas
- ✅ **Ejercicio 2**: Jerarquía empresarial con cálculos
- ✅ **Ejercicio 3**: Sistema de ascensor con paneles
- ✅ **Todos los patrones de diseño** en funcionamiento

## 📋 Estructura del Proyecto

\`\`\`
src/
├── main/java/com/greengardens/
│   ├── MainDemo.java              # 🎯 CLASE PRINCIPAL - EJECUTA TODO
│   ├── sensors/                   # Ejercicio 1: Sistema de Sensores
│   ├── business/                  # Ejercicio 2: Gestión Empresarial  
│   └── elevator/                  # Ejercicio 3: Sistema de Ascensor
└── test/java/                     # Tests unitarios
\`\`\`

## 🎯 Ejercicios Implementados

### Ejercicio 1: Sistema de Monitoreo de Sensores (4 puntos)

**Funcionalidades demostradas automáticamente**:
- ✅ Creación de sensores individuales (5 tipos diferentes)
- ✅ Sensor compuesto con patrón Composite
- ✅ Sistema de alarmas con patrón Observer
- ✅ 5 ciclos de monitoreo automático
- ✅ Desconexión de sensores
- ✅ Reporte completo del sistema

**Patrones**: Composite + Observer + Strategy

### Ejercicio 2: Jerarquía de Unidades de Negocio (2 puntos)

**Funcionalidades demostradas automáticamente**:
- ✅ Estructura empresarial completa (3 niveles)
- ✅ 6 gerentes y 6 unidades de negocio
- ✅ Cálculos agregados por niveles
- ✅ Demostración de sumas y promedios
- ✅ Reporte jerárquico completo

**Patrones**: Composite + Template Method

### Ejercicio 3: Sistema de Ascensor (4 puntos)

**Funcionalidades demostradas automáticamente**:
- ✅ Ascensor con 7 pisos (0-6)
- ✅ 3 tipos de paneles sincronizados
- ✅ 7 movimientos diferentes
- ✅ Casos especiales (mismo piso, piso inválido)
- ✅ Estado completo del sistema

**Patrones**: Observer + State + Command

## 🛠️ Ejecución Individual de Ejercicios

Si quieres ejecutar cada ejercicio por separado:

\`\`\`bash
# Ejercicio 1 - Sistema de Sensores (interactivo)
mvn exec:java -Dexec.mainClass="com.greengardens.sensors.MonitoreoAmbientalApp"

# Ejercicio 2 - Gestión Empresarial (automático)
mvn exec:java -Dexec.mainClass="com.greengardens.business.GestionEmpresarialApp"

# Ejercicio 3 - Sistema de Ascensor (interactivo)
mvn exec:java -Dexec.mainClass="com.greengardens.elevator.SistemaAscensorApp"
\`\`\`

## 📦 Instalación y Configuración

### Prerrequisitos
\`\`\`bash
java -version  # Java 17+
mvn -version   # Maven 3.8+
\`\`\`

### Instalación
\`\`\`bash
# Clonar/descargar el proyecto
cd green-garden-exercises

# Compilar
mvn clean compile

# Ejecutar demo completa
mvn exec:java

# Ejecutar tests
mvn test
\`\`\`

## 🎓 Aspectos Académicos Destacados

### Principios SOLID Aplicados
- ✅ **Single Responsibility**: Cada clase tiene una responsabilidad específica
- ✅ **Open/Closed**: Código extensible sin modificaciones
- ✅ **Liskov Substitution**: Herencia correcta
- ✅ **Interface Segregation**: Interfaces cohesivas
- ✅ **Dependency Inversion**: Dependencias hacia abstracciones

### Patrones de Diseño Implementados
1. **Composite**: Estructuras jerárquicas (sensores y unidades de negocio)
2. **Observer**: Notificaciones automáticas (alarmas y paneles)
3. **Strategy**: Diferentes comportamientos según contexto
4. **Template Method**: Algoritmos con pasos variables
5. **State**: Manejo de estados del ascensor
6. **Command**: Encapsulación de operaciones

### Buenas Prácticas Aplicadas
- ✅ Validación de parámetros con null checks
- ✅ Manejo de excepciones apropiado
- ✅ Documentación JavaDoc completa
- ✅ Thread safety donde es necesario
- ✅ Inmutabilidad y listas defensivas
- ✅ Separación de responsabilidades

## 📊 Lo que verás en la Demo

### Ejercicio 1 - Sensores (2 minutos)
\`\`\`
🌡️ EJERCICIO 1: SISTEMA DE MONITOREO DE SENSORES
1️⃣ Creando sensores individuales...
2️⃣ Creando sensor compuesto (Patrón Composite)...
3️⃣ Agregando sensores al sistema...
4️⃣ Ejecutando ciclos de monitoreo...
🚨 ALARMA DISPARADA - Sensor: TEMP-001...
5️⃣ Probando desconexión de sensores...
6️⃣ Generando reporte final...
\`\`\`

### Ejercicio 2 - Empresa (1 minuto)
\`\`\`
🏢 EJERCICIO 2: GESTIÓN DE UNIDADES DE NEGOCIO
1️⃣ Creando estructura empresarial...
2️⃣ Creando gerentes y unidades principales...
3️⃣ Creando subunidades (Patrón Composite)...
4️⃣ Estableciendo jerarquía...
5️⃣ Demostrando cálculos agregados...
\`\`\`

### Ejercicio 3 - Ascensor (3 minutos)
\`\`\`
🏗️ EJERCICIO 3: SISTEMA DE ASCENSOR
1️⃣ Inicializando sistema de ascensor...
2️⃣ Creando paneles (Patrón Observer)...
🚀 Ascensor iniciando movimiento del piso 0 al piso 3
🏢 Ascensor pasando por el piso 1...
📱 [PANEL-INTERNO] Display actualizado: Piso 3
\`\`\`

## 🧪 Testing

\`\`\`bash
# Ejecutar todos los tests
mvn test

# Ver cobertura
mvn jacoco:prepare-agent test jacoco:report
\`\`\`

## 🎯 Objetivos de Aprendizaje Cumplidos

1. ✅ **Aplicación de Patrones de Diseño**: 6 patrones diferentes
2. ✅ **Principios SOLID**: Todos implementados
3. ✅ **POO Avanzada**: Herencia, polimorfismo, encapsulación
4. ✅ **Manejo de Excepciones**: Validaciones completas
5. ✅ **Concurrencia**: Thread safety y programación asíncrona
6. ✅ **Testing**: Pruebas unitarias
7. ✅ **Documentación**: JavaDoc completo

---

**Trabajo Académico - Programación Orientada a Objetos**  
*Implementación Completa de Patrones de Diseño en Java*

**⚡ Ejecuta `mvn clean compile exec:java` para ver todo funcionando**
