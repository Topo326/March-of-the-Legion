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
    private final Random random = new Random();

    private static final String[] TROOP_SYMBOLS_CHARACTER = {"C", "M", "T", "S", "I"};
    private static final String EMPTY_SYMBOL = "*";

    public Battlefield(int size, char orientation) throws InvalidBattlefieldSizeException, InvalidOrientationException {
        validateSize(size);
        validateOrientation(orientation);

        this.size = size;
        this.orientation = orientation;
        this.field = new String[size][size];
        this.troops = new ArrayList<>();

        initializeEmptyField();
    }

    private void validateSize(int size) throws InvalidBattlefieldSizeException {
        if (size < 5 || size > 1000) {
            throw new InvalidBattlefieldSizeException(
                "El tamaño del campo de batalla debe estar entre 5 y 1000. Valor recibido: " + size);
        }
    }

    private void validateOrientation(char orientation) throws InvalidOrientationException {
        if (orientation != 'n' && orientation != 's' && orientation != 'e' && orientation != 'w') {
            throw new InvalidOrientationException(
                "La orientación debe ser 'n' (norte), 's' (sur), 'e' (este) o 'w' (oeste). Valor recibido: " + orientation);
        }
    }

    private void initializeEmptyField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY_SYMBOL;
            }
        }
    }

    public void generateRandomTroops(int[] troopCounts, char sortType) throws TooManyTroopsException {
        int totalTroops = Arrays.stream(troopCounts).sum();

        if (totalTroops > size * size) {
            throw new TooManyTroopsException(
                "El total de tropas (" + totalTroops + ") excede la capacidad del campo (" + (size * size) + ")");
        }

        troops.clear();
        initializeEmptyField();

        generateTroopsByType(troopCounts[0], Commander.class, 0, sortType);
        generateTroopsByType(troopCounts[1], Medic.class, 1, sortType);
        generateTroopsByType(troopCounts[2], Tank.class, 2, sortType);
        generateTroopsByType(troopCounts[3], Sniper.class, 3, sortType);
        generateTroopsByType(troopCounts[4], Infantry.class, 4, sortType);
    }

    private void generateTroopsByType(int count, Class<? extends Troop> troopClass, int typeIndex, char sortType) {
        for (int i = 0; i < count; i++) {
            int x;
            int y;

            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (!field[x][y].equals(EMPTY_SYMBOL));

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
        Map<String, List<Troop>> troopsByType = new LinkedHashMap<>();
        for (Troop troop : troops) {
            troopsByType.computeIfAbsent(troop.getSymbol(), k -> new ArrayList<>()).add(troop);
        }

        Reorganizer reorganizer = new Reorganizer();
        reorganizer.reorganize(this.orientation, this.field, this.size, troopsByType, sortType);
    }

    public String[][] getField() { return field; }
    public List<Troop> getTroops() { return troops; }
    public int getSize() { return size; }
    public char getOrientation() { return orientation; }

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