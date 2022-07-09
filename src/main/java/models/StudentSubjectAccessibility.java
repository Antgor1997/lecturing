package models;

public class StudentSubjectAccessibility {
    private String name;
    private String lastName;
    private int firstPractice;
    private int secondPractice;
    private int thirdPractice;
    private int fourthPractice;
    private int fifthPractice;
    private int sixthPractice;
    private int module;
    private int total;

    public StudentSubjectAccessibility() {}

    public StudentSubjectAccessibility(String name, String lastName, int firstPractice, int secondPractice, int thirdPractice, int fourthPractice, int fifthPractice, int sixthPractice, int module, int total) {
        this.name = name;
        this.lastName = lastName;
        this.firstPractice = firstPractice;
        this.secondPractice = secondPractice;
        this.thirdPractice = thirdPractice;
        this.fourthPractice = fourthPractice;
        this.fifthPractice = fifthPractice;
        this.sixthPractice = sixthPractice;
        this.module = module;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFirstPractice() {
        return firstPractice;
    }

    public void setFirstPractice(int firstPractice) {
        this.firstPractice = firstPractice;
    }

    public int getSecondPractice() {
        return secondPractice;
    }

    public void setSecondPractice(int secondPractice) {
        this.secondPractice = secondPractice;
    }

    public int getThirdPractice() {
        return thirdPractice;
    }

    public void setThirdPractice(int thirdPractice) {
        this.thirdPractice = thirdPractice;
    }

    public int getFourthPractice() {
        return fourthPractice;
    }

    public void setFourthPractice(int fourthPractice) {
        this.fourthPractice = fourthPractice;
    }

    public int getFifthPractice() {
        return fifthPractice;
    }

    public void setFifthPractice(int fifthPractice) {
        this.fifthPractice = fifthPractice;
    }

    public int getSixthPractice() {
        return sixthPractice;
    }

    public void setSixthPractice(int sixthPractice) {
        this.sixthPractice = sixthPractice;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "StudentSubjectAccessibility{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstPractice=" + firstPractice +
                ", secondPractice=" + secondPractice +
                ", thirdPractice=" + thirdPractice +
                ", fourthPractice=" + fourthPractice +
                ", fifthPractice=" + fifthPractice +
                ", sixthPractice=" + sixthPractice +
                ", module=" + module +
                ", total=" + total +
                '}';
    }

    public Object[] toArray(){
        return new Object[]{name, lastName, fifthPractice, secondPractice, thirdPractice, fourthPractice, fifthPractice, sixthPractice, module, total};
    }
}
