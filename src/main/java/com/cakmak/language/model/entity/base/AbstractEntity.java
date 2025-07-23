package com.cakmak.language.model.entity.base;


import com.cakmak.language.model.constant.RecordStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity implements BaseEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CRE_DATE", updatable = false)
    private Date creDate;

    @CreatedBy
    @Column(name = "CRE_BY", updatable = false)
    private String creBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MOD_DATE")
    private Date modDate;

    @LastModifiedBy
    @Column(name = "MOD_BY")
    private String modBy;

    @Column(name = "RECORD_STATUS")
    private Integer recordStatus = RecordStatus.ACTIVE;

    /*@PrePersist
    protected void prePersist() {
        if (this.recordStatus == null) recordStatus = RecordStatus.ACTIVE;
        if (this.creBy == null) {
            creBy = "SYSTEM";
        }
    }

    @PreUpdate
    protected void preUpdate() {
        if (this.modBy == null) {
            modBy = "SYSTEM";
        }
    }

    @PreRemove
    protected void preRemove() {
        this.modDate = new Date();
        recordStatus= RecordStatus.ACTIVE;
    }*/
}