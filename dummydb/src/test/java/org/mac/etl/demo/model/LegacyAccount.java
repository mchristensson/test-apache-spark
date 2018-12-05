package org.mac.etl.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LEGACYACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = { "accountnumber" }) )
public class LegacyAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNumber;

	@Column(name = "accountnumber", nullable = false, length = 10)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "LegacyAccount [id=" + id + ", accountNumber=" + accountNumber + "]";
	}
	
	
}
