package pl.familiamed.FamiliaNET.api.model;



import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.familiamed.FamiliaNET.model.FileDB;


@Getter
@Setter
public class FileDTO {

    private String id;
    private String name;
    private String type;
    private String url;
    private boolean isUploaded;
    private long size;


    public FileDTO(String id, String name, String type, String url, boolean isUploaded ,  long size){
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.isUploaded = isUploaded;
        this.size = size;
    }

    public  static FileDTO fromFile (FileDB fileDB){
        String fileUrl = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/v1/file/")
            .path(fileDB.getId())
            .toUriString();
        return  new FileDTO(fileDB.getId() , fileDB.getName(), fileDB.getType() , fileUrl , fileDB.isUploaded(), fileDB.getSize());
    }


}
