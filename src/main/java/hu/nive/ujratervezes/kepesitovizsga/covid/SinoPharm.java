package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class SinoPharm extends Vaccine {

    public SinoPharm(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> registrations = getRegistrationsWithoutPregnantsAndChronics();
        List<Person> vaccinationList = new ArrayList<>();
        for (Person p : registrations) {
            if (p.getAge() < 65) {
                vaccinationList.add(p);
            }
        }
        vaccinationList.addAll(getTheOthers(registrations, vaccinationList));

        return vaccinationList;
    }

    private List<Person> getRegistrationsWithoutPregnantsAndChronics() {
        List<Person> registrationsWithoutPregnantAndChronics = new ArrayList<>();

        for (Person p : getPersonsRegistrated()) {
            if (p.getPregnant() == Pregnancy.NO && p.getChronic() == ChronicDisease.NO) {
                registrationsWithoutPregnantAndChronics.add(p);
            }
        }
        return registrationsWithoutPregnantAndChronics;
    }
}
