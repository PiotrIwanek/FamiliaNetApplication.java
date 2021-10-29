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
import pl.familiamed.FamiliaNET.model.Unit;
import pl.familiamed.FamiliaNET.model.Unit;
import pl.familiamed.FamiliaNET.repositories.UnitRepository;
import pl.familiamed.FamiliaNET.services.UnitService;

@RestController
@RequestMapping("v1/unit")
@CrossOrigin("*")
@AllArgsConstructor
public class UnitController {


  private final UnitService unitService;

  @GetMapping
  public ResponseEntity<List<Unit>> getAll(){
    return ResponseEntity.ok(unitService.getAll());
  }

  @PostMapping
  public ResponseEntity<Unit> addUnit( @RequestBody Unit unit){
    return ResponseEntity.ok(unitService.create(unit));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Unit>updateUnit(@PathVariable Long id , @RequestBody Unit unit){
    return ResponseEntity.ok(unitService.update(id , unit));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean>deleteUnit(@PathVariable Long id){
    unitService.delete(id);
    return ResponseEntity.ok(true);
  }
}
