/**
 * @version 07/2007 <BR>
 * @author Setpoint Inform�tica Ltda./Alex Rodrigues <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.std <BR>
 * Classe:
 * @(#)DLChecaExporta.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Dialogo que mostra os registros com erros da exporta��o Contabil/Livros Fiscais.
 * 
 */

package org.freedom.modulos.std;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import org.freedom.componentes.ImprimeOS;
import org.freedom.componentes.JPanelPad;
import org.freedom.componentes.Tabela;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.DLRelatorio;

public class DLChecaExporta extends DLRelatorio {

	private static final long serialVersionUID = 1L;
	
	private final String FREEDOM_CONTABIL = "01";
	
	private final String SAFE_CONTABIL = "02";

	private JPanelPad panelGeral = new JPanelPad( JPanelPad.TP_JPANEL, new BorderLayout() );
	
	private JInternalFrame orig;
	
	private Connection con = null;

	public Tabela tab = new Tabela();
	
	private final String tipo;
	
	private final String sistema;

	public DLChecaExporta( final JInternalFrame orig, final String tipo, final String sistema ) {

		super();
		setModal( true );		
		setTitulo( "Inconsist�ncias de Vendas" );
		setAtribos( 600, 400 );
		
		this.orig = orig;
		this.tipo = tipo;
		this.sistema = sistema;

		c.add( panelGeral, BorderLayout.CENTER );		
		
		montaTela();
	}
	
	private void montaTela() {
		
		if ( SAFE_CONTABIL.equals( sistema ) ) {
			
			montaTelaSafeContabil();
		}
	}
	
	private void montaTelaSafeContabil() {
		
		panelGeral.add( new JScrollPane( tab ), BorderLayout.CENTER );

		tab.adicColuna( "Tipo" );
		tab.adicColuna( "Conta Deb." );
		tab.adicColuna( "Conta Cred." );
		tab.adicColuna( "Documento" );
		tab.adicColuna( "Data" );
		tab.adicColuna( "Valor" );
		tab.adicColuna( "Historico" );
		tab.adicColuna( "Filial" );

		tab.setTamColuna( 100, 0 );
		tab.setTamColuna( 100, 1 );
		tab.setTamColuna( 100, 2 );
		tab.setTamColuna( 80, 3 );
		tab.setTamColuna( 90, 4 );
		tab.setTamColuna( 100, 5 );
		tab.setTamColuna( 300, 6 );
		tab.setTamColuna( 50, 7 );
	}

	public void carregaDados( List<FExporta.SafeBean> dados ) {
		
		if ( SAFE_CONTABIL.equals( sistema ) ) {
			
			try {
				carregaDadosSafeContabil( dados );
			} catch ( ClassCastException e ) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void carregaDadosSafeContabil( List<FExporta.SafeBean> args ) {
		
		tab.limpa();
		int row = 0;
		
		for ( FExporta.SafeBean sb : args ) {
			
			tab.adicLinha();
			
			tab.setValor( sb.getTipo().getDescricao(), row, 0 );
			tab.setValor( sb.getContadeb(), row, 1 );
			tab.setValor( sb.getContacred(), row, 2 );
			tab.setValor( sb.getDocumento(), row, 3 );
			tab.setValor( sb.getData(), row, 4 );
			tab.setValor( sb.getValor().setScale( 2, BigDecimal.ROUND_HALF_UP ), row, 5 );
			tab.setValor( sb.getHistorico(), row, 6 );
			tab.setValor( sb.getFilial(), row, 7);
			
			row++;
		}
		
	}
	
	public void imprimir( boolean bVisualizar ) {
		
		try {
			
			String linha = "|" + Funcoes.replicate( "-", 133 ) + "|";
			String tipo = "";
			ImprimeOS imp = new ImprimeOS( "", con );
			int linPag = imp.verifLinPag() - 1;
			
			imp.montaCab();
			imp.setTitulo( "Relat�rio de Classifica��o de Clientes" );
			
			imp.limpaPags();
			
			
			for ( int i=0; i < tab.getNumLinhas(); i++ ) {
				
				if ( imp.pRow() == 0 ) {
					
					imp.impCab( 136, false );
					
					imp.say( 0, "|" );
					imp.say( 1, "Doc." );
					imp.say( 10, "|" );
					imp.say( 11, "Conta Cred." );
					imp.say( 22, "|" );
					imp.say( 23, "Conta Deb." );
					imp.say( 34, "|" );
					imp.say( 35, "Data" );
					imp.say( 46, "|" );
					imp.say( 47, "Valor" );
					imp.say( 60, "|" );
					imp.say( 61, "Historico" );
					imp.say( 135, "|" );
					
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( imp.pRow() + 0, 0, linha );
				}
				
				if ( ! tipo.equals( (String) tab.getValor( i, 0 ) ) ) {
					
					tipo = (String) tab.getValor( i, 0 );

					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( imp.pRow() + 0, 0, linha );
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( 0, "|" );
					imp.say( ( ( 133-tipo.length() ) / 2 - 1 ), tipo );
					imp.say( 135, "|" );
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( imp.pRow() + 0, 0, linha );
				}

				imp.pulaLinha( 1, imp.comprimido() );
				imp.say( 0, "|" );
				imp.say( 1, (String) tab.getValor( i, 3 ) );
				imp.say( 10, "|" );
				imp.say( 14, (String) tab.getValor( i, 2 ) );
				imp.say( 22, "|" );
				imp.say( 23, (String) tab.getValor( i, 1 ) );
				imp.say( 34, "|" );
				imp.say( 35, Funcoes.dateToStrDate( (Date) tab.getValor( i, 4 ) ) );
				imp.say( 46, "|" );
				imp.say( 47, Funcoes.strDecimalToStrCurrency( 12, 2, ( (BigDecimal) tab.getValor( i, 5 ) ).toString() ) );
				imp.say( 60, "|" );
				imp.say( 61, Funcoes.copy( (String) tab.getValor( i, 6 ), 73) );
				imp.say( 135, "|" );
				
				if ( imp.pRow() >= linPag ) {
					imp.pulaLinha( 1, imp.comprimido() );
					imp.say( imp.pRow() + 0, 0, linha );
					imp.incPags();
					imp.eject();
				}
			}

			imp.pulaLinha( 1, imp.comprimido() );
			imp.say( imp.pRow() + 0, 0, linha );
			imp.eject();
			imp.fechaGravacao();

			if ( bVisualizar ) {
				imp.preview( orig );
			}
			else {
				imp.print();
			}
			
			dispose();
			
		} catch ( Exception err ) {
			err.printStackTrace();
			Funcoes.mensagemErro( this, "Erro ao montar relatorio!\n" + err.getMessage(), true, con, err );
		}
	}
	
	public void setConexao( final Connection con ) {
		
		this.con = con;
	}

};