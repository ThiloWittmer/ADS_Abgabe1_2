package abgabe1_2;

import de.medieninf.ads.ADSTool;

public class Aufgabe1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ersatz=-1;        // �bernimmt die Stelle der Zahl, deren H�ufigkeit schon gez�hlt wurde.
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
                if(datei[i]==datei[j]) {             //j schnappt sich eine nummer und sucht nach ihrer H�ufigkeit
                    zaehler++;                            // Z�hler erh�ht sich 
                    hauefigkeiten[j]=ersatz;            // Nach der Z�hlung der H�ufigkeit einer Nummer 
                }										// gibt der Variable ersatz -1 der eben gez�hlten Nummer ein

            }
            if(hauefigkeiten[i]!=ersatz) {  // Nach der Z�hlung einer Nummer,
                hauefigkeiten[i]=zaehler;    // gib der Z�hler an, wie viel sich eine nummer wiederholt hat.
            }

        }

        for(int i =0; i<hauefigkeiten.length;i++) {  //Ausgabe der H�ufigkeit von der Datei
            if(hauefigkeiten[i]!=ersatz) {
                System.out.println("H�ufigkeiten von " + datei[i] + " : " + hauefigkeiten[i]);
            }
        }
        
	}
}