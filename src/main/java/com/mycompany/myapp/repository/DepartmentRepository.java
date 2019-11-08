package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Department;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data Couchbase repository for the Department entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartmentRepository extends N1qlCouchbaseRepository<Department, String> {

}
