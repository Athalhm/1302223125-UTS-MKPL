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

    // Constructor private, diakses melalui EmployeeBuilder
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

    // Method untuk set gaji bulanan berdasarkan grade
    public void setMonthlySalary(int grade) {
        // Tentukan gaji dasar berdasarkan grade
        int baseSalary = getBaseSalaryForGrade(grade);

        // Jika pegawai adalah warga negara asing, gaji dasar diperbesar 1.5x
        if (isForeigner) {
            baseSalary = (int) (baseSalary * 1.5);
        }

        // Set gaji bulanan
        monthlySalary = baseSalary;
    }

    // Method untuk mendapatkan gaji dasar berdasarkan grade
    private int getBaseSalaryForGrade(int grade) {
        switch (grade) {
            case 1:
                return 3_000_000;
            case 2:
                return 5_000_000;
            case 3:
                return 7_000_000;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }

    // Set deductible tahunan
    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    // Set pendapatan tambahan
    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    // Menambahkan data pasangan
    public void setSpouse(String name, String idNumber) {
        this.spouse = new Spouse(name, idNumber);
    }

    // Menambahkan data anak
    public void addChild(String name, String idNumber) {
        this.children.add(new Child(name, idNumber));
    }

    // Menghitung pajak tahunan pegawai
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
