package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Para que seja possível uma classe java aceitar métodos rest é necessário
 * marcar com a anotação @RestController*/

@RestController
@RequestMapping(value = "/usuario") //mapeando a url a ser interceptada
public class IndexController {
	
	/*A classe ResponseEntity é sempre usada para dar a resposta*/
	@GetMapping(value = "/", produces = "application/json") //o retorno sempre produz um json
	public ResponseEntity<String> init() {
		return new ResponseEntity<String>("Olá REST Spring Boot",HttpStatus.OK);
	}
	
}
