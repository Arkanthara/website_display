package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_metadata" )
public class Metadata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private String data;
	private boolean pairing_status;
	private Integer time_since_last_maintenance_s;
	private Integer stats_mapping_version;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public String getData() { 
		return this.data;
	}

	public boolean getPairing_status() { 
		return this.pairing_status;
	}

	public Integer getTime_since_last_maintenance_s() { 
		return this.time_since_last_maintenance_s;
	}

	public Integer getStats_mapping_version() { 
		return this.stats_mapping_version;
	}
}
