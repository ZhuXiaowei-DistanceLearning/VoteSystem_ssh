package com.zxw.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_option", schema = "votesys")
public class TbOption implements Serializable {
    private String id;
    private String name;
    private Integer tpOrder;
    private String subjectId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tp_order")
    public Integer getTpOrder() {
        return tpOrder;
    }

    public void setTpOrder(Integer tpOrder) {
        this.tpOrder = tpOrder;
    }

    @Basic
    @Column(name = "subjectId")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbOption tbOption = (TbOption) o;
        return Objects.equals(id, tbOption.id) &&
                Objects.equals(name, tbOption.name) &&
                Objects.equals(tpOrder, tbOption.tpOrder) &&
                Objects.equals(subjectId, tbOption.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tpOrder, subjectId);
    }
}
