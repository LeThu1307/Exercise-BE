package io.programming.ticket.management.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.programming.ticket.management.contanst.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
@Builder
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tittle")
	@NotNull
	@NotBlank
	private String tittle;

	@Column(name = "description")
	@NotNull
	private String description;

	@JoinColumn(name = "assignee_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User assignee;

	@Column(name = "assignee_id")
	@NotNull
	private Long assigneeId;

	@JoinColumn(name = "reporter_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User reporter;

	@Column(name = "reporter_id")
	private Long reporterId;

	@Column(name = "status")
	@NotNull
	private Status status;

	@Column(name = "create_time")
	@CreationTimestamp
	private Timestamp createTime;

	@Column(name = "updated_time")
	@UpdateTimestamp
	private Timestamp updatedTime;

	public Ticket() {
	}

	public Ticket(String description, @NotNull Long assigneeId, Long reporterId, Timestamp createTime,
			Timestamp updatedTime, @NotNull Status status) {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
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
