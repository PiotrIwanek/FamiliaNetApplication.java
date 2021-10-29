package pl.familiamed.FamiliaNET.controller;


import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.familiamed.FamiliaNET.model.PriceRecord;
import pl.familiamed.FamiliaNET.services.PriceRecordService;

@RestController
@RequestMapping("v1/priceRecord")
@CrossOrigin("*")
@AllArgsConstructor
public class PriceRecordController {

  private final PriceRecordService priceRecordService;

  @GetMapping
  public ResponseEntity<List<PriceRecord>> getAll(){
    return ResponseEntity.ok(priceRecordService.getAll());
  }

  @PostMapping
  public ResponseEntity<PriceRecord> addRecord( @RequestBody PriceRecord priceRecord){
    return ResponseEntity.ok(priceRecordService.create(priceRecord));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PriceRecord>updateRecord(@PathVariable Long id , @RequestBody PriceRecord priceRecord){
    return ResponseEntity.ok(priceRecordService.update(id , priceRecord));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean>deleteRecord(@PathVariable Long id){
    priceRecordService.delete(id);
    return ResponseEntity.ok(true);
  }

}
