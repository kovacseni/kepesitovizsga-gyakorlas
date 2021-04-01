package hu.nive.ujratervezes.kepesitovizsga.dogs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class DogManagerTest {

    @Test
    public void testGetCountryByExactDogSpecies() {
        Assertions.assertEquals("HUNGARY", new DogManager().getCountryByExactDogSpecies("PULI"));
    }

    @Test
    public void testGetCountryByExactDogSpeciesNotExistingSpecies() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> new DogManager().getCountryByExactDogSpecies("xyz"));
        Assertions.assertEquals("No such dog name found.", ex.getMessage());
    }

    @Test
    public void testGetDogsInAlphabeticalOrderByName() {
        List<Dog> dogs = new DogManager().getDogsInAlphabeticalOrderByName();

        Assertions.assertEquals(354, dogs.size());
        Assertions.assertEquals("AFFENPINSCHER", dogs.get(0).getName());
        Assertions.assertEquals("APPENZELL CATTLE DOG", dogs.get(11).getName());
        Assertions.assertEquals("YAKUTIAN LAIKA", dogs.get(351).getName());
    }

    @Test
    public void testGetDogStatistics() {
        Map<String, Integer> statistics = new DogManager().getDogStatistics();

        Assertions.assertEquals(9, statistics.get("HUNGARY"));
        Assertions.assertEquals(2, statistics.get("DENMARK"));
        Assertions.assertEquals(10, statistics.get("RUSSIA"));
    }
}
