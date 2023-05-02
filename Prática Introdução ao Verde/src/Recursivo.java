import java.util.Scanner;

public class Recursivo {
    static Scanner scan = new Scanner(System.in);
    static int n = 0;
    public static void main(String[] args) {
        String frase;
        while ((frase = scan.nextLine()) != null && !frase.equals("FIM")) {
            maiusculas(frase, frase.length() - 1);
            n = 0;
        }
    }

    public static void maiusculas(String frase, int tamanho) {
        if (tamanho < 0) {
            System.out.println(n);
            return;
        }
        if ((frase.charAt(tamanho) >= 'A') && (frase.charAt(tamanho) <= 'Z')) {
            n += 1;
        }
        maiusculas(frase, tamanho - 1);
    }

}
