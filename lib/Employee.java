package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;

    private boolean isForeigner;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private Spouse spouse;
    private List<Child> children;

    public Employee(String employeeId, String firstName, String lastName, String idNumber,
                    String address, LocalDate dateJoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;
        this.children = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        int baseSalary = switch (grade) {
            case 1 -> 3_000_000;
            case 2 -> 5_000_000;
            case 3 -> 7_000_000;
            default -> 0;
        };

        if (isForeigner) {
            baseSalary *= 1.5;
        }

        monthlySalary = baseSalary;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String name, String idNumber) {
        this.spouse = new Spouse(name, idNumber);
    }

    public void addChild(String name, String idNumber) {
        this.children.add(new Child(name, idNumber));
    }

    public int getAnnualIncomeTax() {
        int monthWorkingInYear;
        LocalDate today = LocalDate.now();

        if (today.getYear() == dateJoined.getYear()) {
            monthWorkingInYear = today.getMonthValue() - dateJoined.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }

        boolean isSingle = (spouse == null || spouse.isEmpty());

        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthWorkingInYear,
            annualDeductible,
            !isSingle,
            children.size()
        );
    }
}
