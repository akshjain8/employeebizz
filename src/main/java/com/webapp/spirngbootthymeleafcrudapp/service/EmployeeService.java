package com.webapp.spirngbootthymeleafcrudapp.service;

import com.webapp.spirngbootthymeleafcrudapp.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);

    Employee getEmployeebyId (long id);

    void  deleteEmployeebyId(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String Keyword );

}
