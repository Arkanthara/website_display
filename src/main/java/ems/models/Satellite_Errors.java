package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_satellite_errors" )
public class Satellite_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer reboot_af;
	private Integer reboot_pz;
	private Integer lost_af;
	private Integer lost_pz;
	private Integer no_af;
	private Integer no_pz;
	private Integer no_sat;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getReboot_af() { 
		return this.reboot_af;
	}

	public Integer getReboot_pz() { 
		return this.reboot_pz;
	}

	public Integer getLost_af() { 
		return this.lost_af;
	}

	public Integer getLost_pz() { 
		return this.lost_pz;
	}

	public Integer getNo_af() { 
		return this.no_af;
	}

	public Integer getNo_pz() { 
		return this.no_pz;
	}

	public Integer getNo_sat() { 
		return this.no_sat;
	}
}
