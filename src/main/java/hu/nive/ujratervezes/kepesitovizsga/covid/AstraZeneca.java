package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AstraZeneca extends Vaccine {

    public AstraZeneca(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> registrations = getRegistrationsWithoutPregnants();
        List<Person> vaccinationList = new ArrayList<>();
        for (Person p : registrations) {
            if (p.getChronic() == ChronicDisease.YES) {
                vaccinationList.add(p);
            }
        }
        vaccinationList.addAll(getElders(registrations, vaccinationList));
        vaccinationList.addAll(getTheOthers(registrations, vaccinationList));

        return vaccinationList;
    }

    protected List<Person> getRegistrationsWithoutPregnants() {
        List<Person> registrationsWithoutPregnants = new ArrayList<>();
        for (Person p : getPersonsRegistrated()) {
            if (p.getPregnant() == Pregnancy.NO) {
                registrationsWithoutPregnants.add(p);
            }
        }
        return registrationsWithoutPregnants;
    }
}
