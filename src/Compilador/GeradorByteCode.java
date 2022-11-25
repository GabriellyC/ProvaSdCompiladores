package Compilador;

import java.util.ArrayList;
import java.util.List;

public class GeradorByteCode {

	public List<String> byteCode;
	public List<String> fonte;
	public Boolean jaExisteScanner;

	public GeradorByteCode(List<String> fonte) {
		this.fonte = fonte;
	}

	public Boolean gerar(String nomeArquivo) {
		String nomeClasse;
		System.out.println(nomeArquivo);
		nomeClasse = limparNome(nomeArquivo);
		jaExisteScanner = false;
		byteCode = new ArrayList<>();
		for (String linha : fonte) {
			// System.out.println(linha);
			gerarPrograma(linha, nomeClasse);
			gerarfimPrograma(linha);
			gerarVariavel(linha);
			gerarVerifique(linha);
			gerarOperacaoAritmetica(linha);
			gerarApresente(linha);
			gerarConfere(linha);
			gerarSenao(linha);
			gerarFimConfere(linha);
			gerarPara(linha);
			gerarFimPara(linha);
			gerarNovaLinha(linha);

		}
		for (String linha : byteCode) {
			System.out.println(linha);
		}
		gravar(nomeArquivo, byteCode);
		return true;
	}

	public void gerarPrograma(String linha, String nome) {
		if (!linha.equals("programa"))
			return;
		byteCode.add("import java.util.Scanner;");
		byteCode.add("public class " + nome + " {");
		byteCode.add("public static void main(String[] args){");

	}

	public void gerarfimPrograma(String linha) {
		if (!linha.equals("fimprograma"))
			return;
		byteCode.add("}");
		byteCode.add("}");
	}

	public void gerarVariavel(String linha) {
		if (!linha.startsWith("variavel"))
			return;
		byteCode.add(linha.replace("variavel", "int") + ";");
	}

	public void gerarVerifique(String linha) {
		if (!linha.startsWith("verifique"))
			return;
		if (!jaExisteScanner) {
			byteCode.add("Scanner teclado = new Scanner(System.in);");
			jaExisteScanner = true;
		}
		byteCode.add(linha.replace("verifique", "") + "= teclado.nextInt();");
	}

	public void gerarOperacaoAritmetica(String linha) {
		if (linha.contains("+") || linha.contains("-") || linha.contains("*") || linha.contains("/")
				|| linha.contains("<-")) {
			linha = linha.replace("<-", "=");
			byteCode.add(linha + ";");
		}
	}

	public void gerarApresente(String linha) {
		if (!linha.startsWith("apresente"))
			return;
		linha = linha.replace("'", "\"");
		byteCode.add(linha.replace("apresente", "System.out.print(") + ");");
	}

	public void gerarConfere(String linha) {
		if (!linha.startsWith("confere"))
			return;
	
		linha = linha.replace("confere", "if (");
		linha = linha.replace("execute", ") {");
		byteCode.add(linha);
	}

	public void gerarSenao(String linha) {
		if (!linha.startsWith("senao"))
			return;
		linha = linha.replace("senao", "} else {");
		byteCode.add(linha);
	}

	public void gerarFimConfere(String linha) {
		if (!linha.startsWith("fimconfere"))
			return;

		linha = linha.replace("fimconfere", "}");
		byteCode.add(linha);
	}

	public void gerarPara(String linha) {
		if (!linha.startsWith("para"))
			return;
		String[] token = linha.split(" ");

		String novaLinha = "for(" + token[5] + "=" + token[1] + ";" + token[5] + "<=" + token[3] + ";" + token[5] + "++"
				+ "){";

		byteCode.add(novaLinha);

	}

	public void gerarFimPara(String linha) {
		if (!linha.startsWith("fimpara"))
			return;
		linha = linha.replace("fimpara", "}");
		byteCode.add(linha);
	}

	public void gerarNovaLinha(String linha) {
		if (!linha.startsWith("novalinha"))
			return;
		linha = linha.replace("novalinha", "System.out.println();");
		byteCode.add(linha);
	}

	public String limparNome(String nome) {
		nome = nome.replace("\\", " ");
		nome = nome.replace(".txt", "");
		String[] token = nome.split(" ");
		String novoNome = token[token.length - 1];
		novoNome = novoNome.substring(0, 1).toUpperCase() + novoNome.substring(1).toLowerCase();
		// novoNome.substring(1, novoNome.length()-1);
		System.out.println(novoNome);
		return novoNome;
	}

	public void gravar(String nome, List<String> byteCode) {
		LeitorArquivo gravador = new LeitorArquivo();
		gravador.gravarArquivo(nome, byteCode);
	}

}
