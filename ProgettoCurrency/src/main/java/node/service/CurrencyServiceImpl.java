package node.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import node.entity.CurrencyEntity;
import node.model.CurrencyDto;
import node.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService{
	
	
	
	@Autowired
	public CurrencyRepository currencyRepository;
	
	
	

	@Override
	public String chiamaServizioEsterno() throws Exception {
		// come faccio a fare una chiamata Rest dall'interno della mia applicazione
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("https://cex.io/api/tickers/EUR");
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		String responseString = response.readEntity(String.class);
		System.out.println(responseString);
		return responseString;
//		// avvia client per l'esecuzione di una chiamata REST (GET)
//		ClientRequest request = new ClientRequest("https://cex.io/api/tickers/EUR");
//		// avvia la chiamata REST
//		ClientResponse<String> response = request.get(String.class);
//		// prendimi il contenuto della risposta
//		String responseString = response.getEntity();
//		// ritorna il contenuto della risposta
//		return responseString;
//		
	}

	@Override
	public List<CurrencyDto> convertiLoScaricoDatiInJava() throws Exception {
		String response = chiamaServizioEsterno();
		
				
		// main thread
		// si ma come facciamo a prendere solo l'elemento DATA
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response);
		
		// scorri lista e fammi vedere cosa hai dentro!!
		/* dataContent = jsonNode.get("data");
		 * for(String dataRow : dataContent) {
			System.out.println(dataRow);
		}*/
		
		final List<CurrencyDto> listCurrencyDto = new ArrayList<>();
		
		// ogni riga del json
		jsonNode.get("data").forEach(dataRow -> {
			Random i = new Random();
			int x = i.nextInt();
			// secondo thread
			String[] pairEstratto = dataRow.get("pair").asText().split(":");
			String pair1 = pairEstratto[0];
			String pair2 = pairEstratto[1];
			Long timestampEstratto = dataRow.get("timestamp").asLong();
			Double lastEstratto = dataRow.get("last").asDouble();
			// crea classe java
			CurrencyDto currencyDto = new CurrencyDto();
			currencyDto.setId(x);
			currencyDto.setPair1(pair1);
			currencyDto.setPair2(pair2);
			currencyDto.setTimestamp(timestampEstratto);
			currencyDto.setLast(lastEstratto);
			// aggiungendo elemento
			listCurrencyDto.add(currencyDto);
		});
		
	return listCurrencyDto;
	}

	@Override
	public List<CurrencyDto> convertiLoScaricoDatiInJavaESalvaloNelDB() throws Exception {
		List<CurrencyDto> result = convertiLoScaricoDatiInJava();
		
		// si ma come si salva sul db? 
		// convertire in entita
		List<CurrencyEntity> entityResult = result.stream().map(
			// per ogni elemento del mio result, tu devi fare una funzione!
			currencyDtoCheStoScorrendo -> {
				// questa è la funzione!!
				CurrencyEntity currencyEntity = new CurrencyEntity();
				currencyEntity.setId(currencyDtoCheStoScorrendo.getId());
				currencyEntity.setPair1(currencyDtoCheStoScorrendo.getPair1());
				currencyEntity.setPair2(currencyDtoCheStoScorrendo.getPair2());
				currencyEntity.setTimestamp(currencyDtoCheStoScorrendo.getTimestamp());
				currencyEntity.setLast(currencyDtoCheStoScorrendo.getLast());
				return currencyEntity;
			}
		).collect(Collectors.toList());
		
		
		currencyRepository.saveAll(entityResult);
		return result;
	}

	@Override
	public List<CurrencyDto> getAllCurrency() {
		// scarica dati
		List<CurrencyEntity> listEntities = currencyRepository.findAll();
		
		// trasforma da entity a DTO
		return listEntities.stream().map(
			// scorro ogni elemento (pairEntity)
			currencyEntity -> {
				// convert il pairEntity in DTO
				CurrencyDto pairDto = new CurrencyDto();
				pairDto.setId(currencyEntity.getId());
				pairDto.setPair1(currencyEntity.getPair1());
				pairDto.setPair2(currencyEntity.getPair2());
				pairDto.setTimestamp(currencyEntity.getTimestamp());
				pairDto.setLast(currencyEntity.getLast());
				return pairDto;
			}
			// lo trasforma da stream a collection
		).collect(Collectors.toList());
		
	
	}
	
	public List<CurrencyDto> getByCurrency(String pair1){
		List<CurrencyEntity> lista = currencyRepository.findByPair1(pair1);
		return lista.stream().map(
				// scorro ogni elemento (pairEntity)
				currencyEntity -> {
					// convert il pairEntity in DTO
					CurrencyDto pairDto = new CurrencyDto();
					pairDto.setId(currencyEntity.getId());
					pairDto.setPair1(currencyEntity.getPair1());
					pairDto.setPair2(currencyEntity.getPair2());
					pairDto.setTimestamp(currencyEntity.getTimestamp());
					pairDto.setLast(currencyEntity.getLast());
					return pairDto;
				}
				// lo trasforma da stream a collection
			).collect(Collectors.toList());
			
		
		}
		
	}

