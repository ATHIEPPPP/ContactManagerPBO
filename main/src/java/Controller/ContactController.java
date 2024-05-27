package tugasmandiri.contactmanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tugasmandiri.contactmanager.Model.contact;

public class ContactController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;

    private Stage dialogStage;
    private contact contact;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setContact(contact contact) {
        this.contact = contact;

        if (contact != null) {
            nameField.setText(contact.getName());
            emailField.setText(contact.getEmail());
            phoneField.setText(contact.getPhone());
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        contact.setName(nameField.getText());
        contact.setEmail(emailField.getText());
        contact.setPhone(phoneField.getText());
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
