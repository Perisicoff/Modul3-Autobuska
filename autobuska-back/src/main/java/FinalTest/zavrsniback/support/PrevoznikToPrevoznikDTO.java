package FinalTest.zavrsniback.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Prevoznik;
import FinalTest.zavrsniback.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDTO implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		PrevoznikDTO prevoznikDTO = new PrevoznikDTO();
		prevoznikDTO.setId(prevoznik.getId());
		prevoznikDTO.setNaziv(prevoznik.getNaziv());
		prevoznikDTO.setAdresa(prevoznik.getAdresa());
		prevoznikDTO.setPib(prevoznik.getPib());

		return prevoznikDTO;
	}

	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici) {
		List<PrevoznikDTO> prevozniciDTO = new ArrayList<>();

		for (Prevoznik prevoznik : prevoznici) {
			prevozniciDTO.add(convert(prevoznik));
		}
		return prevozniciDTO;

	}

}
