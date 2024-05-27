package tugasmandiri.contactmanager.Controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tugasmandiri.contactmanager.MainApp;
import tugasmandiri.contactmanager.Model.contact;
import tugasmandiri.contactmanager.util.CsvUtil;

public class MainController {
    @FXML
    private TableView<contact> contactTable;
    @FXML
    private TableColumn<contact, String> nameColumn;
    @FXML
    private TableColumn<contact, String> emailColumn;
    @FXML
    private TableColumn<contact, String> phoneColumn;

    private ObservableList<contact> contactData = FXCollections.observableArrayList();
    private MainApp mainApp;

    public MainController() {
        try {
            contactData.addAll(CsvUtil.readContacts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        contactTable.setItems(contactData);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNewContact() {
        contact tempContact = new contact("", "", "");
        boolean okClicked = showContactEditDialog(tempContact);
        if (okClicked) {
            contactData.add(tempContact);
            saveContacts();
        }
    }

    @FXML
    private void handleEditContact() {
        contact selectedContact = contactTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            boolean okClicked = showContactEditDialog(selectedContact);
            if (okClicked) {
                refreshContactTable();
                saveContacts();
            }
        }
    }

    @FXML
    private void handleDeleteContact() {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contactTable.getItems().remove(selectedIndex);
            saveContacts();
        }
    }

    private void saveContacts() {
        try {
            CsvUtil.writeContacts(contactData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean showContactEditDialog(contact contact) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/contactEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Contact");
            dialogStage.initOwner(mainApp.getPrimaryStage()); // Menggunakan mainApp
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ContactController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setContact(contact);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void refreshContactTable() {
        contactTable.setItems(null);
        contactTable.setItems(contactData);
    }
}
