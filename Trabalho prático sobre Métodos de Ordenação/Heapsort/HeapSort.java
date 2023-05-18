import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
		System.out.printf("[%d ## ", this.id);
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
			System.out.printf("nao informado]\n");
		} else {
			System.out.printf("%s]\n", this.estadoNascimento);
		}

	}
}

public class HeapSort {

	public static void main(String[] args) throws IOException {
		int comparacoes;
		int trocas;

		long inicio = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String entrada = new String();

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura);
		int i = 0;
		int qtdJogadoresVetor;

		Jogador[] Jog = preencherVetorJogador(leitura, qtdJogadores);
		Jogador[] vetor = new Jogador[qtdJogadores];

		do {
			entrada = in.readLine();

			if (!(entrada.equals("FIM"))) {

				vetor[i] = new Jogador();
				vetor[i] = Jog[Integer.parseInt(entrada)].clone();
				i++;

			}
		} while (!(entrada.equals("FIM")));

		qtdJogadoresVetor = i;

		Heap ordenar = new Heap();

		vetor = ordenar.sort(vetor, qtdJogadoresVetor);

		for (i = 0; i < qtdJogadoresVetor; i++) {
			vetor[i].imprimir();
		}

		comparacoes = ordenar.getComparacoes();
		trocas = ordenar.getTrocas();

		long fim = System.currentTimeMillis();
		gerarLog(inicio, fim, comparacoes, trocas);

	}

	public static void gerarLog(long inicio, long fim, int comparacoes, int trocas) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("689811,750376,763343\t" + mili + "\t" + comparacoes + "\t" + trocas);

		escrita.abrirArquivo("matricula_heapsort.txt");
		escrita.escrever(log);
		escrita.fecharArquivo();
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

	public static Jogador[] preencherVetorJogador(ArquivoTextoLeitura leitura, int qtdLinhas) {
		Jogador[] Jog = new Jogador[qtdLinhas];

		for (int i = 0; i < qtdLinhas; i++)
			Jog[i] = new Jogador();

		leitura.abrirArquivo("/tmp/jogadores.txt");

		leitura.ler();
		for (int i = 0; i < qtdLinhas; i++) {

			String[] linha = leitura.ler().split(",", 8);

			Jog[i].setId(Integer.parseInt((linha[0].toString()))); 

			Jog[i].setNome(linha[1].toString());
			Jog[i].setAltura(Integer.parseInt((linha[2].toString())));
			Jog[i].setPeso(Integer.parseInt((linha[3].toString())));
			Jog[i].setUniversidade(linha[4].toString());
			Jog[i].setAnoNascimento(Integer.parseInt((linha[5].toString())));
			Jog[i].setCidadeNascimento(linha[6].toString());
			Jog[i].setEstadoNascimento(linha[7].toString());

		}

		leitura.fecharArquivo();

		return Jog;
	}

}

class Heap {
	private int comparacoes = 0;
	private int trocas = 0;

	public Jogador[] sort(Jogador[] array, int n) {
		return method(array, n);
	}

	private Jogador[] method(Jogador[] array, int n) {

		Jogador[] tmp = new Jogador[n + 1];
		for (int i = 0; i < n; i++) {
			tmp[i + 1] = array[i];
		}
		array = tmp;

		for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
			constroi(array, tamHeap);
		}

		int tamHeap = n;
		while (tamHeap > 1) {
			troca(array, 1, tamHeap--);
			reconstroi(array, tamHeap);
		}

		tmp = array;
		array = new Jogador[n];
		for (int i = 0; i < n; i++) {
			array[i] = tmp[i + 1];
		}

		return array;
	}

	private void constroi(Jogador[] array, int tamHeap) {

		for (int i = tamHeap; i > 1 && (array[i].getAltura() > array[i / 2].getAltura()
				|| (array[i].getNome().compareTo(array[i / 2].getNome()) > 0
						&& array[i].getAltura() == array[i / 2].getAltura())); i /= 2) {
			troca(array, i, i / 2);
			this.comparacoes++;
		}
	}

	private void reconstroi(Jogador[] array, int tamHeap) {

		int i = 1;

		while (i <= (tamHeap / 2)) {
			int filho = getMaiorFilho(array, i, tamHeap);
			if (array[i].getAltura() < array[filho].getAltura()
					|| ((array[i].getNome().compareTo(array[filho].getNome()) < 0
							&& array[i].getAltura() == array[filho].getAltura()))) {
				troca(array, i, filho);
				i = filho;
			} else {
				i = tamHeap;
			}
			this.comparacoes++;
		}
	}

	private int getMaiorFilho(Jogador[] array, int i, int tamHeap) {
		int filho;

		if ((2 * i == tamHeap || array[2 * i].getAltura() > array[2 * i + 1].getAltura())
				|| (((array[2 * i].getNome().compareTo(array[2 * i + 1].getNome()) > 0
						&& array[2 * i].getAltura() == array[2 * i + 1].getAltura())))) {
			filho = 2 * i;
		} else {
			filho = 2 * i + 1;
		}
		this.comparacoes++;
		return filho;
	}

	private void troca(Jogador[] array, int i, int j) {
		Jogador aux;

		aux = array[i];
		array[i] = array[j];
		array[j] = aux;
		this.trocas++;
	}

	public int getComparacoes() {
		return this.comparacoes;
	}

	public int getTrocas() {
		return this.trocas;
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
		} catch (EOFException excecao) { // Exceção de final de arquivo.
			return null;
		} catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
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