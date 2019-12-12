/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import communication.Communication;
import domain.Auditorium;
import domain.Member;
import domain.Movie;
import domain.Projection;
import domain.Reservation;
import domain.User;
import form.MainForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;

/**
 *
 * @author Monika
 */
public class Controler {

    private static Controler instance;
    private List<Member> listOfMembers;
    private List<Projection> listOfProjections;
    private List<Movie> listOfMovies;
    private List<Auditorium> listOfAuditoriums;
    private List<Reservation> listOfReservations;
    private User currentUser;
    private MainForm mf;

    private Controler() {
    }

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    public MainForm getMf() {
        return mf;
    }

    public void setMf(MainForm mf) {
        this.mf = mf;
    }

    public Response save(Member m) {
        Request req = new Request();
        req.setOperation(Operation.SAVE_MEMBERS);
        req.setData(m);
        Communication.getInstance().sendRequest(req);

        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public String end() {
        Request req = new Request();
        req.setOperation(Operation.END);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res.getMessage();
    }

    public Response update(Member m) {
        Request req = new Request();
        req.setOperation(Operation.EDIT_MEMBERS);
        req.setData(m);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;

    }

    public Response delete(Member m) {
        Request req = new Request();
        req.setOperation(Operation.DELETE_MEMBERS);
        req.setData(m);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public boolean login(User u) {
        Request req = new Request();
        req.setOperation(Operation.LOGIN);
        req.setData(u);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        List<User> listUsers = (List<User>) res.getData();
        JOptionPane.showMessageDialog(null, res.getMessage());
        if (listUsers == null || listUsers.isEmpty()) {
            return false;
        } else {
            currentUser = listUsers.get(0);
            return true;
        }
    }

    public List<Projection> getProjections(String text) {
        Projection p = new Projection(-1, null, 0, 0, 0, new Movie(-1, text, ""), null);
        Request req = new Request();
        req.setOperation(Operation.GET_PROJECTIONS);
        req.setData(p);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        if (!res.isStatus()) {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        listOfProjections = (List<Projection>) res.getData();

        return listOfProjections;
    }

    public Response saveProjection(Projection p) {
        Request req = new Request();
        req.setOperation(Operation.SAVE_PROJECTIONS);
        req.setData(p);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public Response delete(Projection p) {
        Request req = new Request();
        req.setOperation(Operation.DELETE_PROJECTIONS);
        req.setData(p);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public Response updateProjection(Projection p) {
        Request req = new Request();
        req.setOperation(Operation.EDIT_PROJECTIONS);
        req.setData(p);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setListOfMembers(List<Member> listOfMembers) {
        this.listOfMembers = listOfMembers;
    }

    public List<Member> getListOfMembers() {
        return listOfMembers;
    }

    public List<Member> getMembers(String filterText) {
        int score;

        try {
            score = Integer.parseInt(filterText);
        } catch (NumberFormatException | NullPointerException nfe) {
            score = -1;
        }

        Member m = new Member(0, filterText, filterText, "", score);
        Request req = new Request();
        req.setOperation(Operation.GET_MEMBERS);
        req.setData(m);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();

        if (!res.isStatus()) {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        listOfMembers = (List<Member>) res.getData();

        return listOfMembers;
    }

    public List<Projection> getListOfProjections() {
        return listOfProjections;
    }

    public void setListOfProjections(List<Projection> listOfProjections) {
        this.listOfProjections = listOfProjections;
    }

    public List<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<Movie> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public List<Auditorium> getListOfAuditoriums() {
        return listOfAuditoriums;
    }

    public void setListOfAuditoriums(List<Auditorium> listOfAuditoriums) {
        this.listOfAuditoriums = listOfAuditoriums;
    }

    public List<Movie> getMovies() {
        Movie m = new Movie();
        Request req = new Request();
        req.setOperation(Operation.GET_MOVIES);
        req.setData(m);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        if (!res.isStatus()) {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        listOfMovies = (List<Movie>) res.getData();

        return listOfMovies;
    }

    public List<Auditorium> getAuditoriums() {
        Auditorium a = new Auditorium();
        Request req = new Request();
        req.setOperation(Operation.GET_AUDITORIUMS);
        req.setData(a);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        if (!res.isStatus()) {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        listOfAuditoriums = (List<Auditorium>) res.getData();

        return listOfAuditoriums;
    }

    public List<Reservation> getReservations(String text) {
        Reservation r = new Reservation(-1, 0, 0, 0, 0, new Projection(-1, null, 0, 0, 0, new Movie(-1, text, ""), null), new Member(-1, text, text, "", 0));
        Request req = new Request();
        req.setOperation(Operation.GET_RESERVATIONS);
        req.setData(r);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        if (!res.isStatus()) {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        listOfReservations = (List<Reservation>) res.getData();

        return listOfReservations;
    }

    public Response saveReservation(Reservation r) {
        Request req = new Request();
        req.setOperation(Operation.SAVE_RESERVATIONS);
        req.setData(r);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public Response updateReservation(Reservation r) {
        Request req = new Request();
        req.setOperation(Operation.EDIT_RESERVATIONS);
        req.setData(r);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

    public Response deleteReservation(Reservation r) {
        Request req = new Request();
        req.setOperation(Operation.DELETE_RESERVATIONS);
        req.setData(r);
        Communication.getInstance().sendRequest(req);
        Response res = Communication.getInstance().getResponse();
        return res;
    }

}
