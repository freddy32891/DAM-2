package cat.proven.watercons;

import cat.proven.watercons.model.Consum;
import cat.proven.watercons.views.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ProvenSoft
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Consum model = new Consum();
                MainFrame layout = new MainFrame(model);
                layout.setVisible(true);
            }
            
        });
    }
    
}
