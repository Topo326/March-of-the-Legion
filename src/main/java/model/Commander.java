package model;

/**
 * Clase Commander - Tropa de mando con rango 0-10
 * Hereda de Troop e implementa comportamiento específico del comandante
 */
public class Commander extends Troop {
    
    public Commander(int x, int y) {
        // Commander: alta vida, baja velocidad, alta fuerza, rango 0-10
        super(100, 2, 90, (int)(Math.random() * 11), x, y, "C");
    }
    
    @Override
    public void move() {
        System.out.println("Commander se mueve estratégicamente a una nueva posición");
    }
    
    @Override
    public void attack() {
        System.out.println("Commander ordena un ataque coordinado con fuerza " + getStrength());
    }
    
    @Override
    public String getTroopInfo() {
        return "Commander - Líder táctico con alta autoridad y poder de mando";
    }
    
    @Override
    public String getTroopType() {
        return "Commander";
    }
} 