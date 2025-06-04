package com.sinse.table.homeproject;

import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel{
	String[][] data;
	String[] title;
	
	@Override
	public int getRowCount() {
		return data.length;
	}
	
	@Override
	public int getColumnCount() {
		return title.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	
}
