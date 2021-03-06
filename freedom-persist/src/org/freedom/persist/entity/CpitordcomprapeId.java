package org.freedom.persist.entity;

// Generated 12/05/2014 09:11:34 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CpitordcomprapeId generated by hbm2java
 */
@Embeddable
public class CpitordcomprapeId implements java.io.Serializable {

	private int codemp;
	private short codfilial;
	private int codordcp;
	private short coditordcp;
	private short seqitordcppe;

	public CpitordcomprapeId() {
	}

	public CpitordcomprapeId(int codemp, short codfilial, int codordcp,
			short coditordcp, short seqitordcppe) {
		this.codemp = codemp;
		this.codfilial = codfilial;
		this.codordcp = codordcp;
		this.coditordcp = coditordcp;
		this.seqitordcppe = seqitordcppe;
	}

	@Column(name = "CODEMP", nullable = false)
	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	@Column(name = "CODFILIAL", nullable = false)
	public short getCodfilial() {
		return this.codfilial;
	}

	public void setCodfilial(short codfilial) {
		this.codfilial = codfilial;
	}

	@Column(name = "CODORDCP", nullable = false)
	public int getCodordcp() {
		return this.codordcp;
	}

	public void setCodordcp(int codordcp) {
		this.codordcp = codordcp;
	}

	@Column(name = "CODITORDCP", nullable = false)
	public short getCoditordcp() {
		return this.coditordcp;
	}

	public void setCoditordcp(short coditordcp) {
		this.coditordcp = coditordcp;
	}

	@Column(name = "SEQITORDCPPE", nullable = false)
	public short getSeqitordcppe() {
		return this.seqitordcppe;
	}

	public void setSeqitordcppe(short seqitordcppe) {
		this.seqitordcppe = seqitordcppe;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CpitordcomprapeId))
			return false;
		CpitordcomprapeId castOther = (CpitordcomprapeId) other;

		return (this.getCodemp() == castOther.getCodemp())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodordcp() == castOther.getCodordcp())
				&& (this.getCoditordcp() == castOther.getCoditordcp())
				&& (this.getSeqitordcppe() == castOther.getSeqitordcppe());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodemp();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodordcp();
		result = 37 * result + this.getCoditordcp();
		result = 37 * result + this.getSeqitordcppe();
		return result;
	}

}
