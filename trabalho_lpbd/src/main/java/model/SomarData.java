package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SomarData {

	public String SomaData(String pData,int pQtdeDias) {
		 String data_new = "";
		try {
		
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date data = formato.parse(pData);
			formato.applyPattern("dd/MM/yyyy");
			String dataFormatada = formato.format(data);
			
			Date hoje = formato.parse(dataFormatada);
	        int dias = pQtdeDias;  
	        
	        Date nova_data = new Date(hoje.getTime()+((1000*24*60*60)*dias));  
	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        
	        data_new = df.format(nova_data);
        	        
	        SimpleDateFormat vformato = new SimpleDateFormat("dd/MM/yyyy");
	        Date vdata = vformato.parse(data_new);
	        vformato.applyPattern("yyyy-MM-dd");
			String vdataFormatada = vformato.format(vdata);
        
			data_new = vdataFormatada;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na Função Somar Data!");
			data_new = "";
		}
        
        return data_new;
	}
	
}
