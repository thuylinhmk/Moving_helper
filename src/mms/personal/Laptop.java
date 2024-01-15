package mms.personal;

/**
 * A subclass in Personal.
 * Representing a Laptop.
 */
public class Laptop extends Personal {
    /**
     * The age of this laptop.
     */
    private int age;

    /**
     * Class Laptop constructor specifying owner and age.
     *
     * @param owner  The name of owner
     * @param age  The age of laptop
     * Provides properties width, height, length; with defaults 35., 20., 2. (cm)
     * @throws IllegalArgumentException if age < 0
     */
    public Laptop(String owner, int age) throws IllegalArgumentException {
        super(owner);
        this.age = age;
        this.setDimensions(35., 20., 2.);
        if (this.age < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getting age of this laptop.
     *
     * @return int number of the age of this laptop
     */
    public int getAge() {
        return this.age;
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing this laptop with owner and age
     */
    @Override
    public String toString() {
        String toPrint = "Laptop (" + this.getOwner() + ") - " + this.getAge();
        return  toPrint;
    }
}