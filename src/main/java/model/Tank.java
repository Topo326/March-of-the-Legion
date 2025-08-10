package model;

/**
 * Clase Tank - Tropa blindada con rango 21-30
 * Hereda de Troop e implementa comportamiento específico del tanque
 */
public class Tank extends Troop {
    
    //Constructor de Tank

    public Tank(int x, int y) {
        // Tank: muy alta vida, baja velocidad, muy alta fuerza, rango 21-30
        super(150, 1, 100, 21 + (int)(Math.random() * 10), x, y, "T");
    }
    
    @Override
    public void move() {
        // Tank se mueve lentamente pero es muy resistente
        System.out.println("Tank avanza lentamente pero con gran poder destructivo");
    }
    
    @Override
    public void attack() {
        // Tank tiene ataques devastadores
        System.out.println("Tank dispara con fuerza " + getStrength() + " causando destrucción masiva");
    }
    
    @Override
    public String getTroopInfo() {
        return "Tank - Unidad blindada con alta resistencia y poder de fuego devastador";
    }
    
    @Override
    public String getTroopType() {
        return "Tank";
    }
} 