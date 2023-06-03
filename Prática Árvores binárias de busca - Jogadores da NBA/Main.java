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

class noJogador {

    Jogador item;
    noJogador direita;
    noJogador esquerda;

    public noJogador(Jogador registro) {
        item = registro;
        direita = null;
        esquerda = null;
    }

}

class ABB {

    private noJogador raiz;
    private int comparacoes;


    public ABB() {
        this.comparacoes = 0;
        raiz = null;
    }


    public Boolean arvoreVazia() {
        if (this.raiz == null)
            return true;
        else
            return false;
    }


    private noJogador adicionar(noJogador raizArvore, Jogador jogadorNovo) {

        if (raizArvore == null)
            raizArvore = new noJogador(jogadorNovo);
        else {
            if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) > 0)
                raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
            else {

                if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) < 0)
                    raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
                else
                    System.out.println("O jogador " + jogadorNovo.getNome() + ", cuja id e " + jogadorNovo.getId()
                            + ", ja foi inserido anteriormente na arvore.");
            }
        }

        return raizArvore;
    }


    public void inserir(Jogador jogadorNovo) {

        this.raiz = adicionar(this.raiz, jogadorNovo);
    }

    private noJogador antecessor(noJogador jogadorRetirar, noJogador raizArvore) {
        if (raizArvore.direita != null) {
            raizArvore.direita = antecessor(jogadorRetirar, raizArvore.direita);
            return raizArvore;
        }
        else {

            jogadorRetirar.item.setId(raizArvore.item.getId());
            jogadorRetirar.item.setNome(raizArvore.item.getNome());
            jogadorRetirar.item.setAltura(raizArvore.item.getAltura());
            jogadorRetirar.item.setPeso(raizArvore.item.getPeso());
            jogadorRetirar.item.setUniversidade(raizArvore.item.getNome());
            jogadorRetirar.item.setAnoNascimento(raizArvore.item.getAnoNascimento());
            jogadorRetirar.item.setCidadeNascimento(raizArvore.item.getNome());
            jogadorRetirar.item.setEstadoNascimento(raizArvore.item.getNome());

            return raizArvore.esquerda;
        }
    }
    private noJogador retirar(noJogador raizArvore, String nome) {

        if (raizArvore == null) {
            System.out.println("O jogador, cuja matricula e " + nome + ", nao foi encontrado.");
            return raizArvore;
        } else {

            if (raizArvore.item.getNome().equals(nome)) {

                if (raizArvore.direita == null)
                    return (raizArvore.esquerda);
                else

                if (raizArvore.esquerda == null)
                    return (raizArvore.direita);
                else {

                    raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);

                    return (raizArvore);
                }
            } else {
                if ((raizArvore.item.getNome()).compareTo(nome) > 0)
                    raizArvore.esquerda = retirar(raizArvore.esquerda, nome);
                else
                    raizArvore.direita = retirar(raizArvore.direita, nome);

                return raizArvore;
            }
        }
    }

    public void remover(String nomeParaRemover) {
        this.raiz = retirar(this.raiz, nomeParaRemover);
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdem(raiz);
    }

    private void imprimirEmOrdem(noJogador raizArvore) {

        if (raizArvore != null) {
            imprimirEmOrdem(raizArvore.esquerda);
            System.out.print(raizArvore.item.getNome() + " | ");
            imprimirEmOrdem(raizArvore.direita);
        }

    }

    public Jogador menorId() {
        Jogador menor = null;

        if (!arvoreVazia())
            menor = pesquisarMenor(raiz).item;

        return menor;
    }

    private noJogador pesquisarMenor(noJogador raizArvore) {

        if (raizArvore != null)
            if (raizArvore.esquerda == null)
                return raizArvore;
            else
                return pesquisarMenor(raizArvore.esquerda);
        else
            return null;

    }

    public Jogador buscar(String nomePesquisado) {
        Jogador pesquisado;

        noJogador resultado = pesquisar(raiz, nomePesquisado);

        if (resultado == null)
            pesquisado = null;
        else
            pesquisado = resultado.item;

        return pesquisado;
    }

    private noJogador pesquisar(noJogador raizArvore, String nomePesquisado) {

        noJogador pesquisado;
        this.comparacoes++;

        if (raizArvore == null)
            pesquisado = null;
        else {
            if (raizArvore.item.getNome().equals(nomePesquisado)) {
                System.out.print(raizArvore.item.getNome() + " ");
                pesquisado = raizArvore;
            } else if ((raizArvore.item.getNome()).compareTo(nomePesquisado) > 0) {
                System.out.print(raizArvore.item.getNome() + " ");
                pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
            } else {
                System.out.print(raizArvore.item.getNome() + " ");
                pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
            }
        }

        return pesquisado;
    }

    public int numJogadores() {
        return contaNumeroJogadores(raiz);
    }

    private int contaNumeroJogadores(noJogador raizArvore) {

        if (raizArvore == null)
            return 0;
        else
            return 1 + contaNumeroJogadores(raizArvore.esquerda) + contaNumeroJogadores(raizArvore.direita);
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
        } catch (IOException ignored) {

        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        } catch (IOException ignored) {

        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
            saida.newLine();
        } catch (IOException ignored) {

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

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        String id;

        int qtdJogadores = qtdLinhas(leitura);

        Jogador[] jogadores = preencherVetorJogador(leitura, qtdJogadores);

        ABB arvore = new ABB();

        do {
            id = in.readLine();

            if (!(id.equals("FIM"))) {

                arvore.inserir(jogadores[Integer.parseInt(id)]);

            }

        } while (!(id.equals("FIM")));

        String nome;
        long inicio = System.currentTimeMillis();
        do {
            nome = in.readLine();

            if (!(nome.equals("FIM"))) {

                if (arvore.buscar(nome) == null)
                    System.out.print("NAO\n");
                else
                    System.out.print("SIM\n");

            }

        } while (!(nome.equals("FIM")));

        long fim = System.currentTimeMillis();
        int comparacoes = arvore.getComparacoes();
        gerarLog(inicio, fim, comparacoes);

    }

    public static void gerarLog(long inicio, long fim, int comparacoes) {
        long mili = fim - inicio;

        ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
        String log = new String("750376,689811,763343\t" + mili + "\t" + comparacoes);

        escrita.abrirArquivo("matricula_arvoreBinaria.txt");
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
