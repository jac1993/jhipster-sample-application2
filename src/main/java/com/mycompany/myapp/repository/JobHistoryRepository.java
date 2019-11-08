package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.JobHistory;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data Couchbase repository for the JobHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobHistoryRepository extends N1qlCouchbaseRepository<JobHistory, String> {

}
