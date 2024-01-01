package com;
public class MobileFeatures{
	String page;
	int sms_count,mms_count,tel_count;

public void setPage(String page){
	this.page = page;
}
public String getPage(){
	return page;
}
public void setSmsCount(int sms_count){
	this.sms_count = sms_count;
}
public int getSmsCount(){
	return sms_count;
}

public void setMmsCount(int mms_count){
	this.mms_count = mms_count;
}
public int getMmsCount(){
	return mms_count;
}

public void setTelphoneCount(int tel_count){
	this.tel_count = tel_count;
}
public int getTelephoneCount(){
	return tel_count;
}
}