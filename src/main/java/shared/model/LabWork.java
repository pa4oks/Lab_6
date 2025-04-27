package shared.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LabWork implements Serializable, Comparable<LabWork> {
    private static final long serialVersionUID = 1L;

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("coordinates")
    private Coordinates coordinates;
    @SerializedName("creationDate")
    private LocalDate creationDate;
    @SerializedName("minimalPoint")
    private Double minimalPoint;
    @SerializedName("difficulty")
    private Difficulty difficulty;
    @SerializedName("author")
    private Person author;

    public LabWork() {
        this.id = generateUniqueLongId();
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date cannot be null");
        }
        this.creationDate = creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Double minimalPoint) {
        if (minimalPoint != null && minimalPoint <= 0) {
            throw new IllegalArgumentException("Minimal point must be positive");
        }
        this.minimalPoint = minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void print() {
        System.out.println("Name: " + getName());
        System.out.println("Coordinates: " + getCoordinates());
        System.out.println("CreationDate: " + getCreationDate());
        System.out.println("MinimalPoint: " + getMinimalPoint());
        System.out.println("Difficulty: " + getDifficulty());
        System.out.println("Author: " + getAuthor());
    }

    static List<Integer> idList = new ArrayList<>();

    private static final Random random = new Random();


    public static long generateUniqueLongId() {
        long newId;
        newId = generateRandomLongId();
        boolean flag = true;
        do {
            flag = true;
            for (int i = 0; i < idList.size(); i++) {
                if (newId == idList.get(i)) {
                    flag = false;
                }
            }
        } while (!flag);
        return newId;
    }

    private static long generateRandomLongId() {
        return Math.abs(random.nextLong());  // Генерируем неотрицательное случайное long число
    }



    public static LabWork ReadLabWork(String string) throws IllegalAccessException {
        String[] readings = string.split(" ");
        //как задать через строку, если есть Person??
        LabWork newLabWork = null;
        newLabWork.generateUniqueLongId();
        return newLabWork;
    }

    public void ShowLabWork(LabWork labWork) {
        System.out.println("Name: " + labWork.getName());
        System.out.println("ID: " + labWork.getId());
        System.out.println("Coordinates: " + labWork.getCoordinates().getX() + " " + labWork.getCoordinates().getY());
        System.out.println("CreationDate: " + labWork.getCreationDate());
        System.out.println("MinimalPoint: " + labWork.getMinimalPoint());
        System.out.println("Difficulty: " + labWork.getDifficulty());
        System.out.println("Author: " + labWork.getAuthor().getName() + "\n   eyes: " + labWork.getAuthor().getEyeColor() + "\n   hair: " + labWork.getAuthor().getHairColor() + "\n   location: " + labWork.getAuthor().getLocation().getName() + "\n   coordinates: " + labWork.getAuthor().getLocation().getX() + " " + labWork.getAuthor().getLocation().getY() + " " + labWork.getAuthor().getLocation().getZ() + " " + "\n   nationality: " + labWork.getAuthor().getNationality() + "\n   weight: " + labWork.getAuthor().getWeight());
    }


    @Override
    public int compareTo(LabWork other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return Objects.equals(id, labWork.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static LabWork fromJson(String json) {
        return new Gson().fromJson(json, LabWork.class);
    }

    /*public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final LabWork labWork = new LabWork();

        public Builder id(Long id) {
            labWork.setId(id);
            return this;
        }

        public Builder name(String name) {
            labWork.setName(name);
            return this;
        }

        public Builder coordinates(Coordinates coordinates) {
            labWork.setCoordinates(coordinates);
            return this;
        }

        public Builder creationDate(LocalDate creationDate) {
            labWork.setCreationDate(creationDate);
            return this;
        }

        public Builder minimalPoint(Double minimalPoint) {
            labWork.setMinimalPoint(minimalPoint);
            return this;
        }
        public Builder difficulty(Difficulty difficulty) {
            labWork.setDifficulty(difficulty);
            return this;
        }

        public Builder author(Person author) {
            labWork.setAuthor(author);
            return this;
        }

        public LabWork build() {
            return labWork;
        }
    }*/


}