package br.com.procempa.openproject.integration.entity;

public class TotalTime {
	private String workpackageId;
	private String totalTime;
	
	public TotalTime(String pworkpackageId, String pTotalTime){
		this.workpackageId=pworkpackageId;
		this.totalTime=pTotalTime;
		
	}
	
	public String getWorkpackageId() {
		return workpackageId;
	}
	public void setWorkpackageId(String workpackageId) {
		this.workpackageId = workpackageId;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

}
