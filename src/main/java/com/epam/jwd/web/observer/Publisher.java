package com.epam.jwd.web.observer;

import com.epam.jwd.web.model.LotDto;

public interface Publisher<T> {

    void subscribe(Subscriber<? super T> subscriber);
}
