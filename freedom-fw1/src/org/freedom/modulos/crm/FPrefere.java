/*
 * Projeto: Freedom-fw1
 * Pacote: org.freedom.modules.crm
 * Classe: @(#)FPrefere.java
 * 
 * Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 * modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 * na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 * Este programa � distribuido na esperan�a que possa ser  util, mas SEM NENHUMA GARANTIA; <BR>
 * sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 * Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 * escreva para a Funda��o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA <BR> 
 */

package org.freedom.modulos.crm;

import javax.swing.BorderFactory;

import org.freedom.acao.CarregaEvent;
import org.freedom.acao.CarregaListener;
import org.freedom.acao.CheckBoxEvent;
import org.freedom.acao.CheckBoxListener;
import org.freedom.acao.InsertEvent;
import org.freedom.acao.InsertListener;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JCheckBoxPad;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JPanelPad;
import org.freedom.library.swing.component.JPasswordFieldPad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.frame.FTabDados;


/**
 * 
 * @author Setpoint Inform�tica Ltda./Alex Rodrigues
 * @version 10/10/2009 - Alex Rodrigues
 */
public class FPrefere extends FTabDados implements InsertListener, CarregaListener, CheckBoxListener {

	private static final long serialVersionUID = 1L;

	private JPanelPad panelCampanhas = new JPanelPad();
	
	private JPanelPad panelEmail = new JPanelPad();
	
	private JPanelPad panelAtendimentos = new JPanelPad();
	
	private JPanelPad panelPonto = new JPanelPad();
	
	private JPanelPad panelContato = new JPanelPad();
	
	private JPanelPad panelFichaAval = new JPanelPad();

	private JPanelPad pnEmail = new JPanelPad();

	private JTextFieldPad txtSmtpMail = new JTextFieldPad(JTextFieldPad.TP_STRING, 40, 0);

	private JTextFieldPad txtUserMail = new JTextFieldPad(JTextFieldPad.TP_STRING, 40, 0);

