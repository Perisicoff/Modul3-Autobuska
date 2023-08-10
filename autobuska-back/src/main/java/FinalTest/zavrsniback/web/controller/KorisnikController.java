package FinalTest.zavrsniback.web.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FinalTest.zavrsniback.enumeration.KorisnickaUloga;
import FinalTest.zavrsniback.model.Korisnik;
import FinalTest.zavrsniback.model.Linija;
import FinalTest.zavrsniback.security.TokenUtils;
import FinalTest.zavrsniback.service.KorisnikService;
import FinalTest.zavrsniback.support.KorisnikDtoToKorisnik;
import FinalTest.zavrsniback.support.KorisnikToKorisnikDto;
import FinalTest.zavrsniback.web.dto.AuthKorisnikDto;
import FinalTest.zavrsniback.web.dto.KorisnikDTO;
import FinalTest.zavrsniback.web.dto.KorisnikPromenaLozinkeDto;
import FinalTest.zavrsniback.web.dto.KorisnikRegistracijaDTO;

@RestController
@RequestMapping(value = "/api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private KorisnikDtoToKorisnik toKorisnik;

	@Autowired
	private KorisnikToKorisnikDto toKorisnikDto;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PreAuthorize("permitAll()")
	@PostMapping
	public ResponseEntity<KorisnikDTO> create(@RequestBody @Validated KorisnikRegistracijaDTO dto) {

		if (dto.getId() != null || !dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter
		// za njega
		// ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
		Korisnik korisnik = toKorisnik.convert(dto);

		// dodatak za zadatak 1
		String encodedPassword = passwordEncoder.encode(dto.getLozinka());
		korisnik.setLozinka(encodedPassword);

		return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> update(@PathVariable Long id, @Valid @RequestBody KorisnikDTO korisnikDTO) {

		if (!id.equals(korisnikDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Korisnik korisnik = toKorisnik.convert(korisnikDTO);

		return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Korisnik obrisaniKorisnik = korisnikService.delete(id);

		if (obrisaniKorisnik != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<KorisnikDTO> get(@PathVariable Long id) {
		Korisnik korisnik = korisnikService.findOne(id);

		if (korisnik != null) {
			return new ResponseEntity<>(toKorisnikDto.convert(korisnik), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/profil/{korisnickoIme}")
	public ResponseEntity<KorisnikDTO> get(@PathVariable String korisnickoIme) {
		Optional<Korisnik> korisnik = korisnikService.findbyKorisnickoIme(korisnickoIme);

		if (korisnik.isPresent()) {
			return new ResponseEntity<>(toKorisnikDto.convert(korisnik.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<KorisnikDTO>> get(@RequestParam(defaultValue = "0") int page) {
		Page<Korisnik> korisnici = korisnikService.findAll(page);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(korisnici.getTotalPages()));
		return new ResponseEntity<>(toKorisnikDto.convert(korisnici.getContent()), headers, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ROLE_KORISNIK')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "promenaLozinke")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody KorisnikPromenaLozinkeDto dto) {
		// ova metoda se "okida" kada se primi PUT /korisnici?promenaLozinke
		// pogrešno bi bilo mapirati na npr. PUT /korisnici/lozinke, pošto "lozinka"
		// nije punopravan REST resurs!

		if (!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean rezultat;
		try {
			rezultat = korisnikService.changePassword(id, dto);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (rezultat) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/auth", method = RequestMethod.POST)
	public ResponseEntity authenticateUser(@RequestBody AuthKorisnikDto dto) {
		// Perform the authentication
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				dto.getUsername(), dto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		try {
			// Reload user details so we can generate token
			UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
			return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
