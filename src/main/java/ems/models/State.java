package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_state" )
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer init;
	private Integer standby;
	private Integer fault;
	private Integer warning;
	private Integer sw_update;
	private Integer wifi_pairing;
	private Integer bt_pairing;
	private Integer rest;
	private Integer cleaning;
	private Integer af;
	private Integer pz;
	private Integer service;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getInit() { 
		return this.init;
	}

	public Integer getStandby() { 
		return this.standby;
	}

	public Integer getFault() { 
		return this.fault;
	}

	public Integer getWarning() { 
		return this.warning;
	}

	public Integer getSw_update() { 
		return this.sw_update;
	}

	public Integer getWifi_pairing() { 
		return this.wifi_pairing;
	}

	public Integer getBt_pairing() { 
		return this.bt_pairing;
	}

	public Integer getRest() { 
		return this.rest;
	}

	public Integer getCleaning() { 
		return this.cleaning;
	}

	public Integer getAf() { 
		return this.af;
	}

	public Integer getPz() { 
		return this.pz;
	}

	public Integer getService() { 
		return this.service;
	}
}
