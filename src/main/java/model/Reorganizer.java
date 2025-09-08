package model;

import java.util.List;
import java.util.Map;

/**
 * Contiene las estrategias para reorganizar el campo de batalla.
 * Cada método implementa una orientación de colocación diferente.
 */
public class Reorganizer {

    public void reorganize(char orientation, String[][] field, int size, Map<String, List<Troop>> troopsByType, char sortType) {
        switch (orientation) {
            case 's':
                placeGroupsInRowsTopToBottom(field, size, troopsByType, sortType);
                break;
            case 'n':
                placeGroupsInRowsBottomToTop(field, size, troopsByType, sortType);
                break;
            case 'w':
                placeGroupsInColsTopToBottom(field, size, troopsByType, sortType);
                break;
            case 'e':
                placeGroupsInColsBottomToTop(field, size, troopsByType, sortType);
                break;
        }
    }

    private void placeGroupsInRowsTopToBottom(String[][] field, int size, Map<String, List<Troop>> troopsByType, char sortType) {
        String[] troopOrder = {"C", "M", "T", "S", "I"};
        int row = 0, col = 0;
        for (String symbol : troopOrder) {
            List<Troop> group = troopsByType.get(symbol);
            if (group == null) continue;

            for (Troop troop : group) {
                if (col >= size) {
                    col = 0;
                    row++;
                }
                if (row >= size) break;

                field[row][col] = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                col++;
            }
            if (col != 0) {
                row++;
                col = 0;
            }
            if (row >= size) break;
        }
    }

    private void placeGroupsInRowsBottomToTop(String[][] field, int size, Map<String, List<Troop>> troopsByType, char sortType) {
        String[] troopOrder = {"C", "M", "T", "S", "I"};
        int row = size - 1, col = 0;
        for (String symbol : troopOrder) {
            List<Troop> group = troopsByType.get(symbol);
            if (group == null) continue;

            for (Troop troop : group) {
                if (col >= size) {
                    col = 0;
                    row--;
                }
                if (row < 0) break;

                field[row][col] = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                col++;
            }
            if (col != 0) {
                row--;
                col = 0;
            }
            if (row < 0) break;
        }
    }

    private void placeGroupsInColsTopToBottom(String[][] field, int size, Map<String, List<Troop>> troopsByType, char sortType) {
        String[] troopOrder = {"C", "M", "T", "S", "I"};
        int row = 0, col = 0;
        for (String symbol : troopOrder) {
            List<Troop> group = troopsByType.get(symbol);
            if (group == null) continue;

            for (Troop troop : group) {
                if (row >= size) {
                    row = 0;
                    col++;
                }
                if (col >= size) break;

                field[row][col] = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                row++;
            }
            if (row != 0) {
                col++;
                row = 0;
            }
            if (col >= size) break;
        }
    }

    private void placeGroupsInColsBottomToTop(String[][] field, int size, Map<String, List<Troop>> troopsByType, char sortType) {
        String[] troopOrder = {"C", "M", "T", "S", "I"};
        int row = size - 1, col = 0;
        for (String symbol : troopOrder) {
            List<Troop> group = troopsByType.get(symbol);
            if (group == null) continue;

            for (Troop troop : group) {
                if (row < 0) {
                    row = size - 1;
                    col++;
                }
                if (col >= size) break;

                field[row][col] = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                row--;
            }
            if (row != size - 1) {
                col++;
                row = size - 1;
            }
            if (col >= size) break;
        }
    }
}

