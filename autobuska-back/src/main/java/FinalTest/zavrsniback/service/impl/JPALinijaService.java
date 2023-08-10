package FinalTest.zavrsniback.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import FinalTest.zavrsniback.model.Linija;
import FinalTest.zavrsniback.model.Rezervacija;
import FinalTest.zavrsniback.repository.LinijaRepository;
import FinalTest.zavrsniback.repository.RezervacijeRepository;
import FinalTest.zavrsniback.service.LinijaService;

@Service
public class JPALinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;

	@Autowired
	private RezervacijeRepository rezervacijeRepository;

	@Override
	public Linija findOne(Long id) {
		Optional<Linija> linija = linijaRepository.findById(id);
		if (linija.isPresent()) {
			return linija.get();
		}
		return null;
	}

	@Override
	public Page<Linija> findAll(int brojStranice) {
		return linijaRepository.findAll(PageRequest.of(brojStranice, 2));
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Linija linija = findOne(id);
		if (linija != null) {
			linijaRepository.deleteById(id);
			return linija;
		}
		return null;

	}

	@Override
	public Linija rezervacija(Linija linija) {
		Linija rezervacijaLinije = findOne(linija.getId());

		if (rezervacijaLinije.getBrojMesta() > 0) {
			rezervacijaLinije.setBrojMesta(rezervacijaLinije.getBrojMesta() - 1);
			Rezervacija rezervacija = new Rezervacija(linija);
			rezervacijeRepository.save(rezervacija);
			return linijaRepository.save(rezervacijaLinije);
		}
		return null;
	}

	@Override
	public Page<Linija> search(String destinacija, Long prevoznikId, Double maxCenaKarte, int brojStranice) {
		
		if (maxCenaKarte == null) {
			maxCenaKarte = Double.MAX_VALUE;
		}
		
		return linijaRepository.search(destinacija, prevoznikId, maxCenaKarte, PageRequest.of(brojStranice, 3));
	}

}
