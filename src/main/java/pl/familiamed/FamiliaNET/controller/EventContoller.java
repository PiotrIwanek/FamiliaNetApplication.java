package pl.familiamed.FamiliaNET.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.familiamed.FamiliaNET.model.Event;

import pl.familiamed.FamiliaNET.services.EventService;

@Controller
@RequestMapping("v1/event")
@AllArgsConstructor
@CrossOrigin("*")
public class EventContoller {

  private EventService eventService;

  @GetMapping
  public ResponseEntity <List<Event>> getAll (){
    return ResponseEntity.ok(eventService.getAll());
  }

  @PostMapping
  public ResponseEntity<Event> add(@RequestBody Event event){
    return ResponseEntity.ok(eventService.add(event));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Long id){
    return ResponseEntity.ok(eventService.delete(id));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Event> update (@PathVariable Long id , @RequestBody Event event){
    return ResponseEntity.ok(eventService.update(id, event));
  }

}
