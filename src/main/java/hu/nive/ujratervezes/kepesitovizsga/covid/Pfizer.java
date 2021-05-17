package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Pfizer extends Vaccine {

    public Pfizer(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> registrations = getPersonsRegistrated();
        List<Person> vaccinationList = new ArrayList<>();
        for (Person p : registrations) {
            if (p.getPregnant() == Pregnancy.YES) {
                vaccinationList.add(p);
            }
        }
        vaccinationList.addAll(getElders(registrations, vaccinationList));
        vaccinationList.addAll(getTheOthers(registrations, vaccinationList));

        return vaccinationList;
    }
}
