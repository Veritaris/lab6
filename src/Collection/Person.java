package Collection;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private float height;
    private int weight;
    private Country nationality;

    public Person (String name, float height, int weight, Country nationality){
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.nationality = nationality;
    }

    public String getName() { return name; }
    public float getHeight() { return height; }
    public int getWeight() { return weight; }
    public Country getNationality() { return nationality; }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Person groupAdmin = (Person) object;
        return name.equals(groupAdmin.name) &&
                height == groupAdmin.height &&
                weight == groupAdmin.weight &&
                nationality.equals(groupAdmin.nationality);
    }
}
