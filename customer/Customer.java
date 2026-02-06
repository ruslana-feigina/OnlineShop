package customer;

import exceptions.InvalidNameException;

public class Customer extends Person {
    public Customer(String name) throws InvalidNameException {
        super(name);
    }
}