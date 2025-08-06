// 薬のデータを操作するためのリポジトリ

package com.example.medicineNote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}