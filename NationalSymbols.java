/*
* File: NationalSymbols.java
* Author: Jay Galindez
* Date: 6 Oct 2018
* Purpose: Ask the user via the command line for a state name or postal
            code  and return the states national bird and flower
*/
package nationalsymbols;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class NationalSymbols {
    /*
        instance variables
    */
    private String bird;
    private String flower;
    private String state;
    private String postalCode;
    private static List historyQuery = new ArrayList();
    
    /*
        Constructor
    */
    public NationalSymbols(String stateInput, String postalInput, String flowerInput, String birdInput){
        this.state = stateInput;
        this.postalCode = postalInput;
        this.flower = flowerInput;
        this.bird = birdInput;
    }
    
    /*
        Manual constructors Takes in either state name or state postal code
    */
    public NationalSymbols(String stateInput) throws IOException{
        String stringVal = stringEvaluate(stateInput);
        if (!stringVal.equals("false")){
            String flag;
            flag = allStates.isState(stringVal);
            if (flag.equals("false")){
                System.out.println("[NONE STATE ERROR] Please input a valid state name.\n");
            } else if (flag.equals("true")){
                NationalSymbols tempStateObj = createStateObj(stringVal);
                this.state = tempStateObj.getState();
                this.flower = tempStateObj.getFlower();
                this.bird = tempStateObj.getBird();
                this.postalCode = tempStateObj.getPostalCode();
            } else{
                NationalSymbols tempStateObj = createStateObj(flag);
                this.state = tempStateObj.getState();
                this.flower = tempStateObj.getFlower();
                this.bird = tempStateObj.getBird();
                this.postalCode = tempStateObj.getPostalCode();
            }
        }
    }
    /*
        getter block
    */
    public String getFlower(){
        return this.flower;
    }
    public String getBird(){
        return this.bird;
    }
    public String getState(){
        return this.state;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    
    /*
        toString
    */
    public String toString(){
        String str;
        str =String.format( "State: %s\nPostal Code: %s\nBird: %s\nFlower: %s\n", this.state, this.postalCode, this.bird, this.flower);
        return str;
    }
    
    /*
        This block is responsible for instaciating the state object which contains 
        the state name, postal code, national bird and flower. It does it by 
        supplying a valid lowercase state name. 
    */
    public static NationalSymbols createStateObj(String inputState) throws FileNotFoundException, IOException{
        String cwd = System.getProperty("user.dir");
        File file = new File(cwd+"/allStateData.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String testString;
        NationalSymbols tempObj = null;
        while ((testString = br.readLine()) != null) 
              if (testString.toLowerCase().equals(inputState.toLowerCase())){
                  tempObj = new NationalSymbols(testString, br.readLine(), br.readLine(), br.readLine());
                  //System.out.print("\n");
                  //System.out.println(tempObj.toString());
                  break;
              }
        historyQuery.add(testString);
        System.out.println("Closing file");
        br.close();
        return tempObj;
    } 
    
    /*
        This method is responsible for validating the string inputed by the user.
        First ) it removes leading whitespace and then splits the users input into a list delimited by white space.
    
        second ) Tests to see that the users input contains a maxiumum of 2 inputs. 
    
        third ) iterates through each character to test that it's a letter. This was added to prevent 
                open the text file which contains the data base for the states. I would be more effecient 
                to iterate through characters than to open a file to iterate and fail.
    */
    public static String stringEvaluate(String stateInput) throws IOException{
        String userInput[] = stateInput.replaceAll("^\\s+", "").split("\\s+"); // removes leading whitespace and splits inputs by whitespace
        if (userInput.length > 2){
            System.out.println("[LENGTH ERROR] Please try input your state again.\n");
            return "false";
        } else {
            for (String item : userInput){
                for (char c : item.toCharArray()) {
                    if (!Character.isLetter(c)){
                        System.out.println("[STRING ERROR] Please try inputing your state again.\n");
                        return "false";
                        }
                    } // end of character check
                } // end of user input for loop
        

            // Convert Array into a String and return to caller
            
            String validState;
            if (userInput.length == 2){
                validState = userInput[0].toLowerCase() +" "+ userInput[1].toLowerCase();
            } else {
                validState = userInput[0].toLowerCase();
            } 
            return validState;
        
        } // end of array parser
    } // end of stringEvaluate method
    
    
    
    /* 
        main will make sure that user has not entered "none" if not send it to validation
    */
    public static void main(String[] args) throws IOException {
        String stateInput;
        boolean repeat = true;
        Scanner userInput = new Scanner(System.in);
        while (repeat){
            System.out.print("Enter the state you would like to query or enter \"none\":\n--> ");
            stateInput = userInput.nextLine();
            if (stateInput.toLowerCase().equals("none") || stateInput.equals("")){
                System.out.println("Exiting.");
                repeat = false;
            } else {
                String stringVal = stringEvaluate(stateInput);
                if (!stringVal.equals("false")){
                            /*
                                This section uses flags [true, false, statename] to test if the user input
                                is a state. This will be compared to an array in allStates.java 

                            true:
                                true is returned when the string input is expected to be a written out state name
                                the input will be checked against a string array using binarySearch. if True 
                                is returned then the user input will be used to craete the state object

                            false: 
                                not match was found

                            flag/state:
                                if user decides to use a postal code, then the postal code will be compared against
                                an array of postal codes. If there is a match, the index of that postal code will be 
                                used against the array of state strings to return the string of that state. flag will
                                then become the state name.
                            */
                    String flag;
                    flag = allStates.isState(stringVal);
                    if (flag.equals("false")){
                        System.out.println("[NONE STATE ERROR] Please input a valid state name.\n");
                    } else if (flag.equals("true")){
                        NationalSymbols tempStateObj = createStateObj(stringVal);
                        System.out.println(tempStateObj.toString());
                    } else{
                        NationalSymbols tempStateObj = createStateObj(flag);
                        System.out.println(tempStateObj.toString());
                    }
                } 
            }
        }
        userInput.close();
        System.out.println("Thanks for using NationalSymbols, here's today query history:");
        for (Object query : historyQuery){
            System.out.println("\t"+query);
        }
        System.exit(0);
    } // end of main
    
}// end of class
