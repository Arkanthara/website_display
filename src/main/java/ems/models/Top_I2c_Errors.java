package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_top_i2c_errors" )
public class Top_I2c_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer timeout;
	private Integer addrnak;
	private Integer others;
	private Integer readko;
	private Integer writeko;
	private Integer fwriteko;

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

	public Integer getAddrnak() { 
		return this.addrnak;
	}

	public Integer getOthers() { 
		return this.others;
	}

	public Integer getReadko() { 
		return this.readko;
	}

	public Integer getWriteko() { 
		return this.writeko;
	}

	public Integer getFwriteko() { 
		return this.fwriteko;
	}
}
