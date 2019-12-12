/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.projection.model;

import domain.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Monika
 */
public class TableModelMovie extends AbstractTableModel {

    List<Movie> listOfMovies;
    String[] columns = {"Title", "Genre"};

    public TableModelMovie(List<Movie> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Movie getMovie(int row) {
        return listOfMovies.get(row);
    }

    @Override
    public int getRowCount() {
        return listOfMovies.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie m = listOfMovies.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return m.getTitle();
            case 1:
                return m.getGenre();

            default:
                return "N/A";
        }
    }

    public int getSelectedMovie(Movie m) {
        for (Movie mov : listOfMovies) {
            if (mov.getId() == m.getId()) {
                return listOfMovies.indexOf(mov);
            }
        }
        return 0;
    }

}
