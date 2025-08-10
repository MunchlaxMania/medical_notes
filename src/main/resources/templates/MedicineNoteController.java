// アクセスに応じた処理を実行するクラス

package com.example.medicineNote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineNoteController {
    
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private NoteRepository noteRepository;

    // 薬の登録
    @PostMapping("/api/medicines")
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // 記録の登録
    @PostMapping("/api/notes")
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    // 薬の一覧取得
    @GetMapping("/api/medicines")
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    
}
