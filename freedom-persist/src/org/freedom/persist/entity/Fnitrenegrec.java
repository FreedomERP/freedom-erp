package org.freedom.persist.entity;

// Generated 12/05/2014 09:11:34 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Fnitrenegrec generated by hbm2java
 */
@Entity
@Table(name = "FNITRENEGREC")
public class Fnitrenegrec implements java.io.Serializable {

	private FnitrenegrecId id;
	private Fnitreceber fnitreceber;
	private Date dtini;
	private Date dtins;
	private Date hins;
	private String idusuins;
	private Date dtalt;
	private Date halt;
	private String idusualt;

	public Fnitrenegrec() {
	}

	public Fnitrenegrec(FnitrenegrecId id, Fnitreceber fnitreceber, Date dtins,
			Date hins, String idusuins) {
		this.id = id;
		this.fnitreceber = fnitreceber;
		this.dtins = dtins;
		this.hins = hins;
		this.idusuins = idusuins;
	}

	public Fnitrenegrec(FnitrenegrecId id, Fnitreceber fnitreceber, Date dtini,
			Date dtins, Date hins, String idusuins, Date dtalt, Date halt,
			String idusualt) {
		this.id = id;
		this.fnitreceber = fnitreceber;
		this.dtini = dtini;
		this.dtins = dtins;
		this.hins = hins;
		this.idusuins = idusuins;
		this.dtalt = dtalt;
		this.halt = halt;
		this.idusualt = idusualt;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "coditrenegrec", column = @Column(name = "CODITRENEGREC", nullable = false)),
			@AttributeOverride(name = "codrenegrec", column = @Column(name = "CODRENEGREC", nullable = false)),
			@AttributeOverride(name = "codfilial", column = @Column(name = "CODFILIAL", nullable = false)),
			@AttributeOverride(name = "codemp", column = @Column(name = "CODEMP", nullable = false)) })
	public FnitrenegrecId getId() {
		return this.id;
	}

	public void setId(FnitrenegrecId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "CODREC", referencedColumnName = "CODREC", nullable = false),
			@JoinColumn(name = "NPARCITREC", referencedColumnName = "NPARCITREC", nullable = false),
			@JoinColumn(name = "CODFILIALIR", referencedColumnName = "CODFILIAL", nullable = false),
			@JoinColumn(name = "CODEMPIR", referencedColumnName = "CODEMP", nullable = false) })
	public Fnitreceber getFnitreceber() {
		return this.fnitreceber;
	}

	public void setFnitreceber(Fnitreceber fnitreceber) {
		this.fnitreceber = fnitreceber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTINI", length = 10)
	public Date getDtini() {
		return this.dtini;
	}

	public void setDtini(Date dtini) {
		this.dtini = dtini;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTINS", nullable = false, length = 10)
	public Date getDtins() {
		return this.dtins;
	}

	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "HINS", nullable = false, length = 8)
	public Date getHins() {
		return this.hins;
	}

	public void setHins(Date hins) {
		this.hins = hins;
	}

	@Column(name = "IDUSUINS", nullable = false, length = 8)
	public String getIdusuins() {
		return this.idusuins;
	}

	public void setIdusuins(String idusuins) {
		this.idusuins = idusuins;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTALT", length = 10)
	public Date getDtalt() {
		return this.dtalt;
	}

	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "HALT", length = 8)
	public Date getHalt() {
		return this.halt;
	}

	public void setHalt(Date halt) {
		this.halt = halt;
	}

	@Column(name = "IDUSUALT", length = 8)
	public String getIdusualt() {
		return this.idusualt;
	}

	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}

}
