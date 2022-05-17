package abgabe1_2;


import de.medieninf.ads.ADSTool;

public class Aufgabe2 {

    public static void main(String[] args) {
    	char[][] summand1;
        char[][] summand2;
        
        if(args.length == 0) {
        	summand1 = new char[(ADSTool.readStringArray("langeZahlen.dat").length)/2][];
        	summand2 = new char[(ADSTool.readStringArray("langeZahlen.dat").length)/2][];
        	addNumbersInFile(ADSTool.readStringArray("langeZahlen.dat"), summand1, summand2);
        }
        else {
        	summand1 = new char[(ADSTool.readStringArray(args[0]).length)/2][];
        	summand2 = new char[(ADSTool.readStringArray(args[0]).length)/2][];
        	addNumbersInFile(ADSTool.readStringArray(args[0]), summand1, summand2);
        }
        
        //output sums
        for (int i=0; i < summand1.length; i++) {
        	System.out.println(outputString(add(summand1[i], summand2[i])));
        }
        
        //output products
        for (int i=0; i < summand1.length; i++) {
        	System.out.println(outputString(mult(summand1[i], summand2[i])));
        }
    }
    
    //test
    /*** 
     * stores content of summand1 in number2 and from summand2 in number1
     * @param summand1
     * @param summand2
     * @param number1
     * @param number2
     */
    static void swap(char[] summand1, char[] summand2, char[] number1, char[] number2){
    	char[] temp = new char[summand1.length];
    	System.arraycopy(summand1, 0, temp, 0, summand1.length);
    	System.arraycopy(summand2, 0, number1, 0, summand2.length);
    	System.arraycopy(temp, 0, number2, 0, temp.length);
    }
    
    /***
     * takes entries if String array and stores them in summand1 and summand2
     * @param lines String array with numbers in it
     * @param summand1 numbers from String array with an odd position
     * @param summand2 numbers from String array with an even position
     */
    public static void addNumbersInFile(String[] lines, char[][] summand1, char[][] summand2) {
    	for (int i=0; i < lines.length; i +=2) {
    		summand1[i/2] = langeZahl(lines[i]);
    		summand2[i/2] = langeZahl(lines[i+1]);
    	}
    }
    
    /***
     * adds two given numbers which are stored in char arrays
     * @param summand1
     * @param summand2
     * @return result as a char array
     */
    static char[] add(char[] summand1, char[] summand2) {
    	char[] number1, number2;
    	
    	// check if one number is bigger than the other, if yes then swap
        if(summand1.length < summand2.length) {
        	number1 = new char[summand2.length];
        	number2 = new char[summand1.length];
        	swap(summand1, summand2, number1, number2);
        } else {
        	number1 = new char[summand1.length];
        	number2 = new char[summand2.length];
        	System.arraycopy(summand1, 0, number1, 0, summand1.length);
        	System.arraycopy(summand2, 0, number2, 0, summand2.length);
        }
        
        String result = "";
        int digitDiff = number1.length - number2.length;
        int sum, carry = 0;
        
        for (int i = 0; i < number1.length; i++) {
            if(i < number1.length - digitDiff) {
            	sum = Character.getNumericValue(number1[i]) + Character.getNumericValue(number2[i]) + carry;
            }
            else  sum = Character.getNumericValue(number1[i]) + carry;
            result += String.valueOf(sum % 10);
            carry = sum / 10;
        }

        if(carry != 0) {
        	result += "1";
        }

        return result.toCharArray();
    }
    
    /***
     * multiplies two given numbers which are stored in char arrays
     * @param number1
     * @param number2
     * @return
     */
    static char[] mult(char[] number1, char[] number2) {
    	String[] results = new String[number2.length];
    	char[][] addResults = new char[number2.length][];
    	String result = "";
    	String temp = "";
    	
    	int sum, carry = 0;
    	
    	for (int i=0; i < results.length; i++) {
    		results[i] = "";
    	}
    	
    	// runs through digits of second number to multiply with digits of first number
    	//storing the results in a String array and then adding them to a final result
    	for (int i=0; i < number2.length; i++) {
    		for (int n=0; n < number1.length; n++) {
    			sum = Character.getNumericValue(number1[n]) * Character.getNumericValue(number2[i]) + carry;
    			results[i] += String.valueOf(sum % 10);
    			carry = sum / 10;
    		}
    		
    		if(carry != 0) {
    			results[i] += "1";
    			carry = 0;
    		}
    		
    		for (int n=0; n < i; n++) {
    			results[i] = "0" + results[i];
    		}
    	}
    	
    	//converting the String[] array to a char[][] array
     	for(int i=0; i < results.length; i++) {
    		addResults[i] = results[i].toCharArray();
    	}
    	
     	//adding the results and storing in the result string
    	for (char[] currNumber : addResults) {
    		for (char currDigit : add(currNumber, result.toCharArray())) {
    			temp += currDigit;
    		}
    		result = temp;
    		temp = "";
    	}
    	
    	return result.toCharArray();
    }

    /***
     *  turns a String in a char Array with inverted order
     * @param number
     * @return
     */
    static char[] langeZahl(String number) {
        char[] chars = number.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // swap the values at the left and right indices
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            // move the left and right index pointers in toward the center
            left++;
            right--;
        }
        return chars;
    }
    
    /***
     * turns a char array with an inverted order into a String with correct order
     * @param number
     * @return
     */
    static String outputString(char[] number) {
    	String output = "";
    	for (char currDigit : number) {
    		output = currDigit + output;
    	}
    	return output;
    }
}
