package be.odisee;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /**
     * countProducts counts amount of time ``product`` appears in List of ``products``
     * @param producten list of products
     * @param product searchterm
     * @return amount of product in products
     */
    private static int countProducts(List<String> producten, String product) {
        int count = 0;
        for (String p : producten) {
            if (p.equals(product)) {
                count++;
            }
        }
        return count;
    }

    static void main() {
        List<String> producten = new ArrayList<>();
        producten.add("melk");
        //producten.add(...) of in 1x via List.of:
        producten = List.of("boter", "zout", "melk", "Suiker", "melk");

        String product = "melk";
        System.out.printf("%s komt %d keer voor in de lijst%n", product, countProducts(producten, product));

        product = "boter";
        System.out.printf("%s komt %d keer voor in de lijst%n", product, countProducts(producten, product));
    }
}
