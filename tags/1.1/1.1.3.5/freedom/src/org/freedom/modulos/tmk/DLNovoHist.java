/**
 * @version 23/06/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 *
 * Projeto: Freedom <BR>
 *  
 * Pacote: org.freedom.modulos.tmk <BR>
 * Classe: @(#)DLNovoHist.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR> <BR>
 *
 * Nova chamada de hist�rico de contatos.
 * 
 */

package org.freedom.modulos.tmk;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import org.freedom.componentes.JLabelPad;
import org.freedom.componentes.JPanelPad;
import javax.swing.JScrollPane;

import org.freedom.bmps.Icone;
import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JComboBoxPad;
import org.freedom.componentes.JTextAreaPad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FFDialogo;


public class DLNovoHist extends FFDialogo {
	private static final long serialVersionUID = 1L;

	private JPanelPad pnCab = new JPanelPad(JPanelPad.TP_JPANEL,new GridLayout(1,1));
	private JTextFieldPad txtDataCont = new JTextFieldPad(JTextFieldPad.TP_DATE,10,0);
	private JTextFieldPad txtCodCont = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
	private JTextFieldFK txtNomeCont = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
	private JTextFieldPad txtCodCli = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
	private JTextFieldFK txtNomeCli = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
	private JTextFieldPad txtCodAtend = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
	private JTextFieldFK txtNomeAtend = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
	private JTextAreaPad txaDescAtend = new JTextAreaPad();
	private ListaCampos lcCont = new ListaCampos(this);
	private ListaCampos lcCli = new ListaCampos(this);
	private ListaCampos lcAtend = new ListaCampos(this);
	private JScrollPane spnDesc = new JScrollPane(txaDescAtend);
	private JLabelPad lbImg = new JLabelPad(Icone.novo("bannerTMKhistorico.jpg"));
	private Vector vVals = new Vector();
	private Vector vLabs = new Vector();
	private Vector vVals1 = new Vector();
	private Vector vLabs1 = new Vector();
	private JComboBoxPad cbSit = null; 
	private JComboBoxPad cbTipo = null; 
	private String[] sValsAgen = null;
	public DLNovoHist(int iCod,int index,Component cOrig) {
		super(cOrig);
		setTitulo("Nova chamada");
		setAtribos(502,460);		
		
		vVals.addElement("");
		vVals.addElement("RJ");
		vVals.addElement("AG");
		vVals.addElement("EF");
		vLabs.addElement("<--Selecione-->");	
		vLabs.addElement("Rejeitado");		
		vLabs.addElement("Agendar liga��o/visita");
		vLabs.addElement("Efetivada");
		cbSit = new JComboBoxPad(vLabs, vVals, JComboBoxPad.TP_STRING, 3, 0);
		
		vVals1.addElement("");
		vVals1.addElement("H");
		vVals1.addElement("V");
		vVals1.addElement("N");
		vVals1.addElement("I");
		vLabs1.addElement("<--Selecione-->");	
		vLabs1.addElement("Historico");		
		vLabs1.addElement("Visita");
		vLabs1.addElement("Visita(cliente n�o cadastrado)");
		vLabs1.addElement("Indefinida");
		cbTipo = new JComboBoxPad(vLabs1, vVals1, JComboBoxPad.TP_STRING, 4, 2);
		
		lcCont.add(new GuardaCampo( txtCodCont , "CodCto", "C�d.cli", ListaCampos.DB_PK, txtNomeCont, false));
		lcCont.add(new GuardaCampo( txtNomeCont, "NomeCto", "Nome do contato", ListaCampos.DB_SI, false));
		lcCont.montaSql(false, "CONTATO", "TK");    
		lcCont.setReadOnly(true);
		txtCodCont.setTabelaExterna(lcCont);
		txtCodCont.setFK(true);
		txtCodCont.setNomeCampo("CodCto");
		
		lcCli.add(new GuardaCampo( txtCodCli, "CodCli", "C�d.Cont", ListaCampos.DB_PK, txtNomeCli, false));
		lcCli.add(new GuardaCampo( txtNomeCli, "RazCli", "Raz�o social do cliente", ListaCampos.DB_SI, false));
		lcCli.montaSql(false, "CLIENTE", "VD");    
		lcCli.setReadOnly(true);
		txtCodCli.setTabelaExterna(lcCli);
		txtCodCli.setFK(true);
		txtCodCli.setNomeCampo("CodCli");
		
		lcAtend.add(new GuardaCampo( txtCodAtend, "CodAtend", "C�d.Atend", ListaCampos.DB_PK, txtNomeAtend, true));
		lcAtend.add(new GuardaCampo( txtNomeAtend, "NomeAtend", "Nome", ListaCampos.DB_SI, false));
		lcAtend.montaSql(false, "ATENDENTE", "AT");    
		lcAtend.setReadOnly(true);
		txtCodAtend.setTabelaExterna(lcAtend);
		txtCodAtend.setFK(true);
		txtCodAtend.setNomeCampo("CodAtend");
	
	    pnCab.setPreferredSize(new Dimension(500,60));
		pnCab.add(lbImg);
	    c.add(pnCab,BorderLayout.NORTH);
	    
	    
	    if(index==0){
	    	adic(new JLabelPad("C�d. cont."),7,5,80,20);
			adic(txtCodCont,7,25,80,20);
			adic(new JLabelPad("Nome do contato"),90,5,197,20);
			adic(txtNomeCont,90,25,390,20);
			txtCodCont.setVlrInteger(new Integer(iCod));
			txtCodCont.setAtivo(false);			
		}
		else if(index==1){
			adic(new JLabelPad("C�d. cli."),7,5,80,20);
			adic(txtCodCli,7,25,80,20);
			adic(new JLabelPad("Raz�o social do cliente"),90,5,197,20);
			adic(txtNomeCli,90,25,390,20);
			txtCodCli.setVlrInteger(new Integer(iCod));
			txtCodCli.setAtivo(false);
		}
	    		
		adic(new JLabelPad("Situa��o"),7,45,150,20);
		adic(cbSit,7,65,235,20);
		adic(new JLabelPad("Tipo do contato"),245,45,150,20);
		adic(cbTipo,245,65,235,20);
		adic(new JLabelPad("C�d. atend."),7,85,80,20);
		adic(txtCodAtend,7,105,80,20);
		adic(new JLabelPad("Nome do atendente"),90,85,197,20);
		adic(txtNomeAtend,90,105,287,20);
		adic(new JLabelPad("Data"),380,85,100,20);
		adic(txtDataCont,380,105,100,20);
		
		JLabelPad lbChamada = new JLabelPad("   Chamada");
		lbChamada.setOpaque(true);
		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder(BorderFactory.createEtchedBorder());
		
		adic(lbChamada,20,130,80,20);
		adic(lbLinha,7,140,470,2);
		adic(spnDesc,7,155,470,140);
		
		txtDataCont.setRequerido(true);
		txtDataCont.setVlrDate(new java.util.Date());
		
	}
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btOK) {
			if (txtCodAtend.getVlrString().equals("")) { 
				Funcoes.mensagemInforma(this,"C�digo do atendente inv�lido!");
				return;
			}
			else if (txaDescAtend.getVlrString().equals("")) {
				Funcoes.mensagemInforma(this,"N�o foi digitado nenhum procedimento!");
				return;
			}
			else if (cbSit.getVlrString().equals("AG")) {
				DLNovoAgen dl = new DLNovoAgen(this);
				dl.setConexao(con);
				dl.setVisible(true);
				if (!dl.OK)
					return;
				sValsAgen = dl.getValores();
				dl.dispose();
			}
		}
		super.actionPerformed(evt);
	}
	public void buscaAtend() {
		String sSQL = "SELECT CODATEND FROM ATATENDENTE" +
				              " WHERE CODEMPUS=? AND CODFILIALUS=? AND IDUSU=?";
		try {
			PreparedStatement ps = con.prepareStatement(sSQL);
			ps.setInt(1,Aplicativo.iCodEmp);
			ps.setInt(2,Aplicativo.iCodFilialPad);
			ps.setString(3,Aplicativo.strUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				txtCodAtend.setVlrInteger(new Integer(rs.getInt("CodAtend")));
				lcAtend.carregaDados();
			}
			rs.close();
			ps.close();
		}
		catch (SQLException err) {
			Funcoes.mensagemErro(this,"Erro ao buscar o atendente atual!\n"+err.getMessage(),true,con,err);
			err.printStackTrace();
		}
		
    }
	public void setValores(Object[] sVal) {
		txaDescAtend.setVlrString((String)sVal[0]);
		txtCodAtend.setVlrString((String)sVal[1]);
		cbSit.setVlrString((String)sVal[2]);
		cbTipo.setVlrString((String)sVal[3]);
		txtDataCont.setVlrDate((Date)sVal[4]);
		lcAtend.carregaDados();
	}
	public Object[] getValores() {
		Object[] oVal = new Object[13];
		oVal[0] = txaDescAtend.getVlrString();
		oVal[1] = txtCodAtend.getVlrString();
		oVal[2] = cbSit.getVlrString();
		oVal[3] = cbTipo.getVlrString();
		oVal[4] = Funcoes.dateToSQLDate(txtDataCont.getVlrDate());
		if (sValsAgen != null) {
			oVal[5] = sValsAgen[0];
			oVal[6] = sValsAgen[1];
			oVal[7] = sValsAgen[2];
			oVal[8] = sValsAgen[3];
			oVal[9] = sValsAgen[4];
			oVal[10] = sValsAgen[5];
			oVal[11] = sValsAgen[6];
			oVal[12] = sValsAgen[7];
			oVal[13] = sValsAgen[8];
		}
		return oVal;
	}
	public void setConexao(Connection cn) {
		super.setConexao(cn);
		lcAtend.setConexao(cn);
		lcCont.setConexao(cn);
		lcCont.carregaDados();
		lcCli.setConexao(cn);
		lcCli.carregaDados();
		buscaAtend();
	}
}