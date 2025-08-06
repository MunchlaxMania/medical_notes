// 服薬記録を格納するためのクラス

package com.example.medicineNote;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    // 痛みの強さを格納
    private Integer painBefore;
    private Integer painAfter30;
    // 30分後
    private Integer painAfter60;
    // 60分後
    private Integer painAfter120;
    // 120分後

    private LocalDateTime createdAt;
    // 日時を格納

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getPainBefore() {
        return painBefore;
    }

    public void setPainBefore(Integer painBefore) {
        this.painBefore = painBefore;
    }
    public Integer getPainAfter30() {
        return painAfter30;
    }

    public void setPainAfter30(Integer painAfter30) {
        this.painAfter30 = painAfter30;
    }

    public Integer getPainAfter60() {
        return painAfter60;
    }

    public void setPainAfter60(Integer painAfter60) {
        this.painAfter60 = painAfter60;
    }

    public Integer getPainAfter120() {
        return painAfter120;
    }

    public void setPainAfter120(Integer painAfter120) {
        this.painAfter120 = painAfter120;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
