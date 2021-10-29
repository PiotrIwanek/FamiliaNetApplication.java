package pl.familiamed.FamiliaNET.services;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.PriceRecord;
import pl.familiamed.FamiliaNET.repositories.PriceRecordRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PriceRecordService {


  private final PriceRecordRepository priceRecordRepository;


  public PriceRecord create(PriceRecord priceRecord) {
    return priceRecordRepository.save(priceRecord);
  }

  public List<PriceRecord> getAll() {
    log.info("Pobrano wszystkie ceny");
    return priceRecordRepository.findAll();
  }

  public PriceRecord getById(Long id) {
    log.info("Pobrano pozycje ceny : " + id);
    return priceRecordRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono pozycji ceny : ", id)));
  }

  public PriceRecord update(Long id, PriceRecord priceRecord) {
    final PriceRecord priceRecordToUpdate = priceRecordRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono pozycji ceny : ", id)));
    priceRecordToUpdate.setName(priceRecord.getName());
    priceRecordToUpdate.setDescription(priceRecord.getDescription());
    priceRecordToUpdate.setPrice(priceRecord.getPrice());
    return priceRecordRepository.save(priceRecordToUpdate);
  }

  public void delete(Long id) {
    PriceRecord priceRecord = priceRecordRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Nie znaleziono pozycji ceny : ", id)));
    priceRecordRepository.delete(priceRecord);
  }


}
