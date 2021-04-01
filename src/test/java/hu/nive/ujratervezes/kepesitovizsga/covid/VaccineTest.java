package hu.nive.ujratervezes.kepesitovizsga.covid;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VaccineTest {

    private Vaccine vaccine;
    private MariaDbDataSource dataSource;

    @BeforeEach
    public void init() {
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            initTable();
            createDummyData();
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not connect to database", sqle);
        }
    }

    @Test
    public void testPfizer() {
        vaccine = new Pfizer(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        Assertions.assertEquals(15, vaccinationList.size());
        Assertions.assertEquals("Nagy Eleonóra", vaccinationList.get(0).getName());
        Assertions.assertEquals("Németh Béla", vaccinationList.get(3).getName());
        Assertions.assertEquals("Kiss József", vaccinationList.get(6).getName());
        Assertions.assertEquals("Kovács Tamás", vaccinationList.get(14).getName());
    }

    @Test
    public void testAstraZeneca() {
        vaccine = new AstraZeneca(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        Assertions.assertEquals(12, vaccinationList.size());
        Assertions.assertEquals("Szép Virág", vaccinationList.get(0).getName());
        Assertions.assertEquals("Bánkuti Bendegúz", vaccinationList.get(4).getName());
        Assertions.assertEquals("Németh Béla", vaccinationList.get(5).getName());
        Assertions.assertEquals("Bíró Rita", vaccinationList.get(11).getName());
    }

    @Test
    public void testSinoPharm() {
        vaccine = new SinoPharm(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        Assertions.assertEquals(7, vaccinationList.size());
        Assertions.assertEquals("Kiss József", vaccinationList.get(0).getName());
        Assertions.assertEquals("Szabó Veronika", vaccinationList.get(1).getName());
        Assertions.assertEquals("Németh Béla", vaccinationList.get(5).getName());
        Assertions.assertEquals("Török István", vaccinationList.get(6).getName());
    }

    @AfterEach
    public void destruct() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String dropHousePoints = "DROP TABLE IF EXISTS registrations";
            Statement statement = connection.createStatement();
            statement.execute(dropHousePoints);
        }
    }

    private void initTable() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String createRegistrationsTable = "CREATE TABLE IF NOT EXISTS registrations (" +
                    "person_name VARCHAR(255), " +
                    "age INT, " +
                    "chronic_disease VARCHAR(255), " +
                    "pregnancy VARCHAR(255)" +
                    ");";
            Statement statement = connection.createStatement();
            statement.execute(createRegistrationsTable);
        }
    }

    private void createDummyData() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String insertRegistration = "INSERT INTO registrations VALUES ('Kiss József', 45, 'nem', 'nem')";
            Statement stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Nagy Eleonóra', 35, 'nem', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Szép Virág', 65, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Németh Béla', 72, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Gárdos Géza', 25, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Szabó Veronika', 32, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Vedres Károly', 53, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Ökrös Gizella', 33, 'nem', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Fekete Dávid', 62, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Bíró Rita', 29, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Baráth Zita', 58, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Kovács Tamás', 42, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Török István', 81, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Fehér Ágnes', 34, 'igen', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Bánkuti Bendegúz', 93, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
        }
    }
}
