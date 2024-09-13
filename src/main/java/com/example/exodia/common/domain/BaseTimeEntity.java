package com.example.exodia.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "del_yn", nullable = false)
    private DelYN delYn = DelYN.N;

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
        this.delYn = DelYN.Y;
    }

    public boolean isDeleted() {
        return this.delYn == DelYN.Y;
    }
}
