import java.util.Scanner;

public class Iterativo {
    public static int contarMaiusculas(String str) {
        int contador = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linha;
        while (!(linha = sc.nextLine()).equals("FIM")) {
            System.out.println(contarMaiusculas(linha));
        }
    }
}
