package com.rizze.vcfiscal.bean;

import com.google.gson.annotations.Expose;
import com.rizze.vcfiscal.tools.Tools;

public class Foto {
	@Expose public int ordem;					//JECONEX
	@Expose public String key; 				//JE
	@Expose public String data;				//JE 
	public String photoPathAndroid;				//JECONEX
	public String statusEnvioTexto;	 			//JE
	public int statusEnvioCode;	 				//JE

	
	public Foto(){
		data=Tools.getDateAsString();
	}
}