package Controller;

import movieapp.MovieApp;
import facade.Facade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hellen_User
 */
public class AuthenticationFXMLController implements Initializable {
 private Facade facade;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        facade = Facade.getInstance();
    }

    @FXML
    private TextField txtLogin;

    @FXML
    private Label lblAuth;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblAnswer;

    @FXML
    void onClickLoginButton(ActionEvent event) {
        String login = txtLogin.getText();
        String pass = txtPassword.getText();
        if (login.isEmpty() || pass.isEmpty()) {
            lblAnswer.setText("Please fill all fields");
            return;
        }
        
        boolean res = facade.authentication(login, pass);
        if (res == false) {            
            lblAnswer.setText("Incorrect login or password");
            return;
        }
        
        MovieApp.setPersonView(login);
        
    }
    
    @FXML
    void onClickRegisterButton(ActionEvent event) {
        MovieApp.setRegisterView();
    }

}
