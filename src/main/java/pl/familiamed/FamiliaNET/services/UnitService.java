package pl.familiamed.FamiliaNET.services;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.Unit;
import pl.familiamed.FamiliaNET.model.Unit;
import pl.familiamed.FamiliaNET.repositories.UnitRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UnitService {

  private final UnitRepository unitRepository;


  public Unit create(Unit unit) {
    return unitRepository.save(unit);
  }

  public List<Unit> getAll() {
    log.info("Pobrano wszystkie jednostki");
    return unitRepository.findAll();
  }

  public Unit getById(Long id) {
    log.info("Pobrano jednostke id:  " + id);
    return unitRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono jednostki id : ", id)));
  }

  public Unit update(Long id, Unit unit) {
    final Unit unitToUpdate = unitRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono jednostki id : ", id)));
    unitToUpdate.setName(unit.getName());
    unitToUpdate.setAdress(unit.getAdress());
    unitToUpdate.setNumber(unit.getNumber());
    return unitRepository.save(unitToUpdate);
  }

  public void delete(Long id) {
    Unit unit = unitRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono jednostki id : ", id)));
    unitRepository.delete(unit);
  }
}
