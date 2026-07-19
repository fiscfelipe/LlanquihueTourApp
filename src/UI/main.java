package UI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            try { 
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ignored) {
            
            }
            new VentanaPrincipal().setVisible(true);
        });
    }
}
