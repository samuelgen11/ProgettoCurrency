package node.service;

import java.util.List;

import node.model.CurrencyDto;

public interface CurrencyService {
	
	

	public String chiamaServizioEsterno() throws Exception;

	public List<CurrencyDto> convertiLoScaricoDatiInJava() throws Exception;

	public List<CurrencyDto> convertiLoScaricoDatiInJavaESalvaloNelDB() throws Exception;

	public List<CurrencyDto> getAllCurrency(); 
	
	public List<CurrencyDto> getByCurrency(String pair1);
	
}
