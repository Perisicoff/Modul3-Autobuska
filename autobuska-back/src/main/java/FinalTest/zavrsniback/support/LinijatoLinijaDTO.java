package FinalTest.zavrsniback.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Linija;
import FinalTest.zavrsniback.web.dto.LinijaDTO;

@Component
public class LinijatoLinijaDTO implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija linija) {
		LinijaDTO linijaDto = new LinijaDTO();
		linijaDto.setId(linija.getId());
		linijaDto.setBrojMesta(linija.getBrojMesta());
		linijaDto.setCenaKarte(linija.getCenaKarte());
		linijaDto.setDestinacija(linija.getDestinacija());
		linijaDto.setVremePolaska(linija.getVremePolaska());
		linijaDto.setPrevoznikId(linija.getPrevoznik().getId());
		linijaDto.setNazivPrevoznik(linija.getPrevoznik().getNaziv());

		return linijaDto;
	}

	public List<LinijaDTO> convert(List<Linija> linije) {
		List<LinijaDTO> linijeDto = new ArrayList<>();

		for (Linija linija : linije) {
			linijeDto.add(convert(linija));
		}

		return linijeDto;

	}

}
