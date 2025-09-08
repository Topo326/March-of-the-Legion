package model;

/**
 * Clase Medic - Tropa de sanidad con rango 11-20
 * Hereda de Troop e implementa comportamiento específico del médico
 */
public class Medic extends Troop {
    
    public Medic(int x, int y) {
        // Medic: vida media, velocidad media, fuerza baja, rango 11-20
        super(70, 5, 30, 11 + (int)(Math.random() * 10), x, y, "M");
    }
    
    @Override
    public void move() {
        System.out.println("Medic se mueve rápidamente para asistir a tropas heridas");
    }
    
    @Override
    public void attack() {
        System.out.println("Medic ataca con fuerza " + getStrength() + " y cura aliados cercanos");
    }
    
    @Override
    public String getTroopInfo() {
        return "Medic - Especialista en sanidad que cura y apoya a las tropas";
    }
    
    @Override
    public String getTroopType() {
        return "Medic";
    }
} 