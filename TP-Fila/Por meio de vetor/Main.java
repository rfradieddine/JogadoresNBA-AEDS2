import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Jogador {

    private int id;
    private String nome = new String();
    private int altura;
    private int peso;
    private String universidade = new String();
    private int anoNascimento;
    private String cidadeNascimento = new String();
    private String estadoNascimento = new String();

    public Jogador() {

    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
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

    public void setId(int id) {
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

    public String imprimir() {
        return toString();
    }

    public String toString() {
        return "[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
                + universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]";
    }

}

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo) {

        try {
            entrada = new BufferedReader(new FileReader("/tmp/jogadores.txt"));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo n�o encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    public String ler() {

        String textoEntrada;

        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) { // Exce��o de final de arquivo.
            return null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            return null;
        }
        return textoEntrada;
    }
}

class Fila {

    private Celula frente;
    private Celula tras;
    int filaTamanho;

    public Fila() {
        Celula sentinela;

        sentinela = new Celula();
        frente = sentinela;
        tras = sentinela;
        filaTamanho = 0;
    }

    public boolean filaVazia() {
        boolean resp;

        if (frente == tras) {
            resp = true;
        }else{
            resp = false;
        }

        return resp;
    }

    public boolean filaCheia() {
        boolean resp;

        if (filaTamanho == 5) {
            resp = true;
        }else{
            resp = false;
        }

        return resp;
    }

    public void enfileirar(Jogador novo) {
        Celula novaCelula;

        if (!filaCheia()) {
            novaCelula = new Celula(novo);
            tras.proximo = novaCelula;
            tras = novaCelula;
            filaTamanho++;
        } else {
            desenfileirar();
            novaCelula = new Celula(novo);
            tras.proximo = novaCelula;
            tras = novaCelula;
            filaTamanho++;
        }
    }

    public Jogador desenfileirar() {
        Celula aux;
        aux = frente.proximo;
        frente.proximo = aux.proximo;
        aux.proximo = null;

        if (aux == tras) {
            tras = frente;
        }
        filaTamanho--;
        return (aux.item);
    }

    public void mostrar() {
        int cont = 0;
        if (!filaVazia()) {
            Celula aux;
            aux = frente.proximo;

            while (aux != null) {
                System.out.print("[" + cont + "] ## ");
                System.out.printf("%d ## ", aux.item.getId());
                System.out.printf("%s ## ", aux.item.getNome());
                System.out.printf("%d ## ", aux.item.getAltura());
                System.out.printf("%d ## ", aux.item.getPeso());
                System.out.printf("%d ## ", aux.item.getAnoNascimento());

                if (aux.item.getUniversidade().trim().length() == 0) {
                    System.out.printf("nao informado ## ");
                } else {
                    System.out.printf("%s ## ", aux.item.getUniversidade());
                }

                if (aux.item.getCidadeNascimento().trim().length() == 0) {
                    System.out.printf("nao informado ## ");
                } else {
                    System.out.printf("%s ## ", aux.item.getCidadeNascimento());
                }

                if (aux.item.getEstadoNascimento().trim().length() == 0) {
                    System.out.printf("nao informado ## \n");
                } else {
                    System.out.printf("%s ## \n", aux.item.getEstadoNascimento());
                }

                cont++;
                aux = aux.proximo;
            }

        }
    }

    public double obterMediaAltura() {
        double media;

        int cont = 0;
        double somatorio = 0.0;
        if (!filaVazia()) {
            Celula aux;

            aux = frente.proximo;
            while (aux != null) {
                somatorio += aux.item.getAltura();
                cont++;
                aux = aux.proximo;
            }
        }
        media = somatorio / cont;

        return Math.round(media);
    }

}

class Celula {

    Jogador item;
    Celula proximo;

    public Celula() {
        this.item = new Jogador();
        proximo = null;
    }

    public Celula(Jogador player) {
        this.item = player;
        proximo = null;
    }

}

public class App {
    public static void main(String[] args) throws NumberFormatException, Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Fila filaJogadores = new Fila();

        Jogador[] jogadores = preencherJogadores();

        String idInformado;
        do {
            idInformado = in.readLine();

            if (!(idInformado.equals("FIM"))) {
                filaJogadores.enfileirar(jogadores[Integer.parseInt(idInformado)]);
                System.out.println(((int) filaJogadores.obterMediaAltura()));

            }

        } while (!(idInformado.equals("FIM")));

        String[] dadosAcao;
        Jogador desenfilierado = new Jogador();

        int i = 0, id;
        int qtd = Integer.parseInt(in.readLine());

        while (i < qtd) {

            String acao = in.readLine();

            if (acao.charAt(0) == 'I') {
                dadosAcao = acao.split(" ", 2);
                id = Integer.parseInt(dadosAcao[1].toString());
                filaJogadores.enfileirar(jogadores[id]);
                System.out.println(((int) filaJogadores.obterMediaAltura()));
            } else if (acao.charAt(0) == 'R') {
                desenfilierado = filaJogadores.desenfileirar();
                System.out.println("(R) " + desenfilierado.getNome());
            }

            i++;
        }

        filaJogadores.mostrar();

    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) {
        int qtd = 0;
        String linhaLida = new String();
        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        linhaLida = leitura.ler();
        while (linhaLida != null) {
            qtd++;
            linhaLida = leitura.ler();
        }

        leitura.fecharArquivo();

        return qtd;
    }

    public static Jogador[] preencherJogadores() throws Exception {
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        int qtdJogadores = qtdLinhas(leitura);

        Jogador atual = new Jogador();
        Jogador[] todosJogadores = new Jogador[qtdJogadores];
        for (int i = 0; i < qtdJogadores; i++)
            todosJogadores[i] = new Jogador();

        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        for (int i = 0; i < qtdJogadores; i++) {

            String[] dadosDaLinha = leitura.ler().split(",", 8);

            atual.setId(Integer.parseInt((dadosDaLinha[0].toString())));
            atual.setNome(dadosDaLinha[1].toString());
            atual.setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
            atual.setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
            atual.setUniversidade(dadosDaLinha[4].toString());
            atual.setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
            atual.setCidadeNascimento(dadosDaLinha[6].toString());
            atual.setEstadoNascimento(dadosDaLinha[7].toString());

            todosJogadores[i] = atual;
            atual = new Jogador();
        }

        leitura.fecharArquivo();

        return todosJogadores;
    }

}