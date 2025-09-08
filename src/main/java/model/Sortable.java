package model;

import java.util.List;

/**
 * Interfaz Sortable para algoritmos de ordenamiento
 * Implementa polimorfismo para diferentes estrategias de ordenamiento
 */
public interface Sortable {

    List<Troop> sort(List<Troop> troops, char sortType);

    String getAlgorithmName();


    default long measureExecutionTime(List<Troop> troops, char sortType) {
        long startTime = System.nanoTime();
        sort(troops, sortType);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
} 