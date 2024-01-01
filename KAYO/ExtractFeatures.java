package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
public class ExtractFeatures{
	
	static ArrayList<JavascriptFeatures> javascript = new ArrayList<JavascriptFeatures>();
	static ArrayList<MobileFeatures> mobile = new ArrayList<MobileFeatures>();
	static ArrayList<HTMLFeatures> html = new ArrayList<HTMLFeatures>();
	static ArrayList<URLFeatures> url = new ArrayList<URLFeatures>();

public static void clear(){
	javascript.clear();
	mobile.clear();
	html.clear();
	url.clear();
}
public static void readPage(File file){
	try{
		clear();
		File list[] = file.listFiles();
		for(int i=0;i<list.length;i++){
			BufferedReader br = new BufferedReader(new FileReader(list[i]));
			String line = null;
			int js = 0;
			int ex = 0;
			int ns = 0;
			int tel = 0;
			int sms = 0;
			int mms = 0;
			int redirection = 0;
			int subdomain = 0;
			while((line = br.readLine())!=null){
				line = line.trim();
				if(line.length() > 0){
					line = line.toLowerCase();
					if(line.indexOf("text/javascript") != -1 && line.indexOf("src") != -1){
						js = js + 1;
						ex = ex + 1;
					}
					if(line.indexOf("noscript") != -1){
						ns = ns + 1;
					}
					if(line.indexOf("tel:") != -1){
						tel = tel + 1;
					}
					if(line.indexOf("sms:") != -1){
						sms = sms + 1;
					}
					if(line.indexOf("mms:") != -1){
						mms = mms + 1;
					}
					if(line.indexOf("smsto:") != -1){
						sms = sms + 1;
					}
					if(line.indexOf("mmsto:") != -1){
						mms = mms + 1;
					}
					if(line.indexOf("href=") != -1){
						String arr[] = line.split("\\/");
						redirection = redirection + 1;
						String sd[] = line.split("\\.");
						subdomain = subdomain + (sd.length/3);
					}
				}
			}
			br.close();
			System.out.println(js+" "+ex+" "+ns+" "+list[i].getName());
			JavascriptFeatures jf = new JavascriptFeatures();
			jf.setPage(list[i].getName());
			jf.setKeywordCount(js);
			jf.setExternalCount(ex);
			jf.setNoscriptCount(ns);
			javascript.add(jf);

			MobileFeatures mf = new MobileFeatures();
			mf.setPage(list[i].getName());
			mf.setSmsCount(sms);
			mf.setMmsCount(mms);
			mf.setTelphoneCount(tel);
			mobile.add(mf);

			HTMLFeatures hf = new HTMLFeatures();
			hf.setPage(list[i].getName());
			hf.setRedirection(redirection/list.length);
			html.add(hf);

			URLFeatures uf = new URLFeatures();
			uf.setPage(list[i].getName());
			uf.setSubdomain(subdomain/list.length);
			url.add(uf);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}