package Application;

//Prueba para Github
//Importing every form from the Forms package
import Forms.DespensifyForm;
import Forms.LoginForm;
import Forms.MainAppForm;
import Forms.RegisterForm;
import Forms.UserPanelForm;
import javax.lang.model.element.PackageElement;
import javax.swing.JFrame;

public class AppMain extends JFrame {

    public static void main(String[] args) {
//        new Forms.CreateNewPasswordForm();
        new Forms.LoginForm();
//        new Forms.MainAppForm();
//        new Forms.RegisterForm();
//        new Forms.UserPanelForm();
//        new DespensifyForm().setVisible(true);

    }

}
