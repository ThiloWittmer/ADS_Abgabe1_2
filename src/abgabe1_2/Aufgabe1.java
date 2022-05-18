package abgabe1_2;

import de.medieninf.ads.ADSTool;

public class Aufgabe1 {

	public static void main(String[] args) {
		int zaehler = 1;
		int index = 0;
		int [] datei;
 		
		//einlesen der Werte
		if(args.length == 0) {
			datei = new int[ADSTool.readIntArray("haeufigkeit.dat").length];
			System.arraycopy(ADSTool.readIntArray("haeufigkeit.dat"), 0, datei, 0, datei.length);
		} else {
			datei = new int[ADSTool.readIntArray(args[0]).length];
			System.arraycopy(ADSTool.readIntArray(args[0]), 0, datei, 0, datei.length);
		}
		
		//erzeugen eines Speicher-arrays fuer Zahlen und ihre Haeufigkeit
		//arr[0] fuer die Zahlen und arr[1] die entsprechenden Haeufigkeiten
		int length = datei.length;
		int [][] arr = new int[2][length];
		
		sort(datei, 0, length-1);
		
		//wenn die aktuelle Zahl mit der darvor uebereinstimmt: zaehler++
		//ansonsten: wird die Zaahl darvor mit ihrer Haeufigkeit abgespeichert
		for(int i=0; i < length; i++) {
			if (i!=0 && datei[i] != datei[i-1]) {
				arr[0][index] = datei[i-1];
				arr[1][index] = zaehler;
				index++;
				zaehler = 1;
			} else if(i!=0) zaehler++;
		}
		
		//Letzte Zahl und ihre Haeufigkeit speichern
		arr[0][index] = datei[length-1];
		arr[1][index] = zaehler;
		
		System.arraycopy(arr[1], 0, datei, 0, length);
		sort(datei, 0, length-1);
		
		for(int i=0; i<10; i++) {
			for(int j=0; j < length; j++) {
				if(datei[length-1-i] == arr[1][j] && arr[0][j] !=0) {
					System.out.println(arr[0][j] + "\tHaeufigkeit: " + arr[1][j]);
					arr[0][j] = 0;
					break;
				}
			}
		}
	}
	
	//sortiert alle zahlen die kleiner als arr[high] sind links von arr[i+1]; alle groesseren rechts davon
	//setzt dann den pivot bzw. arr[high] and arr[i+1] und gibt dessen index zurueck
	/***
	 * sortiert alle zahlen die kleiner als arr[high] sind links von arr[i+1]; alle groesseren rechts davon
	 * setzt dann den pivot bzw. arr[high] and arr[i+1] und gibt dessen index zurueck
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	static int partition(int arr[], int low, int high) {
        int pivot = arr[high]; 
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
  
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
  
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
  
        return i+1;
    }
	
	/***
	 * Quicksort fuer ein int arr 
	 * @param arr
	 * @param low ist der niederigste array-index
	 * @param high ist der hoechste array-Index; wird als pivot verwendet
	 */
	static void sort(int arr[], int low, int high) {
        if (low < high) {
        	
            // pi ist  der partitions index, arr[pi] ist jetzt am richtigen Platz
            int pi = partition(arr, low, high);
  
            // Rekursiv die Elemente von vorher suchen
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
}