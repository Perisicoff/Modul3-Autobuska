package FinalTest.zavrsniback.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import FinalTest.zavrsniback.model.Linija;
import FinalTest.zavrsniback.service.LinijaService;
import FinalTest.zavrsniback.service.PrevoznikService;
import FinalTest.zavrsniback.web.dto.LinijaDTO;

@Component
public class LinijaDTOtoLinija implements Converter<LinijaDTO, Linija> {

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Linija convert(LinijaDTO linijaDTO) {
		Linija entity = null;

		if (linijaDTO.getId() == null) {
			entity = new Linija();
		} else {
			Linija linija = linijaService.findOne(linijaDTO.getId());
			if (linija != null) {
				entity = linija;
			}
		}

		if (entity != null) {
			entity.setBrojMesta(linijaDTO.getBrojMesta());
			entity.setCenaKarte(linijaDTO.getCenaKarte());
			entity.setDestinacija(linijaDTO.getDestinacija());
			entity.setVremePolaska(linijaDTO.getVremePolaska());
			entity.setPrevoznik(prevoznikService.findOne(linijaDTO.getPrevoznikId()));
		}

		return entity;
	}

}
