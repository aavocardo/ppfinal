package Project.Logic;

import java.util.Random;

public class Footballer {
    private String firstName, lastName;
    private final int id = generateID();
    private double salary;
    private boolean captain;

    public Footballer(String fName, String lName) {
        this.firstName = fName;
        this.lastName = lName;
    }

    protected static int generateID() {
        Random r = new Random();
        return r.nextInt(1000, 9999);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Footballer f)) {
            return false;
        } else {
            return this.firstName.equals(f.firstName) && this.lastName.equals(f.lastName);
        }
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getID() {
        return this.id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void raiseSalary(double amount) {
        this.salary += amount;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setCaptain(boolean c) {
        this.captain = c;
    }

    public boolean isCaptain(Footballer f) {
        return f.captain;
    }
}

