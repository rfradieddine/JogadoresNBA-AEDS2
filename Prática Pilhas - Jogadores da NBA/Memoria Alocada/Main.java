import java.io.*;

class Jogador {

    private int id, altura, peso, anoNascimento;
    private String nome,universidade, cidadeNascimento,estadoNascimento;

    public Jogador() {
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
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

    public Jogador clone() { // clone do objeto

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

    public void imprimir() { // imprimir os dados

        System.out.printf("## %d ## ", this.id);

        System.out.printf("%s ## ", this.nome);

        System.out.printf("%d ## ", this.altura);

        System.out.printf("%d ## ", this.peso);

        System.out.printf("%d ## ", this.anoNascimento);

        if (this.universidade.trim().length() == 0) {
            System.out.printf("nao informado ## ");
        } else {
            System.out.printf("%s ## ", this.universidade);
        }

        if (this.cidadeNascimento.trim().length() == 0) {
            System.out.printf("nao informado ## ");
        } else {
            System.out.printf("%s ## ", this.cidadeNascimento);
        }

        if (this.estadoNascimento.trim().length() == 0) {
            System.out.printf("nao informado ##\n");
        } else {
            System.out.printf("%s ##\n", this.estadoNascimento);
        }

    }
}
class PilhaDinamica {

    private Celula fundo;
    private Celula topo;

    public PilhaDinamica() {

        Celula sentinela = new Celula();
        fundo = topo = sentinela;
    }

    public void empilhar(Jogador novo) { // inserir no topo

        Celula aux = new Celula();
        aux.proximo = topo;
        aux.item = novo;


        topo = aux;
    }

    public Jogador desempilhar() { // remover o topo

        Celula aux;

        Jogador player = null;
        if (!pilhaVazia()) {
            aux = topo;
            topo = topo.proximo;
            aux.proximo = null;
        }
        return player;
    }

    public boolean pilhaVazia() {

        boolean resp;
        if (fundo == topo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void mostrar() { // mostrar os dados

        Celula aux;
        int cont = 0;

        PilhaDinamica invertida = new PilhaDinamica();

        aux = topo;
        while (aux != fundo) {
            invertida.empilhar(aux.item);
            aux = aux.proximo;
        }

        aux = invertida.topo;
        while (aux != invertida.fundo) {

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

class ArquivoTextoLeitura {

    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo) {

        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
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
        } catch (EOFException excecao) {
            return null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            return null;
        }
        return textoEntrada;
    }
}


public class Main {
    public static void main(String[] args) throws NumberFormatException, Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PilhaDinamica stackDinamica = new PilhaDinamica();

        Jogador[] players = preencherJogadores(); // preencher o array de jogadores

        String idInformado = new String();
        do { // inserir na pilha
            idInformado = in.readLine();

            if (!(idInformado.equals("FIM"))) {

                stackDinamica.empilhar(players[Integer.parseInt(idInformado)]);
            }

        } while (!(idInformado.equals("FIM")));

        String[] dadosAcao;
        Jogador desempilhado = new Jogador();

        int i = 0, id;
        int qtd = Integer.parseInt(in.readLine());

        while (i < qtd) {   // realizar as acoes

            String acao = in.readLine();

            if (acao.charAt(0) == 'I') {
                dadosAcao = acao.split(" ", 2);
                id = Integer.parseInt(dadosAcao[1].toString());
                stackDinamica.empilhar(players[id]);
            } else if (acao.charAt(0) == 'R') {
                desempilhado = stackDinamica.desempilhar();
                System.out.println("(R) " + desempilhado.getNome());
            }

            i++;
        }

        stackDinamica.mostrar();

    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) { // contar a quantidade de linhas do arquivo
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

    public static Jogador[] preencherJogadores() throws Exception { // preencher o array de jogadores
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        int qtdJogadores = qtdLinhas(leitura);

        Jogador atual = new Jogador();
        Jogador[] todosJogadores = new Jogador[qtdJogadores];
        for (int i = 0; i < qtdJogadores; i++)
            todosJogadores[i] = new Jogador();

        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler(); /
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


