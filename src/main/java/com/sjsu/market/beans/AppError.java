package com.sjsu.market.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AppError {
	
 private String msg;

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public AppError(String msg) {
	super();
	this.msg = msg;
}


}
