// 服薬記録の出たを操作するためのリポジトリ

package com.example.medicineNote;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    //　渡されたmedicineIdに一致するNoteを検索するメソッド
    List<Note> findByMedicineId(Integer medicineId);
}
