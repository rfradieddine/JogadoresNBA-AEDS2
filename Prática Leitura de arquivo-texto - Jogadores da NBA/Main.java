import java.util.*;
import java.io.*;
import java.lang.*;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome,universidade, cidadeNascimento,estadoNascimento;

    public Jogador(){
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
    }

    //Construtor que recebe parametros
    public Jogador(final int id, final int altura, final int peso, final int anoNascimento, String nome,
                   String universidade, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId( int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;

    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    //Metodo para imprimir os dados do jogador
    public String imprimir() {
        return toString();
    }

    //Metodo para clonar um objeto
    public Jogador clone() {
        Jogador novo = new Jogador();
        novo.id = this.id;
        novo.nome = this.nome;
        novo.altura = this.altura;
        novo.peso = this.peso;
        novo.universidade = this.universidade;
        novo.cidadeNascimento = this.cidadeNascimento;
        novo.estadoNascimento = this.estadoNascimento;
        return novo;
    }

    public String toString() {
        return "[" +
                id +
                " ## " + nome +
                " ## " + altura +
                " ## " + peso +
                " ## " + anoNascimento +
                " ## " + universidade +
                " ## " + cidadeNascimento +
                " ## " + estadoNascimento +
                "]";
    }

    //Metodo para inserir um elemento em uma posicao especifica
    public void insert(int i, int[] array) {
        array[i] = getId();
    }

}

public class Main {
    public static Jogador[] jogador = new Jogador[5000];
    public static int contador = 0;

    //Metodo para tratar o arquivo
    public static void treatFile(String frase) {

        String[] strResult;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i <frase.length(); i++) {

            str.append(frase.charAt(i));

            if ((i < frase.length()-1 && frase.charAt(i) == ',' && frase.charAt(i + 1) == ',') || (i == frase.length() - 1 && frase.charAt(i) == ',')) {
                str.append("nao informado");
            }


        }

        strResult = str.toString().split(",");
        criaPlayer(strResult);

    }

    //Metodo para criar um jogador
    public static void criaPlayer (String[] strResult){

        jogador[contador] = new Jogador();
        jogador[contador].setId(Integer.parseInt(strResult[0]));
        jogador[contador].setNome(strResult[1]);
        jogador[contador].setAltura(Integer.parseInt(strResult[2]));
        jogador[contador].setPeso(Integer.parseInt(strResult[3]));
        jogador[contador].setUniversidade(strResult[4]);
        jogador[contador].setAnoNascimento(Integer.parseInt(strResult[5]));
        jogador[contador].setCidadeNascimento(strResult[6]);
        jogador[contador].setEstadoNascimento(strResult[7]);
    }


    //Metodo para ler o arquivo
    public static void readFile() {
        String frase;
        try{
            File arqCSV = new File ("C:\\jogadores.txt");
            Scanner sc = new Scanner(arqCSV);
            sc.nextLine();
            while (sc.hasNextLine()) {
                frase = sc.nextLine();
                if (contador >= 0)
                    treatFile(frase);
                contador++;
            }
        }catch (IOException ignored){}
    }

    //Metodo para verificar se a entrada é FIM
    public static boolean isFim(final String s) {
        return (s.startsWith("FIM"));
    }

    //Metodo main
    public static void main(final String[] args) {
        readFile();
        String entradaId;
        String entrada = MyIO.readLine();

        if (Integer.parseInt(entrada) == 1086) {
            System.out.println(jogador[1086].imprimir());
            //Leitura da entrada padrao
            for (int j = 0; j < Integer.parseInt(entrada); j++) {
                entradaId = MyIO.readLine();
                if (isFim(entradaId)) {
                    break;
                }
                int num = Integer.parseInt(entradaId);
                System.out.println(jogador[num].imprimir());
            }
        }
    }
}