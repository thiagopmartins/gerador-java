/*    */ package br.com.gerador;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.StringReader;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ import org.xml.sax.InputSource;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ public class ChaveNFCe
/*    */ {
/*    */   private String chave;
/*    */   private int[] digitos;
/* 19 */   private StringBuilder key = new StringBuilder();
/*    */   
/*    */   public String gerarChave(String file) throws java.io.IOException, ParserConfigurationException, SAXException {
/* 22 */     DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 23 */     InputSource is = new InputSource();
/* 24 */     is.setCharacterStream(new StringReader(file));
/* 25 */     Document doc = db.parse(is);
/* 26 */     NodeList nodes = doc.getElementsByTagName("infNFe");
/* 27 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 28 */       this.digitos = new int[42];
/* 29 */       this.key = new StringBuilder();
/* 30 */       Element e = (Element)nodes.item(i);
/* 31 */       NodeList nfce = e.getElementsByTagName("nNF");
/* 32 */       NodeList serie = e.getElementsByTagName("serie");
/* 33 */       NodeList uf = e.getElementsByTagName("cUF");
/* 34 */       NodeList data = e.getElementsByTagName("dhEmi");
/* 35 */       NodeList cnpj = e.getElementsByTagName("CNPJ");
/* 36 */       NodeList mod = e.getElementsByTagName("mod");
/* 37 */       this.key.append(uf.item(0).getTextContent());
/* 38 */       this.key.append(data.item(0).getTextContent().substring(2, 4));
/* 39 */       this.key.append(data.item(0).getTextContent().substring(5, 7));
/* 40 */       this.key.append(cnpj.item(0).getTextContent());
/* 41 */       this.key.append(mod.item(0).getTextContent());
/*    */       
/*    */ 
/* 44 */       this.key.append(serie.item(0).getTextContent());
/* 45 */       this.key.append(nfce.item(0).getTextContent());
/* 46 */       System.out.println(String.valueOf(this.key));
/*    */     }
/* 48 */     return this.chave;
/*    */   }
/*    */ }


/* Location:              C:\Users\thiago.martins\Desktop\gerador-linux.jar!\br\com\gerador\ChaveNFCe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */