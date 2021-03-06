package br.lostpets.project.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.springframework.stereotype.Service;

import br.lostpets.project.service.ServiceGeral;

@Service
public class HistoricoAcessoLog {
	File arquivo;
	FileReader fileReader;
	BufferedReader bufferedReader;
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;

	String[][] acesso = new String[1][3];

	private ServiceGeral serviceGeral = new ServiceGeral();

	public void dataHora(String nomeUsuario) {
		acesso[0][0] = nomeUsuario;
		acesso[0][1] = serviceGeral.getDate();
		acesso[0][2] = serviceGeral.getHour();
		new HistoricoAcessoLog().escreverLog(acesso);

	}

	public void escreverLog(String historicoAcesso[][]) {

		try {
			arquivo = new File("log/historico_acesso.log");
			fileReader = new FileReader(arquivo);
			bufferedReader = new BufferedReader(fileReader);
			Vector<String> texto = new Vector<String>();
			while (bufferedReader.ready()) {
				texto.add(bufferedReader.readLine());
			}
			fileWriter = new FileWriter(arquivo);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (int l = 0; l < historicoAcesso.length; l++) {
				for (int i = 0; i < texto.size(); i++) {
					bufferedWriter.write(texto.get(i).toString() + "\t");
					bufferedWriter.newLine();
				}
				for (int c = 0; c < historicoAcesso[l].length; c++) {
					bufferedWriter.write(historicoAcesso[l][c] + "\t");
				}
			}
			bufferedReader.close();
			bufferedWriter.close();
			System.out.println("Arquivo gerado com sucesso!");
		} catch (FileNotFoundException e) {

			try {
				arquivo.createNewFile();
				escreverLog(historicoAcesso);
			} catch (IOException e1) {
				System.out.println("Deu ruim 1");
				System.exit(0);
			}
		} catch (IOException er) {
			System.out.println("Deu ruim 2");
			System.exit(0);
		}

	}
}
