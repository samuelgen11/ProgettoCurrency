package node.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import node.model.CurrencyDto;
import node.service.CurrencyService;

@RestController
@Slf4j
@RequestMapping("/currency")
public class Controller {

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	CurrencyService currencyService;

	@GetMapping(value = "/helloWorld")
	public ResponseEntity<String> gethelloorld() {

		return ResponseEntity.ok().body("Hello World");
	}

	// fai una get di un servizio esterno
	@GetMapping("/scaricaDaServizioEsterno")
	public ResponseEntity<String> scaricaDaServizioEsterno() {
		// ???
		// eseguire una get di un servizio esterno, scaricare i dati e salvarli nel mio
		// DB
		try {
			String resultRequest = currencyService.chiamaServizioEsterno();
			return ResponseEntity.ok().body(resultRequest);
		} catch (Exception exc) {
			return ResponseEntity.internalServerError().body(exc.getMessage());
		}
	}

	// fai una get di un servizio esterno E trasformalo in java
	@GetMapping("/convertiLoScaricoDatiInJava")
	public ResponseEntity<List<CurrencyDto>> convertiLoScaricoDatiInJava() {
		// ???
		// eseguire una get di un servizio esterno, scaricare i dati e salvarli nel mio
		// DB
		try {
			List<CurrencyDto> resultRequest = currencyService.convertiLoScaricoDatiInJava();
			return ResponseEntity.ok().body(resultRequest);
		} catch (Exception exc) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// scarica, converti e salva nel DB
	@GetMapping("/convertiLoScaricoDatiInJavaESalvaloNelDB")
	public ResponseEntity<List<CurrencyDto>> convertiLoScaricoDatiInJavaESalvaloNelDB() {
		// ???
		// eseguire una get di un servizio esterno, scaricare i dati e salvarli nel mio
		// DB
		try {
			List<CurrencyDto> resultRequest = currencyService.convertiLoScaricoDatiInJavaESalvaloNelDB();
			return ResponseEntity.ok().body(resultRequest);
		} catch (Exception exc) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// seleziona i dati scaricati nel DB
	@GetMapping("/getAllCurrency")
	public ResponseEntity<List<CurrencyDto>> selectPairs() {
		// ???
		// eseguire una get di un servizio esterno, scaricare i dati e salvarli nel mio
		// DB
		try {
			List<CurrencyDto> resultRequest = currencyService.getAllCurrency();
			return ResponseEntity.ok().body(resultRequest);
		} catch (Exception exc) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/getBycurrency")
	public ResponseEntity<List<CurrencyDto>> getByCurrency(@RequestParam String pair1){
		List <CurrencyDto> resultRequest = currencyService.getByCurrency(pair1);
		return ResponseEntity.ok().body(resultRequest);
	}
}
