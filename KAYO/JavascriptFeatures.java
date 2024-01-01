package com;
public class JavascriptFeatures{
	String page;
	int keyword_count,external_count,noscript_count;

public void setPage(String page){
	this.page = page;
}
public String getPage(){
	return page;
}
public void setKeywordCount(int keyword_count){
	this.keyword_count = keyword_count;
}
public int getKeywordCount(){
	return keyword_count;
}

public void setExternalCount(int external_count){
	this.external_count = external_count;
}
public int getExternalCount(){
	return external_count;
}

public void setNoscriptCount(int noscript_count){
	this.noscript_count = noscript_count;
}
public int getNoscriptCount(){
	return noscript_count;
}
}