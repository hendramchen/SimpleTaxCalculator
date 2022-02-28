package org.hendra;

import java.text.NumberFormat;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        TaxationScheme[] TAXATION_SCHEMES = new TaxationScheme[4];
        TaxRelief[] TAX_RELIEFS = new TaxRelief[5];

        TAXATION_SCHEMES[0] = new TaxationScheme(0, 50_000_000, (byte) 5);
        TAXATION_SCHEMES[1] = new TaxationScheme(50_000_000, 250_000_000, (float) 15);
        TAXATION_SCHEMES[2] = new TaxationScheme(250_000_000, 500_000_000, (float) 25);
        TAXATION_SCHEMES[3] = new TaxationScheme(500_000_000, 500_000_000, (float) 30);

        TAX_RELIEFS[0] = new TaxRelief("TK0", "Single", 54_000_000);
        TAX_RELIEFS[1] = new TaxRelief("K0", "Married with no dependant", 58_000_000);
        TAX_RELIEFS[2] = new TaxRelief("K1", "Married with 1 dependant", 63_000_000);
        TAX_RELIEFS[3] = new TaxRelief("K2", "Married with 2 dependants", 67_000_000);
        TAX_RELIEFS[4] = new TaxRelief("K3", "Married with 3 dependants", 72_000_000);

        int salary = (int) Console.readNumber("How much your salary?: ", 1000, 100_000_000_000L);
        System.out.println("Tax reliefs are based on the person’s profile:");

        for(byte i=0; i<TAX_RELIEFS.length; i++) {
            System.out.println("["+(i+1)+"] "+ TAX_RELIEFS[i].code + " - " + TAX_RELIEFS[i].description);
        }
        byte personStatus = (byte) Console.readNumber("Selected number: ", 1, 5);

        TaxCalculator calculator = new TaxCalculator(TAX_RELIEFS, TAXATION_SCHEMES);
        calculator.setSalary(salary);
        calculator.setPersonStatus(personStatus);
        System.out.println("Total amount of tax payable is:");

        String taxDisplay = NumberFormat.getCurrencyInstance().format(calculator.calculateTax());
        System.out.println(taxDisplay);

    }

}
