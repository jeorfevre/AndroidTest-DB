package com.rizze.vcfiscal.bean;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.rizze.vcfiscal.tools.Tools;

/**
 * Bean para enviar as fotos no S3
 * @author JE@RIZZE.COM
 * @since v0.0.1
 * @category BEAN
 */
public class FotosBean {

	
	@Expose public String data; 					
	@Expose public String idfiscalisacao; 			//JECONEX
	public int HTTP_CODE;
	@Expose public Lugar lugar=new Lugar();
	@Expose public Fiscal fiscal=new Fiscal();
	@Expose public ArrayList<Foto> fotos = new ArrayList<Foto>();
	private String uuid;
	
	
	/**
	 * Constructor of fotobean
	 */
	public FotosBean(){
		data=Tools.getDateAsString();
		uuid= Tools.getUUID();
	}
	
	/**
	 * set Lugar of FotosBean
	 * @param estado
	 * @param municipio
	 * @param localdavotacao_id
	 * @param localdavotacao_descricao
	 * @param urna_id
	 * @return 400: malformed, 200: OK
	 */
	public int setLugar(String estado, String municipio, String localdavotacao_id, String localdavotacao_descricao, String urna_id){
		if(estado==null || municipio==null || localdavotacao_id==null || urna_id==null){
			
			return 400;
		}		
		lugar.estado=estado;
		lugar.localdavotacao_descricao=localdavotacao_descricao;
		lugar.localdavotacao_id=localdavotacao_id;
		lugar.municipio=municipio;
		lugar.urna_id=urna_id;
		return 200;		
	}
	
	/**
	 * @param sobrenome
	 * @param nome
	 * @param email
	 * @param funcao
	 * @return
	 */
	public int setFiscal(String sobrenome,String nome, String email, String funcao){
		fiscal.sobrenome=sobrenome;
		fiscal.nome=nome;
		fiscal.email=email;
		fiscal.funcao=funcao;
		return 200;
	}

	/**
	 * Acrecentar com uma foto
	 * @param path
	 * @return 400: error malformed call, 200 : added
	 */
	public int addFoto(String path, int ordem){
		
		//ERRORS
		if(path==null || path.trim().length()==0){return 400;}
		if(ordem<0 && ordem>= fotos.size()){return 400;}
		if(positionOrdem(ordem)!=-1){return 400;}
		
		Foto f =new Foto();
		f.ordem=ordem;
		f.photoPathAndroid=path;
		f.statusEnvioCode=0;
		f.statusEnvioTexto="WAITING";
		f.data=Tools.getDateAsString();
		
		f.key="FOTOS/"+lugar.estado.toUpperCase()+"/"+lugar.municipio.toUpperCase()+
				"/"+uuid.substring(0,2)+"/"+uuid.substring(2,4)+"/"+uuid.substring(4,6)+"/"+uuid+"-"+ordem+".jpg";
		   
		
		//ADDING TO FOTOS
		fotos.add(f);
		
		return 200;
	}
	
	

	/**
	 * get Bucket key for foto (ordem)
	 * @param ordem
	 * @return
	 */
	public String getPhotoKey(int ordem) {
		
		//LOOKUP FOR ORDEM
		int i= positionOrdem(ordem);
		if(i==-1)return null;
		
		//ORDEM IS FOUND
		return fotos.get(i).key;	

	}
	
	
	public String getJsonKey(){
		return "FOTOSJSON/"+lugar.estado.toUpperCase()+"/"+lugar.municipio.toUpperCase()+
				"/"+uuid.substring(0,2)+"/"+uuid.substring(2,4)+"/"+uuid.substring(4,6)+"/"+uuid+".json";
		   
		
	}

	/**
	 * find ordem in fotos list
	 * @param ordem
	 * @return -1:not found, numbe
	 * r =pos i
	 */
	public int positionOrdem(int ordem) {
		int i=0;
		for(i=0; i< fotos.size();i++){
			if(ordem==fotos.get(i).ordem){break;}
		}
		
		if(i==fotos.size()){
			
			return -1;
		}
		
		return i;
	}
	
	
	/**
	 * return json string of current object
	 * @return
	 */
	public String toJson(){		
		GsonBuilder gb= new GsonBuilder();
		gb.serializeNulls();
		gb.excludeFieldsWithoutExposeAnnotation();
		Gson gson =gb.create();
		return gson.toJson(this);
	}
	
	
}
