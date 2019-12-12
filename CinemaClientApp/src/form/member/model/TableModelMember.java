/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.member.model;

import domain.Member;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Miroslav
 */
public class TableModelMember extends AbstractTableModel {

    List<Member> listOfMembers;
    String[] columns = {"First name", "Last name", "Email", "Total score"};

    public TableModelMember(List<Member> listOfMembers) {
        this.listOfMembers = listOfMembers;
    }

    @Override
    public int getRowCount() {
        return listOfMembers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member m = listOfMembers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getFirstname();
            case 1:
                return m.getLastname();
            case 2:
                return m.getEmail();
            case 3:
                return m.getTotalScore();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Member getMember(int row) {
        return listOfMembers.get(row);
    }

    public int getSelectedMember(Member m) {
        for (Member mem : listOfMembers) {
            if (mem.getId() == m.getId()) {
                return listOfMembers.indexOf(mem);
            }
        }
        return 0;
    }

}
