import java.util.List;

public interface EmployeeDAO {
    void createNewEmployee(Employee employee);

    Employee getEmployee(Integer id);

    List<Employee> getAllEmployee();

    void updateEmployee(Integer id, Employee employeeUpdate);

    void deleteEmployee(Integer id);
}
