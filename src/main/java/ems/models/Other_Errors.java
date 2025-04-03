package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_other_errors" )
public class Other_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer top_touch_reset;
	private Integer sound_play_err;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getTop_touch_reset() { 
		return this.top_touch_reset;
	}

	public Integer getSound_play_err() { 
		return this.sound_play_err;
	}
}
