import controller.BattleController;

/**
 * March of the Legion - Programa principal
 * Simula un campo de batalla con tropas militares usando arquitectura MVC
 */
public class Main {
    public static void main(String[] args) {
        try {
            BattleController controller = new BattleController();
            controller.run(args);
        } catch (Exception e) {
            System.err.println("Error crítico: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
} 