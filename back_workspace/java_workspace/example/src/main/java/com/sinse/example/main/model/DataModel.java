package com.sinse.example.main.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class DataModel {
	
	public static void fillTable(DefaultTableModel model, List<UserModel> userList) {
		model.setRowCount(0);	//기존 행 삭제
		for(int i=0; i<userList.size(); i++) {
			UserModel user = userList.get(i);
			Object[] row = {user.getEmpno(), user.getEname(), user.getJob(), user.getMgr(), user.getHiredate(),
									user.getSal(), user.getComm(), user.getDeptno()};
			model.addRow(row);
		}
	}
}
