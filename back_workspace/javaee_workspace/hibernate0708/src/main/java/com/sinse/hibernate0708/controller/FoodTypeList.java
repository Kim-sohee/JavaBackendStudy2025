package com.sinse.hibernate0708.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sinse.hibernate0708.repository.FoodTypeDAO;

public class FoodTypeList extends HttpServlet {
	FoodTypeDAO foodTypeDAO;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public FoodTypeList() {
		foodTypeDAO = new FoodTypeDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = foodTypeDAO.selectAll();
		
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("application/json");
		
		Gson gson = new Gson();
		String result = gson.toJson(list);
		
		logger.debug("문자열로 변환 후 데이터"+result);
		out.print(result);
	}
}
