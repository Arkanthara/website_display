package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_pneumatic_errors" )
public class Pneumatic_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer sensor_press;
	private Integer over_press;
	private Integer afsat;
	private Integer clogged;
	private Integer no_chamber;
	private Integer no_air;
	private Integer timeout_up;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getSensor_press() { 
		return this.sensor_press;
	}

	public Integer getOver_press() { 
		return this.over_press;
	}

	public Integer getAfsat() { 
		return this.afsat;
	}

	public Integer getClogged() { 
		return this.clogged;
	}

	public Integer getNo_chamber() { 
		return this.no_chamber;
	}

	public Integer getNo_air() { 
		return this.no_air;
	}

	public Integer getTimeout_up() { 
		return this.timeout_up;
	}
}
