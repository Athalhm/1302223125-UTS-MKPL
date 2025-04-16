package lib;

import java.time.LocalDate;

public class EmployeeBuilder {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    private LocalDate dateJoined;
    private boolean isForeigner;
    private Gender gender;

    public EmployeeBuilder setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public EmployeeBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public EmployeeBuilder setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
        return this;
    }

    public EmployeeBuilder setIsForeigner(boolean isForeigner) {
        this.isForeigner = isForeigner;
        return this;
    }

    public EmployeeBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Employee build() {
        return new Employee(employeeId, firstName, lastName, idNumber, address, dateJoined, isForeigner, gender);
    }
}
