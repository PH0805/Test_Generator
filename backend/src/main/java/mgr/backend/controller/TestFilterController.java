package mgr.backend.controller;

import mgr.backend.repository.TestFilterRepository;
import mgr.common.entities.TestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-filter")
public class TestFilterController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TestFilterRepository repository;

    public TestFilterController(TestFilterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<TestFilter> findAllTestFilters() {
        log.trace("get all test filters request");
        return repository.findAll();
    }

    @PostMapping("/create")
    public void createTestFilter(@RequestBody TestFilter testFilter) {
        log.trace("create test filter request: {}", testFilter);
        repository.save(testFilter);
    }


    @DeleteMapping("/delete/{filterId}")
    public void deleteQuestionById(@PathVariable Integer filterId) {
        log.trace("delete request for filter with id {}",filterId);
        repository.deleteById(filterId);
    }


}
