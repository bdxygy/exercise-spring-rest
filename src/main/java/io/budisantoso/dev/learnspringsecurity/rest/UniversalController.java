package io.budisantoso.dev.learnspringsecurity.rest;

import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UniversalController<C, T, X, ID>{
    @PostMapping({"", "/"})
    ResponseEntity<UniversalResponse<T>> save(@RequestBody C request);

    @GetMapping({"", "/"})
    ResponseEntity<UniversalResponse<List<T>>> findAll();

    @GetMapping({"{id}", "/{id}"})
    ResponseEntity<UniversalResponse<T>> findById(@PathVariable ID id);

    @PutMapping({"", "/"})
    ResponseEntity<UniversalResponse<T>> update(@RequestBody C request);

    @DeleteMapping({"{id}", "/{id}"})
    ResponseEntity<UniversalResponse<X>> delete(@PathVariable ID id);

    @DeleteMapping({"", "/"})
    ResponseEntity<UniversalResponse<X>> deleteAll();
}
