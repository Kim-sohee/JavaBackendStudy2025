package com.sinse.example.main.model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.example.main.repository.UserDAO;

public class UserDataModel extends AbstractTableModel{
	UserDAO userDAO;
	List<UserModel> list;
	String[] column = {"empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno"};
	
	public UserDataModel() {
		userDAO = new UserDAO();
		list = userDAO.selectAll();
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		UserModel user = list.get(row);
		String value = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		switch(col) {
			case 0: value=Integer.toString(user.getEmpno()); break;
			case 1: value=user.getEname(); break;
			case 2: value=user.getJob(); break;
			case 3: value=Integer.toString(user.getMgr()); break;
			case 4: value=format.format(user.getHiredate()); break;
			case 5: value=Integer.toString(user.getSal()); break;
			case 6: value=Integer.toString(user.getComm()); break;
			case 7: value=Integer.toString(user.getDeptno()); break;
		}
		
		return value;
	}
}
