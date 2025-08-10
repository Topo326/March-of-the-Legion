# March of the Legion

## Descripción

**March of the Legion** 
es un simulador de campo de batalla con tropas militares desarrollado en Java que implementa la arquitectura MVC (Model-View-Controller). El programa simula un campo de batalla con diferentes tipos de tropas militares y aplica algoritmos de ordenamiento para reorganizar las tropas según criterios específicos.

## Características Principales

- **Arquitectura MVC**: Separación clara de responsabilidades entre Modelo, Vista y Controlador
- **Sistema de Tropas**: 5 tipos de tropas con jerarquía de herencia y polimorfismo
- **Algoritmos de Ordenamiento**: 4 algoritmos implementados (Bubble Sort, Insertion Sort, Merge Sort, Quick Sort)
- **Campo de Batalla Configurable**: Matriz NxN configurable (5x5 a 1000x1000)
- **Orientaciones de Ordenamiento**: Norte-Sur, Sur-Norte, Este-Oeste, Oeste-Este
- **Generación Aleatoria**: Colocación aleatoria de tropas evitando conflictos
- **Manejo de Excepciones**: Sistema robusto de validación y manejo de errores
- **Interfaz CLI**: Comandos de línea para configuración y ejecución

## Tipos de Tropas

| Tipo | Símbolo | Rango | Características |
|------|---------|-------|-----------------|
| **Commander** | C | 0-10 | Alta vida, baja velocidad, alta fuerza |
| **Medic** | M | 11-20 | Vida media, velocidad media, fuerza baja |
| **Tank** | T | 21-30 | Muy alta vida, baja velocidad, muy alta fuerza |
| **Sniper** | S | 31-40 | Vida baja, alta velocidad, alta fuerza |
| **Infantry** | I | 41-50 | Vida media, velocidad media, fuerza media |

## Algoritmos de Ordenamiento

1. **Bubble Sort** (`b`): Ordenamiento por burbuja
2. **Insertion Sort** (`i`): Ordenamiento por inserción
3. **Merge Sort** (`m`): Ordenamiento por mezcla
4. **Quick Sort** (`q`): Ordenamiento rápido

## Requisitos del Sistema

- Java 11 o superior
- Maven 3.6 o superior
- Sistema operativo: Windows, macOS, Linux

## Instalación y Compilación

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd march-of-the-legion
```

### 2. Compilar con Maven
```bash
mvn clean compile
```

### 3. Crear JAR ejecutable
```bash
mvn package
```

## Uso del Programa

### Sintaxis Básica
```bash
java -jar target/march-of-the-legion-1.0.0.jar a=<algoritmo> t=<tipo> o=<orientación> u=<tropas> [f=<tamaño>]
```

### Parámetros

| Parámetro | Descripción | Valores Válidos |
|-----------|-------------|-----------------|
| `a` | Algoritmo de ordenamiento | `b`, `i`, `m`, `q` |
| `t` | Tipo de ordenamiento | `n` (numérico), `c` (caracter) |
| `o` | Orientación | `n` (norte), `s` (sur), `e` (este), `w` (oeste) |
| `u` | Cantidades de tropas | CSV: `Commander,Medic,Tank,Sniper,Infantry` |
| `f` | Tamaño del campo | 5-1000 (opcional, default: 10) |

### Ejemplos de Uso

#### Ejemplo 1: Ordenamiento básico
```bash
java -jar target/march-of-the-legion-1.0.0.jar a=i t=n o=s u=1,2,3,2,4 f=8
```
- **Algoritmo**: Insertion Sort
- **Tipo**: Numérico (por rango)
- **Orientación**: Sur a Norte
- **Tropas**: 1 Commander, 2 Medics, 3 Tanks, 2 Snipers, 4 Infantry
- **Campo**: 8x8

#### Ejemplo 2: Ordenamiento por caracter
```bash
java -jar target/march-of-the-legion-1.0.0.jar a=q t=c o=e u=0,1,1,1,2
```
- **Algoritmo**: Quick Sort
- **Tipo**: Caracter (por símbolo)
- **Orientación**: Este a Oeste
- **Tropas**: 0 Commander, 1 Medic, 1 Tank, 1 Sniper, 2 Infantry
- **Campo**: 10x10 (default)

#### Ejemplo 3: Campo grande con Merge Sort
```bash
java -jar target/march-of-the-legion-1.0.0.jar a=m t=n o=n u=5,10,15,8,12 f=20
```
- **Algoritmo**: Merge Sort
- **Tipo**: Numérico
- **Orientación**: Norte a Sur
- **Tropas**: 5 Commander, 10 Medics, 15 Tanks, 8 Snipers, 12 Infantry
- **Campo**: 20x20

## Estructura del Proyecto

```
src/
├── Main.java                 # Clase principal
├── model/                    # Modelo (lógica de negocio)
│   ├── Troop.java           # Clase abstracta base
│   ├── Commander.java       # Tropa Commander
│   ├── Medic.java          # Tropa Medic
│   ├── Tank.java           # Tropa Tank
│   ├── Sniper.java         # Tropa Sniper
│   ├── Infantry.java       # Tropa Infantry
│   ├── Battlefield.java    # Campo de batalla
│   ├── Sortable.java       # Interfaz para algoritmos
│   ├── BubbleSort.java     # Algoritmo Bubble Sort
│   ├── InsertionSort.java  # Algoritmo Insertion Sort
│   ├── MergeSort.java      # Algoritmo Merge Sort
│   ├── QuickSort.java      # Algoritmo Quick Sort
│   └── exceptions/         # Excepciones personalizadas
├── view/                    # Vista (presentación)
│   └── BattlefieldView.java # Visualización del campo
└── controller/              # Controlador (coordinación)
    └── BattleController.java # Controlador principal
```

## Características Técnicas

### Principios de POO Implementados

- **Herencia**: Jerarquía de tropas desde la clase base `Troop`
- **Polimorfismo**: Métodos abstractos implementados por cada tipo de tropa
- **Encapsulamiento**: Atributos privados con getters/setters
- **Abstracción**: Interfaz `Sortable` para algoritmos de ordenamiento

### Manejo de Excepciones

- `InvalidBattlefieldSizeException`: Tamaño de campo inválido
- `InvalidOrientationException`: Orientación inválida
- `TooManyTroopsException`: Demasiadas tropas para el campo
- `InvalidParametersException`: Parámetros mal formados

### Validaciones Implementadas

- Tamaño del campo entre 5 y 1000
- Orientaciones válidas: n, s, e, w
- Tipos de ordenamiento válidos: n, c
- Algoritmos válidos: b, i, m, q
- Cantidades de tropas no negativas
- Total de tropas no excede capacidad del campo


## Extensibilidad

El proyecto está diseñado para ser fácilmente extensible:

- **Nuevos tipos de tropas**: Extender la clase `Troop`
- **Nuevos algoritmos**: Implementar la interfaz `Sortable`
- **Interfaz gráfica**: Reemplazar `BattlefieldView` con implementación GUI
- **Persistencia**: Agregar capa de datos para guardar/cargar configuraciones

## Historial de Versiones

- **v1.0.0** - Implementación inicial con arquitectura MVC completa
  - Sistema de tropas con herencia y polimorfismo
  - 4 algoritmos de ordenamiento
  - Campo de batalla configurable
  - Manejo robusto de excepciones
  - Interfaz CLI funcional
