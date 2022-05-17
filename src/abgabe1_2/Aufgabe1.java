package abgabe1_2;

import de.medieninf.ads.ADSTool;

public class Aufgabe1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ersatz=-1;        // übernimmt die Stelle der Zahl, deren Häufigkeit schon gezählt wurde.
		int zaehler=1;
		int [] datei;
 		
		if(args.length == 0) {
			datei = new int[ADSTool.readIntArray("haeufigkeit.dat").length];
			System.arraycopy(ADSTool.readIntArray("haeufigkeit.dat"), 0, datei, 0, datei.length);
		} else {
			datei = new int[ADSTool.readIntArray(args[0]).length];
			System.arraycopy(ADSTool.readIntArray(args[0]), 0, datei, 0, datei.length);
		}
 		
		int [] hauefigkeiten= new int [datei.length]; 

        for(int i=0; i<datei.length; i++) {
            for(int j=i+1; j<datei.length; j++) {
                if(datei[i]==datei[j]) {             //j schnappt sich eine nummer und sucht nach ihrer Häufigkeit
                    zaehler++;                            // Zähler erhöht sich 
                    hauefigkeiten[j]=ersatz;            // Nach der Zählung der Häufigkeit einer Nummer 
                }										// gibt der Variable ersatz -1 der eben gezählten Nummer ein

            }
            if(hauefigkeiten[i]!=ersatz) {  // Nach der Zählung einer Nummer,
                hauefigkeiten[i]=zaehler;    // gib der Zähler an, wie viel sich eine nummer wiederholt hat.
            }

        }

        for(int i =0; i<hauefigkeiten.length;i++) {  //Ausgabe der Häufigkeit von der Datei
            if(hauefigkeiten[i]!=ersatz) {
                System.out.println("Häufigkeiten von " + datei[i] + " : " + hauefigkeiten[i]);
            }
        }
        
	}
}