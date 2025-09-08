package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del algoritmo Quick Sort
 * Implementa la interfaz Sortable para ordenar tropas por rango
 */
public class QuickSort implements Sortable {
    
    @Override
    public List<Troop> sort(List<Troop> troops, char sortType) {
        List<Troop> sortedTroops = new ArrayList<>(troops);
        quickSort(sortedTroops, 0, sortedTroops.size() - 1, sortType);
        return sortedTroops;
    }
    

    private void quickSort(List<Troop> troops, int low, int high, char sortType) {
        if (low < high) {
            int pi = partition(troops, low, high, sortType);
            
            quickSort(troops, low, pi - 1, sortType);
            quickSort(troops, pi + 1, high, sortType);
        }
    }
    
    private int partition(List<Troop> troops, int low, int high, char sortType) {
        Troop pivot = troops.get(high);
        int i = low - 1; 
        
        for (int j = low; j < high; j++) {
            if (shouldComeFirst(troops.get(j), pivot, sortType)) {
                i++;
                
                Troop temp = troops.get(i);
                troops.set(i, troops.get(j));
                troops.set(j, temp);
            }
        }
        
        Troop temp = troops.get(i + 1);
        troops.set(i + 1, troops.get(high));
        troops.set(high, temp);
        
        return i + 1;
    }
    
    private boolean shouldComeFirst(Troop troop1, Troop troop2, char sortType) {
        if (sortType == 'n') {
            return troop1.getRank() <= troop2.getRank();
        } else if (sortType == 'c') {
            return troop1.getSymbol().compareTo(troop2.getSymbol()) <= 0;
        }
        return false;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }
} 