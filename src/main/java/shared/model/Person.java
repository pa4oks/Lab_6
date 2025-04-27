package shared.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("name")
    private String name; // Поле не может быть null, Строка не может быть пустой
    @SerializedName("weight")
    private Integer weight; // Поле может быть null, Значение поля должно быть больше 0
    @SerializedName("eyeColor")
    private Color eyeColor; // Поле не может быть null
    @SerializedName("hairColor")
    private Color hairColor; // Поле может быть null
    @SerializedName("nationality")
    private Country nationality; // Поле может быть null
    @SerializedName("location")
    private Location location; // Поле не может быть null

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new IllegalArgumentException("Eye color cannot be null");
        }
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(weight, person.weight) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor &&
                nationality == person.nationality &&
                Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, eyeColor, hairColor, nationality, location);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Person fromJson(String json) {
        return new Gson().fromJson(json, Person.class);
    }

    /*public static Builder builder() {
        return new Builder();
    }*/

   /* public static class Builder {
        private final Person person = new Person();

        public Builder name(String name) {
            person.setName(name);
            return this;
        }

        public Builder weight(Integer weight) {
            person.setWeight(weight);
            return this;
        }

        public Builder eyeColor(Color eyeColor) {
            person.setEyeColor(eyeColor);
            return this;
        }

        public Builder hairColor(Color hairColor) {
            person.setHairColor(hairColor);
            return this;
        }

        public Builder nationality(Country nationality) {
            person.setNationality(nationality);
            return this;
        }

        public Builder location(Location location) {
            person.setLocation(location);
            return this;
        }

        public Person build() {
            return person;
        }
    }*/
}