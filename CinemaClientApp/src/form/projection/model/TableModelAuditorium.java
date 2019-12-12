/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.projection.model;

import domain.Auditorium;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Monika
 */
public class TableModelAuditorium extends AbstractTableModel {

    List<Auditorium> listOfAuditoriums;
    String[] columns = {"Name", "Capacity"};

    public TableModelAuditorium(List<Auditorium> listOfAuditoriums) {
        this.listOfAuditoriums = listOfAuditoriums;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Auditorium getAuditorium(int row) {
        return listOfAuditoriums.get(row);
    }

    @Override
    public int getRowCount() {
        return listOfAuditoriums.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auditorium a = listOfAuditoriums.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getName();
            case 1:
                return a.getCapacity();
            default:
                return "N/A";
        }
    }

    public List<Auditorium> getAuditoriums(int[] rows) {
        List<Auditorium> list = new ArrayList<Auditorium>();

        for (int i = 0; i < rows.length; i++) {
            list.add(getAuditorium(rows[i]));
        }
        return list;
    }

    public int getSelectedAuditorium(Auditorium a) {
        for (Auditorium aud : listOfAuditoriums) {
            if (aud.getId() == a.getId()) {
                return listOfAuditoriums.indexOf(aud);
            }
        }
        return 0;
    }

}
