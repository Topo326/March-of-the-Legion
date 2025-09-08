package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del algoritmo Insertion Sort
 * Implementa la interfaz Sortable para ordenar tropas por rango
 */
public class InsertionSort implements Sortable {
    
    @Override
    public List<Troop> sort(List<Troop> troops, char sortType) {
        List<Troop> sortedTroops = new ArrayList<>(troops);
        int n = sortedTroops.size();
        
        for (int i = 1; i < n; i++) {
            Troop key = sortedTroops.get(i);
            int j = i - 1;
            
            while (j >= 0 && shouldMove(sortedTroops.get(j), key, sortType)) {
                sortedTroops.set(j + 1, sortedTroops.get(j));
                j = j - 1;
            }
            sortedTroops.set(j + 1, key);
        }
        
        return sortedTroops;
    }
   
    private boolean shouldMove(Troop troop1, Troop troop2, char sortType) {
        if (sortType == 'n') {
            return troop1.getRank() > troop2.getRank();
        } else if (sortType == 'c') {
            return troop1.getSymbol().compareTo(troop2.getSymbol()) > 0;
        }
        return false;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }
} 