package ems.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table( name = "database_adc_errors" )
public class Adc_Errors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serial_number;

	@Temporal(TemporalType.DATE)
	private Date time;
	private Integer sampling_error;
	private Integer full_f_e_pressure;
	private Integer full_f_e_temp_in;
	private Integer full_f_e_temp_out;
	private Integer full_f_e_umain;
	private Integer filter_e_pressure;
	private Integer filter_e_temp_in;
	private Integer filter_e_temp_out;
	private Integer filter_e_umain;
	private Integer sample_e_pressure;
	private Integer sample_e_temp_in;
	private Integer sample_e_temp_out;
	private Integer sample_e_umain;
	private Integer no_sam_e_pressure;
	private Integer no_sam_e_temp_in;
	private Integer no_sam_e_temp_out;
	private Integer no_sam_e_umain;

	public Long getId() { 
		return this.id;
	}

	public String getSerial_number() { 
		return this.serial_number;
	}

	public Date getTime() { 
		return this.time;
	}

	public Integer getSampling_error() { 
		return this.sampling_error;
	}

	public Integer getFull_f_e_pressure() { 
		return this.full_f_e_pressure;
	}

	public Integer getFull_f_e_temp_in() { 
		return this.full_f_e_temp_in;
	}

	public Integer getFull_f_e_temp_out() { 
		return this.full_f_e_temp_out;
	}

	public Integer getFull_f_e_umain() { 
		return this.full_f_e_umain;
	}

	public Integer getFilter_e_pressure() { 
		return this.filter_e_pressure;
	}

	public Integer getFilter_e_temp_in() { 
		return this.filter_e_temp_in;
	}

	public Integer getFilter_e_temp_out() { 
		return this.filter_e_temp_out;
	}

	public Integer getFilter_e_umain() { 
		return this.filter_e_umain;
	}

	public Integer getSample_e_pressure() { 
		return this.sample_e_pressure;
	}

	public Integer getSample_e_temp_in() { 
		return this.sample_e_temp_in;
	}

	public Integer getSample_e_temp_out() { 
		return this.sample_e_temp_out;
	}

	public Integer getSample_e_umain() { 
		return this.sample_e_umain;
	}

	public Integer getNo_sam_e_pressure() { 
		return this.no_sam_e_pressure;
	}

	public Integer getNo_sam_e_temp_in() { 
		return this.no_sam_e_temp_in;
	}

	public Integer getNo_sam_e_temp_out() { 
		return this.no_sam_e_temp_out;
	}

	public Integer getNo_sam_e_umain() { 
		return this.no_sam_e_umain;
	}
}
