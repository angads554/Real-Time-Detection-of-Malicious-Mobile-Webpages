package com;
import java.util.ArrayList;
public class Classification{
	
	static ArrayList<String> malicious = new ArrayList<String>();

public static void classify(){
	malicious.clear();
	for(int i=0;i<ExtractFeatures.javascript.size();i++){
		JavascriptFeatures jf = ExtractFeatures.javascript.get(i);
		if(jf.getKeywordCount() > 3 && jf.getExternalCount() > 3 && jf.getNoscriptCount() > 3){
			if(!malicious.contains(jf.getPage()))
				malicious.add(jf.getPage());
		}
	}

	for(int i=0;i<ExtractFeatures.html.size();i++){
		HTMLFeatures hf = ExtractFeatures.html.get(i);
		if(hf.getRedirection() > 7){
			if(!malicious.contains(hf.getPage()))
				malicious.add(hf.getPage());
		}
	}

	for(int i=0;i<ExtractFeatures.url.size();i++){
		URLFeatures uf = ExtractFeatures.url.get(i);
		if(uf.getSubdomain() > 7){
			if(!malicious.contains(uf.getPage()))
				malicious.add(uf.getPage());
		}
	}

	for(int i=0;i<ExtractFeatures.mobile.size();i++){
		MobileFeatures mf = ExtractFeatures.mobile.get(i);
		if(mf.getSmsCount() > 3 || mf.getMmsCount() > 3 || mf.getTelephoneCount() > 3){
			if(!malicious.contains(mf.getPage()))
				malicious.add(mf.getPage());
		}
	}
}
}