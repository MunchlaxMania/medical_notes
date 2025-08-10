package com.example.medicineNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.time.LocalDateTime;

@Controller // Webページを返すコントローラー
public class PageController {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/medicines") // "http://localhost:8080/medicines" にアクセスした時の処理
    public String medicineList(Model model) {
        // 1. DBから全ての薬のリストを取得
        model.addAttribute("medicines", medicineRepository.findAll());
        // 2. "medicines.html" という名前のHTMLファイルを表示する
        return "medicines";
    }

    //　新規画面登録を表示するメソッド
    @GetMapping("/medicines/new")
    public String showNewMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "new-medicine";
    }

    // 記録一覧を表示するメソッド
    @GetMapping("/medicines/{medicineId}/notes")
    public String noteList(@PathVariable Integer medicineId, Model model) {
        //URLのIDから薬の情報を取得
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        //取得した薬に関する記録のリストを取得
        List<Note> notes = noteRepository.findByMedicineId(medicineId);

        model.addAttribute("medicine", medicine);
        model.addAttribute("notes", notes);

        return "notes";
    }

    //新規登録フォームを表示するメソッド
    @GetMapping("/medicines/{medicineId}/notes/new")
    public String showNewNoteForm(@PathVariable Integer medicineId, Model model) {
        // どの薬に対する記録かをフォームに伝える
        model.addAttribute("medicineId", medicineId);
        // フォームで使うための、空のNoteオブジェクトを用意
        model.addAttribute("note", new Note());
        return "new-note"; // new-note.html を表示
    }

    //編集フォームを表示するメソッド
    @GetMapping("/medicines/edit/{id}")
    public String showEditMedicineForm(@PathVariable Integer id, Model model) {
        // IDを探索し、フォームに渡す
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid medicine Id:" + id));
        model.addAttribute("medicine", medicine);
        return "edit-medicine";
    }

    //フォームから送られたデータをDBに保存するメソッド
    @PostMapping("/medicines/{medicineId}/notes")
    public String createNote(@PathVariable Integer medicineId, Note note) {
        // どの薬に対する記録かを特定
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) {
            //エラー処理
            return "redirect:/medicines";
        }

        // Noteオブジェクトに必要な情報をセットし保存
        note.setMedicine(medicine);
        note.setCreatedAt(LocalDateTime.now());
        noteRepository.save(note);

        // 同じ薬の記録一覧画面にリダイレクト
        return "redirect:/medicines/" + medicineId + "/notes";
    }

     // 薬の新規登録フォームから送られたデータを受け取り、DBに保存するためのメソッド
    @PostMapping("/medicines")
    public String createMedicine(Medicine medicine) {
        // フォームから送られてきたmedicineオブジェクトをDBに保存
        medicineRepository.save(medicine);
        // 保存後、薬一覧画面にリダイレクト（再表示）する
        return "redirect:/medicines";
    }

    // 編集フォームから送られたデータで更新処理を行うメソッド
    @PostMapping("/medicines/update/{id}")
    public String updateMedicine(@PathVariable Integer id, Medicine medicine) {
        medicineRepository.save(medicine);

        return "redirect:/medicines";
    }


    

}