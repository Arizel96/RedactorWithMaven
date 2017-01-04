package com.debtapp.javafx.interfaces.impl;

import com.debtapp.javafx.interfaces.PersonBook;
import com.debtapp.javafx.objects.Debtor;
import com.debtapp.javafx.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Arizel on 25.12.2016.
 */
public class DebtorBook implements PersonBook {

    private ObservableList<Debtor> debtors = FXCollections.observableArrayList();


    public void fillData() {
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
        debtors.add(new Debtor("NameVasya", "897987349854", "Ukraine", "VK EPTA", "IP"));
    }

    @Override
    public void add(Person person) {
        debtors.add((Debtor) person);
    }

    @Override
    public void delete(Person person) {
        debtors.remove(person);
    }

    @Override
    public void update(Person person) {
        //for DataBase impl
    }

    public ObservableList<Debtor> getDebtors() {
        return debtors;
    }
}
