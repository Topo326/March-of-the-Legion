package view;

import model.Battlefield;
import model.Troop;
import java.util.List;
import java.util.Arrays;


 //Clase BattlefieldView - Maneja la presentación del campo de batalla
 //Implementa métodos para mostrar el estado inicial y final del campo
 
public class BattlefieldView {
    
    //Muestra el campo de batalla en formato tabular
    
    public void displayBattlefield(Battlefield battlefield, String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
        
        String[][] field = battlefield.getField();
        int size = battlefield.getSize();
        
        // Mostrar encabezados de columnas
        System.out.print("    ");
        for (int j = 0; j < size; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();
        
        // Mostrar separador
        System.out.print("   +");
        for (int j = 0; j < size; j++) {
            System.out.print("---");
        }
        System.out.println("+");
        
        // Mostrar filas con índices
        for (int i = 0; i < size; i++) {
            System.out.printf("%2d |", i);
            for (int j = 0; j < size; j++) {
                System.out.printf(" %s ", field[i][j]);
            }
            System.out.println("|");
        }
        
        // Mostrar separador final
        System.out.print("   +");
        for (int j = 0; j < size; j++) {
            System.out.print("---");
        }
        System.out.println("+");
        
        System.out.println();
    }
    
    //Muestra la leyenda de símbolos
    
    public void displayLegend() {
        System.out.println("LEYENDA:");
        System.out.println("C = Commander (Rango 0-10)");
        System.out.println("M = Medic (Rango 11-20)");
        System.out.println("T = Tank (Rango 21-30)");
        System.out.println("S = Sniper (Rango 31-40)");
        System.out.println("I = Infantry (Rango 41-50)");
        System.out.println("* = Posición vacía");
        System.out.println();
    }
    
    
    //Muestra los detalles de configuración
    
    public void displayConfiguration(String algorithm, char sortType, char orientation, 
                                   int[] troopCounts, int fieldSize) {
        System.out.println("CONFIGURACIÓN:");
        System.out.println("Algoritmo: " + algorithm);
        System.out.println("Tipo de ordenamiento: " + (sortType == 'n' ? "Numérico" : "Caracter"));
        System.out.println("Orientación: " + getOrientationDescription(orientation));
        System.out.println("Tamaño del campo: " + fieldSize + "x" + fieldSize);
        System.out.println("Distribución de tropas:");
        System.out.println("  Commander: " + troopCounts[0]);
        System.out.println("  Medic: " + troopCounts[1]);
        System.out.println("  Tank: " + troopCounts[2]);
        System.out.println("  Sniper: " + troopCounts[3]);
        System.out.println("  Infantry: " + troopCounts[4]);
        System.out.println("Total de tropas: " + Arrays.stream(troopCounts).sum());
        System.out.println();
    }
    //Obtiene la descripción de la orientación
    
    private String getOrientationDescription(char orientation) {
        switch (orientation) {
            case 'n': return "Norte a Sur";
            case 's': return "Sur a Norte";
            case 'e': return "Este a Oeste";
            case 'w': return "Oeste a Este";
            default: return "Desconocida";
        }
    }
    
   
    //Muestra el tiempo de ejecución del algoritmo
     
    public void displayExecutionTime(String algorithmName, long executionTime) {
        System.out.println("TIEMPO DE EJECUCIÓN:");
        System.out.println("Algoritmo: " + algorithmName);
        System.out.printf("Tiempo: %.3f milisegundos%n", executionTime / 1_000_000.0);
        System.out.println();
    }

    // Muestra un mensaje de error

    public void displayError(String error) {
        System.err.println("ERROR: " + error);
        System.err.println();
    }
    
    
    // Muestra un mensaje informativo
   
    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
} 