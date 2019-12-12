/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import domain.GeneralEntity;
import java.io.Serializable;

/**
 *
 * @author Monika
 */
public class Request implements Serializable {

    private int operation;
    private GeneralEntity data;

    public Request() {
    }

    public Request(int operation, GeneralEntity data) {
        this.operation = operation;
        this.data = data;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public GeneralEntity getData() {
        return data;
    }

    public void setData(GeneralEntity data) {
        this.data = data;
    }
}
