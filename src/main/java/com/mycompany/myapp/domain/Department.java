package com.mycompany.myapp.domain;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.core.query.FetchType;
import org.springframework.data.couchbase.core.query.N1qlJoin;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mycompany.myapp.config.Constants.ID_DELIMITER;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * A Department.
 */
@Document
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "department";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @NotNull
    @Field("department_name")
    private String departmentName;

    @Field("location")
    private String locationId;

    @N1qlJoin(on = "lks.location=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Location location;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    @Field("employee")
    private Set<String> employeeIds = new HashSet<>();

    @N1qlJoin(on = "lks.employee=meta(rks).id", fetchType = FetchType.IMMEDIATE)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public Department location(Location location) {
        this.location = location;
        this.locationId = location.getId();
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
        this.locationId = location.getId();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Department employees(Set<Employee> employees) {
        this.employees = employees;
        this.employeeIds = employees.stream()
            .map(Employee::getId)
            .collect(Collectors.toSet());
        return this;
    }

    public Department addEmployee(Employee employee) {
        this.employees.add(employee);
        this.employeeIds.add(employee.getId());
        employee.setDepartment(this);
        return this;
    }

    public Department removeEmployee(Employee employee) {
        this.employees.remove(employee);
        this.employeeIds.remove(employee.getId());
        employee.setDepartment(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
        this.employeeIds = employees.stream()
            .map(Employee::getId)
            .collect(Collectors.toSet());
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            "}";
    }
}
