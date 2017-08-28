package br.com.gerador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gerar {
	String data;
	int i;
	int nitem;
	int agente = 0;
	int loja = 1;
	int lojas = Main.getLojas();
	long inicio = Main.getIniciar() + Main.getQuantidade();
	int agenteInicial = Main.getAgenteInicial();
	BufferedReader arquivo;
	private String caminho = "";
	File file;
	StringBuilder relatorio = new StringBuilder();
	StringBuilder itens;
	Data data1 = new Data();
	int maxLojas = 0;
	int agenteLoja = 0;
	public void Txt(File arquivos, int k) throws IOException, InterruptedException {
		this.data = this.data1.getDateFromTimeapi_org(Main.getFuso());
		this.relatorio = new StringBuilder();
		try {
			this.file = arquivos;
			this.caminho = ("" + this.file);
			this.arquivo = new BufferedReader(new FileReader(this.caminho));

			while (this.arquivo.ready()) {
				String linha = this.arquivo.readLine();
				this.itens = new StringBuilder();
				if (linha.contains("{$num}")) {
					linha = linha.replace("{$num}", ""+k);
				}
				if (linha.contains("{$date}")) {
					linha = linha.replace("{$date}", this.data);
				}
				if (linha.contains("{$serie}")) {
					linha = linha.replace("{$serie}", ""+Main.getSerie());
				}
				if (linha.contains("{$cnpj}")) {
					linha = linha.replace("{$cnpj}", Main.getCnpj());
				}
				if (linha.contains("{$cancSat}")) {
					File canc = new File(Main.getCancSat());
					File[] txt = canc.listFiles(new FileFilter() {
						public boolean accept(File pathname) {
							return pathname.getName().toLowerCase().endsWith("");
						}
					});
					for (int d = 0; d < txt.length; d++) {
						String[] texto = txt[d].getName().split("#");
						linha = linha.replace("{$cancSat}", texto[0]);
					}
				}
				this.nitem = 1;
				if (linha.contains("{$item}")) {
					while (linha.contains("{$item}")) {
						linha = linha.replace("{$item}", "");
						this.itens.append(linha);
						this.itens.append("\n");
						linha = this.arquivo.readLine();
					}
					this.i = 1;
					while (this.i <= Main.getItens()) {
						this.relatorio.append(this.itens);
						if (linha.contains("{$nitem}")) {
							linha = linha.replace("{$nitem}",
									String.format("%03d", new Object[] { Integer.valueOf(this.nitem) }));
							System.out.println(this.nitem);
						}
						System.out.println(this.nitem);
						this.nitem += 1;
						this.i += 1;
					}
					this.relatorio.append(linha);
					this.relatorio.append("\n");
				} else {
					this.relatorio.append(linha);
					this.relatorio.append("\n");
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String[] palavra = this.file.getName().split("#");
			String[] ext = palavra[1].split("_");
			File f = null;
			if (this.agente >= Main.getAgentes()) {
				this.agente = 1;
			} else
				this.agente += 1;				
			if (Main.getAgentes() > 1) {
				String[] nomeAgente = palavra[0].split("-");
				if(lojas > 1){				
					maxLojas ++;
					agenteLoja ++;
					nomeAgente[3] = "" + agenteLoja;
					f = new File(Main.getDestino() + "_" + this.agente + System.getProperty("file.separator") + "in"
							+ System.getProperty("file.separator") + nomeAgente[0]
							+ "" + (Integer.parseInt(nomeAgente[1]) * loja)
							+ "" + nomeAgente[2]							
							+ String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(nomeAgente[3])) }) + "#"
							+ k + "_" + ext[1] + "_" + ext[2]);	
					if(Main.getAgentes()/lojas * loja == maxLojas){
						loja ++;
						agenteLoja = 0;
						if(loja > lojas){
							loja = 1;
							maxLojas = 0;
						}
					}				
				}
				else{				
					nomeAgente[1] = "" + this.agente;
					f = new File(Main.getDestino() + "_" + this.agente + System.getProperty("file.separator") + "in"
							+ System.getProperty("file.separator") + nomeAgente[0]
							+ String.format("%03d", new Object[] { Integer.valueOf(Integer.parseInt(nomeAgente[1])) }) + "#"
							+ k + "_" + ext[1] + "_" + ext[2]);
				}
			} else {
				f = new File(Main.getDestino() + System.getProperty("file.separator") + "in"
						+ System.getProperty("file.separator") + palavra[0] + "#" + k + "_" + ext[1] + "_" + ext[2]);
			}
			System.out.println(f);
			f.createNewFile();
			FileWriter fos = new FileWriter(f);
			fos.write(String.valueOf(this.relatorio));
			fos.close();
			System.out.println(String.valueOf(this.relatorio));
			System.out.println("NOTA: " + k);
			long seconds = (this.inicio - k) * Main.getSleep() / 1000;
			Time.getTime(seconds);
			Main.setIniciar(k);
			Thread.sleep(Main.getSleep());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}