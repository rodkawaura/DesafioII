package DesafioII;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner read = new Scanner(System.in);

		System.out.println("Digite o caminho do arquivo .txt a ser validado:");
		String txtPath = read.nextLine();

		File arquivoIn = new File(txtPath);
		BufferedReader reader = new BufferedReader(new FileReader(arquivoIn));
		
		File arquivoOut = new File("check-"+txtPath);
		FileWriter writer = new FileWriter(arquivoOut);
		arquivoOut.createNewFile();

		boolean out = false;
		do {
			String linha = reader.readLine();
			if (linha != null) {
				if (validaLinha(linha)) {
					System.out.println(linha + " - válido");
					writer.write(linha + " - válido \r\n");
				} else {
					System.out.println(linha + " - inválido");
					writer.write(linha + " - inválido \r\n");
				}
			}else {
				out = true;
				writer.close();
			}
		} while (!out);

	}

	public static boolean validaLinha(String linha) {
		Stack<Character> pilha = new Stack<>();

		for (int i = 0; i < linha.length(); i++) {

			if (linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '{' || linha.charAt(i) == '<') {
				pilha.push(linha.charAt(i));
			} else {
				if (pilha.isEmpty()) {
					return false;
				} else {
					if (linha.charAt(i) == ')' && pilha.peek() == '(' || linha.charAt(i) == ']' && pilha.peek() == '['
							|| linha.charAt(i) == '}' && pilha.peek() == '{'
							|| linha.charAt(i) == '>' && pilha.peek() == '<') {
						pilha.pop();
					} else {
						return false;
					}
				}
			}
		}
		if (pilha.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
