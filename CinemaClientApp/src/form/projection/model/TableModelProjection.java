/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.projection.model;

import domain.Auditorium;
import domain.Projection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Monika
 */
public class TableModelProjection extends AbstractTableModel {

    List<Projection> listOfProjections;
    String[] columns = {"Date", "Price", "Score", "Free seats", "Movie title", "Auditoriums"};

    public TableModelProjection(List<Projection> listOfProjections) {
        this.listOfProjections = listOfProjections;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Projection getProjection(int row) {
        return listOfProjections.get(row);
    }

    @Override
    public int getRowCount() {
        return listOfProjections.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projection p = listOfProjections.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return new SimpleDateFormat("dd.MM.yyyy. HH:mm").format(p.getDate());
            case 1:
                return p.getPrice();
            case 2:
                return p.getScore();
            case 3:
                return p.getFreeSeats();
            case 4:
                return p.getMovie().getTitle();
            case 5:
                String s = "";

                int i;
                for (i = 0; i < p.getAuditoriums().size() - 1; i++) {
                    Auditorium a = p.getAuditoriums().get(i);
                    s += a.getName() + ", ";
                }
                s += p.getAuditoriums().get(i).getName();
                return s;
            default:
                return "N/A";
        }
    }

    public int getSelectedProjection(Projection p) {
        for (Projection proj : listOfProjections) {
            if (proj.getId() == p.getId()) {
                return listOfProjections.indexOf(proj);
            }
        }
        return 0;
    }

}
