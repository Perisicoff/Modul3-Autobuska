package FinalTest.zavrsniback.service;

import org.springframework.data.domain.Page;
import FinalTest.zavrsniback.model.Linija;

public interface LinijaService {

	Linija findOne(Long id);

	Page<Linija> findAll(int brojStranice);
	
	Page<Linija> search(String destinacija, Long prevoznikId, Double maxCenaKarte, int brojStranice);

	Linija save(Linija linija);

	Linija delete(Long id);
	
	Linija rezervacija(Linija linija);
}
