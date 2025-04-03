package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_monitor_errors" )
public class Monitor_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer sensor_pwr;
	private Integer no_pwr;
	private Integer power_loss;
	private Integer valve_access_error;
	private Integer valve_over_current;
	private Integer valve_open_load;
	private Integer us_module;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getSensor_pwr() { 
		return this.sensor_pwr;
	}

	public Integer getNo_pwr() { 
		return this.no_pwr;
	}

	public Integer getPower_loss() { 
		return this.power_loss;
	}

	public Integer getValve_access_error() { 
		return this.valve_access_error;
	}

	public Integer getValve_over_current() { 
		return this.valve_over_current;
	}

	public Integer getValve_open_load() { 
		return this.valve_open_load;
	}

	public Integer getUs_module() { 
		return this.us_module;
	}
}
