package FinalTest.zavrsniback.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import FinalTest.zavrsniback.model.Korisnik;
import FinalTest.zavrsniback.service.KorisnikService;
import FinalTest.zavrsniback.web.dto.KorisnikDTO;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik> {

	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO korisnikDTO) {
		Korisnik entity = null;

		if (korisnikDTO.getId() == null) {
			entity = new Korisnik();
		} else {
			Korisnik korisnikOptional = korisnikService.findOne(korisnikDTO.getId());
			if (korisnikOptional != null) {
				entity = korisnikOptional;
			}
		}

		if (entity != null) {
			entity.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
			entity.seteMail(korisnikDTO.geteMail());
			entity.setIme(korisnikDTO.getIme());
			entity.setPrezime(korisnikDTO.getPrezime());
		}

		return entity;
	}

}
