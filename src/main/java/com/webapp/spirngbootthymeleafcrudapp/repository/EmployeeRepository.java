package com.webapp.spirngbootthymeleafcrudapp.repository;

import com.webapp.spirngbootthymeleafcrudapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface EmployeeRepository extends JpaRepository<Employee,Long> {
//     Query method -> returns results based on the query search

    //JPQL java persistence query language.
    @Query("SELECT e FROM Employee e WHERE " +
    "concat(e.firstName, e.lastName , e.phoneNumber , e.Email)" +
    "LIKE %?1%")
    public Page<Employee> findAll(String Keyword , Pageable pageable);

}

//    public <S extends T, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>,R> queryFunction) { /* compiled code */ }
