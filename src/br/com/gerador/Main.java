package br.com.gerador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Main {
	private static String origem;
	private static String destino;
	private static String sleep;
	private static String quantidade;
	private static String iniciar;
	private static String fuso;
	private static String serie;
	private static String itens;
	private static String cancSat;
	private static String pedPrint;
	private static String reprint;
	private static String cnpj;
	private static String agentes;
	private static String agenteInicial;
	private static String lojas;
	private static Properties prop;
	
	public static int getLojas() {
		int loja = Integer.parseInt(lojas);
		if(loja == 0)
			loja = 1;
		return loja;
	}
	public static int getAgenteInicial() {
		int agente = Integer.parseInt(agenteInicial);
		if(agente == 0)
			agente = 1;
		return agente;
	}

	public static int getAgentes() {
		return Integer.parseInt(agentes);
	}

	public static int getPedPrint() {
		return Integer.parseInt(pedPrint);
	}

	public static String getCnpj() {
		return cnpj;
	}

	public static String getReprint() {
		return reprint;
	}

	public static String getOrigem() {
		return origem;
	}

	public static String getCancSat() {
		return cancSat;
	}

	public static int getItens() {
		int val = 0;
		val = Integer.parseInt(itens);
		Random gerador = new Random();
		if (val < 1) {
			val = 1 + gerador.nextInt(49);
		}
		return val;
	}

	public static String getDestino() {
		return destino;
	}

	public static int getSleep() {
		return Integer.parseInt(sleep);
	}

	public static int getQuantidade() {
		return Integer.parseInt(quantidade);
	}

	public static int getIniciar() {
		return Integer.parseInt(iniciar);
	}

	public static String getFuso() {
		return fuso;
	}

	public static int getSerie() {
		int s = 0;
		Random gerador = new Random();
		s = Integer.parseInt(serie);
		if (s < 1) {
			s = 1 + gerador.nextInt(998);
			serie = String.valueOf(s);
		}
		return s;
	}

	private static Properties getProp() throws IOException {
		Properties props = new LinkedProperties();
		props.load(FileInput());
		return props;
	}

	private static File FileProperties() {
		File file = new File("." + System.getProperty("file.separator") + "properties"
				+ System.getProperty("file.separator") + "config.properties");
		return file;
	}

	private static FileInputStream FileInput() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(FileProperties());
		return file;
	}

	public static void main(String[] args) {
		try {
			prop = getProp();
			origem = prop.getProperty("origem");
			destino = prop.getProperty("destino");
			cancSat = prop.getProperty("cancSat");
			sleep = prop.getProperty("sleep");
			quantidade = prop.getProperty("quantidade");
			iniciar = prop.getProperty("iniciar");
			fuso = prop.getProperty("fuso");
			serie = prop.getProperty("serie");
			itens = prop.getProperty("itens");
			cnpj = prop.getProperty("cnpj");
			pedPrint = prop.getProperty("pedPrint");
			reprint = prop.getProperty("reprint");
			agentes = prop.getProperty("agentes");
			lojas = prop.getProperty("lojas");
			agenteInicial = prop.getProperty("agenteInicial");
			
			Thread t = new Thread(new Processador());
			t.start();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo de configuracao: ");
			e.printStackTrace();
		}
	}



	public static void setIniciar(int valor) throws IOException {
		int serie = 0;
		serie = getSerie();
		if (valor > 9999999) {
			valor = 0;
			serie = getSerie() + 1;
		}
		Properties properties = new LinkedProperties();
		try {
			properties.load(FileInput());
		} catch (IOException e) {
			e.printStackTrace();
		}

		properties.put("iniciar", "" + (valor + 1));
		properties.put("serie", "" + serie);
		try {
			FileOutputStream fos = new FileOutputStream(FileProperties());
			properties.store(fos, null);
			fos.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
