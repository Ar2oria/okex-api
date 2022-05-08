package cc.w0rm.crypto.db.domain;

import java.util.Date;

public class Task {
    private Integer id;

    private String bizId;

    private String instId;

    private String bar;

    private Long begin;

    private Long end;

    private Integer taskLimit;

    private Integer taskThreads;

    private Long taskInterval;

    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Integer getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(Integer taskLimit) {
        this.taskLimit = taskLimit;
    }

    public Integer getTaskThreads() {
        return taskThreads;
    }

    public void setTaskThreads(Integer taskThreads) {
        this.taskThreads = taskThreads;
    }

    public Long getTaskInterval() {
        return taskInterval;
    }

    public void setTaskInterval(Long taskInterval) {
        this.taskInterval = taskInterval;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBizId() == null ? other.getBizId() == null : this.getBizId().equals(other.getBizId()))
            && (this.getInstId() == null ? other.getInstId() == null : this.getInstId().equals(other.getInstId()))
            && (this.getBar() == null ? other.getBar() == null : this.getBar().equals(other.getBar()))
            && (this.getBegin() == null ? other.getBegin() == null : this.getBegin().equals(other.getBegin()))
            && (this.getEnd() == null ? other.getEnd() == null : this.getEnd().equals(other.getEnd()))
            && (this.getTaskLimit() == null ? other.getTaskLimit() == null : this.getTaskLimit().equals(other.getTaskLimit()))
            && (this.getTaskThreads() == null ? other.getTaskThreads() == null : this.getTaskThreads().equals(other.getTaskThreads()))
            && (this.getTaskInterval() == null ? other.getTaskInterval() == null : this.getTaskInterval().equals(other.getTaskInterval()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBizId() == null) ? 0 : getBizId().hashCode());
        result = prime * result + ((getInstId() == null) ? 0 : getInstId().hashCode());
        result = prime * result + ((getBar() == null) ? 0 : getBar().hashCode());
        result = prime * result + ((getBegin() == null) ? 0 : getBegin().hashCode());
        result = prime * result + ((getEnd() == null) ? 0 : getEnd().hashCode());
        result = prime * result + ((getTaskLimit() == null) ? 0 : getTaskLimit().hashCode());
        result = prime * result + ((getTaskThreads() == null) ? 0 : getTaskThreads().hashCode());
        result = prime * result + ((getTaskInterval() == null) ? 0 : getTaskInterval().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        return result;
    }
}