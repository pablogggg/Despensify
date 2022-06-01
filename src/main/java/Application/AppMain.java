package Application;

import Forms.LoginForm;
import javax.swing.JFrame;

//Merges completados, trabajando sobre rama master por fin.

public class AppMain extends JFrame {

    /*El metodo main arranca la app mostrando el formulario de login, a partir
    del cual podremos navegar por nuestro programa*/
    public static void main(String[] args) {
        LoginForm loginForm = new Forms.LoginForm();
    }
}