package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.core.query.FetchType;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mycompany.myapp.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A Job.
 */
@Document
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "job";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("job_title")
    private String jobTitle;

    @Field("min_salary")
    private Long minSalary;

    @Field("max_salary")
    private Long maxSalary;

    @Field("employee")
    private String employeeId;

    @N1qlJoin(on = "lks.employee=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    @JsonIgnoreProperties("jobs")
    private Employee employee;

    @Field("tasks")
    private Set<String> taskIds = new HashSet<>();

    @N1qlJoin(on = "lks.tasks=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Set<Task> tasks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Job jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public Job minSalary(Long minSalary) {
        this.minSalary = minSalary;
        return this;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public Job maxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Job employee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Job tasks(Set<Task> tasks) {
        this.tasks = tasks;
        this.taskIds = tasks.stream()
            .map(Task::getId)
            .collect(Collectors.toSet());
        return this;
    }

    public Job addTask(Task task) {
        this.tasks.add(task);
        this.taskIds.add(task.getId());
        task.getJobs().add(this);
        return this;
    }

    public Job removeTask(Task task) {
        this.tasks.remove(task);
        this.taskIds.remove(task.getId());
        task.getJobs().remove(this);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        this.taskIds = tasks.stream()
            .map(Task::getId)
            .collect(Collectors.toSet());
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        return id != null && id.equals(((Job) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", jobTitle='" + getJobTitle() + "'" +
            ", minSalary=" + getMinSalary() +
            ", maxSalary=" + getMaxSalary() +
            "}";
    }
}
