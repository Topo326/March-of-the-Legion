package model;

/**
 * Clase Sniper - Tropa de precisión con rango 31-40
 * Hereda de Troop e implementa comportamiento específico del francotirador
 */
public class Sniper extends Troop {

    public Sniper(int x, int y) {
        // Sniper: vida baja, velocidad alta, fuerza alta, rango 31-40
        super(50, 8, 80, 31 + (int)(Math.random() * 10), x, y, "S");
    }
    
    @Override
    public void move() {
        System.out.println("Sniper se reposiciona rápidamente para obtener el mejor ángulo de tiro");
    }
    
    @Override
    public void attack() {
        System.out.println("Sniper dispara con precisión letal y fuerza " + getStrength());
    }
    
    @Override
    public String getTroopInfo() {
        return "Sniper - Especialista en ataques de larga distancia con alta precisión";
    }
    
    @Override
    public String getTroopType() {
        return "Sniper";
    }
} 