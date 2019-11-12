package co.edu.icesi.miniproyecto.dtos;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public class ConducsWithServices {
	
	private Tmio1Conductore conduc;
	private long totalServices;
	public ConducsWithServices(Tmio1Conductore conduc, long totalServices) {
		this.conduc = conduc;
		this.totalServices = totalServices;
	}
	public Tmio1Conductore getConduc() {
		return conduc;
	}
	public void setConduc(Tmio1Conductore conduc) {
		this.conduc = conduc;
	}
	public long getTotalServices() {
		return totalServices;
	}
	public void setTotalServices(long totalServices) {
		this.totalServices = totalServices;
	}
	

}
