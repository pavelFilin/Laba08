package DAL.file;

import DAL.abstruct.INetWorkDAL;
import enitities.PersonDTO;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class MariaDBDAL implements INetWorkDAL {
    Properties properties = new Properties();

    public MariaDBDAL() {
//        properties.setProperty("url", "jdbc:mariadb://localhost/schoolnetwork?useUnicode=yes&characterEncoding=UTF-8");
//        properties.setProperty("url", "jdbc:mysql://localhost/schoolnetwork");
        properties.setProperty("url", "jdbc:mysql://localhost/schoolnetwork?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        properties.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
        properties.setProperty("user", "root");
        properties.setProperty("password", "2017");

        try {
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found.");
            e.printStackTrace();
        }
    }


    @Override
    public PersonDTO getPerson(UUID id) {
        PersonDTO personDTO = new PersonDTO();
        String SqlQuery = "SELECT persons.uuid, persons.firstName, persons.middleName, persons.secondName, schools.name, years.attendDate, years.endDate from persons LEFT JOIN schools ON persons.uuid=schools.uuid  LEFT JOIN years ON schools.uuid=years.uuid WHERE persons.uuid=?";

        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.setString(1, id.toString());
                st.executeQuery();
                try (ResultSet rs = st.getResultSet()) {
                    while (rs.next()) {
                        personDTO.id = UUID.fromString(rs.getString(1));
                        personDTO.firstName = (rs.getString(2));
                        personDTO.middleName = (rs.getString(3));
                        personDTO.secondName = (rs.getString(4));
                        if (rs.getString(5) != null) {
                            personDTO.school = (rs.getString(5));
                            personDTO.attendDate = new GregorianCalendar(rs.getInt(6), 1, 1);
                            personDTO.endDate = new GregorianCalendar(rs.getInt(7), 1, 1);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }


        return personDTO;
    }

    @Override
    public Iterable<PersonDTO> getAllPersons() {
        List<PersonDTO> personsList = null;
        // String SqlQuery = "SELECT UUID, FirstName, MiddleName, SecondName from persons";
        String SqlQuery = "SELECT persons.uuid, persons.firstName, persons.middleName, persons.secondName, schools.name, years.attendDate, years.endDate from persons LEFT JOIN schools ON persons.uuid=schools.uuid  LEFT JOIN years ON schools.uuid=years.uuid";

        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.executeQuery();

                try (ResultSet rs = st.getResultSet()) {
                    personsList = new ArrayList<>();
                    while (rs.next()) {
                        PersonDTO personDTO = new PersonDTO();
                        personDTO.id = UUID.fromString(rs.getString(1));
                        personDTO.firstName = (rs.getString(2));
                        personDTO.middleName = (rs.getString(3));
                        personDTO.secondName = (rs.getString(4));
                        if (rs.getString(5) != null) {
                            personDTO.school = (rs.getString(5));
                            personDTO.attendDate = new GregorianCalendar(rs.getInt(6), 1, 1);
                            personDTO.endDate = new GregorianCalendar(rs.getInt(7), 1, 1);
                        }

                        personsList.add(personDTO);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }


        return personsList;
    }

    @Override
    public boolean update(PersonDTO person) {
//        //TODO update
//        deletePerson(person.id);
//        create(person);
        String SqlQuery = "UPDATE persons SET uuid = ?, firstName = ?, middleName = ?, secondName = ? WHERE uuid = ?";//SET '" + person.id + "' ,'" + person.firstName + "', '" + person.middleName + "', '" + person.secondName + "')";
        String SqlQuery2 = "UPDATE schools SET uuid = ?, name = ? WHERE uuid = ?";      // VALUES('" + person.id + "' ,'" + person.school + "')";
       // String SqlQuery2 = "UPDATE schools SET name = `"+ person.school +"`" +  "WHERE uuid = `"+ person.id +"`";// VALUES('" + person.id + "' ,'" + person.school + "')";
        String SqlQuery3 = "UPDATE years SET uuid = ?, attendDate = ?, endDate = ? WHERE uuid = ?";     // VALUES ('" + person.id + "' ," + person.attendDate.get(Calendar.YEAR) + ", " + person.endDate.get(Calendar.YEAR) + ")";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.setString(1, person.id.toString());
                st.setString(2, person.firstName);
                st.setString(3, person.middleName);
                st.setString(4, person.secondName);
                st.setString(5, person.id.toString());

                st.executeUpdate();
            }
            if (person.school != null && !person.school.isEmpty()) {
                SqlQuery2 = "INSERT INTO schools SET uuid = ?, name = ? ON DUPLICATE KEY UPDATE uuid = ?, name = ?"; // UPDATE schools SET uuid = ?, name = ? WHERE uuid = ?";
                SqlQuery3 = "INSERT INTO years SET uuid = ?, attendDate = ?, endDate = ? ON DUPLICATE KEY UPDATE attendDate = ?, endDate = ?";// years SET uuid = ?, attendDate = ?, endDate = ? WHERE uuid = ?";
                try (PreparedStatement st = connection.prepareStatement(SqlQuery2)) {
                    st.setString(1, person.id.toString());
                    st.setString(2, person.school);
                    st.setString(3, person.id.toString());
                    st.setString(4, person.school);


                    st.executeUpdate();
                }
                try (PreparedStatement st = connection.prepareStatement(SqlQuery3)) {
                    st.setString(1, person.id.toString());
                    st.setInt(2, person.attendDate.get(Calendar.YEAR));
                    st.setInt(3, person.endDate.get(Calendar.YEAR));
                    st.setInt(4, person.attendDate.get(Calendar.YEAR));
                    st.setInt(5, person.endDate.get(Calendar.YEAR));
                    st.executeUpdate();
                }
            }
//            if (person.school != null && !person.school.isEmpty()) {
//                try (PreparedStatement st = connection.prepareStatement(SqlQuery2)) {
//                    st.setString(1, person.id.toString());
//                    st.setString(2, person.school);
//                    st.setString(3, person.id.toString());
//
//                    st.executeUpdate();
//                }
//                try (PreparedStatement st = connection.prepareStatement(SqlQuery3)) {
//                    st.setString(1, person.id.toString());
//                    st.setInt(2, person.attendDate.get(Calendar.YEAR));
//                    st.setInt(3, person.endDate.get(Calendar.YEAR));
//                    st.setString(4, person.id.toString());
//
//                    st.executeUpdate();
//                }
//            }

        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deletePerson(UUID id) {

        String SqlQuery = "DELETE FROM persons WHERE uuid='" + id.toString() + "'";
        String SqlQuery2 = "DELETE FROM schools WHERE uuid='" + id.toString() + "'";
        String SqlQuery3 = "DELETE FROM years WHERE uuid='" + id.toString() + "'";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.executeUpdate();
            }
            try (PreparedStatement st = connection.prepareStatement(SqlQuery2)) {
                st.executeUpdate();
            }
            try (PreparedStatement st = connection.prepareStatement(SqlQuery3)) {
                st.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }
        System.out.println("delete");
        return true;
    }

    @Override
    public boolean create(PersonDTO person) {
        String SqlQuery = "INSERT INTO persons VALUES ('" + person.id + "' ,'" + person.firstName + "', '" + person.middleName + "', '" + person.secondName + "')";

        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.executeUpdate();
            }
            if (person.school != null && !person.school.isEmpty()) {
                String SqlQuery2 = "INSERT INTO schools VALUES ('" + person.id + "' ,'" + person.school + "')";
                String SqlQuery3 = "INSERT INTO years VALUES ('" + person.id + "' ," + person.attendDate.get(Calendar.YEAR) + ", " + person.endDate.get(Calendar.YEAR) + ")";
                try (PreparedStatement st = connection.prepareStatement(SqlQuery2)) {
                    st.executeUpdate();
                }
                try (PreparedStatement st = connection.prepareStatement(SqlQuery3)) {
                    st.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }

        System.out.println("create");
        return true;
    }

    @Override
    public boolean save() throws IOException {
        return false;
    }

    @Override
    public Map<String, Integer> getSchoolsWithRating() {
        Map<String, Integer> result = null;

        String SqlQuery = "SELECT schools.name from schools LEFT JOIN persons on schools.uuid = persons.uuid  ";

        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            try (PreparedStatement st = connection.prepareStatement(SqlQuery)) {
                st.executeQuery();

                result = new HashMap<String, Integer>();

                try (ResultSet rs = st.getResultSet()) {
                    while (rs.next()) {
                        Integer val = result.get(rs.getString(1));
                        if (val != null) {
                            result.put(rs.getString(1), val + 1);
                        } else {
                            result.put(rs.getString(1), 1);
                        }
                    }
                }
            }


        } catch (SQLException e) {
            System.out.println("Connection problem.");
            e.printStackTrace();
        }

        return result;
    }
}
