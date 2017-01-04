package com.debtapp.javafx.interfaces;

import com.debtapp.javafx.objects.Person;

/**
 * Created by Arizel on 25.12.2016.
 */
public interface PersonBook {
    void add(Person person);
    void delete(Person person);
    void update(Person person);
}
