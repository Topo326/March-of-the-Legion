package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del algoritmo Merge Sort
 * Implementa la interfaz Sortable para ordenar tropas por rango
 */
public class MergeSort implements Sortable {
    
    @Override
    public List<Troop> sort(List<Troop> troops, char sortType) {
        List<Troop> sortedTroops = new ArrayList<>(troops);
        mergeSort(sortedTroops, 0, sortedTroops.size() - 1, sortType);
        return sortedTroops;
    }
 
    private void mergeSort(List<Troop> troops, int left, int right, char sortType) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(troops, left, mid, sortType);
            mergeSort(troops, mid + 1, right, sortType);
            merge(troops, left, mid, right, sortType);
        }
    }
    
    private void merge(List<Troop> troops, int left, int mid, int right, char sortType) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        List<Troop> leftTroops = new ArrayList<>();
        List<Troop> rightTroops = new ArrayList<>();
        
        for (int i = 0; i < n1; i++) {
            leftTroops.add(troops.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightTroops.add(troops.get(mid + 1 + j));
        }
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (shouldComeFirst(leftTroops.get(i), rightTroops.get(j), sortType)) {
                troops.set(k, leftTroops.get(i));
                i++;
            } else {
                troops.set(k, rightTroops.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            troops.set(k, leftTroops.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            troops.set(k, rightTroops.get(j));
            j++;
            k++;
        }
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
        return "Merge Sort";
    }
} 