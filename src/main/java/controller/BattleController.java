package controller;

import model.*;
import model.exceptions.*;
import view.BattlefieldView;
import java.util.*;

/**
 * Clase BattleController - Controlador principal que coordina el flujo del programa
 * Implementa el parsing de argumentos CLI y coordina la interacción entre Model y View
 */
public class BattleController {
    
    private BattlefieldView view;
    private Map<String, Sortable> sortingAlgorithms;
    
    // Constructor del controlador
    
    public BattleController() {
        this.view = new BattlefieldView();
        initializeSortingAlgorithms();
    }
    
    //Inicializa los algoritmos de ordenamiento disponibles
     
    private void initializeSortingAlgorithms() {
        sortingAlgorithms = new HashMap<>();
        sortingAlgorithms.put("b", new BubbleSort());
        sortingAlgorithms.put("i", new InsertionSort());
        sortingAlgorithms.put("m", new MergeSort());
        sortingAlgorithms.put("q", new QuickSort());
    }
    
    //Método principal que ejecuta el programa

    public void run(String[] args) {
        try {
            // Parsear argumentos
            CommandLineArgs cliArgs = parseArguments(args);
            
            // Validar argumentos
            validateArguments(cliArgs);
            
            // Crear campo de batalla
            Battlefield battlefield = new Battlefield(cliArgs.fieldSize, cliArgs.orientation);
            
            // Mostrar configuración
            view.displayConfiguration(
                getAlgorithmName(cliArgs.algorithm),
                cliArgs.sortType,
                cliArgs.orientation,
                cliArgs.troopCounts,
                cliArgs.fieldSize
            );
            
            // Generar tropas aleatoriamente
            battlefield.generateRandomTroops(cliArgs.troopCounts);
            
            // Mostrar campo inicial
            view.displayLegend();
            view.displayBattlefield(battlefield, "CAMPO DE BATALLA INICIAL");
            
            // Aplicar ordenamiento
            Sortable sorter = sortingAlgorithms.get(cliArgs.algorithm);
            if (sorter == null) {
                throw new InvalidParametersException("Algoritmo de ordenamiento no válido: " + cliArgs.algorithm);
            }
            
            // Medir tiempo de ejecución
            long startTime = System.nanoTime();
            List<Troop> sortedTroops = sorter.sort(battlefield.getTroops(), cliArgs.sortType);
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            
            // Mostrar tiempo de ejecución
            view.displayExecutionTime(sorter.getAlgorithmName(), executionTime);
            
            // Reorganizar campo según orientación
            battlefield.reorganizeField(sortedTroops);
            
            // Mostrar campo final
            view.displayBattlefield(battlefield, "CAMPO DE BATALLA FINAL (ORDENADO)");
            
            view.displayMessage("¡Simulación completada exitosamente!");
            
        } catch (InvalidParametersException e) {
            view.displayError("Parámetros inválidos: " + e.getMessage());
            displayUsage();
        } catch (InvalidBattlefieldSizeException e) {
            view.displayError("Tamaño de campo inválido: " + e.getMessage());
            displayUsage();
        } catch (InvalidOrientationException e) {
            view.displayError("Orientación inválida: " + e.getMessage());
            displayUsage();
        } catch (TooManyTroopsException e) {
            view.displayError("Demasiadas tropas: " + e.getMessage());
            displayUsage();
        } catch (Exception e) {
            view.displayError("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            displayUsage();
        }
    }
    
    // Parsea los argumentos de línea de comandos
  
    private CommandLineArgs parseArguments(String[] args) throws InvalidParametersException {
        CommandLineArgs cliArgs = new CommandLineArgs();
        
        for (String arg : args) {
            if (arg.startsWith("a=")) {
                cliArgs.algorithm = arg.substring(2);
            } else if (arg.startsWith("t=")) {
                cliArgs.sortType = arg.substring(2).charAt(0);
            } else if (arg.startsWith("o=")) {
                cliArgs.orientation = arg.substring(2).charAt(0);
            } else if (arg.startsWith("u=")) {
                cliArgs.troopCounts = parseTroopCounts(arg.substring(2));
            } else if (arg.startsWith("f=")) {
                cliArgs.fieldSize = Integer.parseInt(arg.substring(2));
            }
        }
        
        return cliArgs;
    }
    
    //Parsea las cantidades de tropas desde un string

    private int[] parseTroopCounts(String troopCountsStr) throws InvalidParametersException {
        try {
            String[] parts = troopCountsStr.split(",");
            if (parts.length != 5) {
                throw new InvalidParametersException("Se requieren exactamente 5 tipos de tropas");
            }
            
            int[] counts = new int[5];
            for (int i = 0; i < 5; i++) {
                counts[i] = Integer.parseInt(parts[i].trim());
                if (counts[i] < 0) {
                    throw new InvalidParametersException("Las cantidades de tropas no pueden ser negativas");
                }
            }
            
            return counts;
        } catch (NumberFormatException e) {
            throw new InvalidParametersException("Formato inválido para cantidades de tropas: " + troopCountsStr);
        }
    }
    
    //Valida que todos los argumentos requeridos estén presentes

    private void validateArguments(CommandLineArgs cliArgs) throws InvalidParametersException {
        if (cliArgs.algorithm == null) {
            throw new InvalidParametersException("Falta el parámetro 'a' (algoritmo)");
        }
        if (cliArgs.sortType == '\0') {
            throw new InvalidParametersException("Falta el parámetro 't' (tipo de ordenamiento)");
        }
        if (cliArgs.orientation == '\0') {
            throw new InvalidParametersException("Falta el parámetro 'o' (orientación)");
        }
        if (cliArgs.troopCounts == null) {
            throw new InvalidParametersException("Falta el parámetro 'u' (cantidades de tropas)");
        }
        if (cliArgs.fieldSize == 0) {
            cliArgs.fieldSize = 10; // Valor por defecto
        }
        
        // Validar valores específicos
        if (!sortingAlgorithms.containsKey(cliArgs.algorithm)) {
            throw new InvalidParametersException("Algoritmo no válido: " + cliArgs.algorithm);
        }
        if (cliArgs.sortType != 'n' && cliArgs.sortType != 'c') {
            throw new InvalidParametersException("Tipo de ordenamiento debe ser 'n' o 'c'");
        }
        if (cliArgs.orientation != 'n' && cliArgs.orientation != 's' && 
            cliArgs.orientation != 'e' && cliArgs.orientation != 'w') {
            throw new InvalidParametersException("Orientación debe ser 'n', 's', 'e' o 'w'");
        }
    }
    
    //Obtiene el nombre del algoritmo de ordenamiento
  
    private String getAlgorithmName(String algorithmKey) {
        Sortable sorter = sortingAlgorithms.get(algorithmKey);
        return sorter != null ? sorter.getAlgorithmName() : "Desconocido";
    }
    
    //Muestra la ayuda de uso del programa
    private void displayUsage() {
        System.out.println("\nUSO:");
        System.out.println("java Main a=<algoritmo> t=<tipo> o=<orientación> u=<tropas> [f=<tamaño>]");
        System.out.println("\nPARÁMETROS:");
        System.out.println("a = Algoritmo de ordenamiento (b=bubble, i=insertion, m=merge, q=quick)");
        System.out.println("t = Tipo de ordenamiento (n=numérico, c=caracter)");
        System.out.println("o = Orientación (n=norte, s=sur, e=este, w=oeste)");
        System.out.println("u = Cantidades de tropas CSV (Commander,Medic,Tank,Sniper,Infantry)");
        System.out.println("f = Tamaño del campo (opcional, default=10, rango 5-1000)");
        System.out.println("\nEJEMPLOS:");
        System.out.println("java Main a=i t=n o=s u=1,2,3,2,4 f=8");
        System.out.println("java Main a=q t=c o=e u=0,1,1,1,2");
        System.out.println();
    }
    
    // Clase interna para almacenar los argumentos de línea de comandos
    // Utilizada para simplificar el manejo de parámetros
    // y evitar múltiples parámetros individuales en los métodos
    private static class CommandLineArgs {
        String algorithm;
        char sortType = '\0';
        char orientation = '\0';
        int[] troopCounts;
        int fieldSize = 0;
    }
} 