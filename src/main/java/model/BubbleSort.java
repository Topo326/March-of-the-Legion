package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del algoritmo Bubble Sort
 * Implementa la interfaz Sortable para ordenar tropas por rango
 */
public class BubbleSort implements Sortable {
    
    @Override
    public List<Troop> sort(List<Troop> troops, char sortType) {
        List<Troop> sortedTroops = new ArrayList<>(troops);
        int n = sortedTroops.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Troop troop1 = sortedTroops.get(j);
                Troop troop2 = sortedTroops.get(j + 1);
                
                boolean shouldSwap = false;
                
                if (sortType == 'n') {
                    // Ordenamiento numérico por rango
                    shouldSwap = troop1.getRank() > troop2.getRank();
                } else if (sortType == 'c') {
                    // Ordenamiento por caracter (símbolo)
                    shouldSwap = troop1.getSymbol().compareTo(troop2.getSymbol()) > 0;
                }
                
                if (shouldSwap) {
                    // Intercambiar tropas
                    Troop temp = sortedTroops.get(j);
                    sortedTroops.set(j, sortedTroops.get(j + 1));
                    sortedTroops.set(j + 1, temp);
                }
            }
        }
        
        return sortedTroops;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }
} 