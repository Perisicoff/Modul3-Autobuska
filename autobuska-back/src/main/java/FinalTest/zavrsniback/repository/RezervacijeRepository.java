package FinalTest.zavrsniback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FinalTest.zavrsniback.model.Rezervacija;

@Repository
public interface RezervacijeRepository extends JpaRepository<Rezervacija, Long> {

}
