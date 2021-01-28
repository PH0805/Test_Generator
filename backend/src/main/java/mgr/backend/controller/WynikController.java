package mgr.backend.controller;

import mgr.backend.repository.WynikRepository;
import mgr.common.entities.Wynik;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class WynikController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WynikRepository repository;

    public WynikController(WynikRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<Wynik> findAllScores() {
        log.trace("get all questions request");
        return repository.findAll();
    }

    @PostMapping("/create")
    public void createScore(@RequestBody Wynik score) {
        log.trace("create score request: {}", score);
        repository.save(score);
    }

    @DeleteMapping("/delete/{scoreId}")
    public void deleteScoreById(@PathVariable Integer scoreId) {
        log.trace("delete request for score with id {}", scoreId);
        repository.deleteById(scoreId);
    }



}
