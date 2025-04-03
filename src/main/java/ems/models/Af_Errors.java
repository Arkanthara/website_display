package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_af_errors" )
public class Af_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer timeout;
	private Integer nak;
	private Integer frameinv;
	private Integer data_inv;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getTimeout() { 
		return this.timeout;
	}

	public Integer getNak() { 
		return this.nak;
	}

	public Integer getFrameinv() { 
		return this.frameinv;
	}

	public Integer getData_inv() { 
		return this.data_inv;
	}
}
