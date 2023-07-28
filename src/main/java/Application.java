import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "!Fagore240899";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT first_name, last_name, gender, city_name FROM employee INNER JOIN city ON employee.city_id = city.city_id WHERE id = (?)")) {

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String employeeName = "Name: " + resultSet.getString("first_name");
                String employeeLastName = "Last Name: " + resultSet.getString("last_name");
                String employeeGender = "Gender: " + resultSet.getString("gender");
                String employeeCity = "City name: " + resultSet.getString("city_name");

                System.out.println(employeeName);
                System.out.println(employeeLastName);
                System.out.println(employeeGender);
                System.out.println(employeeCity);

            }
        }

        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            employeeDAO.createNewEmployee(new Employee("Ivan", "Vasiliy", "m", 56, new City(1, "Moscow")));

            System.out.println(employeeDAO.getEmployee(5));


            employeeDAO.updateEmployee(6, new Employee("Sveta", "Ivanova", "f", 18, new City(3, "Chita")));

            employeeDAO.deleteEmployee(12);

            List<Employee> employeeList = employeeDAO.getAllEmployee ();
            employeeList.forEach ( System.out::println );
        }
    }
}