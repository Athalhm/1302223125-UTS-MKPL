package lib;

public class TaxFunction {

    private static final int BASIC_NONTAXABLE_INCOME = 54_000_000;
    private static final int MARRIAGE_ALLOWANCE = 4_500_000;
    private static final int CHILD_ALLOWANCE = 4_500_000;
    private static final int MAX_CHILDREN_COUNT = 3;
    private static final int MAX_MONTHS_IN_YEAR = 12;
    private static final double TAX_RATE = 0.05;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked,
                                   int deductible, boolean isMarried, int numberOfChildren) {

        int validMonthsWorked = sanitizeMonthsWorked(monthsWorked);
        int cappedChildren = Math.min(numberOfChildren, MAX_CHILDREN_COUNT);

        // Langkah-langkah diperjelas lewat penamaan
        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, validMonthsWorked);
        int netIncome = calculateNetIncome(annualIncome, deductible);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, cappedChildren);
        int taxableIncome = calculateTaxableIncome(netIncome, nonTaxableIncome);
        int tax = calculateFinalTax(taxableIncome);

        return tax;
    }

    private static int sanitizeMonthsWorked(int monthsWorked) {
        if (monthsWorked > MAX_MONTHS_IN_YEAR) {
            System.err.println("Jumlah bulan bekerja tidak boleh lebih dari " + MAX_MONTHS_IN_YEAR + ".");
            return MAX_MONTHS_IN_YEAR;
        }
        return monthsWorked;
    }

    private static int calculateAnnualIncome(int salary, int otherIncome, int monthsWorked) {
        return (salary + otherIncome) * monthsWorked;
    }

    private static int calculateNetIncome(int annualIncome, int deductible) {
        return annualIncome - deductible;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int childrenCount) {
        int nonTaxable = BASIC_NONTAXABLE_INCOME;
        if (isMarried) {
            nonTaxable += MARRIAGE_ALLOWANCE;
        }
        nonTaxable += childrenCount * CHILD_ALLOWANCE;
        return nonTaxable;
    }

    private static int calculateTaxableIncome(int netIncome, int nonTaxableIncome) {
        return Math.max(netIncome - nonTaxableIncome, 0);
    }

    private static int calculateFinalTax(int taxableIncome) {
        return (int) Math.round(taxableIncome * TAX_RATE);
    }
}
