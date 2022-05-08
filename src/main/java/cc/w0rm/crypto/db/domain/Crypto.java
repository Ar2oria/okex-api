package cc.w0rm.crypto.db.domain;

import java.math.BigDecimal;

public class Crypto {
    private Integer id;

    private Long ts;

    private BigDecimal o;

    private BigDecimal h;

    private BigDecimal l;

    private BigDecimal c;

    private BigDecimal vol;

    private BigDecimal volccy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public BigDecimal getO() {
        return o;
    }

    public void setO(BigDecimal o) {
        this.o = o;
    }

    public BigDecimal getH() {
        return h;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getVolccy() {
        return volccy;
    }

    public void setVolccy(BigDecimal volccy) {
        this.volccy = volccy;
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
        Crypto other = (Crypto) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTs() == null ? other.getTs() == null : this.getTs().equals(other.getTs()))
                && (this.getO() == null ? other.getO() == null : this.getO().equals(other.getO()))
                && (this.getH() == null ? other.getH() == null : this.getH().equals(other.getH()))
                && (this.getL() == null ? other.getL() == null : this.getL().equals(other.getL()))
                && (this.getC() == null ? other.getC() == null : this.getC().equals(other.getC()))
                && (this.getVol() == null ? other.getVol() == null : this.getVol().equals(other.getVol()))
                && (this.getVolccy() == null ? other.getVolccy() == null : this.getVolccy().equals(other.getVolccy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTs() == null) ? 0 : getTs().hashCode());
        result = prime * result + ((getO() == null) ? 0 : getO().hashCode());
        result = prime * result + ((getH() == null) ? 0 : getH().hashCode());
        result = prime * result + ((getL() == null) ? 0 : getL().hashCode());
        result = prime * result + ((getC() == null) ? 0 : getC().hashCode());
        result = prime * result + ((getVol() == null) ? 0 : getVol().hashCode());
        result = prime * result + ((getVolccy() == null) ? 0 : getVolccy().hashCode());
        return result;
    }
}
