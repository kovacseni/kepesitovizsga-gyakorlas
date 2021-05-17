package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Vaccine {

    private DataSource dataSource;
    private List<Person> personsRegistrated = new ArrayList<>();

    public Vaccine(DataSource dataSource) {
        this.dataSource = dataSource;
        getDataFromDatabase();
    }

    public List<Person> getPersonsRegistrated() {
        return new ArrayList<>(personsRegistrated);
    }

    protected abstract List<Person> getVaccinationList();

    protected List<Person> getElders(List<Person> registrations, List<Person> vaccinationList) {
        List<Person> elders = new ArrayList<>();
        for (Person p : registrations) {
            if (p.getAge() > 65 && !vaccinationList.contains(p)) {
                vaccinationList.add(p);
            }
        }
        return elders;
    }

    public List<Person> getTheOthers(List<Person> registrations, List<Person> vaccinationList) {
        List<Person> restPerson = new ArrayList<>();
        for (Person p : registrations) {
            if (!vaccinationList.contains(p)) {
                restPerson.add(p);
            }
        }
        return restPerson;
    }

    private void getDataFromDatabase() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from registrations;")) {
            loadRegistrations(rs);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not get data.", sqle);
        }
    }

    private void loadRegistrations(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String name = rs.getString(1);
            int age = rs.getInt(2);
            ChronicDisease chronic = getChronic(rs.getString(3));
            Pregnancy pregnant = getPregnant(rs.getString(4));

            personsRegistrated.add(new Person(name, age, chronic, pregnant));
        }
    }

    private ChronicDisease getChronic(String chronic) {
        if ("nem".equals(chronic)) {
            return ChronicDisease.NO;
        } else if ("igen".equals(chronic)) {
            return ChronicDisease.YES;
        } else {
            throw new IllegalArgumentException("Can not determine chronic disease.");
        }
    }

    private Pregnancy getPregnant(String pregnant) {
        if ("nem".equals(pregnant)) {
            return Pregnancy.NO;
        } else if ("igen".equals(pregnant)) {
            return Pregnancy.YES;
        } else {
            throw new IllegalArgumentException("Can not determine pregnancy.");
        }
    }
}
