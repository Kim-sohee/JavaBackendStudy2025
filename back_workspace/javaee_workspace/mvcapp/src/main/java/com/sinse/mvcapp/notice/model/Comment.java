package com.sinse.mvcapp.notice.model;

import lombok.Data;

@Data
public class Comment {
	private int comment_id;
	private String msg;
	private String user;
	private String msgdate;
	
	private News news;
}
