package mgr.backend.controller;

import mgr.backend.repository.PytaniaRepository;
import mgr.common.entities.Pytania;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class PytaniaController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PytaniaRepository repository;

    public PytaniaController(PytaniaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<Pytania> findAllQuestions() {
        log.trace("get all questions request");
        return repository.findAll();
    }

    @PostMapping("/create")
    public void createQuestion(@RequestBody Pytania question) {
        log.trace("create question request: {}", question);
        repository.save(question);
    }

    @DeleteMapping("/delete/{questionId}")
    public void deleteQuestionById(@PathVariable Integer questionId) {
        log.trace("delete request for question with id {}", questionId);
        repository.deleteById(questionId);
    }

    @PutMapping("/update")
    public void updateQuestion(@RequestBody Pytania question) {
        repository.deleteById(question.getId());
        repository.save(question);
    }

}