	private JTextFieldPad txtCodAtivTE = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescAtivTE = new JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);

	private JTextFieldPad txtCodAtivCE = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescAtivCE = new JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);

	private JTextFieldPad txtCodEmailNC = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescEmailNC = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);

	private JTextFieldPad txtCodEmailEA = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescEmailEA = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModel = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModAtendo = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelME = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModAtendoME = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelFJ = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModelFJ = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelFI = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModelFI = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelAP = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModelAP = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelOR = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModelOR = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodModelMC = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldFK txtDescModelMC = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);

	private JTextFieldPad txtCodEmailEC = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescEmailEC = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtTempoMax = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldPad txtTolerancia = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);
	
	private JTextFieldPad txtCodTipoCont1 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);

	private JTextFieldFK txtDescTipoCont = new JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodConfEmail = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);

	private JTextFieldFK txtNomeRemet = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodEmailEN = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescEmailEN = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);

	private JTextFieldPad txtEmailNotif1 = new JTextFieldPad( JTextFieldPad.TP_STRING, 250, 0 );
	
	private JTextFieldPad txtCodTipoCont2 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);

	private JTextFieldFK txtDescTipoCont2 = new JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodConfEmail2 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);

	private JTextFieldFK txtNomeRemet2 = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);
	
	private JTextFieldPad txtCodEmailEN2 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 5, 0);

	private JTextFieldFK txtDescEmailEN2 = new JTextFieldFK(JTextFieldPad.TP_STRING, 50, 0);

	private JTextFieldPad txtEmailNotif2 = new JTextFieldPad( JTextFieldPad.TP_STRING, 250, 0 );
	
	private JTextFieldPad txtPeriodoBloq = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 10, 0);
	
	private JTextFieldPad txtLayoutFichaAval = new JTextFieldPad( JTextFieldPad.TP_STRING, 100, 0);
	
	private JTextFieldPad txtLayoutPreFichaAval = new JTextFieldPad( JTextFieldPad.TP_STRING, 100, 0);
	
	private JPasswordFieldPad txpPassMail = new JPasswordFieldPad(16);
	
	private JTextFieldPad txtCodVarG1 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG1 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG2 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG2 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG3 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG3 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG4 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG4 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG5 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG5 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG6 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG6 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG7 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG7 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodVarG8 = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);
	
	private JTextFieldFK txtDescVarG8 = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JTextFieldPad txtCodSetor = new  JTextFieldPad(JTextFieldPad.TP_INTEGER, 8, 0);

	private JTextFieldFK txtDescSetor = new  JTextFieldFK(JTextFieldPad.TP_STRING, 40, 0);
	
	private JCheckBoxPad cbAutoHorario = new JCheckBoxPad("Data/Hor�rio autom�tico no atendimento?", "S", "N");
	
	private JCheckBoxPad cbMostraCliAtraso = new JCheckBoxPad("Mostra clientes em atraso no painel de controle ?", "S", "N");
	
	private JCheckBoxPad cbBloqueiaCliAtraso = new JCheckBoxPad("Bloquear atendimentos para clientes em atraso ?", "S", "N");
	
	private JCheckBoxPad cbLancaPontoAF = new JCheckBoxPad("Lan�a ponto na abertura e fechamento do sistema ?", "S", "N");

	private JCheckBoxPad cbUsaCtoSeq = new JCheckBoxPad("Chave sequencial. ", "S", "N");
	
	private JCheckBoxPad cbBloqAtendimento = new JCheckBoxPad("Bloquear atendimentos ?", "S", "N");
	
	private JCheckBoxPad cbControleAcesAtend = new JCheckBoxPad("Controle de acesso pelo atendente ?", "S", "N");
	
	private JCheckBoxPad cbAgendaObrigOrc = new JCheckBoxPad("Agendamento obrigat�rio vinculado ao or�amento ?", "S", "N");
	
	private ListaCampos lcAtivTE = new ListaCampos(this, "TE");

	private ListaCampos lcAtivCE = new ListaCampos(this, "CE");
	
	private ListaCampos lcEmailNC = new ListaCampos( this, "NC" );
	
	private ListaCampos lcEmailEA = new ListaCampos( this, "EA" );
	
	private ListaCampos lcEmailEC = new ListaCampos( this, "EC" );
	
	private ListaCampos lcModAtendo = new ListaCampos( this, "MI" );
	
	private ListaCampos lcModAtendoME = new ListaCampos( this, "ME" );
	
	private ListaCampos lcModelAtendoFI = new ListaCampos( this, "FI" );
	
	private ListaCampos lcModelAtendoFJ = new ListaCampos( this, "FJ" );
	
	private ListaCampos lcModelAtendoAP = new ListaCampos( this, "AP" );
	
	private ListaCampos lcModelAtendoOR = new ListaCampos( this, "OR" );
	
	private ListaCampos lcModelAtendoMC = new ListaCampos( this, "MC" );
	
	private ListaCampos lctipoCont1 = new ListaCampos( this, "T1" );
	
	private ListaCampos lcConfEmail = new ListaCampos( this, "CF" );
	
	private ListaCampos lcEmailEN = new ListaCampos( this, "EN" );

	private ListaCampos lctipoCont2 = new ListaCampos( this, "T2" );
	
	private ListaCampos lcConfEmail2 = new ListaCampos( this, "C2" );
	
	private ListaCampos lcEmailEN2 = new ListaCampos( this, "E2" );

	private ListaCampos lcVariante1 = new ListaCampos( this, "V1");
	
	private ListaCampos lcVariante2 = new ListaCampos( this, "V2");
	
	private ListaCampos lcVariante3 = new ListaCampos( this, "V3");
	
	private ListaCampos lcVariante4 = new ListaCampos( this, "V4");
	
	private ListaCampos lcVariante5 = new ListaCampos( this, "V5");
	
	private ListaCampos lcVariante6 = new ListaCampos( this, "V6");
	
	private ListaCampos lcVariante7 = new ListaCampos( this, "V7");
	
	private ListaCampos lcVariante8 = new ListaCampos( this, "V8");
	
	private ListaCampos lcSetor = new ListaCampos(this, "SR");
	
	public FPrefere() {

		super();
		setTitulo("Prefer�ncias CRM");
		setAtribos(50, 50, 500, 530);

		montaListaCampos();


		/******************
		 * ABA EMAIL
		 *****************/
		
		adicTab("Campanhas", panelCampanhas);

		setPainel(panelCampanhas);

		adicCampo(txtCodAtivCE, 10, 30, 80, 20, "CodAtivCE", "C�d.Ativ.", ListaCampos.DB_FK, txtDescAtivCE, false);
		adicDescFK(txtDescAtivCE, 93, 30, 320, 20, "DescAtiv", "Atividade padr�o para campanha enviada");
		adicCampo(txtCodAtivTE, 10, 70, 80, 20, "CodAtivTE", "C�d.Ativ.", ListaCampos.DB_FK, txtDescAtivTE, false);
		adicDescFK(txtDescAtivTE, 93, 70, 320, 20, "DescAtiv", "Atividade padr�o para tentativa de envio de campanha");

	
		/******************
		 * ABA EMAIL
		 *****************/

		adicTab("Email", panelEmail);
		
		setPainel(panelEmail);
				
		pnEmail.setBorder(BorderFactory.createTitledBorder("Servidor para envio de email"));
		adic(pnEmail, 10, 10, 405, 120);
		setPainel(pnEmail);
		adicCampo(txtSmtpMail, 10, 20, 190, 20, "SmtpMail", "SMTP", ListaCampos.DB_SI, false);
		adicCampo(txtUserMail, 10, 60, 190, 20, "UserMail", "Usuario", ListaCampos.DB_SI, false);
		adicCampo(txpPassMail, 203, 60, 180, 20, "PassMail", "Senha", ListaCampos.DB_SI, false);
		
		/******************
		 * ABA ATENDIMENTOS
		 *****************/

		adicTab("Atendimentos", panelAtendimentos);

		setPainel(panelAtendimentos);
		
		adicDB(cbAutoHorario, 10, 10, 405, 20, "AUTOHORATEND", "", false);
		adicDB(cbMostraCliAtraso, 10, 30, 405, 20, "MOSTRACLIATRASO", "", false);
		adicDB(cbBloqueiaCliAtraso, 10, 50, 405, 20, "BLOQATENDCLIATRASO", "", false);
		adicDB(cbControleAcesAtend,10, 70, 405, 20, "ControleAcesAtend", "", true);
		adicDB(cbAgendaObrigOrc,10, 90, 405, 20, "AgendaObrigOrc", "", true);
		adicDB(cbBloqAtendimento, 10, 110, 250, 20, "BLOQATENDIMENTO", "", false);
		
		JLabelPad label = new JLabelPad("Per�odo de bloqueio em horas.");
		adic(label, 7, 140, 250, 20);
		adicCampo(txtPeriodoBloq, 7, 160, 80, 20, "PeriodoBloq", "", ListaCampos.DB_SI, false);
		
		
		adicCampo(txtCodEmailNC, 7, 200, 80, 20, "CodEmailNC", "C�d.Email", ListaCampos.DB_FK, txtDescEmailNC, false);
		adicDescFK(txtDescEmailNC, 90, 200, 320, 20, "DescEmail", "Email para notifica��o de chamados ao t�cnico");
		txtCodEmailNC.setFK( true );
		txtCodEmailNC.setNomeCampo( "CodEmail" );

		adicCampo(txtCodEmailEA, 7, 240, 80, 20, "CodEmailEA", "C�d.Email", ListaCampos.DB_FK, txtDescEmailEA, false);
		adicDescFK(txtDescEmailEA, 90, 240, 320, 20, "DescEmail", "Email para notifica��o de chamados ao atendente");
		txtCodEmailEA.setFK( true );
		txtCodEmailEA.setNomeCampo( "CodEmail" );
		
		adicCampo(txtCodEmailEC, 7, 280, 80, 20, "CodEmailEC", "C�d.Email", ListaCampos.DB_FK, txtDescEmailEC, false);
		adicDescFK(txtDescEmailEC, 90, 280, 320, 20, "DescEmail", "Email para notifica��o de chamados ao cliente");
		txtCodEmailEC.setFK( true );
		txtCodEmailEC.setNomeCampo( "CodEmail" );
		
		adicCampo(txtCodModel, 7, 320, 80, 20, "CodModelMi", "C�d.Model", ListaCampos.DB_FK, txtDescModAtendo, false);
		adicDescFK(txtDescModAtendo, 90, 320, 320, 20, "DescModel", "Descri��o modelo de atendimento para intervalo");
		txtCodModel.setFK( true );
		txtCodModel.setNomeCampo( "CodModel" );
		
		adicCampo(txtCodModelOR, 7, 360, 80, 20, "CodModelOR", "C�d.Model.OR", ListaCampos.DB_FK, txtDescModelOR, false);
		adicDescFK(txtDescModelOR, 90, 360, 320, 20, "DescModel", "Desc. mod. para or�amentos. " );
		txtCodModelOR.setFK( true );
		txtCodModelOR.setNomeCampo( "CodModel" );
		
		adicCampo(txtCodModelMC, 7, 400, 80, 20, "CodModelMC", "C�d.Model.MC", ListaCampos.DB_FK, txtDescModelMC, false);
		adicDescFK(txtDescModelMC, 90, 400, 320, 20, "DescModel", "Desc. mod. para hist�rico de Clientes. " );
		txtCodModelMC.setFK( true );
		txtCodModelMC.setNomeCampo( "CodModel" );
		
		/******************
		 * ABA PONTO
		 *****************/
		
		adicTab("Ponto", panelPonto);
		
		setPainel(panelPonto);
		
		adicCampo(txtCodModelME, 7, 30, 80, 20, "CodModelMe", "C�d.Model", ListaCampos.DB_FK, txtDescModAtendoME, false);
		adicDescFK(txtDescModAtendoME, 90, 30, 320, 20, "DescModel", "Desc. mod. interv. entre chegada e inic. equip. " );
		txtCodModelME.setFK( true );
		txtCodModelME.setNomeCampo( "CodModel" );
		
		adicCampo(txtCodModelFJ, 7, 70, 80, 20, "CodModelFJ", "C�d.Model.FJ", ListaCampos.DB_FK, txtDescModelFJ, false);
		adicDescFK(txtDescModelFJ, 90, 70, 320, 20, "DescModel", "Desc. mod. para falta justiticada. " );
		txtCodModelFJ.setFK( true );
		txtCodModelFJ.setNomeCampo( "CodModel" );
		
		adicCampo(txtCodModelFI, 7, 110, 80, 20, "CodModelFI", "C�d.Model.FI", ListaCampos.DB_FK, txtDescModelFI, false);
		adicDescFK(txtDescModelFI, 90, 110, 320, 20, "DescModel", "Desc. mod.  para falta injustiticada. " );
		txtCodModelFI.setFK( true );
		txtCodModelFI.setNomeCampo( "CodModel" );
		
		adicCampo(txtCodModelAP, 7, 150, 80, 20, "CodModelAP", "C�d.Model.AP", ListaCampos.DB_FK, txtDescModelAP, false);
		adicDescFK(txtDescModelAP, 90, 150, 320, 20, "DescModel", "Desc. mod. para atividades pessoais. " );
		txtCodModelAP.setFK( true );
		txtCodModelAP.setNomeCampo( "CodModel" );

		adicCampo(txtTempoMax, 7, 190, 140, 20, "TempoMaxInt", "Tempo m�x.int.(min.)", ListaCampos.DB_SI, false); 
		adicCampo(txtTolerancia, 7, 230, 140, 20, "TolRegPonto", "Toler�ncia (min.)", ListaCampos.DB_SI, false); 
		adicDB(cbLancaPontoAF, 7, 250, 340, 20, "LANCAPONTOAF", "", true);
		
		/******************
		 * ABA CONTATO
		 *****************/
		
		adicTab("Contato", panelContato);
		setPainel(panelContato);
		
		adicDB( cbUsaCtoSeq, 7, 10, 405, 20, "UsaCtoSeq", "", true );
		adicCampo(txtCodTipoCont1, 7, 55, 80, 20, "CodTipoCont1", "C�d.Tp.Cont", ListaCampos.DB_FK, txtDescTipoCont, false);
		adicDescFK(txtDescTipoCont, 90, 55, 320, 20, "DescTipoCont", "Desc. do tipo de contato para formul�rio web 1 " );
		txtCodTipoCont1.setFK( true );
		txtCodTipoCont1.setNomeCampo( "CodTipoCont" );
		
		adicCampo(txtCodConfEmail, 7, 95, 80, 20, "CodConfEmail", "C�d.Conf.Email", ListaCampos.DB_FK, txtNomeRemet, false);
		adicDescFK(txtNomeRemet, 90, 95, 320, 20, "NomeRemet", "Nome do remetente");
		
		adicCampo(txtCodEmailEN, 7, 135, 80, 20, "CodEmailEN", "C�d.Email", ListaCampos.DB_FK, txtDescEmailEN, false);
		adicDescFK(txtDescEmailEN, 90, 135, 320, 20, "DescEmail", "Modelo de email para notifica��o formul�rio web 1");
		txtCodEmailEN.setFK( true );
		txtCodEmailEN.setNomeCampo( "CodEmail" );
				
		adicCampo(txtEmailNotif1, 7,175,320 ,20, "EmailNotif1", "Email para notifica��o de contato formul�rio web 1", ListaCampos.DB_SI, false);
		txtCodConfEmail.setFK( true );
		txtCodConfEmail.setNomeCampo( "CodConfEmail" );


		adicCampo(txtCodTipoCont2, 7, 215, 80, 20, "CodTipoCont2", "C�d.Tp.Cont", ListaCampos.DB_FK, txtDescTipoCont2, false);
		adicDescFK(txtDescTipoCont2, 90, 215, 320, 20, "DescTipoCont", "Desc. do tipo de contato para formul�rio web 2 " );
		txtCodTipoCont2.setFK( true );
		txtCodTipoCont2.setNomeCampo( "CodTipoCont" );
		
		adicCampo(txtCodConfEmail2, 7, 255, 80, 20, "CodConfEmail2", "C�d.Conf.Email", ListaCampos.DB_FK, txtNomeRemet2, false);
		adicDescFK(txtNomeRemet2, 90, 255, 320, 20, "NomeRemet", "Nome do remetente");
		
		adicCampo(txtCodEmailEN2, 7, 295, 80, 20, "CodEmailEN2", "C�d.Email", ListaCampos.DB_FK, txtDescEmailEN2, false);
		adicDescFK(txtDescEmailEN2, 90, 295, 320, 20, "DescEmail", "Modelo de email para notifica��o formul�rio web 2");
		txtCodEmailEN2.setFK( true );
		txtCodEmailEN2.setNomeCampo( "CodEmail" );
				
		adicCampo(txtEmailNotif2, 7,335,320 ,20, "EmailNotif2", "Email para notifica��o de contato formul�rio web 2", ListaCampos.DB_SI, false);
		txtCodConfEmail2.setFK( true );
		txtCodConfEmail2.setNomeCampo( "CodConfEmail" );

		
		adicCampo(txtCodSetor, 7, 395, 80, 20, "CodSetor", "C�d.Setor", ListaCampos.DB_FK, txtDescSetor, false);
		adicDescFK(txtDescSetor, 90, 395, 320, 20, "DescSetor", "Desc. Setor. " );
		
		
		/***********************
		 * ABA FICHA AVALIATIVA
		 ***********************/
		
		adicTab("Ficha Avaliativa", panelFichaAval);
		setPainel(panelFichaAval);
		
		adicCampo(txtCodVarG1, 7, 25, 80, 20, "CodVarG1", "C�d.Var.1", ListaCampos.DB_FK, txtDescVarG1, false);
		adicDescFK(txtDescVarG1, 90, 25, 320, 20, "DescVarG", "Descri��o da variante 1 ");
		txtCodVarG1.setFK( true );
		txtCodVarG1.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG2, 7, 65, 80, 20, "CodVarG2", "C�d.Var.2", ListaCampos.DB_FK, txtDescVarG2, false);
		adicDescFK(txtDescVarG2, 90, 65, 320, 20, "DescVarG", "Descri��o da variante 2 ");
		txtCodVarG2.setFK( true );
		txtCodVarG2.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG3, 7, 105, 80, 20, "CodVarG3", "C�d.Var.3", ListaCampos.DB_FK, txtDescVarG3, false);
		adicDescFK(txtDescVarG3, 90, 105, 320, 20, "DescVarG", "Descri��o da variante 3 ");
		txtCodVarG3.setFK( true );
		txtCodVarG3.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG4, 7, 145, 80, 20, "CodVarG4", "C�d.Var.4", ListaCampos.DB_FK, txtDescVarG4, false);
		adicDescFK(txtDescVarG4, 90, 145, 320, 20, "DescVarG", "Descri��o da variante 4 ");
		txtCodVarG4.setFK( true );
		txtCodVarG4.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG5, 7, 185, 80, 20, "CodVarG5", "C�d.Var.5", ListaCampos.DB_FK, txtDescVarG4, false);
		adicDescFK(txtDescVarG5, 90, 185, 320, 20, "DescVarG", "Descri��o da variante 5 ");
		txtCodVarG5.setFK( true );
		txtCodVarG5.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG6, 7, 225, 80, 20, "CodVarG6", "C�d.Var.6", ListaCampos.DB_FK, txtDescVarG4, false);
		adicDescFK(txtDescVarG6, 90, 225, 320, 20, "DescVarG", "Descri��o da variante 6 ");
		txtCodVarG6.setFK( true );
		txtCodVarG6.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG7, 7, 265, 80, 20, "CodVarG7", "C�d.Var.7", ListaCampos.DB_FK, txtDescVarG4, false);
		adicDescFK(txtDescVarG7, 90, 265, 320, 20, "DescVarG", "Descri��o da variante 7 ");
		txtCodVarG7.setFK( true );
		txtCodVarG7.setNomeCampo( "CODVARG" );
		
		adicCampo(txtCodVarG8, 7, 305, 80, 20, "CodVarG8", "C�d.Var.8", ListaCampos.DB_FK, txtDescVarG4, false);
		adicDescFK(txtDescVarG8, 90, 305, 320, 20, "DescVarG", "Descri��o da variante 8 ");
		txtCodVarG8.setFK( true );
		txtCodVarG8.setNomeCampo( "CODVARG" );
		
		adicCampo(txtLayoutFichaAval, 7,355,320 ,20, "LayoutFichaAval", "Layout para ficha avaliativa", ListaCampos.DB_SI, false);
		
		adicCampo(txtLayoutPreFichaAval, 7,395,320 ,20, "LayoutPreFichaAval", "Layout para ficha avaliativa preenchida", ListaCampos.DB_SI, false);
		
				

		setListaCampos(false, "PREFERE3", "SG");

		nav.setAtivo(0, false);
		nav.setAtivo(1, false);
		
		cbBloqAtendimento.addCheckBoxListener(this);
		lcCampos.addCarregaListener(this);
	
	}

	private void montaListaCampos() {

		lcAtivCE.add(new GuardaCampo(txtCodAtivCE, "CodAtiv", "C�d.ativ.", ListaCampos.DB_PK, false));
		lcAtivCE.add(new GuardaCampo(txtDescAtivCE, "DescAtiv", "Descri��o da atividade", ListaCampos.DB_SI, false));
		lcAtivCE.montaSql(false, "ATIVIDADE", "TK");
		lcAtivCE.setReadOnly(true);
		lcAtivCE.setQueryCommit(false);
		txtCodAtivCE.setTabelaExterna(lcAtivCE, null);

		lcAtivTE.add(new GuardaCampo(txtCodAtivTE, "CodAtiv", "C�d.ativ.", ListaCampos.DB_PK, false));
		lcAtivTE.add(new GuardaCampo(txtDescAtivTE, "DescAtiv", "Descri��o da atividade", ListaCampos.DB_SI, false));
		lcAtivTE.montaSql(false, "ATIVIDADE", "TK");
		lcAtivTE.setReadOnly(true);
		lcAtivTE.setQueryCommit(false);
		txtCodAtivTE.setTabelaExterna(lcAtivTE, null);
		
		// Email Notifica��o de T�cnico
		lcEmailNC.add( new GuardaCampo( txtCodEmailNC, "CodEmail", "C�d.Email", ListaCampos.DB_PK, false ) );
		lcEmailNC.add( new GuardaCampo( txtDescEmailNC, "DescEmail", "Descri��o do Email", ListaCampos.DB_SI,  false ) );
		lcEmailNC.montaSql( false, "EMAIL", "TK" );
		lcEmailNC.setQueryCommit( false );
		lcEmailNC.setReadOnly( true );
		txtCodEmailNC.setTabelaExterna(lcEmailNC, null);
		txtCodEmailNC.setListaCampos( lcEmailNC );
		
		// Email Notifica��o de Atendente
		lcEmailEA.add( new GuardaCampo( txtCodEmailEA, "CodEmail", "C�d.Email", ListaCampos.DB_PK, false ) );
		lcEmailEA.add( new GuardaCampo( txtDescEmailEA, "DescEmail", "Descri��o do Email", ListaCampos.DB_SI,  false ) );
		lcEmailEA.montaSql( false, "EMAIL", "TK" );
		lcEmailEA.setQueryCommit( false );
		lcEmailEA.setReadOnly( true );
		txtCodEmailEA.setTabelaExterna(lcEmailEA, null);
		txtCodEmailEA.setListaCampos( lcEmailEA );
		
		// Email Notifica��o de Cliente
		lcEmailEC.add( new GuardaCampo( txtCodEmailEC, "CodEmail", "C�d.Email", ListaCampos.DB_PK, false ) );
		lcEmailEC.add( new GuardaCampo( txtDescEmailEC, "DescEmail", "Descri��o do Email", ListaCampos.DB_SI,  false ) );
		lcEmailEC.montaSql( false, "EMAIL", "TK" );
		lcEmailEC.setQueryCommit( false );
		lcEmailEC.setReadOnly( true );
		txtCodEmailEC.setTabelaExterna(lcEmailEC, null);
		txtCodEmailEC.setListaCampos( lcEmailEC );
		
		//Modelo de Atendimento.
		lcModAtendo.add( new GuardaCampo(txtCodModel, "CodModel", "C�d.Model", ListaCampos.DB_PK, false ) );
		lcModAtendo.add( new GuardaCampo(txtDescModAtendo, "DescModel", "Descri��o do Modelo de Atendimento", ListaCampos.DB_SI, false ));
		lcModAtendo.montaSql( false, "MODATENDO", "AT" );
		lcModAtendo.setQueryCommit( false );
		lcModAtendo.setReadOnly( true );
		txtCodModel.setTabelaExterna(lcModAtendo, null);
	
		txtCodModel.setListaCampos( lcModAtendo );
		
		//Modelo de Atendimento para or�amentos
		lcModelAtendoOR.add( new GuardaCampo(txtCodModelOR, "CodModel", "C�d.Model.Orc", ListaCampos.DB_PK, false ) );
		lcModelAtendoOR.add( new GuardaCampo(txtDescModelOR, "DescModel", "Descri��o do Modelo de Atendimento para or�amentos", ListaCampos.DB_SI, false ));
		lcModelAtendoOR.montaSql( false, "MODATENDO", "AT" );
		lcModelAtendoOR.setQueryCommit( false );
		lcModelAtendoOR.setReadOnly( true );
		txtCodModelOR.setTabelaExterna(lcModelAtendoOR, null);
		txtCodModelOR.setListaCampos( lcModelAtendoOR );
		
		//Modelo de Atendimento - ABA PONTO.
		lcModAtendoME.add( new GuardaCampo(txtCodModelME, "CodModel", "C�d.Model.ME", ListaCampos.DB_PK, false ) );
		lcModAtendoME.add( new GuardaCampo(txtDescModAtendoME, "DescModel", "Descri��o do Modelo de Atendimento", ListaCampos.DB_SI, false ));
		lcModAtendoME.montaSql( false, "MODATENDO", "AT" );
		lcModAtendoME.setQueryCommit( false );
		lcModAtendoME.setReadOnly( true );
		txtCodModelME.setTabelaExterna(lcModAtendoME, null);	
		txtCodModelME.setListaCampos( lcModAtendoME );
		
		//Modelo Falta Justificada - ABA PONTO.
		lcModelAtendoFJ.add( new GuardaCampo(txtCodModelFJ, "CodModel", "C�d.Model.FJ.", ListaCampos.DB_PK, false ) );
		lcModelAtendoFJ.add( new GuardaCampo(txtDescModelFJ, "DescModel", "Descri��o do Modelo para Falta Justificada", ListaCampos.DB_SI, false ));
		lcModelAtendoFJ.montaSql( false, "MODATENDO", "AT" );
		lcModelAtendoFJ.setQueryCommit( false );
		lcModelAtendoFJ.setReadOnly( true );
		txtCodModelFJ.setTabelaExterna(lcModelAtendoFJ, null);	
		txtCodModelFJ.setListaCampos( lcModelAtendoFJ );
		
		//Modelo Falta Injustificada - ABA PONTO.
		lcModelAtendoFI.add( new GuardaCampo(txtCodModelFI, "CodModel", "C�d.Model.FI.", ListaCampos.DB_PK, false ) );
		lcModelAtendoFI.add( new GuardaCampo(txtDescModelFI, "DescModel", "Descri��o do Modelo para Falta Injustificada", ListaCampos.DB_SI, false ));
		lcModelAtendoFI.montaSql( false, "MODATENDO", "AT" );
		lcModelAtendoFI.setQueryCommit( false );
		lcModelAtendoFI.setReadOnly( true );
		txtCodModelFI.setTabelaExterna(lcModelAtendoFI, null);
		txtCodModelFI.setListaCampos( lcModelAtendoFI );
		
		
		//Modelo de Cadastro de cliente lan�amento de contato
		lcModelAtendoMC.add( new GuardaCampo(txtCodModelMC, "CodModel", "C�d.Model.Orc", ListaCampos.DB_PK, false ) );
		lcModelAtendoMC.add( new GuardaCampo(txtDescModelMC, "DescModel", "Descri��o do Modelo de Atendimento para lan�amento de contato, Cadastro de cliente", ListaCampos.DB_SI, false ));
		lcModelAtendoMC.montaSql( false, "MODATENDO", "AT" );
		lcModelAtendoMC.setQueryCommit( false );
		lcModelAtendoMC.setReadOnly( true );
		txtCodModelMC.setTabelaExterna(lcModelAtendoMC, null);
		txtCodModelMC.setListaCampos( lcModelAtendoMC );
		
		
		//Modelo Falta.
		lcModelAtendoAP.add( new GuardaCampo(txtCodModelAP, "CodModel", "C�d.Model.AP.", ListaCampos.DB_PK, false ) );
		lcModelAtendoAP.add( new GuardaCampo(txtDescModelAP, "DescModel", "Descri��o do Modelo Atendimento para atividades pessoais", ListaCampos.DB_SI, false ));
		lcModelAtendoAP.montaSql( false, "MODATENDO", "AT" );
		lcModelAtendoAP.setQueryCommit( false );
		lcModelAtendoAP.setReadOnly( true );
		txtCodModelAP.setTabelaExterna(lcModelAtendoAP, null);
		txtCodModelAP.setListaCampos( lcModelAtendoAP );
		
		//tipo do contato.
		lctipoCont1.add ( new GuardaCampo(txtCodTipoCont1, "CodTipoCont", "C�d.Tp.Cont", ListaCampos.DB_PK, false ) );
		lctipoCont1.add( new GuardaCampo(txtDescTipoCont, "DescTipoCont", "Descri��o do tipo de contato", ListaCampos.DB_SI, false ));
		lctipoCont1.montaSql( false, "TIPOCONT", "TK" );
		lctipoCont1.setQueryCommit( false );
		lctipoCont1.setReadOnly( true );
		txtCodTipoCont1.setTabelaExterna(lctipoCont1, null);
		//FTipoCont.class.getCanonicalName()
		txtCodTipoCont1.setListaCampos( lctipoCont1 );
		
		//Configura��o de email
		lcConfEmail.add ( new GuardaCampo(txtCodConfEmail, "CodConfEmail", "C�d.Conf.Email", ListaCampos.DB_PK, false ) );
		lcConfEmail.add ( new GuardaCampo(txtNomeRemet , "NomeRemet", "Nome do remetente ", ListaCampos.DB_SI, false ) );
		lcConfEmail.montaSql( false, "CONFEMAIL", "TK" );
		lcConfEmail.setQueryCommit( false );
		lcConfEmail.setReadOnly( true );
		txtCodConfEmail.setTabelaExterna(lcConfEmail, null);
		txtCodConfEmail.setListaCampos( lcConfEmail);
		
		// Email Notifica��o web.
		lcEmailEN.add( new GuardaCampo( txtCodEmailEN, "CodEmail", "C�d.Email", ListaCampos.DB_PK, false ) );
		lcEmailEN.add( new GuardaCampo( txtDescEmailEN, "DescEmail", "Descri��o do Email", ListaCampos.DB_SI,  false ) );
		lcEmailEN.montaSql( false, "EMAIL", "TK" );
		lcEmailEN.setQueryCommit( false );
		lcEmailEN.setReadOnly( true );
		txtCodEmailEN.setTabelaExterna(lcEmailEN, null);
		txtCodEmailEN.setListaCampos( lcEmailEN );

		//tipo do contato.
		lctipoCont2.add ( new GuardaCampo(txtCodTipoCont2, "CodTipoCont", "C�d.Tp.Cont", ListaCampos.DB_PK, false ) );
		lctipoCont2.add( new GuardaCampo(txtDescTipoCont2, "DescTipoCont", "Descri��o do tipo de contato", ListaCampos.DB_SI, false ));
		lctipoCont2.montaSql( false, "TIPOCONT", "TK" );
		lctipoCont2.setQueryCommit( false );
		lctipoCont2.setReadOnly( true );
		txtCodTipoCont2.setTabelaExterna(lctipoCont2, null);
		//FTipoCont.class.getCanonicalName()
		txtCodTipoCont2.setListaCampos( lctipoCont2 );
		
		//Configura��o de email
		lcConfEmail2.add ( new GuardaCampo(txtCodConfEmail2, "CodConfEmail", "C�d.Conf.Email", ListaCampos.DB_PK, false ) );
		lcConfEmail2.add ( new GuardaCampo(txtNomeRemet2 , "NomeRemet", "Nome do remetente ", ListaCampos.DB_SI, false ) );
		lcConfEmail2.montaSql( false, "CONFEMAIL", "TK" );
		lcConfEmail2.setQueryCommit( false );
		lcConfEmail2.setReadOnly( true );
		txtCodConfEmail2.setTabelaExterna(lcConfEmail2, null);
		txtCodConfEmail2.setListaCampos( lcConfEmail2);
		
		// Email Notifica��o web.
		lcEmailEN2.add( new GuardaCampo( txtCodEmailEN2, "CodEmail", "C�d.Email", ListaCampos.DB_PK, false ) );
		lcEmailEN2.add( new GuardaCampo( txtDescEmailEN2, "DescEmail", "Descri��o do Email", ListaCampos.DB_SI,  false ) );
		lcEmailEN2.montaSql( false, "EMAIL", "TK" );
		lcEmailEN2.setQueryCommit( false );
		lcEmailEN2.setReadOnly( true );
		txtCodEmailEN2.setTabelaExterna(lcEmailEN2, null);
		txtCodEmailEN2.setListaCampos( lcEmailEN2 );
		
		
		
		// Ficha Avaliativa.
		lcVariante1.add( new GuardaCampo( txtCodVarG1, "CodVarG", "C�d.var.1", ListaCampos.DB_PK, false ) );
		lcVariante1.add( new GuardaCampo( txtDescVarG1, "DescVarG", "Descri��o da Variante 1", ListaCampos.DB_SI,  false ) );
		lcVariante1.montaSql( false, "VARGRADE", "EQ" );
		lcVariante1.setQueryCommit( false );
		lcVariante1.setReadOnly( true );
		txtCodVarG1.setTabelaExterna(lcVariante1, null);
		txtCodVarG1.setListaCampos( lcVariante1 );
		
		lcVariante2.add( new GuardaCampo( txtCodVarG2, "CodVarG", "C�d.var.2", ListaCampos.DB_PK, false ) );
		lcVariante2.add( new GuardaCampo( txtDescVarG2, "DescVarG", "Descri��o da Variante 2", ListaCampos.DB_SI,  false ) );
		lcVariante2.montaSql( false, "VARGRADE", "EQ" );
		lcVariante2.setQueryCommit( false );
		lcVariante2.setReadOnly( true );
		txtCodVarG2.setTabelaExterna(lcVariante2, null);
		txtCodVarG2.setListaCampos( lcVariante2 );
		
		lcVariante3.add( new GuardaCampo( txtCodVarG3, "CodVarG", "C�d.var.3", ListaCampos.DB_PK, false));
		lcVariante3.add( new GuardaCampo( txtDescVarG3, "DescVarG", "Descri��o da Variante 3", ListaCampos.DB_SI, false));
		lcVariante3.montaSql( false,"VARGRADE", "EQ");
		lcVariante3.setQueryCommit( false);
		lcVariante3.setReadOnly( true );
		txtCodVarG3.setTabelaExterna(lcVariante3, null);
		txtCodVarG3.setListaCampos( lcVariante3 );
		
		lcVariante4.add( new GuardaCampo( txtCodVarG4, "CodVarG", "C�d.var.4", ListaCampos.DB_PK, false));
		lcVariante4.add( new GuardaCampo( txtDescVarG4, "DescVarG", "Descri��o da Variante 4", ListaCampos.DB_SI, false));
		lcVariante4.montaSql( false,"VARGRADE", "EQ");
		lcVariante4.setQueryCommit( false);
		lcVariante4.setReadOnly( true );
		txtCodVarG4.setTabelaExterna(lcVariante4, null);
		txtCodVarG4.setListaCampos( lcVariante4 );
		
		lcVariante5.add( new GuardaCampo( txtCodVarG5, "CodVarG", "C�d.var.5", ListaCampos.DB_PK, false));
		lcVariante5.add( new GuardaCampo( txtDescVarG5, "DescVarG", "Descri��o da Variante 5", ListaCampos.DB_SI, false));
		lcVariante5.montaSql( false,"VARGRADE", "EQ");
		lcVariante5.setQueryCommit( false);
		lcVariante5.setReadOnly( true );
		txtCodVarG5.setTabelaExterna(lcVariante5, null);
		txtCodVarG5.setListaCampos( lcVariante5 );
		
		lcVariante6.add( new GuardaCampo( txtCodVarG6, "CodVarG", "C�d.var.6", ListaCampos.DB_PK, false));
		lcVariante6.add( new GuardaCampo( txtDescVarG6, "DescVarG", "Descri��o da Variante 6", ListaCampos.DB_SI, false));
		lcVariante6.montaSql( false,"VARGRADE", "EQ");
		lcVariante6.setQueryCommit( false);
		lcVariante6.setReadOnly( true );
		txtCodVarG6.setTabelaExterna(lcVariante6, null);
		txtCodVarG6.setListaCampos( lcVariante6 );
		
		lcVariante7.add( new GuardaCampo( txtCodVarG7, "CodVarG", "C�d.var.7", ListaCampos.DB_PK, false));
		lcVariante7.add( new GuardaCampo( txtDescVarG7, "DescVarG", "Descri��o da Variante 7", ListaCampos.DB_SI, false));
		lcVariante7.montaSql( false,"VARGRADE", "EQ");
		lcVariante7.setQueryCommit( false);
		lcVariante7.setReadOnly( true );
		txtCodVarG7.setTabelaExterna(lcVariante7, null);
		txtCodVarG7.setListaCampos( lcVariante7 );
		
		lcVariante8.add( new GuardaCampo( txtCodVarG8, "CodVarG", "C�d.var.8", ListaCampos.DB_PK, false));
		lcVariante8.add( new GuardaCampo( txtDescVarG8, "DescVarG", "Descri��o da Variante 8", ListaCampos.DB_SI, false));
		lcVariante8.montaSql( false,"VARGRADE", "EQ");
		lcVariante8.setQueryCommit( false);
		lcVariante8.setReadOnly( true );
		txtCodVarG8.setTabelaExterna(lcVariante8, null);
		txtCodVarG8.setListaCampos( lcVariante8 );
		
		lcSetor.add( new GuardaCampo( txtCodSetor, "CodSetor", "C�d.setor", ListaCampos.DB_PK, false ) );
		lcSetor.add( new GuardaCampo( txtDescSetor, "DescSetor", "Descri��o do setor", ListaCampos.DB_SI, false ) );
		lcSetor.montaSql( false, "SETOR", "VD" );
		lcSetor.setQueryCommit( false );
		lcSetor.setReadOnly( true );
		txtCodSetor.setTabelaExterna( lcSetor, null );
		txtCodSetor.setListaCampos(lcSetor);

	}

	public void setConexao(DbConnection cn) {

		super.setConexao(cn);
		
		lcAtivCE.setConexao(cn);
		lcAtivTE.setConexao(cn);
		lcEmailNC.setConexao(cn);
		lcEmailEA.setConexao(cn);
		lcEmailEC.setConexao(cn);
		lcModAtendo.setConexao(cn);
		lcModAtendoME.setConexao(cn);
		lcModelAtendoFI.setConexao(cn);
		lcModelAtendoFJ.setConexao(cn);
		lcModelAtendoAP.setConexao(cn);
		lcModelAtendoOR.setConexao(cn);
		lcModelAtendoMC.setConexao(cn);
		lctipoCont1.setConexao(cn);
		lcConfEmail.setConexao(cn);
		lcEmailEN.setConexao(cn);
		lctipoCont2.setConexao(cn);
		lcConfEmail2.setConexao(cn);
		lcEmailEN2.setConexao(cn);
		lcVariante1.setConexao(cn);
		lcVariante2.setConexao(cn);
		lcVariante3.setConexao(cn);
		lcVariante4.setConexao(cn);
		lcVariante5.setConexao(cn);
		lcVariante6.setConexao(cn);
		lcVariante7.setConexao(cn);
		lcVariante8.setConexao(cn);
		lcSetor.setConexao(cn);
		lcCampos.carregaDados();
	}
	
	public void habilitaPeriodobloq(boolean habilit) {
		txtPeriodoBloq.setAtivo(habilit);
	}
	
	public void afterInsert(InsertEvent ievt) {
		/*
		if (ievt.getListaCampos() == lcCampos) {
			txtTempoMax.setVlrInteger(18);	
		}
		if (ievt.getListaCampos() == lcCampos) {
			txtTolerancia.setVlrInteger(20);	
		}
		*/
		
		if (ievt.getListaCampos() == lcCampos) {
			cbBloqueiaCliAtraso.setVlrString("N");
			cbUsaCtoSeq.setVlrString("N");
			cbBloqAtendimento.setVlrString("N");
			txtPeriodoBloq.setVlrInteger(new Integer(0));
			cbControleAcesAtend.setVlrString("N");
		}

	}

	public void beforeInsert(InsertEvent ievt) {
		
	}

	@Override
	public void valorAlterado(CheckBoxEvent evt) {
		if (evt.getCheckBox() == cbBloqAtendimento) {
			habilitaPeriodobloq(cbBloqAtendimento.getVlrString().equals("S"));
		}
		
	}

	@Override
	public void beforeCarrega(CarregaEvent cevt) {
		
	}

	@Override
	public void afterCarrega(CarregaEvent cevt) {
		if (cevt.getListaCampos() == lcCampos) {
			habilitaPeriodobloq(cbBloqAtendimento.getVlrString().equals("S"));
		}
		
	}
}
