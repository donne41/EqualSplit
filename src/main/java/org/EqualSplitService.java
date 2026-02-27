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

    /**
     * Get a persons spending by searching for their name
     *
     * @param inputName search for name
     * @return persons spending or -1 if no person is found.
     */
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

    /**
     * Edit a persons spending
     * @param nameSearch find person by name
     * @param newMoney set the new amount of expenditure
     * @return True when person is found and money updated otherwise False
     */
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

    /**
     * Removes a person from the group list
     * @param name of person to be removed
     * @return True when person is removed otherwise returns False
     */
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

    /**
     * Streams through the list of people and matches by name
     * <p>
     * also se {@link #findPerson(int)} to find person by index
     * @param name of person
     * @return Optional Person or Optional Empty
     */
    public Optional<Person> findPerson(String name) {
        return group.stream().filter(
                        p -> p.getName().equals(name))
                .findFirst();
    }

    /**
     * Returns person of index in the group list
     * <p>
     * also see {@link #findPerson(String)} to find by name
     *
     * @param index the position of the person in the group
     * @return the {@link Person} object at the index
     * @throws RuntimeException when index is out of bounds
     */
    public Person findPerson(int index){
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
