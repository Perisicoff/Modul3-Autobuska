package FinalTest.zavrsniback.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import FinalTest.zavrsniback.model.Prevoznik;
import FinalTest.zavrsniback.service.PrevoznikService;
import FinalTest.zavrsniback.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOtoPrevoznik implements Converter<PrevoznikDTO, Prevoznik> {

	@Autowired
	PrevoznikService prevoznikService;

	@Override
	public Prevoznik convert(PrevoznikDTO prevoznikDTO) {
		Prevoznik entity = null;

		if (prevoznikDTO.getId() == null) {
			entity = new Prevoznik();
		} else {
			Prevoznik prevoznik = prevoznikService.findOne(prevoznikDTO.getId());
			if (prevoznik != null) {
				entity = prevoznik;
			}
		}

		if (entity != null) {
			entity.setNaziv(prevoznikDTO.getNaziv());
			entity.setAdresa(prevoznikDTO.getAdresa());
			entity.setPib(prevoznikDTO.getPib());
		}

		return entity;
	}

}
