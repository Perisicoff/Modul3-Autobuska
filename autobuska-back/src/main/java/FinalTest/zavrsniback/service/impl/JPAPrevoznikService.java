package FinalTest.zavrsniback.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import FinalTest.zavrsniback.model.Prevoznik;
import FinalTest.zavrsniback.repository.PrevoznikRepository;
import FinalTest.zavrsniback.service.PrevoznikService;

@Service
public class JPAPrevoznikService implements PrevoznikService {

	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik findOne(Long id) {
		Optional<Prevoznik> prevoznik = prevoznikRepository.findById(id);
		if (prevoznik.isPresent()) {
			return prevoznik.get();
		}
		return null;
	}

}
