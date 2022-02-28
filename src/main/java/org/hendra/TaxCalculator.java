package org.hendra;

public class TaxCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int salary;
    private byte personStatus;
    private TaxRelief[] taxReliefs;
    private TaxationScheme[] taxationSchemes;

    public TaxCalculator(TaxRelief[] taxReliefs, TaxationScheme[] taxationSchemes) {
        this.taxReliefs = taxReliefs;
        this.taxationSchemes = taxationSchemes;
    }

    public double calculateTax() {
        double tax = 0;
        int annualTaxableIncome = getAnnualTaxableIncome();

        if (annualTaxableIncome > taxReliefs[getPersonStatus() - 1].amount) {
            // update annualTaxableIncome
            annualTaxableIncome = annualTaxableIncome - taxReliefs[getPersonStatus() - 1].amount;
            if (annualTaxableIncome > taxationSchemes[taxationSchemes.length - 1].min) {
                taxationSchemes[taxationSchemes.length - 1].max = annualTaxableIncome;
            }

            for(short i = 0; i < taxationSchemes.length; i++) {
                if (annualTaxableIncome > taxationSchemes[i].max) {
                    tax = tax + ((taxationSchemes[i].max - taxationSchemes[i].min) * taxationSchemes[i].rate / PERCENT);
                } else {
                    tax = tax + ((annualTaxableIncome - taxationSchemes[i].min)* taxationSchemes[i].rate / PERCENT);
                    break;
                }
            }
        }

        return tax;
    }

    private int getAnnualTaxableIncome() {
        return getSalary() * MONTHS_IN_YEAR;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public byte getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(byte personStatus) {
        this.personStatus = personStatus;
    }
}
