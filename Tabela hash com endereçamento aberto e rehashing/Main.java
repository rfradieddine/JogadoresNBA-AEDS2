import java.io.*;

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


    public Jogador clone() throws CloneNotSupportedException {
        Jogador clone = (Jogador) super.clone();

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

        if (this.universidade.trim().length() == 0) {
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


class tHashEmAberto {

    private int M;
    private Jogador[] tabela;
    private int comparacoes;

    public tHashEmAberto(int tamanho) {
        this.comparacoes = 0;
        this.M = tamanho;
        this.tabela = new Jogador[tamanho];

        for (int i = 0; i < tamanho; i++)
            this.tabela[i] = null;
    }

    public int funcaoHash(int x, int k) {
        return ((x % M) + k * (x % 23)) % M;
    }

    public Jogador pesquisar(Jogador informado) {
        int k = 0;
        int posicao;
        Jogador procurado = null;
        while (k < this.M) {
            this.comparacoes++;

            posicao = funcaoHash(informado.getAltura(), k);

            if (tabela[posicao] == null) {
                procurado = null;
                k = M;
            } else if (tabela[posicao].getNome().equals(informado.getNome())) {
                System.out.print(posicao + " ");
                procurado = tabela[posicao];
                k = M;
            }

            k++;
        }

        return procurado;

    }

    public void inserir(Jogador novo) {
        int k = 0;
        int posicao;

        while (k < this.M) {
            posicao = funcaoHash(novo.getAltura(), k);

            if (tabela[posicao] == null) {
                tabela[posicao] = novo;
                k = M;
            } else if (tabela[posicao].getNome().equals(novo.getNome())) {
                System.out.println("Chave ja inserida");
                k = M;
            }

            k++;
        }

    }

    public int getComparacoes() {
        return this.comparacoes;
    }

}


class ArquivoTextoEscrita {

    private BufferedWriter saida;

    public void abrirArquivo(String nomeArquivo) {

        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (FileNotFoundException excecao) {

        } catch (IOException excecao) {

        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        } catch (IOException excecao) {

        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
            saida.newLine();
        } catch (IOException excecao) {

        }
    }
}

class ArquivoTextoLeitura {

    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo) {

        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
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
        } catch (EOFException excecao) {
            return null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            return null;
        }
        return textoEntrada;
    }
}


class Main {

    public static void main(String[] args) throws NumberFormatException, Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        String idInformado;

        int qtdJogadores = qtdLinhas(leitura);

        Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);


        tHashEmAberto tabela = new tHashEmAberto(79);

        do {
            idInformado = in.readLine();

            if (!(idInformado.equals("FIM"))) {

                tabela.inserir(players[Integer.parseInt(idInformado)]);

            }

        } while (!(idInformado.equals("FIM")));

        String nome;
        long inicio = System.currentTimeMillis();
        do {
            nome = in.readLine();

            if (!(nome.equals("FIM"))) {

                Jogador buscado = null;
                for (int i = 0; i < players.length; i++)
                    if (players[i].getNome().equals(nome))
                        buscado = players[i];

                if (tabela.pesquisar(buscado) == null)
                    System.out.print("NAO\n");
                else
                    System.out.print("SIM\n");

            }

        } while (!(nome.equals("FIM")));

        long fim = System.currentTimeMillis();
        int comparacoes = tabela.getComparacoes();
        gerarLog(inicio, fim, comparacoes);

    }

    public static void gerarLog(long inicio, long fim, int comparacoes) {
        long mili = fim - inicio;

        ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
        String log = new String("750376,689811,763343\t" + mili + "\t" + comparacoes);

        escrita.abrirArquivo("matricula_hashRehashing.txt");
        escrita.escrever(log);
        escrita.fecharArquivo();
    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) {
        int qtd = 0;
        String linhaLida;
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

    public static Jogador[] preencherVetorJogador(ArquivoTextoLeitura leitura, int qtdLinhas) {
        Jogador[] jogadores = new Jogador[qtdLinhas];

        for (int i = 0; i < qtdLinhas; i++)
            jogadores[i] = new Jogador();

        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        for (int i = 0; i < qtdLinhas; i++) {

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