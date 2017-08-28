package br.com.gerador;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Processador implements Runnable {
	
	public void run() {
		File diretorio = new File(Main.getOrigem());
		File[] txt = diretorio.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().toLowerCase().endsWith(".txt");
			}

		});
		Gerar arq = new Gerar();
		int k = Main.getIniciar();
		int cont = 0;
		if (txt.length == 0) {
			System.out.println("Nenhum arquivo txt encontrado");
		} else {
			while (k < Main.getIniciar() + Main.getQuantidade()) {
				cont ++;
				for (int i = 0; i < txt.length; i++) {
					try {
						arq.Txt(txt[i], k);
						if(k != 0)
							k ++;
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
