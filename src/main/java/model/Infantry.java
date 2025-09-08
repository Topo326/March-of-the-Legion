package model;

/**
 * Clase Infantry - Tropa de infantería con rango 41-50
 * Hereda de Troop e implementa comportamiento específico de la infantería
 */
public class Infantry extends Troop {

    
    public Infantry(int x, int y) {
        super(60, 6, 60, 41 + (int)(Math.random() * 10), x, y, "I");
    }
    
    @Override
    public void move() {
        System.out.println("Infantry se mueve de manera ágil y versátil por el campo de batalla");
    }
    
    @Override
    public void attack() {
        System.out.println("Infantry ataca con fuerza " + getStrength() + " usando tácticas de combate");
    }
    
    @Override
    public String getTroopInfo() {
        return "Infantry - Unidad versátil y equilibrada para combate general";
    }
    
    @Override
    public String getTroopType() {
        return "Infantry";
    }
} 