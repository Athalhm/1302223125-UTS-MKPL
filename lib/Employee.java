package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private static final int GRADE_1_SALARY = 3_000_000;
    private static final int GRADE_2_SALARY = 5_000_000;
    private static final int GRADE_3_SALARY = 7_000_000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;
    private static final int MAX_MONTHS_IN_YEAR = 12;

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

    Employee(String employeeId, String firstName, String lastName, String idNumber,
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
        int baseSalary = getBaseSalaryForGrade(grade);
        if (isForeigner) {
            baseSalary = (int) (baseSalary * FOREIGNER_SALARY_MULTIPLIER);
        }
        monthlySalary = baseSalary;
    }

    private int getBaseSalaryForGrade(int grade) {
        switch (grade) {
            case 1:
                return GRADE_1_SALARY;
            case 2:
                return GRADE_2_SALARY;
            case 3:
                return GRADE_3_SALARY;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
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
            monthWorkingInYear = MAX_MONTHS_IN_YEAR;
        }

        boolean isMarried = (spouse != null && !spouse.isEmpty());

        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthWorkingInYear,
            annualDeductible,
            isMarried,
            children.size()
        );
    }
}
