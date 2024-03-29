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

    public void imprimir() { // imprimir os dados dos jogadores

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

class Pilha {
    private int tamanho;
    private int topo;
    private Jogador jogadors[];

    Pilha (){
        topo = 0;
        tamanho = 4000;
        jogadors = new Jogador[tamanho];
        topo = -1;
    }
    Pilha (int size){
        topo = 0;
        tamanho = size;
        jogadors = new Jogador[tamanho];
        topo = -1;
    }

    public void empilhar (Jogador player) throws CloneNotSupportedException { //empilhar
        topo++;
        if (topo == tamanho){
            System.out.println("Fail to stack up: Stack is already full");
            topo--;
        }
        else{
            jogadors[topo] = player.clone();
        }
    }

    public Jogador desemplihar() throws CloneNotSupportedException { //desempilhar
        if (topo == -1){
            System.out.println("Fail to unstack: Stack is empty");
            return null;
        }
        Jogador aux = new Jogador();
        aux = jogadors[topo].clone();
        jogadors[topo] = null;
        topo--;
        return aux;
    }

    public void mostrar(){ //printar
        for (int i=0;i<topo+1;i++){
            System.out.printf("[%d] ", i);
            jogadors[i].imprimir();
        }
    }

}

class ArquivoTextoLeitura {

    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo) { // abre arquivo texto para leitura

        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public void fecharArquivo() { // fecha o arquivo de entrada

        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    public String ler() { // retorna uma linha do arquivo

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
    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        int qtdJogadores = qtdLinhas(leitura);

        Jogador allPlayers[] = criaPlayer(leitura, qtdJogadores);
        Pilha stack = new Pilha(qtdJogadores);
        int id;

        String read; //variavel para ler o que o usuario digita
        do{
            read = in.readLine();

            if (!read.equals("FIM")){
                id = Integer.parseInt(read);
                stack.empilhar(allPlayers[id]);
            }

        }while(!read.equals("FIM"));

        int qtdcomandos = Integer.parseInt(in.readLine());
        int toStackUpId;

        String comando = new String();
        for (int i=0;i<qtdcomandos;i++){

            comando = in.readLine();

            if (comando.charAt(0) == 'I'){

                toStackUpId = Integer.parseInt(comando.substring(2, comando.length()));
                stack.empilhar(allPlayers[toStackUpId]);
            }
            else if (comando.charAt(0) == 'R')
                System.out.println("(R) " + stack.desemplihar().getNome());

        }

        stack.mostrar();

    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) { //conta a quantidade de linhas do arquivo
        int qtd = 0;
        String linhaL = new String();
        leitura.abrirArquivo("/tmp/jogadores.txt");

        leitura.ler();
        linhaL = leitura.ler();
        while (linhaL != null) { //enquanto não chegar no fim do arquivo
            qtd++;
            linhaL = leitura.ler();
        }

        leitura.fecharArquivo();

        return qtd;
    }

    public static Jogador[] criaPlayer(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
        Jogador jogadores = new Jogador();
        Jogador listaJogadores[] = new Jogador[qtdJogadores]; //lista de todos os jogadores

        leitura.abrirArquivo("/tmp/jogadores.txt");//abre o arquivo

        leitura.ler();
        for (int i = 0; i < qtdJogadores; i++) {

            String[] dadosDaLinha = leitura.ler().split(",", 8); //8 é a quantidade de atributos que cada jogador tem


            jogadores.setId(Integer.parseInt((dadosDaLinha[0].toString())));
            jogadores.setNome(dadosDaLinha[1].toString());
            jogadores.setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
            jogadores.setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
            jogadores.setUniversidade(dadosDaLinha[4].toString());
            jogadores.setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
            jogadores.setCidadeNascimento(dadosDaLinha[6].toString());
            jogadores.setEstadoNascimento(dadosDaLinha[7].toString());

            listaJogadores[i] = jogadores.clone();
            jogadores = new Jogador();
        }

        leitura.fecharArquivo();

        return listaJogadores;
    }
}
