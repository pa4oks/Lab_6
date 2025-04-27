package shared.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("x")
    private Long x; // Поле не может быть null
    @SerializedName("y")
    private Integer y; // Поле не может быть null
    @SerializedName("z")
    private Float z; // Поле не может быть null
    @SerializedName("name")
    private String name; // Поле не может быть null

    public Location() {
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        if (x == null) {
            throw new IllegalArgumentException("X coordinate cannot be null");
        }
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (y == null) {
            throw new IllegalArgumentException("Y coordinate cannot be null");
        }
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        if (z == null) {
            throw new IllegalArgumentException("Z coordinate cannot be null");
        }
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(x, location.x) &&
                Objects.equals(y, location.y) &&
                Objects.equals(z, location.z) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Location fromJson(String json) {
        return new Gson().fromJson(json, Location.class);
    }

    /*public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Location location = new Location();

        public Builder x(Long x) {
            location.setX(x);
            return this;
        }

        public Builder y(Integer y) {
            location.setY(y);
            return this;
        }

        public Builder z(Float z) {
            location.setZ(z);
            return this;
        }

        public Builder name(String name) {
            location.setName(name);
            return this;
        }

        public Location build() {
            return location;
        }
    }*/
}