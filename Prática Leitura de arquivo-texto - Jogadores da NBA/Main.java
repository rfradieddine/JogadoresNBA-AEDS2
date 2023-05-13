import java.io.*;

class Jogador {

    private int id, altura, peso, anoNascimento;
    private String nome,universidade, cidadeNascimento,estadoNascimento;

    public Jogador() {
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
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

        if (this.universidade.trim().length() == 0) { // se a String for vazia
            System.out.printf("nao informado ## ");
        }
        else {
            System.out.printf("%s ## ", this.universidade);
        }

        if (this.cidadeNascimento.trim().length() == 0) {
            System.out.printf("nao informado ## ");
        }
        else {
            System.out.printf("%s ## ", this.cidadeNascimento);
        }

        if (this.estadoNascimento.trim().length() == 0) {
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
            textoEntrada = entrada.readLine();
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

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        String idInformado = new String();

        int qtdJogadores = qtdLinhas(leitura); // conta a quantidade de linhas do arquivo

        Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);

        do { // enquanto não for digitado FIM
            idInformado = in.readLine();

            if(!(idInformado.equals("FIM"))) {

                players[Integer.parseInt(idInformado)].imprimir();;

            }

        } while ( !(idInformado.equals("FIM")) );

    }

    public static int qtdLinhas (ArquivoTextoLeitura leitura) { // conta a quantidade de linhas do arquivo
        int qtd=0;
        String linhaLida = new String();
        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        linhaLida = leitura.ler();
        while (linhaLida != null){ // enquanto não chegar no fim do arquivo
            qtd++;
            linhaLida = leitura.ler();
        }

        leitura.fecharArquivo();

        return qtd;
    }

    public static Jogador[] preencherVetorJogador (ArquivoTextoLeitura leitura, int qtdLinhas) { // preenche o vetor de jogadores
        Jogador[] players = new Jogador[qtdLinhas];

        for(int i=0; i<qtdLinhas; i++)
            players[i] = new Jogador();

        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        for(int i=0; i<qtdLinhas; i++) { // enquanto não chegar no fim do arquivo

            String[] dados = leitura.ler().split(",", 8);

            players[i].setId(Integer.parseInt((dados[0].toString())));
            players[i].setNome(dados[1].toString());
            players[i].setAltura(Integer.parseInt((dados[2].toString())));
            players[i].setPeso(Integer.parseInt((dados[3].toString())));
            players[i].setUniversidade(dados[4].toString());
            players[i].setAnoNascimento(Integer.parseInt((dados[5].toString())));
            players[i].setCidadeNascimento(dados[6].toString());
            players[i].setEstadoNascimento(dados[7].toString());

        }

        leitura.fecharArquivo();

        return players;
    }

}