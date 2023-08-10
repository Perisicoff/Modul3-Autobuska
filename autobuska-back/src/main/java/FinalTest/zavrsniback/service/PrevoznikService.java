package FinalTest.zavrsniback.service;

import java.util.List;

import FinalTest.zavrsniback.model.Prevoznik;

public interface PrevoznikService {

	List<Prevoznik> findAll();

	Prevoznik save(Prevoznik prevoznik);
	
	Prevoznik findOne(Long id);

}
