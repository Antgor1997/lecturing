package models;

public class Subject {
    private String name;
    private int credits;
    private int hours;
    private String type;

    public Subject() {}

    public Subject(String name, int credits, int hours, String type) {
        this.name = name;
        this.credits = credits;
        this.hours = hours;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                ", type='" + type + '\'' +
                '}';
    }
}
