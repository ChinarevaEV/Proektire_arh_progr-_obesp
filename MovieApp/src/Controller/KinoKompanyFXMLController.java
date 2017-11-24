package Controller;

import buisness_logic.Film;
import facade.Facade;
import facade.PersonFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import movieapp.MovieApp;

public class KinoKompanyFXMLController  implements Initializable , ControllerWithIdentificator{
private Facade facade;
PersonFacade compF;
static String login;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<String> cmbBoxStatus;
    
    @FXML
    private TableView<Film> tblViewTrips;

    @FXML
    private Button btnLogOut;

    @FXML
    private Label lblName;

    @FXML
    private Button btnAddCash;

    @FXML
    private Label lblCash;

    @FXML
    private Label lblType;

    @FXML
    private Label lblAnswer;

    @FXML
    private TextField txtAddCash;

    @FXML
    private Button btnCreateNewTrip;

    @FXML
    private Label lblLogin;

    @FXML
    void onClickLogOutButton(ActionEvent event) {
        MovieApp.setAuthenticationView();
    }

    @FXML
    void onClickCreateNewTripButton(ActionEvent event) {
        MovieApp.FilmView(login,"create","");
    }

    @FXML
    void onStatusChoosed(ActionEvent event) {
        facade.sync();
        String status = cmbBoxStatus.getValue();
        //initTableView1(status);
        initTableView(status);
        lblCash.setText(compF.getCash());
    }
    
    @FXML
    void onClickAddCashButton(ActionEvent event) {
        try{
            int cash = Integer.parseInt(txtAddCash.getText());
            compF.addCash(cash);
            lblAnswer.setText("Seccesful addition");
            
            initLabels();
        }
        catch (NumberFormatException e) {
            lblAnswer.setText("Bad cash. Try again");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @Override
    public void init(String login, String empty, String empty2) {
        this.login = login;
        compF = new PersonFacade(login);
        facade = Facade.getInstance();
        initLabels();
        initTableView("All");
        initComboBox();
    }
    
    private void initComboBox() {
        cmbBoxStatus.getItems().add("All");
        cmbBoxStatus.getItems().addAll(facade.getFilmStatuses());
        cmbBoxStatus.setValue("All");
    }
    
    private void initLabels() {
        lblName.setText(facade.getPersonName(login));
        lblLogin.setText(login);
        lblType.setText(facade.getPersonType(login));
        lblAnswer.setText("");
        lblCash.setText(compF.getCash());
        
    }
        
    private void initTableView(String status) {
        tblViewTrips.getColumns().clear();
        
        TableColumn<Film, String> dateOfCreation = new TableColumn<>("Name");
        dateOfCreation.setMaxWidth(300);
        dateOfCreation.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Film, String> targetCity = new TableColumn<>("Status");
        targetCity.setMaxWidth(300);
        targetCity.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableColumn<Film, String> statusColumn1 = new TableColumn<>("KinoKompany");
        statusColumn1.setMaxWidth(300);
        statusColumn1.setCellValueFactory(cell -> 
                new SimpleStringProperty(cell.getValue().getKinkomp().getName()));
        
        TableColumn<Film, Integer> dateFrom = new TableColumn<>("Cost");
        dateFrom.setMaxWidth(300);
        dateFrom.setCellValueFactory(new PropertyValueFactory<>("filmProkatCost"));
                
        TableColumn<Film, Integer> statusColumn = new TableColumn<>("Time");
        statusColumn.setMaxWidth(300);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        
        tblViewTrips.setItems(facade.getFilms(login,status));
        tblViewTrips.getColumns().addAll(dateOfCreation,targetCity,statusColumn1,
                dateFrom,statusColumn);
        
        tblViewTrips.setRowFactory(tv -> {
            TableRow<Film> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Film rowData = row.getItem();
                        MovieApp.FilmView(login,"",Integer.toString(rowData.getId()));
                        //MovieApp.createCustomerTripView(Integer.toString(rowData.getId()));
                    }
                }
            });
            return row;
        });
    }
   

}
