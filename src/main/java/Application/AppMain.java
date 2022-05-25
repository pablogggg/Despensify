package Application;

import Forms.LoginForm;
import javax.swing.JFrame;

public class AppMain extends JFrame {

    /*El metodo main arranca la app mostrando el formulario de login, a partir
    del cual podremos navegar por esta*/
    public static void main(String[] args) {
        LoginForm loginForm = new Forms.LoginForm();
    }
}