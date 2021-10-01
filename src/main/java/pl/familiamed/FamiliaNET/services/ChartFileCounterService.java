package pl.familiamed.FamiliaNET.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.ChartFileCounter;
import pl.familiamed.FamiliaNET.repositories.ChartFileCounterRepository;

@Service
@AllArgsConstructor
public class ChartFileCounterService {

  private ChartFileCounterRepository chartFileCounterRepository;

  public long add(){
    Long counter = chartFileCounterRepository.count();
    counter++;

    chartFileCounterRepository.save(new ChartFileCounter(null , counter));

    return counter;
  }

  public long get(){
    return chartFileCounterRepository.count();
  }
}
