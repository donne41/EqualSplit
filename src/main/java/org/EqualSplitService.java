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
        String normalName = normalizeName(name);
        group.add(new Person(normalName, moneySpent));
    }

    public double getPersonSpent(String inputName) {
        if (inputValidation(inputName))
            throw new RuntimeException("Invalid name");
        String name = normalizeName(inputName);
        Optional<Person> person = findPerson(name);
        if (person.isPresent()) {
            return person.get().getMoneySpent();
        } else {
            return -1;
        }
    }

    public boolean editPerson(String nameSearch, double newMoney) {
        if (inputValidation(nameSearch, newMoney))
            throw new RuntimeException("Invalid name or money");
        String normalName = normalizeName(nameSearch);
        Optional<Person> person = findPerson(normalName);
        if (person.isPresent()) {
            person.get().setMoneySpent(newMoney);
            return true;
        } else {
            return false;
        }
    }

    public boolean removePerson(String name) {
        if (inputValidation(name))
            throw new RuntimeException("invalid name");
        String normalName = normalizeName(name);
        Optional<Person> person = findPerson(normalName);
        if (person.isPresent()) {
            group.remove(person.get());
            return true;
        } else {
            return false;
        }
    }

    public void calculate() {
        history.add(equalSplit.getResult(group));
    }

    public List<SplitResult> getResultList() {
        return List.copyOf(history);
    }

    public List<Person> getGroup() {
        return List.copyOf(group);
    }

    public Optional<Person> findPerson(String name) {
        return group.stream().filter(
                        p -> p.getName().equals(name))
                .findFirst();
    }

    public Person findPerson(int index) throws RuntimeException {
        try {
            return group.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Index out of bounds when looking for person");
        }
    }

    private boolean inputValidation(String name, double money) {
        if (name.trim().isEmpty())
            return true;
        if (money < 0)
            return true;
        return false;
    }

    private String normalizeName(String rawName) {
        return rawName.trim().substring(0, 1).toUpperCase() + rawName.trim().substring(1).toLowerCase();
    }

    private boolean inputValidation(String name) {
        if (name.trim().isEmpty())
            return true;
        return false;
    }

}
