import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Jogador {

    private int id, altura, peso, anoNascimento;
    private String universidade, cidadeNascimento, estadoNascimento, nome;

    public Jogador () {

    }

    public Jogador (int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // In�cio GETS
    public int getId () {
        return this.id;
    }

    public String getNome () {
        return this.nome;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso () {
        return this.peso;
    }

    public String getUniversidade () {
        return this.universidade;
    }

    public int getAnoNascimento () {
        return this.anoNascimento;
    }

    public String getCidadeNascimento () {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento () {
        return this.estadoNascimento;
    }
    // Fim GETS

    // In�cio SETS
    public void setId (int id) {
        this.id = id;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso (int peso) {
        this.peso = peso;
    }

    public void setUniversidade (String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento (int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento (String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento (String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }
    // Fim SETS

    public Jogador clone() {

        Jogador jogador = new Jogador();
        jogador.id = this.id;
        jogador.nome = this.nome;
        jogador.altura = this.altura;
        jogador.peso = this.peso;
        jogador.universidade = this.universidade;
        jogador.anoNascimento = this.anoNascimento;
        jogador.cidadeNascimento = this.cidadeNascimento;
        jogador.estadoNascimento = this.estadoNascimento;

        return jogador;
    }

    public void imprimir () {

        System.out.printf("[%d ## ", this.id);

        System.out.printf("%s ## ", this.nome);

        System.out.printf("%d ## ", this.altura);

        System.out.printf("%d ## ", this.peso);

        System.out.printf("%d ## ", this.anoNascimento);

        if (this.universidade.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado ## ");
        }
        else {
            System.out.printf("%s ## ", this.universidade);
        }

        if (this.cidadeNascimento.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado ## ");
        }
        else {
            System.out.printf("%s ## ", this.cidadeNascimento);
        }

        if (this.estadoNascimento.trim().length() == 0) { // Se o dado esta vazio
            System.out.printf("nao informado]\n");
        }
        else {
            System.out.printf("%s]\n", this.estadoNascimento);
        }

    }


}



class ArquivoTextoLeitura {

    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo){

        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        }
        catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public void fecharArquivo() {

        try {
            entrada.close();
        }
        catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    public String ler() {

        String textoEntrada;

        try {
            textoEntrada = this.entrada.readLine();
        }
        catch (EOFException excecao) {
            return null;
        }
        catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            return null;
        }
        return textoEntrada;
    }
}

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        String id;

        int qtdJogadores = qtdLinhas(leitura);

        Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);

        do {
            id = in.readLine();

            if(!(id.equals("FIM"))) {

                players[Integer.parseInt(id)].imprimir();;

            }

        } while ( !(id.equals("FIM")) );

    }

    public static int qtdLinhas (ArquivoTextoLeitura leitura) {
        int quantidade=0;
        String linhaLida;
        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        linhaLida = leitura.ler();
        while (linhaLida != null){
            quantidade++;
            linhaLida = leitura.ler();
        }

        leitura.fecharArquivo();

        return quantidade;
    }

    public static Jogador[] preencherVetorJogador (ArquivoTextoLeitura leitura, int linhas) {
        Jogador[] jogadores = new Jogador[linhas];

        for(int i=0; i<linhas; i++)
            jogadores[i] = new Jogador();

        leitura.abrirArquivo("/tmp/jogadores.txt");
        leitura.ler();

        for(int i=0; i<linhas; i++) {

            String[] linhasDados = leitura.ler().split(",", 8);

            jogadores[i].setId(Integer.parseInt((linhasDados[0].toString())));
            jogadores[i].setNome(linhasDados[1].toString());
            jogadores[i].setAltura(Integer.parseInt((linhasDados[2].toString())));
            jogadores[i].setPeso(Integer.parseInt((linhasDados[3].toString())));
            jogadores[i].setUniversidade(linhasDados[4].toString());
            jogadores[i].setAnoNascimento(Integer.parseInt((linhasDados[5].toString())));
            jogadores[i].setCidadeNascimento(linhasDados[6].toString());
            jogadores[i].setEstadoNascimento(linhasDados[7].toString());

        }

        leitura.fecharArquivo();

        return jogadores;
    }

}