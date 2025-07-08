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
import com.sinse.hibernate0708.exception.StoreException;
import com.sinse.hibernate0708.repository.StoreDAO;

public class StoreList extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		
		try {
			List list = storeDAO.selectAll();
			out.print(gson.toJson(list));		//클라이언트가 받을 json 문자열 스트림에 넣기
		}catch(StoreException e) {
			e.printStackTrace();
		}
	}
}
