package com.webapp.spirngbootthymeleafcrudapp.service;

import com.webapp.spirngbootthymeleafcrudapp.Util.CsvGeneratorUtil;
import com.webapp.spirngbootthymeleafcrudapp.model.Employee;
import com.webapp.spirngbootthymeleafcrudapp.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeebyId(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
       Employee employee ;
        if (optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee id :: " +id + " not found in the records");
        }
        return employee;
    }



    @Override
    public void deleteEmployeebyId(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection,String Keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1 , pageSize,sort);
        if (Keyword != null){
            return this.employeeRepository.findAll(Keyword ,pageable);
        }
        else {
            return this.employeeRepository.findAll(pageable);
        }
    }

}
