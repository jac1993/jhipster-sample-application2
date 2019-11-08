package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.core.query.FetchType;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mycompany.myapp.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Document
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "employee";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("phone_number")
    private String phoneNumber;

    @Field("hire_date")
    private Instant hireDate;

    @Field("salary")
    private Long salary;

    @Field("commission_pct")
    private Long commissionPct;

    @Field("department")
    private String departmentId;

    @N1qlJoin(on = "lks.department=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    @JsonIgnoreProperties("employees")
    private Department department;

    @Field("job")
    private Set<String> jobIds = new HashSet<>();

    @N1qlJoin(on = "lks.job=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Set<Job> jobs = new HashSet<>();

    @Field("manager")
    private String managerId;

    @N1qlJoin(on = "lks.manager=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    @JsonIgnoreProperties("employees")
    private Employee manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public Employee hireDate(Instant hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSalary() {
        return salary;
    }

    public Employee salary(Long salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCommissionPct() {
        return commissionPct;
    }

    public Employee commissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
        return this;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee department(Department department) {
        this.department = department;
        this.departmentId = department.getId();
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
        this.departmentId = department.getId();
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public Employee jobs(Set<Job> jobs) {
        this.jobs = jobs;
        this.jobIds = jobs.stream()
            .map(Job::getId)
            .collect(Collectors.toSet());
        return this;
    }

    public Employee addJob(Job job) {
        this.jobs.add(job);
        this.jobIds.add(job.getId());
        job.setEmployee(this);
        return this;
    }

    public Employee removeJob(Job job) {
        this.jobs.remove(job);
        this.jobIds.remove(job.getId());
        job.setEmployee(null);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
        this.jobIds = jobs.stream()
            .map(Job::getId)
            .collect(Collectors.toSet());
    }

    public Employee getManager() {
        return manager;
    }

    public Employee manager(Employee employee) {
        this.manager = employee;
        this.managerId = employee.getId();
        return this;
    }

    public void setManager(Employee employee) {
        this.manager = employee;
        this.managerId = employee.getId();
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", salary=" + getSalary() +
            ", commissionPct=" + getCommissionPct() +
            "}";
    }
}
