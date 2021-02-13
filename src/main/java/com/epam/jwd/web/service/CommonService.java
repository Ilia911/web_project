package com.epam.jwd.web.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {

    Optional<List<T>> findAll();

    Optional<T> save(T dto);

}
