package com.mycompany.myapp.domain;
import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.core.query.FetchType;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import java.io.Serializable;
import java.time.Instant;
import java.util.stream.Collectors;

import com.mycompany.myapp.domain.enumeration.Language;

import static com.mycompany.myapp.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A JobHistory.
 */
@Document
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "jobhistory";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("language")
    private Language language;

    @Field("job")
    private String jobId;

    @N1qlJoin(on = "lks.job=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Job job;

    @Field("department")
    private String departmentId;

    @N1qlJoin(on = "lks.department=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Department department;

    @Field("employee")
    private String employeeId;

    @N1qlJoin(on = "lks.employee=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public JobHistory startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public JobHistory endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Language getLanguage() {
        return language;
    }

    public JobHistory language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Job getJob() {
        return job;
    }

    public JobHistory job(Job job) {
        this.job = job;
        this.jobId = job.getId();
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
        this.jobId = job.getId();
    }

    public Department getDepartment() {
        return department;
    }

    public JobHistory department(Department department) {
        this.department = department;
        this.departmentId = department.getId();
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
        this.departmentId = department.getId();
    }

    public Employee getEmployee() {
        return employee;
    }

    public JobHistory employee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobHistory)) {
            return false;
        }
        return id != null && id.equals(((JobHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "JobHistory{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
