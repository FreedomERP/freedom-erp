/**
 * @version 28/09/2011 <BR>
 * @author Setpoint Inform�tica Ltda. / Bruno Nascimento<BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.tmk <BR>
 *         Classe: @(#)FRCronograma.java <BR>
 * 
 *         Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *         modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *         na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *         Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *         sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *         Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *         Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *         de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *         Relat�rio Cronograma Sint�tico.
 * 
 */

package org.freedom.modulos.crm.view.frame.report;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperPrintManager;

import org.freedom.acao.CarregaEvent;
import org.freedom.acao.CarregaListener;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JRadioGroup;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.library.swing.frame.FPrinterJob;
import org.freedom.library.swing.frame.FRelatorio;
import org.freedom.modulos.crm.view.frame.crud.detail.FContrato;
import org.freedom.modulos.std.view.frame.crud.tabbed.FCliente;

public class FRCronograma extends FRelatorio implements CarregaListener{

	private static final long serialVersionUID = 1L;
	
	private JTextFieldPad txtDataini = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );
	
	private JTextFieldPad txtCodCli = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );
	
	private JTextFieldFK txtCodCli2 = new JTextFieldFK( JTextFieldFK.TP_INTEGER, 8, 0 );
	
	private JTextFieldFK txtRazCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldPad txtStatus = new JTextFieldPad( JTextFieldPad.TP_STRING, 100, 0 );
	
	private JTextFieldPad txtCodContr = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );
	
	private JTextFieldFK txtDescContr = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );
	
	private JTextFieldFK txtContHSubContr = new JTextFieldFK( JTextFieldFK.TP_STRING, 2, 0 );
	
	private Vector<String> vLabsSaldo = new Vector<String>();
	
	private Vector<String> vValsSaldo = new Vector<String>();
	
	private JRadioGroup<String, String> rgSaldoHoras = null;

	private ListaCampos lcCli = new ListaCampos( this );
	
	private ListaCampos lcContr = new ListaCampos( this );
	
	public FRCronograma() {		
		setTitulo( "Cronograma Sint�tico" );
		setAtribos( 80, 80, 410	, 230 );
		
		montaListaCampos();
		montaTela();
		
	}
	
	private void montaTela(){
		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder( BorderFactory.createEtchedBorder() );
		JLabelPad lbPeriodo = new JLabelPad( "Per�odo:", SwingConstants.CENTER );
		lbPeriodo.setOpaque( true );

		adic( lbPeriodo, 7, 1, 80, 20 );
		adic( lbLinha, 5, 10, 300, 45 );
		
		adic( new JLabelPad( "De:" ), 15, 25, 25, 20 );
		adic( txtDataini, 42, 25, 95, 20 );
		adic( new JLabelPad( "At�:" ), 148, 25, 25, 20 );
		adic( txtDatafim, 178, 25, 95, 20 );
		
		adic( txtCodCli, 7, 80, 80, 20, "Cod.Cli" );
		adic( txtRazCli, 90, 80, 225, 20, "Raz�o social do cliente" );
		adic( txtCodContr, 7, 120, 80, 20, "Cod.Contr");
		adic( txtDescContr, 90, 120, 225, 20, "Descri��o do Contrato" );
		
		Calendar cPeriodo = Calendar.getInstance();
		txtDatafim.setVlrDate( cPeriodo.getTime() );
	
		cPeriodo.set( Calendar.DAY_OF_MONTH, cPeriodo.get( Calendar.DAY_OF_MONTH ) - 30 );
		txtDataini.setVlrDate( cPeriodo.getTime() );
	}
	
	private void montaListaCampos() {
		
		//cliente
		lcCli.add( new GuardaCampo( txtCodCli, "CodCli", "C�d.cli.", ListaCampos.DB_PK, true ) );
		lcCli.add( new GuardaCampo( txtRazCli, "RazCli", "Raz�o social do cliente", ListaCampos.DB_SI, false ) );
		lcCli.montaSql( false, "CLIENTE", "VD" );
		lcCli.setReadOnly( true );
		txtCodCli.setTabelaExterna( lcCli, FCliente.class.getCanonicalName() );
		txtCodCli.setFK( true );
		txtCodCli.setNomeCampo( "CodCli" );
		
		// Contrato

		lcContr.add( new GuardaCampo( txtCodContr, "CodContr", "C�d.Contr.", ListaCampos.DB_PK, true ) );
		lcContr.add( new GuardaCampo( txtDescContr, "DescContr", "Descri��o do contrato", ListaCampos.DB_SI, false ) );
		lcContr.add( new GuardaCampo( txtContHSubContr, "ContHSubContr", "Cont.HSubContr.", ListaCampos.DB_SI, false ) );
		lcContr.add( new GuardaCampo( txtCodCli2, "CodCli", "C�d.cli", ListaCampos.DB_SI, false ) );
		lcContr.setDinWhereAdic( "CodCli=#N", txtCodCli );
		lcContr.montaSql( false, "CONTRATO", "VD" );
		lcContr.setReadOnly( true );
	
		txtCodContr.setTabelaExterna( lcContr, FContrato.class.getCanonicalName() );
		txtCodContr.setFK( true );
		txtCodContr.setNomeCampo( "CodContr" );
		
		lcCli.addCarregaListener( this );
		lcContr.addCarregaListener( this );
		
		
	}

	public void imprimir( boolean bVisualizar ) {
		
		if ( txtDatafim.getVlrDate().before( txtDataini.getVlrDate() ) ) {
			Funcoes.mensagemInforma( this, "Data inicial maior que a data final!" );
			return;
		}
		
		String sCab = "";
		String Ordem = "";
		StringBuilder sql = null;
		Blob fotoemp = null;

		try {
			PreparedStatement ps = con.prepareStatement( "SELECT FOTOEMP FROM SGEMPRESA WHERE CODEMP=?" );
			ps.setInt( 1, Aplicativo.iCodEmp );

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fotoemp = rs.getBlob( "FOTOEMP" );
			}
			rs.close();
			ps.close();
			con.commit();

		} catch (Exception e) {
			Funcoes.mensagemErro( this, "Erro carregando logotipo.\n" + e.getMessage() );
			e.printStackTrace();
		}	
		
		sCab = txtCodCli.getVlrInteger().toString() + " - " + txtRazCli.getVlrString() + " \n" + txtCodContr.getVlrInteger().toString() + " - " + txtDescContr.getVlrString() +" - Per�odo de " + txtDataini.getVlrString()  + " a " +  txtDatafim.getVlrString();
		/*
		sql.append( " SELECT CT.INDICE, " );
		sql.append( "( CASE  " );
		sql.append( "WHEN IDX=1 AND TIPO='SC' THEN DESCCONTRSC " );
		sql.append( "WHEN IDX=1 AND TIPO='CT' THEN DESCCONTR " );
		sql.append( "WHEN IDX=2 THEN DESCITCONTR " );
		sql.append( "WHEN IDX=3 THEN DESCTAREFA " );
		sql.append( "WHEN IDX=4 THEN DESCTAREFAST " );
		sql.append( "END ) DESCRICAO, " );
		sql.append( "CT.TIPO, CT.IDX, CT.CODCONTR, CT.CODCONTRSC, CT.CODITCONTR, CT.CODTAREFA, CT.CODTAREFAST " );
		sql.append( "FROM VDCONTRATOVW01 CT " );
		sql.append(	"WHERE CT.CODEMPCT=? AND CT.CODFILIALCT=? AND CT.CODCONTR=? ");
		sql.append(	"ORDER BY  IDX01, IDX02, IDX03, IDX04, IDX05 " );
		*/
		
		sql = new StringBuilder( "select ct.indice, " );
		sql.append( "( case " );
		sql.append( "when idx=1 and ct.tipo in ('sc','sp') then desccontrsc " );
		sql.append( "when idx=1 and ct.tipo in ('ct','pj') then desccontr " );
		sql.append( "when idx=2 then ct.descitcontr " );
		sql.append( "when idx=3 then ct.desctarefa " );
		sql.append( "when idx=4 then ct.desctarefast " );
		sql.append( "end ) descricao, " );
		sql.append( "t.totalprevgeral, t.totalgeral, t.totalcobcligeral, t.totalant, t.totalcobcliant, t.totalper, t.totalcobcliper, " );
		sql.append( "ct.tipo, ct.idx, ct.codcontr, ct.codcontrsc, ct.coditcontr, ct.codtarefa, ct.codtarefast " );
		sql.append( "from vdcontratovw01 ct, vdcontratototsp(ct.codempct, ct.codfilialct, ct.codcontr, ct.coditcontr, " );
		sql.append( "ct.codempsc, ct.codfilialsc, ct.codcontrsc, " );
		sql.append( "ct.codempta, ct.codfilialta, ct.codtarefa, " );
		sql.append( "ct.codempst, ct.codfilialst, ct.codtarefast, " );
		sql.append( "?, ? ) t " );
		if ("S".equals(txtContHSubContr.getVlrString())) {
			sql.append( "and ct.codcontrsc is not null " );
		} else {
			sql.append( "and ct.codcontrsc is null ");
		}
		sql.append( "where ct.codempct=? and ct.codfilialct=? and ct.codcontr=? " );
		sql.append( "order by idx01, idx02, idx03, idx04, idx05 " );



		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			ps = con.prepareStatement( sql.toString() );
			int param = 1;
			
			ps.setDate( param++, Funcoes.dateToSQLDate( txtDataini.getVlrDate() ) );
			ps.setDate( param++, Funcoes.dateToSQLDate( txtDatafim.getVlrDate() ) );
			ps.setInt( param++, Aplicativo.iCodEmp );
			ps.setInt( param++, ListaCampos.getMasterFilial( "VDCONTRATO" ) );
			ps.setInt( param++, txtCodContr.getVlrInteger() );
			
			rs = ps.executeQuery();

		} catch (Exception err) {
			Funcoes.mensagemErro( this, "Erro consulta Cronograma Sint�tico\n" + err.getMessage(), true, con, err );
		}

		imprimiGrafico( bVisualizar, rs,  sCab, fotoemp );

	}

	private void imprimiGrafico( boolean bVisualizar, ResultSet rs, String sCab, Blob fotoemp) {
		String report = "layout/rel/REL_CRONOGRAMA_01.jasper";
		String label = "Cronograma Sint�tico";
		
	    HashMap<String, Object> hParam = new HashMap<String, Object>();
		hParam.put( "SUBREPORT_DIR", "org/freedom/layout/rel/" );
		hParam.put( "CODEMP", new Integer(Aplicativo.iCodEmp) );
		hParam.put( "CODFILIAL", new Integer(ListaCampos.getMasterFilial( "VDCONTRATO" )) );
		hParam.put( "CODCONTR", txtCodContr.getVlrInteger() );

		
	    try {
			hParam.put( "LOGOEMP",  new ImageIcon(fotoemp.getBytes(1, ( int ) fotoemp.length())).getImage() );
	
		} catch ( SQLException e ) {
			Funcoes.mensagemErro( this, "Erro carregando logotipo !\n" + e.getMessage()  );
			e.printStackTrace();
		}
		
		FPrinterJob dlGr = new FPrinterJob( report, label, sCab, rs, hParam , this );

		if ( bVisualizar ) {
			dlGr.setVisible( true );
		} else {
			try {
				JasperPrintManager.printReport( dlGr.getRelatorio(), true );
			} catch ( Exception err ) {
				Funcoes.mensagemErro( this, "Erro na impress�o do Cronograma Sint�tico!" + err.getMessage(), true, con, err );
			}
		}
	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		lcCli.setConexao( cn );
		lcContr.setConexao( cn );
	}

	public void afterCarrega( CarregaEvent cevt ) {
		if(cevt.getListaCampos() == lcContr ){
			if( txtCodContr.getVlrInteger() !=  txtCodCli2.getVlrInteger() ){
				txtCodCli.setVlrInteger( txtCodCli2.getVlrInteger() );
				lcCli.carregaDados();
			}	
		}
	}

	public void beforeCarrega( CarregaEvent cevt ) {

	}

}