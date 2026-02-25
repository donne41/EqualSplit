package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EqualSplitService {
    private List<Person> group = new ArrayList<>();
    private EqualSplit equalSplit = new EqualSplit();
    private List<SplitResult> history = new ArrayList<>();

    public void addPerson(String name, double moneySpent) {
        if (inputValidation(name, moneySpent))
            throw new RuntimeException("Invalid input when adding person");

        group.add(new Person(name, moneySpent));
    }

    public void editPerson(String nameSearch, double newMoney) {
        if (inputValidation(nameSearch, newMoney))
            throw new RuntimeException("Invalid name or money");
        Optional<Person> person = findPerson(nameSearch);
        if (person.isPresent()) {
            person.get().setMoneySpent(newMoney);
        }
    }

    public void removePerson(String name) {
        if (inputValidation(name))
            throw new RuntimeException("invalid name");
        Optional<Person> person = findPerson(name);
        if (person.isPresent())
            group.remove(person);
    }

    public void calculate() {
        history.add(equalSplit.getResult(group));
    }

    public List<SplitResult> getResultList() {
        return history;
    }

    private Optional<Person> findPerson(String name) {
        return group.stream().filter(
                        p -> p.getName().equals(name))
                .findFirst();
    }

    private boolean inputValidation(String name, double money) {
        if (name.trim().isEmpty())
            return true;
        if (money < 0)
            return true;
        return false;
    }

    private boolean inputValidation(String name) {
        if (name.trim().isEmpty())
            return true;
        return false;
    }

}
