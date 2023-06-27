package io.budisantoso.dev.learnspringsecurity.services;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UniversalService<C, T, ID> {
    @Transactional
    T create(C newData);
    @Transactional
    T findById(ID id);
    @Transactional
    List<T> findAll();
    @Transactional
    T update(C updatedData);
    @Transactional
    void delete(ID id);
    @Transactional
    void deleteAll();
}
