package br.com.procempa.openproject.integration.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the time_entries database table.
 * 
 */
@Entity
@Table(name="time_entries")
@NamedQuery(name="TimeEntry.findAll", query="SELECT t FROM TimeEntry t")
public class TimeEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="activity_id")
	private int activityId;

	private String comments;

	private BigDecimal costs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	private float hours;

	@Column(name="overridden_costs")
	private BigDecimal overriddenCosts;

	@Column(name="project_id")
	private int projectId;

	@Column(name="rate_id")
	private int rateId;

	@Temporal(TemporalType.DATE)
	@Column(name="spent_on")
	private Date spentOn;

	private int tmonth;

	private int tweek;

	private int tyear;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	@Column(name="user_id")
	private int userId;

	@Column(name="work_package_id")
	private int workPackageId;

	public TimeEntry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getCosts() {
		return this.costs;
	}

	public void setCosts(BigDecimal costs) {
		this.costs = costs;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public float getHours() {
		return this.hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	public BigDecimal getOverriddenCosts() {
		return this.overriddenCosts;
	}

	public void setOverriddenCosts(BigDecimal overriddenCosts) {
		this.overriddenCosts = overriddenCosts;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getRateId() {
		return this.rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public Date getSpentOn() {
		return this.spentOn;
	}

	public void setSpentOn(Date spentOn) {
		this.spentOn = spentOn;
	}

	public int getTmonth() {
		return this.tmonth;
	}

	public void setTmonth(int tmonth) {
		this.tmonth = tmonth;
	}

	public int getTweek() {
		return this.tweek;
	}

	public void setTweek(int tweek) {
		this.tweek = tweek;
	}

	public int getTyear() {
		return this.tyear;
	}

	public void setTyear(int tyear) {
		this.tyear = tyear;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getWorkPackageId() {
		return this.workPackageId;
	}

	public void setWorkPackageId(int workPackageId) {
		this.workPackageId = workPackageId;
	}

}