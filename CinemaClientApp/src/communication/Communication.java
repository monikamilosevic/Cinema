/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Monika
 */
public class Communication {

    private static Communication instance;
    Socket s;

    private Communication() {
        try {
            s = new Socket("localhost", 9000);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server is down.");
            System.exit(0);
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void sendRequest(Request req) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(req);
        } catch (Exception ex) {
            System.out.println("Can't send request.");

            JOptionPane.showMessageDialog(null, "Server is down!");
            System.exit(0);
        }
    }

    public Response getResponse() {
        Response res = new Response();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            res = (Response) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Can't get the response from server.");

            JOptionPane.showMessageDialog(null, "Server is down!");
            System.exit(0);
        }
        return res;
    }
}
