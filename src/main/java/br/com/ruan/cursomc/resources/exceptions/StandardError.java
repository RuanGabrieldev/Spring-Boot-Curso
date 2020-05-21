package br.com.ruan.cursomc.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String msg;
	private Integer status;
	private long timeStamp;
	
	public StandardError(String msg, Integer status, long timeStamp) {
		super();
		this.msg = msg;
		this.status = status;
		this.timeStamp = timeStamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
