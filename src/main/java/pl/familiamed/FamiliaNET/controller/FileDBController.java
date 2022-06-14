package pl.familiamed.FamiliaNET.controller;



import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.familiamed.FamiliaNET.api.model.FileDTO;
import pl.familiamed.FamiliaNET.model.FileDB;
import pl.familiamed.FamiliaNET.services.ChartFileCounterService;
import pl.familiamed.FamiliaNET.services.FileDBService;

@RestController
@RequestMapping("v1/file")
@CrossOrigin("*")
@AllArgsConstructor
public class FileDBController {


  private final FileDBService fileDBService;
  private ChartFileCounterService chartFileCounterService;

  @PostMapping
  public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      return ResponseEntity.ok(FileDTO.fromFile(fileDBService.store(file)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }


  @PostMapping("/addChartFile")
  public ResponseEntity<FileDTO> uploadChartFile (@RequestParam("file") MultipartFile file) {
    if (fileDBService.getFileByName("chartFile") == null) {
      try {
        return ResponseEntity.ok(FileDTO.fromFile(fileDBService.storeFileWithName(file, "chartFile")));
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
    } else {
      try {
        return ResponseEntity.ok(FileDTO.fromFile(fileDBService.updateFileWithName(file, "chartFile")));
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileDTO>> getListFiles() {
    List<FileDTO> files = fileDBService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/v1/file/")
          .path(dbFile.getId())
          .toUriString();

      return new FileDTO(dbFile.getId(), dbFile.getName(), dbFile.getType(), fileDownloadUri , dbFile.isUploaded(), dbFile.getSize());
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/getChartFile")
  public ResponseEntity<FileDTO> getChartFile(){
    FileDB fileDB = fileDBService.getFileByName("chartFile");
    FileDTO fileDTO = FileDTO.fromFile(fileDB);
    return  ResponseEntity.ok(fileDTO);

  }


  @GetMapping("/byName/{name}")
  public ResponseEntity<byte[]> getChartFile(@PathVariable String name) throws IOException {
    FileDB fileDB = fileDBService.getFileByName(name);

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.parseMediaType(fileDB.getType()));
    String filename = fileDB.getName();

    headers.add("content-disposition", "inline;filename=" + filename);

    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(fileDB.getData(), headers, HttpStatus.OK);
    return response;
  }

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
    FileDB fileDB = fileDBService.getFile(id);

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.parseMediaType(fileDB.getType()));
    String filename = fileDB.getName();

    headers.add("content-disposition", "inline;filename=" + filename);

    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(fileDB.getData(), headers, HttpStatus.OK);
    return response;

  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete (@PathVariable String id){
    fileDBService.delete(id);
    return ResponseEntity.ok().build();
  }
}
