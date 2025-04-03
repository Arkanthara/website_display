package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_heater_errors" )
public class Heater_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer hw_safety_temp;
	private Integer sensor_tc;
	private Integer burn;
	private Integer tc_diff;
	private Integer delta_target;
	private Integer hot;
	private Integer cold;
	private Integer full;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getHw_safety_temp() { 
		return this.hw_safety_temp;
	}

	public Integer getSensor_tc() { 
		return this.sensor_tc;
	}

	public Integer getBurn() { 
		return this.burn;
	}

	public Integer getTc_diff() { 
		return this.tc_diff;
	}

	public Integer getDelta_target() { 
		return this.delta_target;
	}

	public Integer getHot() { 
		return this.hot;
	}

	public Integer getCold() { 
		return this.cold;
	}

	public Integer getFull() { 
		return this.full;
	}
}
