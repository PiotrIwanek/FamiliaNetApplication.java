package pl.familiamed.FamiliaNET.controller;


import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.familiamed.FamiliaNET.model.Category;
import pl.familiamed.FamiliaNET.services.CategoryService;


@RestController
@RequestMapping("v1/category")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

  private final CategoryService catService;

  @PostMapping
  public ResponseEntity<Object> addCategory(@RequestParam(name = "parentId", required = false) String parentId,
      @RequestParam("name") String name) {
    if (parentId.equals("null")) {
      return ResponseEntity.ok(catService.addCategory(null, name));
    }
    else {
      return ResponseEntity.ok(catService.addCategory(Long.valueOf(parentId).longValue(), name));
    }
  }

  @GetMapping("/all")
  public List<Category> getAll() {
    return catService.getAll();
  }

  @GetMapping("/tree")
  public Category getTree() {
    return catService.getCategoryTree();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getById(@PathVariable Long id) {
    return ResponseEntity.ok(catService.getCategoryById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
    catService.deleteCategory(catService.getCategoryById(id));
    return ResponseEntity.ok().build();
  }
}