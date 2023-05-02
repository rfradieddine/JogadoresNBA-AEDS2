import java.util.Scanner;


class Jogador {
    private int id, anoNascimento, altura, peso;
    private String nome, estadoNascimento, universidade, cidadeNascimento;

    //Leitura de dados do jogador
    void entradaDados(){
        Scanner sc = new Scanner(System.in);

        this.id = sc.nextInt();
        this.anoNascimento = sc.nextInt();
        this.altura = sc.nextInt();
        this.peso = sc.nextInt();
        this.nome = sc.nextLine();
        this.estadoNascimento = sc.nextLine();
        this.universidade = sc.nextLine();
        this.cidadeNascimento = sc.nextLine();

    }

    //Imprimir dados do jogador
    public String imprimir() {
        return toString();
    }

    //Construtor 1
    public Jogador() {
    }

    //Construtor 2
    public Jogador (int id, String nome, int altura, int peso, String universidade, int anoNascimento,
                    String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    //Geters e Seters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    //Metodo toString
    @Override
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

    //Metodo clone
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lê dados dos jogadores
        Jogador[] jogadores = new Jogador[5000];
        int numJogadores = 0;
        String entrada;
        while(!(entrada = sc.nextLine()).equals("FIM")) {
            String[] dados = entrada.split(",");
            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(dados[0]));
            jogador.setNome(dados[1]);
            jogador.setAltura(dados.length > 2 && !dados[2].equals("") ? Integer.parseInt(dados[2]) : 0);
            jogador.setPeso(dados.length > 3 && !dados[3].equals("") ? Integer.parseInt(dados[3]) : 0);
            jogador.setUniversidade(dados.length > 4 && !dados[4].equals("") ? dados[4] : "nao informado");
            jogador.setAnoNascimento(dados.length > 5 && !dados[5].equals("") ? Integer.parseInt(dados[5]) : 0);
            jogador.setCidadeNascimento(dados.length > 6 && !dados[6].equals("") ? dados[6] : "nao informado");
            jogador.setEstadoNascimento(dados.length > 7 && !dados[7].equals("") ? dados[7] : "nao informado");
            jogadores[numJogadores++] = jogador;
        }

        // Realiza as buscas pelos jogadores
        int numBuscas = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < numBuscas; i++) {
            int idBusca = Integer.parseInt(sc.nextLine());
            for(int j = 0; j < numJogadores; j++) {
                Jogador jogador = jogadores[j];
                if(jogador.getId() == idBusca) {
                    System.out.println(jogador.toString());
                    break;
                }
            }
        }
        sc.close();
    }
}