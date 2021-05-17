package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.text.Collator;
import java.util.Locale;
import java.util.Objects;

public class Dog implements Comparable<Dog> {

    private String name;
    private String country;
    private String url;

    public Dog(String name, String country, String url) {
        this.name = name;
        this.country = country;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Dog other) {
        return Collator.getInstance(new Locale("hu", "HU")).compare(this.name, other.name);
    }
}
