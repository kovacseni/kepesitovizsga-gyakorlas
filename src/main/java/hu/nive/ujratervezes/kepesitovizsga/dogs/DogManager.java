package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DogManager {

    private Map<String, List<Dog>> dogs = new HashMap<>();

    public Map<String, List<Dog>> getDogs() {
        return new HashMap<>(dogs);
    }

    public DogManager() {
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DogManager.class.getResourceAsStream("/dogs.csv")))) {
            br.readLine();
            loadDogs(br);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file.", ioe);
        }
    }

    private void loadDogs(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(";");
            if (!dogs.containsKey(temp[4])) {
                dogs.put(temp[4], new ArrayList<>());
            }
            dogs.get(temp[4]).add(new Dog(temp[1], temp[4], temp[5]));
        }
    }

    public String getCountryByExactDogSpecies(String dogSpecies) {
        for (String country : dogs.keySet()) {
            if (getDog(country, dogSpecies)) {
                return country;
            }
        }
        throw new IllegalArgumentException("No such dog name found.");
    }

    private boolean getDog(String country, String dogSpecies) {
        for (Dog d : dogs.get(country)) {
            if (d.getName().equals(dogSpecies.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public List<Dog> getDogsInAlphabeticalOrderByName() {
        List<Dog> allDogs = new ArrayList<>();
        for (List<Dog> dogs : dogs.values()) {
            allDogs.addAll(dogs);
        }
        Collections.sort(allDogs);

        return allDogs;
    }

    public Map<String, Integer> getDogStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        for (String country : dogs.keySet()) {
            statistics.put(country, dogs.get(country).size());
        }
        return statistics;
    }
}
