package customer;

import exceptions.InvalidNameException;

public abstract class Person {
    private String name;

    public Person(String name) throws InvalidNameException {
        setName(name);
    }

    public String getName() { return name; }

    public void setName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException();
        }
        this.name = name.trim();
    }
}