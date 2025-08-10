package model;

import java.util.List;

/**
 * Interfaz Sortable para algoritmos de ordenamiento
 * Implementa polimorfismo para diferentes estrategias de ordenamiento
 */
public interface Sortable {
    
    // Ordena una lista de tropas por rango

    List<Troop> sort(List<Troop> troops, char sortType);
    
    //Obtiene el nombre del algoritmo de ordenamiento

    String getAlgorithmName();
    
    // Mide el tiempo de ejecución del algoritmo

    default long measureExecutionTime(List<Troop> troops, char sortType) {
        long startTime = System.nanoTime();
        sort(troops, sortType);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
} 