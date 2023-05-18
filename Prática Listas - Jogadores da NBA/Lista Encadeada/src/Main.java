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

    public void imprimir() {

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

class Celula {
    Jogador item;
    Celula proximo;

    Celula(){
        item = new Jogador();
        proximo = null;
    }
}

class Lista {

    private int tamanho;
    private Celula primeiro;
    private Celula ultimo;

    Lista (){
        primeiro = new Celula();
        ultimo = primeiro;
        tamanho=0;
    }

    public void inserirInicio (Jogador player){
        Celula aux = primeiro.proximo;
        primeiro.proximo = new Celula();
        primeiro.proximo.proximo = aux;

        primeiro.proximo.item = player.clone();

        tamanho++;
    }

    public void inserir (Jogador player, int posicao){

        if (posicao == 1)
            inserirInicio(player);

        else if (posicao == tamanho)
            inserirFim(player);

        else{
            Celula find = primeiro;

            for (int i=0 ; i<posicao; i++){
                find = find.proximo;
            }

            Celula aux = find.proximo;
            find.proximo = new Celula();
            find.proximo.proximo = aux;

            find.proximo.item = player.clone();

            tamanho++;
        }
    }

    public void inserirFim (Jogador player){
        ultimo.proximo = new Celula();
        ultimo = ultimo.proximo;

        ultimo.item = player.clone();

        tamanho++;
    }

    public Jogador removerInicio(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = primeiro.proximo.item.clone();

        primeiro.proximo = primeiro.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador remover(int posicao){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        if (posicao == 1)
            return removerInicio();

        else if (posicao == tamanho+1)
            return removerFim();


        Celula find = primeiro;
        for (int i=0;i<posicao;i++){
            find = find.proximo;
        }

        Jogador toReturn = new Jogador();
        toReturn = find.proximo.item.clone();

        find.proximo = find.proximo.proximo;

        tamanho--;

        return toReturn;
    }

    public Jogador removerFim(){
        if (primeiro == ultimo){
            System.out.println("Fail to remove: The list is empty");
            return null;
        }

        Jogador toReturn = new Jogador();
        toReturn = ultimo.item.clone();

        Celula find = primeiro;
        for (int i=0;i<tamanho-1;i++){
            find = find.proximo;
        }

        ultimo = find;
        ultimo.proximo = null;

        tamanho--;

        return toReturn;
    }

    public void mostrar(){
        Celula atual = primeiro.proximo;
        for (int i=0 ; i<tamanho ; i++ , atual = atual.proximo){
            System.out.printf("[%d] ", i);
            atual.item.imprimir();
        }
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
    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        int jogadorQtd = qtdLinhas(leitura);

        Jogador allPlayers[] = preencherJogadores(leitura, jogadorQtd);
        Lista lista = new Lista();
        int id;

        String read;
        do{
            read = in.readLine();

            if (!read.equals("FIM")){
                id = Integer.parseInt(read);
                lista.inserirFim(allPlayers[id]);
            }

        }while(!read.equals("FIM"));

        int qtdCmd = Integer.parseInt(in.readLine());
        String split[] = new String[3];
        int tolistUpId;
        int position=1;
        for (int i=0;i<3;i++)
            split[i] = new String();

        String cmd = new String();
        for (int i=0;i<qtdCmd;i++){

            cmd = in.readLine();

            if (cmd.charAt(0) == 'I'){

                split = cmd.split(" ");
                if ( split.length == 2 )
                    tolistUpId = Integer.parseInt(split[1]);
                else{
                    tolistUpId = Integer.parseInt(split[2]);
                    position = Integer.parseInt(split[1]);
                }


                if (cmd.charAt(1) == 'I')
                    lista.inserirInicio(allPlayers[tolistUpId]);

                else if (cmd.charAt(1) == 'F')
                    lista.inserirFim(allPlayers[tolistUpId]);

                else if (cmd.charAt(1) == '*')
                    lista.inserir(allPlayers[tolistUpId], position);

            }
            else if (cmd.charAt(0) == 'R'){

                if (cmd.charAt(1) == 'I')
                    System.out.println("(R) " + lista.removerInicio().getNome());

                else if (cmd.charAt(1) == 'F')
                    System.out.println("(R) " + lista.removerFim().getNome());

                else if (cmd.charAt(1) == '*'){
                    position = Integer.parseInt(cmd.substring(3, cmd.length()));
                    System.out.println("(R) " + lista.remover(position).getNome());
                }

            }

        }
        lista.mostrar();
    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) {
        int qtd = 0;
        String linhaLida = new String(); // String para receber a linha lida do arquivo
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

    public static Jogador[] preencherJogadores(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
        Jogador jogadorAtual = new Jogador();
        Jogador listaJogadores[] = new Jogador[qtdJogadores];

        leitura.abrirArquivo("/tmp/jogadores.txt"); // Abre o arquivo

        leitura.ler(); // Remove o cabecalho
        for (int i = 0; i < qtdJogadores; i++) {

            String[] dadosL = leitura.ler().split(",", 8); // Le os dados do jogador atual

            jogadorAtual.setId(Integer.parseInt((dadosL[0].toString())));
            jogadorAtual.setNome(dadosL[1].toString());
            jogadorAtual.setAltura(Integer.parseInt((dadosL[2].toString())));
            jogadorAtual.setPeso(Integer.parseInt((dadosL[3].toString())));
            jogadorAtual.setUniversidade(dadosL[4].toString());
            jogadorAtual.setAnoNascimento(Integer.parseInt((dadosL[5].toString())));
            jogadorAtual.setCidadeNascimento(dadosL[6].toString());
            jogadorAtual.setEstadoNascimento(dadosL[7].toString());
            listaJogadores[i] = jogadorAtual.clone();

            jogadorAtual = new Jogador();
        }
        leitura.fecharArquivo();
        return listaJogadores;
    }
}