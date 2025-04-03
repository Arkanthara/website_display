package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_version" )
public class Version {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private String version_main;
	private String version_us;
	private String version_af;
	private String version_pz;
	private String version_esp32;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public String getVersion_main() { 
		return this.version_main;
	}

	public String getVersion_us() { 
		return this.version_us;
	}

	public String getVersion_af() { 
		return this.version_af;
	}

	public String getVersion_pz() { 
		return this.version_pz;
	}

	public String getVersion_esp32() { 
		return this.version_esp32;
	}
}
