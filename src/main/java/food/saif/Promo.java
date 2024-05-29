package food.saif;

import food.saif.design.Color;

import java.time.LocalDate;

public class Promo implements ApplicationData, Color {
    private String code;
    private double discountPercentage; //0.xx
    private LocalDate expirationDate;

    final static char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public Promo(String code, double discountPercentage, LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public static Promo validateCode(String code) {
        Promo promo = Search.findPromo("code", code);
        if (promo==null) {
            System.out.println(RED+"Invalid code."+RESET);
            return null;
        }
        if (promo.expirationDate.isBefore(LocalDate.now())) {
            System.out.println(RED+"Promo code is expired."+RESET);
            return null;
        }
        return promo;
    }

    private static String generateRandomCode(int length) {
        String code = "";
        int min = 0;
        int max = CHARACTERS.length;
        for (int i=0; i<length; i++) {
            int randIndex = (int) (min+Math.random()*(max-min));
            code += CHARACTERS[randIndex];
        }
        // possibly duplicated code>
        return generateRandomCode(length);
    }
//
//    public static String addPromoCode(double discountPercentage, LocalDate expirationDate, String code) {
//        //System.out.println(LocalDateTime.now().plusMonths(2).toLocalDate());
//        if (discountPercentage<0 || discountPercentage>100) {
//            throw new RuntimeException("Discount must be between 1 and 100.");
//        }
//        discountPercentage = discountPercentage/100;
//
//        promoCodes.put(
//            code.toLowerCase(),
//            JSWriter.getNewJsonNode()
//                    .put("discountPercentage", discountPercentage)
//                    .put("expirationDate", String.valueOf(expirationDate))
//        );
//        updatePromoCodes();
//        return code;
//    }
//    public static String addPromoCode(double discountPercentage, LocalDate expirationDate) {
//        return addPromoCode(discountPercentage, expirationDate, generateRandomCode(8));
//    }
//    public static boolean removePromoCode(String code) {
//        if (promoCodes.get(code)!=null) {
//            promoCodes.remove(code);
//            updatePromoCodes();
//            return true;
//        }
//        return false;
//    }
//

    public String getCode() {
        return code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
