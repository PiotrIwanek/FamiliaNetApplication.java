package pl.familiamed.FamiliaNET.services;

import java.io.File;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.familiamed.FamiliaNET.model.FileDB;
import pl.familiamed.FamiliaNET.repositories.FileDBRepository;

import java.io.IOException;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileDBService {

    private FileDBRepository fileDBRepository;


    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB FileDB = new FileDB("",fileName, file.getContentType(), true ,   file.getBytes(),"" , file.getSize());
        
        return fileDBRepository.save(FileDB);
    }

    public FileDB storeFileWithName(MultipartFile file, String name) throws IOException {


        String fileName = StringUtils.cleanPath(name);

        FileDB FileDB = new FileDB("" ,fileName, file.getContentType(), true ,   file.getBytes(),"" , file.getSize());

        return fileDBRepository.save(FileDB);
    }

    public FileDB updateFileWithName (MultipartFile file , String name) throws IOException{
        FileDB fileToUpdate = fileDBRepository.findByName(name);
        return fileDBRepository.save(new FileDB( fileToUpdate.getId() , fileToUpdate.getName() , file.getContentType() , true , file.getBytes() , "" ,file.getSize()));
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public  FileDB getFileByName (String name ){ return fileDBRepository.findByName(name);}

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void delete (String id){fileDBRepository.deleteById(id);}
}
