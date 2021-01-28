package mgr.backend.repository;

import mgr.common.entities.Wynik;
import org.springframework.data.repository.CrudRepository;

public interface WynikRepository extends CrudRepository<Wynik, Integer> {
}
