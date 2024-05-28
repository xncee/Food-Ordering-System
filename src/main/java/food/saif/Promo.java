package food.saif;

import food.saif.io.JsonFileWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;

public class Promo {
    private String code;
    private double percentage; //0.xx
    private LocalDate expirationDate;

    final static char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public Promo(String code, double percentage, LocalDate expirationDate) {
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

//    public static boolean validatePromoCode(String code) {
//        // ort
//        code = code.toLowerCase();
//        return (promoCodes.get(code)!=null && LocalDate.parse(promoCodes.get(code).get("expirationDate").asText()).isAfter(LocalDate.now()));
//    }
//    private static double getPercentage(String code) {
//        return promoCodes.get(code.toLowerCase()).get("percentage").asDouble()*100;
//    }
//    public static double usePromoCode(String code) {
//        if (validatePromoCode(code))
//            return getPercentage(code);
//        return -1;
//    }
//    private static String generateRandomCode(int length) {
//        String code = "";
//        int min = 0;
//        int max = CHARACTERS.length;
//        for (int i=0; i<length; i++) {
//            int randIndex = (int) (min+Math.random()*(max-min));
//            code += CHARACTERS[randIndex];
//        }
//        // possibly duplicated code>
//        return generateRandomCode(length);
//    }
//
//    public static String addPromoCode(double percentage, LocalDate expirationDate, String code) {
//        //System.out.println(LocalDateTime.now().plusMonths(2).toLocalDate());
//        if (percentage<0 || percentage>100) {
//            throw new RuntimeException("Discount must be between 1 and 100.");
//        }
//        percentage = percentage/100;
//
//        promoCodes.put(
//            code.toLowerCase(),
//            JSWriter.getNewJsonNode()
//                    .put("percentage", percentage)
//                    .put("expirationDate", String.valueOf(expirationDate))
//        );
//        updatePromoCodes();
//        return code;
//    }
//    public static String addPromoCode(double percentage, LocalDate expirationDate) {
//        return addPromoCode(percentage, expirationDate, generateRandomCode(8));
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
    public String getId() {
        return code;
    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
}
