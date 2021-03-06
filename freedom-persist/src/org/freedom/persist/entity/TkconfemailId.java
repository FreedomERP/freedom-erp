package org.freedom.persist.entity;

// Generated 12/05/2014 09:11:34 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TkconfemailId generated by hbm2java
 */
@Embeddable
public class TkconfemailId implements java.io.Serializable {

	private int codconfemail;
	private short codfilial;
	private int codemp;

	public TkconfemailId() {
	}

	public TkconfemailId(int codconfemail, short codfilial, int codemp) {
		this.codconfemail = codconfemail;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "CODCONFEMAIL", nullable = false)
	public int getCodconfemail() {
		return this.codconfemail;
	}

	public void setCodconfemail(int codconfemail) {
		this.codconfemail = codconfemail;
	}

	@Column(name = "CODFILIAL", nullable = false)
	public short getCodfilial() {
		return this.codfilial;
	}

	public void setCodfilial(short codfilial) {
		this.codfilial = codfilial;
	}

	@Column(name = "CODEMP", nullable = false)
	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TkconfemailId))
			return false;
		TkconfemailId castOther = (TkconfemailId) other;

		return (this.getCodconfemail() == castOther.getCodconfemail())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodconfemail();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
