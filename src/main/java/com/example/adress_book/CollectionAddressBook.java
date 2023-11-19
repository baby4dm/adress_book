package com.example.adress_book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook{
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }


    public void fillTestData(){
        personList.add(new Person("Dmytro", "77777"));
        personList.add(new Person("Bogdan", "12345"));
        personList.add(new Person("Oleg", "13455"));
    }

    public ObservableList<Person> search(String searchTerm) {
        ObservableList<Person> searchResults = FXCollections.observableArrayList();

        for (Person person : personList) {
            if (person.getPip().toLowerCase().contains(searchTerm) || person.getPhone().toLowerCase().contains(searchTerm)) {
                searchResults.add(person);
            }
        }

        return searchResults;
    }
}
