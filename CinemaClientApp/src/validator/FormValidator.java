/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author Monika
 */
public class FormValidator {

    private static FormValidator instance;
    String validationMessage = "";

    private FormValidator() {
    }

    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new FormValidator();
        }
        return instance;
    }

    public boolean validateEmpty(JTextField... textF) {
        boolean valid = true;
        int count = 0;
        for (JTextField oneField : textF) {
            if ("".equals(oneField.getText())) {
                oneField.setBorder(BorderFactory.createLineBorder(Color.red, 3, true));
                count++;
                valid = false;
            }
        }

        if (!valid) {
            validationMessage = "You need to fill in all fields.";
        }

        return valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

}
