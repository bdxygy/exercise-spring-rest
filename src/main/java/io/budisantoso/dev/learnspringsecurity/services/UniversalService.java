package io.budisantoso.dev.learnspringsecurity.services;

import java.util.List;

public interface UniversalService<C, T, ID> {
    T create(C newData);
    T findById(ID id);
    List<T> findAll();
    T update(C updatedData);
    void delete(ID id);
    void deleteAll();
}
