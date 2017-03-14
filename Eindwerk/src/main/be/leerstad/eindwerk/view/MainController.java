package be.leerstad.eindwerk.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    public TextField voornaam;
    public TextField achternaam;
    public Label resultaat;

    public void handleButtonAction(ActionEvent event) {
        resultaat.setText(nummerUitNaam(voornaam.getText(),achternaam.getText()));
    }

    private String nummerUitNaam (String voornaam, String achternaam) {
        StringBuilder result = new StringBuilder();
        if (voornaam.length() >= 1) result.append(voornaam.charAt(0));
        if (achternaam.length() >= 7) result.append(achternaam.substring(0,7));
        else if (achternaam.length() >= 1) result.append(achternaam);
        return result.toString();
    }
}
