/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Monika
 */
public class Response implements Serializable {

    private boolean status;
    private Object data;
    private String message;
    private boolean end = false;

    public Response() {
    }

    public Response(boolean status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEnd() {
        return end;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
