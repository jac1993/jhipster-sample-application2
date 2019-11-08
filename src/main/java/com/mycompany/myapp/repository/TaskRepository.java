package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Task;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data Couchbase repository for the Task entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskRepository extends N1qlCouchbaseRepository<Task, String> {

}
