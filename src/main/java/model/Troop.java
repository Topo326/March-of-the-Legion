package model;

/**
 * Clase abstracta base para todas las tropas militares
 * Implementa encapsulamiento y define la interfaz común para todas las tropas
 */
public abstract class Troop {
    
    // Atributos encapsulados - privados
    private int health;
    private int speed;
    private int strength;
    private int rank;
    private int x;
    private int y;
    private String symbol;
    
    //Constructor de la clase Troop

    public Troop(int health, int speed, int strength, int rank, int x, int y, String symbol) {
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.rank = rank;
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }
    
    // Getters y Setters para encapsulamiento
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    
    // Métodos abstractos que deben ser implementados por las clases hijas
    public abstract void move();
    public abstract void attack();
    
    // Método polimórfico para obtener información de la tropa

    public abstract String getTroopInfo();
    
    //Método polimórfico para obtener el tipo de tropa
  
    public abstract String getTroopType();
    
    @Override
    public String toString() {
        return String.format("%s [Rank: %d, Health: %d, Speed: %d, Strength: %d, Position: (%d,%d)]", 
                           getTroopType(), rank, health, speed, strength, x, y);
    }
} 