package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_power" )
public class Power {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer time_power_on_s;
	private Integer time_on_s;
	private Integer power_on_counter;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getTime_power_on_s() { 
		return this.time_power_on_s;
	}

	public Integer getTime_on_s() { 
		return this.time_on_s;
	}

	public Integer getPower_on_counter() { 
		return this.power_on_counter;
	}
}
