package FinalTest.zavrsniback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import FinalTest.zavrsniback.model.Korisnik;
import FinalTest.zavrsniback.web.dto.KorisnikPromenaLozinkeDto;

public interface KorisnikService {

	Korisnik findOne(Long id);

	List<Korisnik> findAll();

	Page<Korisnik> findAll(int brojStranice);

	Korisnik save(Korisnik korisnik);

	Korisnik delete(Long id);

	Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme);

	boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto);
}
