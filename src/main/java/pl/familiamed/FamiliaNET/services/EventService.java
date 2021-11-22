package pl.familiamed.FamiliaNET.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.Event;
import pl.familiamed.FamiliaNET.repositories.EventRepository;

@Service
@AllArgsConstructor
public class EventService {


  private EventRepository eventRepository;


  public List<Event> getAll(){
    return eventRepository.findAll();
  }

  public Event add(Event event){
    return eventRepository.saveAndFlush(event);
  }

  public  boolean delete (Long id){
    eventRepository.deleteById(id);
    return true;
  }

  public Event update(Long id , Event event){
    Event eventToUpdate = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono zdarzenia : ", id)));

    eventToUpdate.setTitle(event.getTitle());
    eventToUpdate.setStart(event.getStart());
    eventToUpdate.setMain(event.getMain());
    eventToUpdate.setBackgroundColor(event.getBackgroundColor());

    return eventRepository.save(eventToUpdate);

  }
}
