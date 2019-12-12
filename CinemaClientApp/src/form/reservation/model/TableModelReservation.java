/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.reservation.model;

import domain.Auditorium;
import domain.Reservation;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Monika
 */
public class TableModelReservation extends AbstractTableModel {

    List<Reservation> listOfReservations;
    String[] columns = {"First name", "Last name", "Total score", "Price", "Discount",
        "Discount price", "Tickets", "Movie title", "Date", "Projection points", "Auditoriums"};

    public TableModelReservation(List<Reservation> listOfReservations) {
        this.listOfReservations = listOfReservations;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Reservation getReservation(int row) {
        return listOfReservations.get(row);
    }

    public List<Reservation> getListOfReservations() {
        return listOfReservations;
    }

    @Override
    public int getRowCount() {
        return listOfReservations.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation r = listOfReservations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getMember().getFirstname();
            case 1:
                return r.getMember().getLastname();
            case 2:
                return r.getMember().getTotalScore();
            case 3:
                return r.getPrice();
            case 4:
                return r.getDiscount();
            case 5:
                return r.getDiscountPrice();
            case 6:
                return r.getTickets();
            case 7:
                return r.getProjection().getMovie().getTitle();
            case 8:
                return new SimpleDateFormat("dd.MM.yyyy. HH:mm").format(r.getProjection().getDate());
            case 9:
                return r.getProjection().getScore();
            case 10:
                String s = "";

                int i;
                for (i = 0; i < r.getProjection().getAuditoriums().size() - 1; i++) {
                    Auditorium a = r.getProjection().getAuditoriums().get(i);
                    s += a.getName() + ", ";
                }
                s += r.getProjection().getAuditoriums().get(i).getName();
                return s;
            default:
                return "N/A";
        }
    }
}
