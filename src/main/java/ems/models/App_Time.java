package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_app_time" )
public class App_Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer pressurized;
	private Integer run_pz_p1;
	private Integer boost_pz_p1;
	private Integer run_pz_p2;
	private Integer boost_pz_p2;
	private Integer run_pz_p3;
	private Integer boost_pz_p3;
	private Integer run_af_water_only_p1;
	private Integer run_af_water_only_p2;
	private Integer run_af_water_only_p3;
	private Integer run_af_p1;
	private Integer run_af_p2;
	private Integer run_af_p3;
	private Integer boost_af_p1;
	private Integer boost_af_p2;
	private Integer boost_af_p3;
	private Integer clean_pause;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getPressurized() { 
		return this.pressurized;
	}

	public Integer getRun_pz_p1() { 
		return this.run_pz_p1;
	}

	public Integer getBoost_pz_p1() { 
		return this.boost_pz_p1;
	}

	public Integer getRun_pz_p2() { 
		return this.run_pz_p2;
	}

	public Integer getBoost_pz_p2() { 
		return this.boost_pz_p2;
	}

	public Integer getRun_pz_p3() { 
		return this.run_pz_p3;
	}

	public Integer getBoost_pz_p3() { 
		return this.boost_pz_p3;
	}

	public Integer getRun_af_water_only_p1() { 
		return this.run_af_water_only_p1;
	}

	public Integer getRun_af_water_only_p2() { 
		return this.run_af_water_only_p2;
	}

	public Integer getRun_af_water_only_p3() { 
		return this.run_af_water_only_p3;
	}

	public Integer getRun_af_p1() { 
		return this.run_af_p1;
	}

	public Integer getRun_af_p2() { 
		return this.run_af_p2;
	}

	public Integer getRun_af_p3() { 
		return this.run_af_p3;
	}

	public Integer getBoost_af_p1() { 
		return this.boost_af_p1;
	}

	public Integer getBoost_af_p2() { 
		return this.boost_af_p2;
	}

	public Integer getBoost_af_p3() { 
		return this.boost_af_p3;
	}

	public Integer getClean_pause() { 
		return this.clean_pause;
	}
}
