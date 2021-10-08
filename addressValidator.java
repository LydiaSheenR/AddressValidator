import java.util.Scanner;
import org.owasp.html.Sanitizers;
import org.owasp.html.PolicyFactory;

public class Main {


    public static boolean StreetAddressValidation(String address) {

        // The source for address regex: https://www.codeproject.com/Tips/989012/Validate-and-Find-Addresses-with-RegEx

        if (address == null || address.trim().isEmpty() || !address.matches("\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?")) {
            return false;
        }
        return true;
    }

    public static String SanitizeAddress(String address) {

        if ( address.getClass() == String.class) {
          //The source for sanitize:  https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html

            PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);
            String cleanAddress = sanitizer.sanitize(address);
            return cleanAddress;
        }
        return "input is not a string";
    }

    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter street address");
        String address = s.nextLine();

        if (StreetAddressValidation(address)){
            System.out.print("Sanitized address:  " + SanitizeAddress(address));
        }else{
            System.out.print("Please enter a valid street address" ) ;
        }
    }

}
