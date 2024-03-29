package cat.proven.storedb;

import cat.proven.storedb.controllers.MainController;
import cat.proven.storedb.model.Model;

/**
 *
 * @author mati
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Model model = new Model();
        MainController controller = new MainController(model);
        controller.getView().show();
    }

}
