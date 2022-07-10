package Controller;

import DBAccess.DBAppointments;
import Database.DBConnection;
import Model.Appointments;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class loginController implements Initializable {
    public TextField usernameField;
    public TextField passwordField;
    public Button loginButton;
    public Button exitButton;
    public Text invalidCreds;
    public Text zoneInfo;
    private final static Locale LOCALE = Locale.getDefault();
    final static ResourceBundle BUNDLE = ResourceBundle.getBundle("Language/LanguageBundle", LOCALE);
    public Text loginTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTitle.setText(BUNDLE.getString("Title"));
        zoneInfo.setText(ZoneId.systemDefault().toString());

    }

    /**
     * Logs user on click of "Login" button if credentials are valid, else rejects login
     * @throws SQLException
     * @throws IOException
     */

    public void onLogin(ActionEvent actionEvent) throws IOException, SQLException {
        invalidCreds.setText("");
        String username = usernameField.getText();
        String password = passwordField.getText();
        int userID = getUserID(username);
        Users user = new Users();

        FileWriter writer = new FileWriter("login_activity.txt", true);
        PrintWriter printer = new PrintWriter(writer);

        if(validate(userID, password)) {
            user.setUserID(userID);
            user.setUserName(username);
            printer.print("user: " + username + " successfully logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");


            Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main window");
            stage.setScene(scene);
            stage.show();
            notifyAppointment();
        }
        else{
            invalidCreds.setText(BUNDLE.getString("LoginError"));
         //   invalidCreds.setText("Invalid credentials. Please try again");
            printer.print("user: " + username + " failed login attempt at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
        }
        printer.close();



    }

    /**
     * Notifies user if there is an appointment within 15 minutes

     */

    public void notifyAppointment(){
        boolean upcomingAppointment = false;
        LocalDateTime displayStartDateAndTime =  null;
        int getAppID = 0;
        LocalDateTime after15 = LocalDateTime.now().plusMinutes(15);
        LocalDateTime before15 = LocalDateTime.now().minusMinutes(15);
        LocalDateTime start;
        for(Appointments appointment : DBAppointments.getAllAppointments()){
           start = appointment.getAppStartDateAndTime();
           if((start.isAfter(before15) || start.isEqual(before15)) && (start.isBefore(after15) || start.isEqual(after15))){
               getAppID = appointment.getAppID();
               displayStartDateAndTime = start;
               upcomingAppointment = true;
           }
        }

        if(upcomingAppointment){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("You have an appointment within 15 minutes");
            Optional<ButtonType> result = alert.showAndWait();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("You do not have any upcoming appointments");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    /**
     * Validates password
     * @throws SQLException
     * returns Boolean
     *
     */

    private boolean validate(int user, String password) throws SQLException {

        String sql = "SELECT Password FROM users WHERE User_ID ='" + user + "'";
        Statement statement = DBConnection.connection.createStatement();


        ResultSet res = statement.executeQuery(sql);

        while (res.next()) {
            if (res.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets User ID
     * @throws SQLException
     * returns integer
     *
     */

    private int getUserID(String username) throws SQLException {
        int userID = 0;

        Statement st = DBConnection.connection.createStatement();

        String sql = "SELECT User_ID FROM users WHERE User_Name ='" + username + "'";

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            userID = rs.getInt("User_ID");
        }
        return userID;
    }


}
