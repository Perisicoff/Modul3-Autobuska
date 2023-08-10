package FinalTest.zavrsniback.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import FinalTest.zavrsniback.enumeration.KorisnickaUloga;
import FinalTest.zavrsniback.model.Korisnik;
import FinalTest.zavrsniback.repository.KorisnikRepository;
import FinalTest.zavrsniback.service.KorisnikService;
import FinalTest.zavrsniback.web.dto.KorisnikPromenaLozinkeDto;

@Service
public class JpaKorisnikService implements KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Korisnik findOne(Long id) {
		Optional<Korisnik> korisnik = korisnikRepository.findById(id);
		if (korisnik.isPresent()) {
			return korisnik.get();
		}
		return null;
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Page<Korisnik> findAll(int brojStranice) {
		return korisnikRepository.findAll(PageRequest.of(brojStranice, 10));
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		if (korisnik.getId() == null) {
			korisnik.setUloga(KorisnickaUloga.KORISNIK);
			return korisnikRepository.save(korisnik);
		} else {
			if (korisnik.getUloga().equals(KorisnickaUloga.ADMIN)) {
				korisnik.setUloga(KorisnickaUloga.ADMIN);
				return korisnikRepository.save(korisnik);
			} else {
				korisnik.setUloga(KorisnickaUloga.KORISNIK);
				return korisnikRepository.save(korisnik);
			}
		}
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik korisnik = findOne(id);
		if (korisnik != null) {
			korisnikRepository.deleteById(id);
			return korisnik;
		}
		return null;
	}

	@Override
	public Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme) {
		return korisnikRepository.findFirstByKorisnickoIme(korisnickoIme);
	}

	@Override
	public boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto) {
		Optional<Korisnik> rezultat = korisnikRepository.findById(id);

		if (!rezultat.isPresent()) {
			throw new EntityNotFoundException();
		}

		Korisnik korisnik = rezultat.get();

		boolean passwordsMatch = BCrypt.checkpw(korisnikPromenaLozinkeDto.getStaraLozinka(), korisnik.getLozinka());
		if (!korisnik.getKorisnickoIme().equals(korisnikPromenaLozinkeDto.getKorisnickoIme()) || !passwordsMatch) {
			return false;
		}

		// dodatak za zadatak 2
		String password = korisnikPromenaLozinkeDto.getLozinka();
		if (!korisnikPromenaLozinkeDto.getLozinka().equals("")) {
			password = passwordEncoder.encode(korisnikPromenaLozinkeDto.getLozinka());
		}

		korisnik.setLozinka(password);

		korisnikRepository.save(korisnik);

		return true;
	}

}
