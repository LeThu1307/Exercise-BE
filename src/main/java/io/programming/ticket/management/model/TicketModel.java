package io.programming.ticket.management.model;

import java.sql.Timestamp;

import io.programming.ticket.management.contanst.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketModel {

	private Long id;
	private String tittle;
	private String description;
	private Long assigneeId;
	private Long reporterId;
	private Timestamp createTime;
	private Timestamp updatedTime;
	private Status status = Status.OPEN;

	public TicketModel() {
		super();
	}

	public TicketModel(Long id, String tittle, String description, Long assigneeId, Long reporterId,
			Timestamp createTime, Timestamp updatedTime, Status status) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.description = description;
		this.assigneeId = assigneeId;
		this.reporterId = reporterId;
		this.createTime = createTime;
		this.updatedTime = updatedTime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Long getReporterId() {
		return reporterId;
	}

	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
