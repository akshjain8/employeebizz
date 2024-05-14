package com.webapp.spirngbootthymeleafcrudapp.Util;

import com.webapp.spirngbootthymeleafcrudapp.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
//check typo in index.html
//works when you manually hit https://localhost8080/employees/csv
@Component
public class CsvGeneratorUtil {
    private static final String CSV_HEADER = "firstName,lastName,Email\n";

    public String generateCsv(List<Employee> employees) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);

        for (Employee employee : employees) {
            csvContent
                    .append(employee.getFirstName()).append(",")
                    .append(employee.getLastName()).append(",")
                    .append(employee.getEmail()).append("\n");
        }
        return csvContent.toString();
    }
}
