package model;

import model.exceptions.*;
import java.util.*;

/**
 * Clase Battlefield - Representa el campo de batalla y maneja la lógica de negocio
 * Implementa la generación aleatoria de tropas y la reorganización según orientación
 */
public class Battlefield {
    
    private String[][] field;
    private List<Troop> troops;
    private int size;
    private char orientation;
    
    // Constantes para tipos de tropas
    private static final String[] TROOP_SYMBOLS_CHARACTER = {"C", "M", "T", "S", "I"};
    private static final String EMPTY_SYMBOL = "*";
    
    //Constructor del campo de batalla

    public Battlefield(int size, char orientation) throws InvalidBattlefieldSizeException, InvalidOrientationException {
        validateSize(size);
        validateOrientation(orientation);
        
        this.size = size;
        this.orientation = orientation;
        this.field = new String[size][size];
        this.troops = new ArrayList<>();
        
        // Inicializar campo vacío
        initializeEmptyField();
    }
    
    //Valida el tamaño del campo de batalla

    private void validateSize(int size) throws InvalidBattlefieldSizeException {
        if (size < 5 || size > 1000) {
            throw new InvalidBattlefieldSizeException(
                "El tamaño del campo de batalla debe estar entre 5 y 1000. Valor recibido: " + size);
        }
    }
    
    //Valida la orientación del ordenamiento

    private void validateOrientation(char orientation) throws InvalidOrientationException {
        if (orientation != 'n' && orientation != 's' && orientation != 'e' && orientation != 'w') {
            throw new InvalidOrientationException(
                "La orientación debe ser 'n' (norte), 's' (sur), 'e' (este) o 'w' (oeste). Valor recibido: " + orientation);
        }
    }
    
    //Inicializa el campo vacío con símbolos de posición vacía
    private void initializeEmptyField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY_SYMBOL;
            }
        }
    }
    

    //Genera tropas aleatoriamente según las cantidades especificadas
    public void generateRandomTroops(int[] troopCounts, char sortType) throws TooManyTroopsException {
        int totalTroops = Arrays.stream(troopCounts).sum();
        
        if (totalTroops > size * size) {
            throw new TooManyTroopsException(
                "El total de tropas (" + totalTroops + ") excede la capacidad del campo (" + (size * size) + ")");
        }
        
        troops.clear();
        initializeEmptyField();
        
        // Generar tropas por tipo
        generateTroopsByType(troopCounts[0], Commander.class, 0, sortType);
        generateTroopsByType(troopCounts[1], Medic.class, 1, sortType);
        generateTroopsByType(troopCounts[2], Tank.class, 2, sortType);
        generateTroopsByType(troopCounts[3], Sniper.class, 3, sortType);
        generateTroopsByType(troopCounts[4], Infantry.class, 4, sortType);
    }
    
   
    //Genera tropas de un tipo específico

    private void generateTroopsByType(int count, Class<? extends Troop> troopClass, int typeIndex, char sortType) {
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            int x, y;
            
            // Encontrar posición vacía aleatoria
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (!field[x][y].equals(EMPTY_SYMBOL));
            
            // Crear tropa y colocarla
            try {
                Troop troop = troopClass.getConstructor(int.class, int.class).newInstance(x, y);
                troops.add(troop);

                if (sortType == 'n') {
                    field[x][y] = String.valueOf(troop.getRank());
                } else {
                    field[x][y] = TROOP_SYMBOLS_CHARACTER[typeIndex];
                }
            } catch (Exception e) {
                System.err.println("Error al crear tropa: " + e.getMessage());
            }
        }
    }


    public void reorganizeField(List<Troop> troops, char sortType) {
        initializeEmptyField();

        // Agrupar las tropas por su símbolo.
        Map<String, List<Troop>> troopsByType = new LinkedHashMap<>();
        for (Troop troop : troops) {
            troopsByType.computeIfAbsent(troop.getSymbol(), k -> new ArrayList<>()).add(troop);
        }

        String[] troopOrderByCharacter = TROOP_SYMBOLS_CHARACTER;

        switch (orientation) {

            case 's':
                int rowS = 0, colS = 0;
                for (String symbol : troopOrderByCharacter) {
                    List<Troop> group = troopsByType.get(symbol);
                    if (group == null) continue;

                    for (Troop troop : group) {
                        if (colS >= size) { // Si la fila actual se llena, saltar a la siguiente.
                            colS = 0;
                            rowS++;
                        }
                        if (rowS >= size) break; // Si el campo de batalla se llena, parar.

                        String valueToDisplay = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                        field[rowS][colS] = valueToDisplay;
                        colS++;
                    }
                    if (colS != 0) { // Forzar que el siguiente grupo empiece en una nueva fila.
                        rowS++;
                        colS = 0;
                    }
                    if (rowS >= size) break;
                }
                break;


            case 'n':
                int rowN = size - 1, colN = 0;
                for (String symbol : troopOrderByCharacter) {
                    List<Troop> group = troopsByType.get(symbol);
                    if (group == null) continue;

                    for (Troop troop : group) {
                        if (colN >= size) {
                            colN = 0;
                            rowN--;
                        }
                        if (rowN < 0) break;

                        String valueToDisplay = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                        field[rowN][colN] = valueToDisplay;
                        colN++;
                    }
                    if (colN != 0) {
                        rowN--;
                        colN = 0;
                    }
                    if (rowN < 0) break;
                }
                break;

            case 'w':
                int rowW = 0, colW = 0;
                for (String symbol : troopOrderByCharacter) {
                    List<Troop> group = troopsByType.get(symbol);
                    if (group == null) continue;

                    for (Troop troop : group) {
                        if (rowW >= size) {
                            rowW = 0;
                            colW++;
                        }
                        if (colW >= size) break;

                        String valueToDisplay = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                        field[rowW][colW] = valueToDisplay;
                        rowW++;
                    }
                    if (rowW != 0) {
                        colW++;
                        rowW = 0;
                    }
                    if (colW >= size) break;
                }
                break;

            case 'e':
                int rowE = size - 1, colE = 0;
                for (String symbol : troopOrderByCharacter) {
                    List<Troop> group = troopsByType.get(symbol);
                    if (group == null) continue;

                    for (Troop troop : group) {
                        if (rowE < 0) {
                            rowE = size - 1;
                            colE++;
                        }
                        if (colE >= size) break;

                        String valueToDisplay = (sortType == 'n') ? String.valueOf(troop.getRank()) : troop.getSymbol();
                        field[rowE][colE] = valueToDisplay;
                        rowE--;
                    }
                    if (rowE != size - 1) {
                        colE++;
                        rowE = size - 1;
                    }
                    if (colE >= size) break;
                }
                break;
        }
    }
    // Getters
    public String[][] getField() { return field; }
    public List<Troop> getTroops() { return troops; }
    public int getSize() { return size; }
    public char getOrientation() { return orientation; }
    
    //Obtiene el campo de batalla como String para visualización

    public String getFieldAsString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(field[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
} 