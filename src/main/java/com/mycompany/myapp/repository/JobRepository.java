package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data Couchbase repository for the Job entity.
 */
@Repository
public interface JobRepository extends N1qlCouchbaseRepository<Job, String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    Page<Job> findAllWithEagerRelationships(Pageable pageable);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    List<Job> findAllWithEagerRelationships();

    @Query("#{#n1ql.selectEntity} USE KEYS $1 WHERE #{#n1ql.filter}")
    Optional<Job> findOneWithEagerRelationships(String id);

}
