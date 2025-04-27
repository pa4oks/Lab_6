package shared.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("x")
    private float x; // Значение поля должно быть больше -423
    @SerializedName("y")
    private Float y; // Поле не может быть null

    public Coordinates() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        if (x <= -423) {
            throw new IllegalArgumentException("X coordinate must be greater than -423");
        }
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        if (y == null) {
            throw new IllegalArgumentException("Y coordinate cannot be null");
        }
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Coordinates fromJson(String json) {
        return new Gson().fromJson(json, Coordinates.class);
    }

    /*public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Coordinates coordinates = new Coordinates();

        public Builder x(float x) {
            coordinates.setX(x);
            return this;
        }

        public Builder y(Float y) {
            coordinates.setY(y);
            return this;
        }

        public Coordinates build() {
            return coordinates;
        }
    }*/
}