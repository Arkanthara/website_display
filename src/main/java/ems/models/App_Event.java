package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_app_event" )
public class App_Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer pressurization;
	private Integer pairing_start;
	private Integer pairing_complete;
	private Integer cleaning_start;
	private Integer cleaning_pause;
	private Integer cleaning_finalized;
	private Integer pedal_pz_p1;
	private Integer boost_pz_p1;
	private Integer pedal_pz_p2;
	private Integer boost_pz_p2;
	private Integer pedal_pz_p3;
	private Integer boost_pz_p3;
	private Integer pedal_af_p1;
	private Integer boost_af_p1;
	private Integer pedal_af_p2;
	private Integer boost_af_p2;
	private Integer pedal_af_p3;
	private Integer boost_af_p3;
	private Integer pedal_afwo_p1;
	private Integer pedal_afwo_p2;
	private Integer pedal_afwo_p3;
	private Integer on_start;
	private Integer on_after_7h;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getPressurization() { 
		return this.pressurization;
	}

	public Integer getPairing_start() { 
		return this.pairing_start;
	}

	public Integer getPairing_complete() { 
		return this.pairing_complete;
	}

	public Integer getCleaning_start() { 
		return this.cleaning_start;
	}

	public Integer getCleaning_pause() { 
		return this.cleaning_pause;
	}

	public Integer getCleaning_finalized() { 
		return this.cleaning_finalized;
	}

	public Integer getPedal_pz_p1() { 
		return this.pedal_pz_p1;
	}

	public Integer getBoost_pz_p1() { 
		return this.boost_pz_p1;
	}

	public Integer getPedal_pz_p2() { 
		return this.pedal_pz_p2;
	}

	public Integer getBoost_pz_p2() { 
		return this.boost_pz_p2;
	}

	public Integer getPedal_pz_p3() { 
		return this.pedal_pz_p3;
	}

	public Integer getBoost_pz_p3() { 
		return this.boost_pz_p3;
	}

	public Integer getPedal_af_p1() { 
		return this.pedal_af_p1;
	}

	public Integer getBoost_af_p1() { 
		return this.boost_af_p1;
	}

	public Integer getPedal_af_p2() { 
		return this.pedal_af_p2;
	}

	public Integer getBoost_af_p2() { 
		return this.boost_af_p2;
	}

	public Integer getPedal_af_p3() { 
		return this.pedal_af_p3;
	}

	public Integer getBoost_af_p3() { 
		return this.boost_af_p3;
	}

	public Integer getPedal_afwo_p1() { 
		return this.pedal_afwo_p1;
	}

	public Integer getPedal_afwo_p2() { 
		return this.pedal_afwo_p2;
	}

	public Integer getPedal_afwo_p3() { 
		return this.pedal_afwo_p3;
	}

	public Integer getOn_start() { 
		return this.on_start;
	}

	public Integer getOn_after_7h() { 
		return this.on_after_7h;
	}
}
