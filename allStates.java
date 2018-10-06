/*
    This purpuse of this script is to check if the validated string input that user
    has entered is actually a state or postal abb
 */
package nationalsymbols;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class allStates {
    static final String allStatesArray[] = { "alabama",
                                "alaska",
                                "arizona",
                                "arkansas",
                                "california",
                                "colorado",
                                "connecticut",
                                "delaware",
                                "florida",
                                "georgia",
                                "hawaii",
                                "idaho",
                                "illinois",
                                "indiana",
                                "iowa",
                                "kansas",
                                "kentucky",
                                "louisiana",
                                "maine",
                                "maryland",
                                "massachusetts",
                                "michigan",
                                "minnesota",
                                "mississippi",
                                "missouri",
                                "montana",
                                "nebraska",
                                "nevada",
                                "new hampshire",
                                "new jersey",
                                "new mexico",
                                "new york",
                                "north carolina",
                                "north dakota",
                                "ohio",
                                "oklahoma",
                                "oregon",
                                "pennsylvania",
                                "rhode island",
                                "south carolina",
                                "south dakota",
                                "tennessee",
                                "texas",
                                "utah",
                                "vermont",
                                "virginia",
                                "washington",
                                "west virginia",
                                "wisconsin",
                                "wyoming"
                                        };
    static final String allStateAbbArray[] = {"AL",
                                                "AK",
                                                "AZ",
                                                "AR",
                                                "CA",
                                                "CO",
                                                "CT",
                                                "DE",
                                                "FL",
                                                "GA",
                                                "HI",
                                                "ID",
                                                "IL",
                                                "IN",
                                                "IA",
                                                "KS",
                                                "KY",
                                                "LA",
                                                "ME",
                                                "MD",
                                                "MA",
                                                "MI",
                                                "MN",
                                                "MS",
                                                "MO",
                                                "MT",
                                                "NE",
                                                "NV",
                                                "NH",
                                                "NJ",
                                                "NM",
                                                "NY",
                                                "NC",
                                                "ND",
                                                "OH",
                                                "OK",
                                                "OR",
                                                "PA",
                                                "RI",
                                                "SC",
                                                "SD",
                                                "TN",
                                                "TX",
                                                "UT",
                                                "VT",
                                                "VA",
                                                "WA",
                                                "WV",
                                                "WI",
                                                "WY",};
    public static String isState(String input) throws IOException{
        int result = -1; // variable returns 0 or higher if string input is in our list
        if (input.length() == 2){
            int counter = 0;
            for (String postalCode : allStateAbbArray){
                if (postalCode.equals(input.toUpperCase())) {
                    return (allStatesArray[counter]);
                } else {
                    counter ++;
                }
            } if (counter == 49) {
                return "false";
            }
        
            
        // this section will test for written out states
        } else {
            result = Arrays.binarySearch(allStatesArray, input);
        }
        if (result >= 0){
            return "true";
        } else {
            return "false";
        }
    }
    
    public static void main(String[] args) throws IOException {
 
        /*
        Scanner userInput = new Scanner(System.in);
        String stateInput = userInput.nextLine();
        String result = isState(stateInput);
        System.out.println(result);
        */
        
        NationalSymbols obj = new NationalSymbols("nc");
        System.out.println(obj.getState());
        System.out.println(obj.getBird());
        System.out.println(obj.toString());
        
    }
}