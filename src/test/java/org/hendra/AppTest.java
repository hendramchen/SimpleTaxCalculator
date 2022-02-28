package org.hendra;

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;

import org.junit.Test;

public class AppTest 
{
    private TaxationScheme[] TAXATION_SCHEMES = new TaxationScheme[4];
    private TaxRelief[] TAX_RELIEFS = new TaxRelief[5];
    private TaxCalculator calculator;

    public AppTest() {
        TAXATION_SCHEMES[0] = new TaxationScheme(0, 50_000_000, (byte) 5);
        TAXATION_SCHEMES[1] = new TaxationScheme(50_000_000, 250_000_000, (float) 15);
        TAXATION_SCHEMES[2] = new TaxationScheme(250_000_000, 500_000_000, (float) 25);
        TAXATION_SCHEMES[3] = new TaxationScheme(500_000_000, 500_000_000, (float) 30);

        TAX_RELIEFS[0] = new TaxRelief("TK0", "Single", 54_000_000);
        TAX_RELIEFS[1] = new TaxRelief("K0", "Married with no dependant", 58_000_000);
        TAX_RELIEFS[2] = new TaxRelief("K1", "Married with 1 dependant", 63_000_000);
        TAX_RELIEFS[3] = new TaxRelief("K2", "Married with 2 dependants", 67_000_000);
        TAX_RELIEFS[4] = new TaxRelief("K3", "Married with 3 dependants", 72_000_000);

        calculator = new TaxCalculator(TAX_RELIEFS, TAXATION_SCHEMES);
    }

    @Test
    public void testCalculateTaxSalary_3500_000_with_status_TK0() {
        calculator.setSalary(3500_000);
        calculator.setPersonStatus((byte) 1);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR0.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_10_000_000_with_status_K0() {
        calculator.setSalary(10_000_000);
        calculator.setPersonStatus((byte) 2);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR4,300,000.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_6_500_000_with_status_K1(){
        calculator.setSalary(6500000);
        calculator.setPersonStatus((byte) 3);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR750,000.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_25_000_000_with_status_TK0(){
        calculator.setSalary(25_000_000);
        calculator.setPersonStatus((byte) 1);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR31,900,000.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_54_500_000_with_status_TK0() {
        calculator.setSalary(54_500_000);
        calculator.setPersonStatus((byte) 1);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR125,000,000.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_15_000_000_with_status_K2() {
        calculator.setSalary(15_000_000);
        calculator.setPersonStatus((byte) 4);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR11,950,000.00", taxDisplay);
    }

    @Test
    public void testCalculateTaxSalary_20_000_000_with_status_K3() {
        calculator.setSalary(20_000_000);
        calculator.setPersonStatus((byte) 5);
        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        assertEquals("IDR20,200,000.00", taxDisplay);
    }

}
