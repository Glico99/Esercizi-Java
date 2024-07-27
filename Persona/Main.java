import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Ciao il mio nome e GianEduardo, so fare le operazioni di base, chiedimi qualcosa!");
        Calcolatrice gianCalc = new Calcolatrice();
        System.out.println(
                "1->Addizione\n2->Sottrazione\n3->Moltiplicazione\n4->Divisione\n5->Modulo\n6->Non sai fare altro?");

        int scelta = 0;
        double a = 0;
        double b = 0;
        Scanner input = new Scanner(System.in);
        scelta = input.nextInt();

        while (scelta < 1 && scelta > 6) {
            System.out.println("Fai una scelta valida!!!");
            scelta = input.nextInt();
        }

        
        

        if (scelta >= 1 && scelta <= 5) {
            System.out.println("Dammi i due numeri su cui vuoi fare l'operazione...");
            a = input.nextDouble();
            b = input.nextDouble();
            input.close();
            switch (scelta) {
                case 1:
                    System.out.println(gianCalc.somma(a, b));
                    break;
                case 2:
                    System.out.println(gianCalc.sottrazione(a, b));
                    break;
                case 3:
                    System.out.println(gianCalc.moltiplicazione(a, b));
                    break;
                case 4:
                    System.out.println(gianCalc.divisione(a, b));
                    break;
                case 5:
                    System.out.println(gianCalc.modulo(a, b));
                    break;
                default:
                    break;
            }
        }

        if (scelta == 6) {
            System.out.println("Beh, so fare anche l'area e la circonferenza del cerchio");
            Cerchio gianCerc = new Cerchio();
            System.out.println("1->Area\n2->Circonferenza\n");

            //Scanner inputCerc = new Scanner(System.in);
            int sceltaCerc = input.nextInt();

            while (sceltaCerc < 1 && sceltaCerc > 2) {
                System.out.println("Fai una scelta corretta!!!");
                sceltaCerc = input.nextInt();
            }

            System.out.println("Ora dimmi il raggio...");
            double raggio = input.nextDouble();

            input.close();

            if (sceltaCerc == 1) {
                System.out.println(gianCerc.area(raggio));
            } else if (sceltaCerc == 2) {
                System.out.println(gianCerc.circonferenza(raggio));
            }
        }
        System.out.println("Visto? So fare un sacco di cose!");
    }

}