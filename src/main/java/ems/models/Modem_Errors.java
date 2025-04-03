package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_modem_errors" )
public class Modem_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer timeout;
	private Integer header;
	private Integer footer;
	private Integer crc;
	private Integer ctx;

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

	public Integer getHeader() { 
		return this.header;
	}

	public Integer getFooter() { 
		return this.footer;
	}

	public Integer getCrc() { 
		return this.crc;
	}

	public Integer getCtx() { 
		return this.ctx;
	}
}
