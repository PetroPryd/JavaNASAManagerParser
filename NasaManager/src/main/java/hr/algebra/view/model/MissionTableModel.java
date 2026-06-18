/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Mission;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petro Prydatkevych
 */
public class MissionTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"ID", "Name", "Code", "Agency", "Launch Year"};
 
    private List<Mission> items;
 
    public MissionTableModel(List<Mission> items) {
        this.items = items;
    }
 
    public void setItems(List<Mission> items) {
        this.items = items;
        fireTableDataChanged();
    }
 
    public Mission getItemAt(int rowIndex) {
        return items.get(rowIndex);
    }
 
    @Override
    public int getRowCount() {
        return items == null ? 0 : items.size();
    }
 
    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }
 
    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex == 0 || columnIndex == 4) ? Integer.class : String.class;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mission m = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> m.getId();
            case 1 -> m.getName();
            case 2 -> m.getCode();
            case 3 -> m.getAgency() == null ? "" : m.getAgency().getFullName();
            case 4 -> m.getLaunchYear();
            default -> null;
        };
    }
    
}
