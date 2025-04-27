import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Boolean running = true;
        do {
            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();

            currencyCodes.put(1, "USD");
            currencyCodes.put(2, "CAD");
            currencyCodes.put(3, "EUR");
            currencyCodes.put(4, "HKD");
            currencyCodes.put(5, "INR");
            currencyCodes.put(6, "AUD");
            currencyCodes.put(7, "BGN");
            currencyCodes.put(8, "BRL");
            currencyCodes.put(9, "CNY");
            currencyCodes.put(10, "GBP");
            currencyCodes.put(11, "JPY");
            currencyCodes.put(12, "KRW");
            currencyCodes.put(13, "MXN");
            currencyCodes.put(14, "NZD");
            currencyCodes.put(15, "CHF");
            currencyCodes.put(16, "CZK");
            currencyCodes.put(17, "DKK");
            currencyCodes.put(18, "IDR");
            currencyCodes.put(19, "ILS");
            currencyCodes.put(20, "ISK");
            currencyCodes.put(21, "HUF");
            currencyCodes.put(22, "MYR");
            currencyCodes.put(23, "NOK");
            currencyCodes.put(24, "PHP");
            currencyCodes.put(25, "PLN");
            currencyCodes.put(26, "RON");
            currencyCodes.put(27, "SEK");
            currencyCodes.put(28, "SGD");
            currencyCodes.put(29, "THB");
            currencyCodes.put(30, "TRY");

            Integer from, to;
            String fromCode, toCode;
            double amount;

            Scanner sc = new Scanner(System.in);

            System.out.println("------------------------------------------");
            System.out.println("Welcome to our Currency Convertor!");
            System.out.println("------------------------------------------");

            System.out.println("Currency converting from: ");
            System.out.println(" 1: USD \t 2: CAD \t 3: EUR \t 4: HKD \t 5: IND \t 6: AUD \t 7: BGN \t 8: BRL \t 9: CNY \t 10: GBP \n " +
                    "11: JPY \t 12: KRW \t 13: MXN \t 14: NZD \t 15: CHF \t 16: CZK \t 17: DKK \t 18: IDR \t 19: ILS \t 20: ISK \n" +
                    " 21: HUF \t 22: MYR \t 23: NOK \t 24: PHP \t 25: PLN \t 26: RON \t 27: SEK \t 28: SGD \t 29: THB \t 30: TRY");
            from = sc.nextInt();
            while (from < 1 || from > 30) {
                System.out.println("Please enter a valid number (1-5)!");
                System.out.println(" 1: USD \t 2: CAD \t 3: EUR \t 4: HKD \t 5: IND \t 6: AUD \t 7: BGN \t 8: BRL \t 9: CNY \t 10: GBP \n " +
                        "11: JPY \t 12: KRW \t 13: MXN \t 14: NZD \t 15: CHF \t 16: CZK \t 17: DKK \t 18: IDR \t 19: ILS \t 20: ISK \n" +
                        " 21: HUF \t 22: MYR \t 23: NOK \t 24: PHP \t 25: PLN \t 26: RON \t 27: SEK \t 28: SGD \t 29: THB \t 30: TRY");
                from = sc.nextInt();
            }
            fromCode = currencyCodes.get(from);

            System.out.println("Currency converting to: ");
            System.out.println(" 1: USD \t 2: CAD \t 3: EUR \t 4: HKD \t 5: IND \t 6: AUD \t 7: BGN \t 8: BRL \t 9: CNY \t 10: GBP \n " +
                    "11: JPY \t 12: KRW \t 13: MXN \t 14: NZD \t 15: CHF \t 16: CZK \t 17: DKK \t 18: IDR \t 19: ILS \t 20: ISK \n" +
                    " 21: HUF \t 22: MYR \t 23: NOK \t 24: PHP \t 25: PLN \t 26: RON \t 27: SEK \t 28: SGD \t 29: THB \t 30: TRY");
            to = sc.nextInt();
            while (to < 1 || to > 30) {
                System.out.println("Please enter a valid number (1-5)!");
                System.out.println(" 1: USD \t 2: CAD \t 3: EUR \t 4: HKD \t 5: IND \t 6: AUD \t 7: BGN \t 8: BRL \t 9: CNY \t 10: GBP \n " +
                        "11: JPY \t 12: KRW \t 13: MXN \t 14: NZD \t 15: CHF \t 16: CZK \t 17: DKK \t 18: IDR \t 19: ILS \t 20: ISK \n" +
                        " 21: HUF \t 22: MYR \t 23: NOK \t 24: PHP \t 25: PLN \t 26: RON \t 27: SEK \t 28: SGD \t 29: THB \t 30: TRY");
                to = sc.nextInt();
            }
            toCode = currencyCodes.get(to);

            System.out.println("Amount you wish to convert: ");
            amount = sc.nextFloat();

            sendHttpGetRequest(fromCode, toCode, amount);

            System.out.println("Would you like to use the converter again?");
            System.out.println("1: Yes \t Any other key: No");
            if(!sc.next().equals("1")) {
                running = false;
            }

        }while(running);
        System.out.println("Thank you for using our application!");

    }

    private static void sendHttpGetRequest(String fromCode, String toCode, double amount) throws IOException {

        String GET_URL = "https://api.frankfurter.dev/v1/latest?base=" + fromCode + "&symbols=" + toCode;
        DecimalFormat de = new DecimalFormat("00.00");
        URL url = new URL(GET_URL);
        HttpURLConnection httpsUrlCon = (HttpURLConnection) url.openConnection();
        httpsUrlCon.setRequestMethod("GET");
        int responseCode = httpsUrlCon.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(httpsUrlCon.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in.close();

            JSONObject obj = new JSONObject(response.toString());
            Double exRate = obj.getJSONObject("rates").getDouble(toCode);
            System.out.println("-----------------------------------------");
            System.out.println("The exchange rate is: " + exRate);
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println(de.format(amount) + " " + fromCode + " = " + de.format(amount*exRate) + " " + toCode);
            System.out.println("-----------------------------------------");
        }
        else {
            System.out.println("Sorry! Your request didn't work.");
        }

    }
}

