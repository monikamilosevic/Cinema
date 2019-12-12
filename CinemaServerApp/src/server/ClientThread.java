/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controler.Controler;
import domain.GeneralEntity;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;
import transfer.Request;
import transfer.util.Operation;

/**
 *
 * @author Monika
 */
class ClientThread extends Thread {

    Socket socket;
    boolean end = false;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!end) {
            Request req = getRequest();
            Response res = new Response();
            GeneralEntity ge = req.getData();
            switch (req.getOperation()) {
                case Operation.LOGIN:
                    res = Controler.getInstance().login(ge);
                    break;
                case Operation.END:
                    end = true;
                    res.setMessage("Server is finished with work.");
                    break;
                case Operation.GET_MEMBERS:
                    res = Controler.getInstance().getMembers(ge);
                    break;
                case Operation.EDIT_MEMBERS:
                    res = Controler.getInstance().updateMember(ge);
                    break;
                case Operation.DELETE_MEMBERS:
                    res = Controler.getInstance().deleteMember(ge);
                    break;
                case Operation.SAVE_MEMBERS:
                    res = Controler.getInstance().saveMember(ge);
                    break;
                case Operation.GET_PROJECTIONS:
                    res = Controler.getInstance().getProjections(ge);
                    break;
                case Operation.GET_MOVIES:
                    res = Controler.getInstance().getMovies(ge);
                    break;
                case Operation.GET_AUDITORIUMS:
                    res = Controler.getInstance().getAuditoriums(ge);
                    break;
                case Operation.SAVE_PROJECTIONS:
                    res = Controler.getInstance().saveProjection(ge);
                    break;
                case Operation.DELETE_PROJECTIONS:
                    res = Controler.getInstance().deleteProjection(ge);
                    break;
                case Operation.EDIT_PROJECTIONS:
                    res = Controler.getInstance().updateProjection(ge);
                    break;
                case Operation.GET_RESERVATIONS:
                    res = Controler.getInstance().getReservations(ge);
                    break;
                case Operation.SAVE_RESERVATIONS:
                    res = Controler.getInstance().saveReservation(ge);
                    break;
                case Operation.EDIT_RESERVATIONS:
                    res = Controler.getInstance().updateReservation(ge);
                    break;
                case Operation.DELETE_RESERVATIONS:
                    res = Controler.getInstance().deleteReservation(ge);
                    break;
            }
            send(res);
        }
    }

    public void send(Response res) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(res);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Request getRequest() {
        Request req = new Request();
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            req = (Request) ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return req;
    }

    void closeConnection() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error while closing client connection.");
        }
    }
}
