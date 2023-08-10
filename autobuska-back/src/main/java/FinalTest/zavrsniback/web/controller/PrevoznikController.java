package FinalTest.zavrsniback.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalTest.zavrsniback.model.Prevoznik;
import FinalTest.zavrsniback.service.PrevoznikService;
import FinalTest.zavrsniback.support.PrevoznikDTOtoPrevoznik;
import FinalTest.zavrsniback.support.PrevoznikToPrevoznikDTO;
import FinalTest.zavrsniback.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {

	@Autowired
	private PrevoznikService prevoznikService;

	@Autowired
	private PrevoznikToPrevoznikDTO toPrevoznikDTO;

	@Autowired
	private PrevoznikDTOtoPrevoznik toPrevoznik;

	@GetMapping
	public ResponseEntity<List<PrevoznikDTO>> getAll() {

		List<Prevoznik> prevoznici = prevoznikService.findAll();

		return new ResponseEntity<>(toPrevoznikDTO.convert(prevoznici), HttpStatus.OK);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrevoznikDTO> create(@RequestBody @Validated PrevoznikDTO prevoznikDTO) {

		Prevoznik prevoznik = toPrevoznik.convert(prevoznikDTO);
		Prevoznik sacuvaniPrevoznik = prevoznikService.save(prevoznik);

		return new ResponseEntity<>(toPrevoznikDTO.convert(sacuvaniPrevoznik), HttpStatus.CREATED);

	}

}
