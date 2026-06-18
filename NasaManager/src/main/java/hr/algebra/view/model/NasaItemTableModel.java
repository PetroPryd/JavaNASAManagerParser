/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.NasaItem;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petro Prydatkevych
 */
public class NasaItemTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {"ID", "Title", "Published"};
 
    private List<NasaItem> items;
 
    public NasaItemTableModel(List<NasaItem> items) {
        this.items = items;
    }
 
    public void setItems(List<NasaItem> items) {
        this.items = items;
        fireTableDataChanged();
    }
 
    public NasaItem getItemAt(int rowIndex) {
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
        return columnIndex == 0 ? Integer.class : String.class;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NasaItem item = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getTitle();
            case 2 -> item.getPublishedDate() == null
                    ? "" : item.getPublishedDate().format(NasaItem.DATE_FORMATTER);
            default -> null;
        };
    }
    
}
