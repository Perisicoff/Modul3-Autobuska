package FinalTest.zavrsniback.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import FinalTest.zavrsniback.model.Linija;
import FinalTest.zavrsniback.service.LinijaService;
import FinalTest.zavrsniback.support.LinijaDTOtoLinija;
import FinalTest.zavrsniback.support.LinijatoLinijaDTO;
import FinalTest.zavrsniback.web.dto.LinijaDTO;

@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private LinijatoLinijaDTO toLinijaDTO;

	@Autowired
	private LinijaDTOtoLinija toLinija;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> getAll(@RequestParam(required=false) String destinacija,
										          @RequestParam(required=false) Long prevoznikId,
										          @RequestParam(required=false) Double maxCenaKarte,
										          @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

		Page<Linija> linije = linijaService.search(destinacija, prevoznikId, maxCenaKarte, pageNo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(linije.getTotalPages()));

		return new ResponseEntity<>(toLinijaDTO.convert(linije.getContent()), headers, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Linija obrisanaLinija = linijaService.delete(id);

		if (obrisanaLinija != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id) {
		Linija linija = linijaService.findOne(id);

		if (linija != null) {
			return new ResponseEntity<>(toLinijaDTO.convert(linija), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO LinijaDTO) {
		Linija linija = toLinija.convert(LinijaDTO);

		if (linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Linija sacuvanlinija = linijaService.save(linija);

		return new ResponseEntity<>(toLinijaDTO.convert(sacuvanlinija), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO LinijaDTO) {

		if (!id.equals(LinijaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Linija linija = toLinija.convert(LinijaDTO);

		if (linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Linija sacuvanlinija = linijaService.save(linija);

		return new ResponseEntity<>(toLinijaDTO.convert(sacuvanlinija), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}/rezervacija")
	public ResponseEntity<Void> rezervacija(@PathVariable Long id) {

		Linija linija = linijaService.findOne(id);
		if (linija == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Linija sacuvanlinija = linijaService.rezervacija(linija);
		

		if (sacuvanlinija == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

}
