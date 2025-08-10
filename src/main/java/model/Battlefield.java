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
    private static final String[] TROOP_SYMBOLS = {"C", "M", "T", "S", "I"};
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
    public void generateRandomTroops(int[] troopCounts) throws TooManyTroopsException {
        int totalTroops = Arrays.stream(troopCounts).sum();
        
        if (totalTroops > size * size) {
            throw new TooManyTroopsException(
                "El total de tropas (" + totalTroops + ") excede la capacidad del campo (" + (size * size) + ")");
        }
        
        troops.clear();
        initializeEmptyField();
        
        // Generar tropas por tipo
        generateTroopsByType(troopCounts[0], Commander.class, 0);
        generateTroopsByType(troopCounts[1], Medic.class, 1);
        generateTroopsByType(troopCounts[2], Tank.class, 2);
        generateTroopsByType(troopCounts[3], Sniper.class, 3);
        generateTroopsByType(troopCounts[4], Infantry.class, 4);
    }
    
   
    //Genera tropas de un tipo específico

    private void generateTroopsByType(int count, Class<? extends Troop> troopClass, int typeIndex) {
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
                field[x][y] = TROOP_SYMBOLS[typeIndex];
            } catch (Exception e) {
                System.err.println("Error al crear tropa: " + e.getMessage());
            }
        }
    }
    
    //Reorganiza el campo de batalla según la orientación especificada
    public void reorganizeField(List<Troop> troops) {
        // Limpiar campo
        initializeEmptyField();
        
        // Colocar tropas según orientación
        int troopIndex = 0;
        
        switch (orientation) {
            case 'n': // Norte a Sur
                for (int col = 0; col < size && troopIndex < troops.size(); col++) {
                    for (int row = 0; row < size && troopIndex < troops.size(); row++) {
                        Troop troop = troops.get(troopIndex);
                        field[row][col] = troop.getSymbol();
                        troop.setX(row);
                        troop.setY(col);
                        troopIndex++;
                    }
                }
                break;
                
            case 's': // Sur a Norte
                for (int col = 0; col < size && troopIndex < troops.size(); col++) {
                    for (int row = size - 1; row >= 0 && troopIndex < troops.size(); row--) {
                        Troop troop = troops.get(troopIndex);
                        field[row][col] = troop.getSymbol();
                        troop.setX(row);
                        troop.setY(col);
                        troopIndex++;
                    }
                }
                break;
                
            case 'e': // Este a Oeste
                for (int row = 0; row < size && troopIndex < troops.size(); row++) {
                    for (int col = 0; col < size && troopIndex < troops.size(); col++) {
                        Troop troop = troops.get(troopIndex);
                        field[row][col] = troop.getSymbol();
                        troop.setX(row);
                        troop.setY(col);
                        troopIndex++;
                    }
                }
                break;
                
            case 'w': // Oeste a Este
                for (int row = 0; row < size && troopIndex < troops.size(); row++) {
                    for (int col = size - 1; col >= 0 && troopIndex < troops.size(); col--) {
                        Troop troop = troops.get(troopIndex);
                        field[row][col] = troop.getSymbol();
                        troop.setX(row);
                        troop.setY(col);
                        troopIndex++;
                    }
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