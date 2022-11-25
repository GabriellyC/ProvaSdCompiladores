package Compilador;

import java.util.ArrayList;
import java.util.List;

public class Compilar {

	public List<String> programa;
	public List<String> alfabeto;
	public List<String> dicionario;
	public List<String> gramatica;

	public Compilar() {
		programa = new ArrayList();
		alfabeto = new ArrayList();
		dicionario = new ArrayList();
		gramatica = new ArrayList();
	}

	public void executar(String fonte) {
		LeitorArquivo leitor = new LeitorArquivo();
		try {

			String currentPath = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + currentPath);

			this.programa = leitor.lerArquivo(fonte);
			alfabeto = leitor.lerArquivo("arquivos\\alfabeto.txt");
			dicionario = leitor.lerArquivo("arquivos\\dicionario.txt");
			gramatica = leitor.lerArquivo("arquivos\\gramatica.txt");
			// leitor.Imprimir(alfabeto);

			AnalisadorLexico lexico = new AnalisadorLexico();
			AnalisadorSintatico sintatico = new AnalisadorSintatico();
			AnalisadorSemantico semantico = new AnalisadorSemantico();
			if (lexico.validar(programa, alfabeto)) {
				System.out.println("\nAnalise Sintatica...");
				if (sintatico.validar(programa, gramatica)) {
					System.out.println("Analise Sintatica OK");
					// comecar a analise sem�ntica
					if (semantico.validar(programa, dicionario)) {
						System.out.println("Analise Semantica OK");
						GeradorByteCode gerador = new GeradorByteCode(programa);

						gerador.gerar(fonte);
					}
				} else {
					System.out.println("Erro na analise Sintatica");
				}
			} else {
				System.out.println("\nExistem erros na analise Lexica...");
				return;
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}

//gabrielly colman