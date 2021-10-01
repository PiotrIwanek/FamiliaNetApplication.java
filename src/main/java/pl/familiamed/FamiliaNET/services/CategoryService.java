package pl.familiamed.FamiliaNET.services;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.familiamed.FamiliaNET.model.Category;
import pl.familiamed.FamiliaNET.repositories.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {

  private final CategoryRepository catRepo;


  public Category addCategory(Long parentId, String name) {
    if (parentId == null) {
      Category category = new Category();
      category.setName(name);
      category.setChildren(new ArrayList<Category>());
      return catRepo.save(category);
    } else {
      Category parentCategory = catRepo.findById(parentId).get();
      Category category = new Category();
      category.setName(name);
      category.setChildren(new ArrayList<Category>());
      category.setParentId(parentId);
      List<Category> categoryList = parentCategory.getChildren();
      categoryList.add(category);
      parentCategory.setChildren(categoryList);
      catRepo.save(category);
      catRepo.save(parentCategory);
      return category;
    }
  }

  public List<Category> getAll() {
    return catRepo.findByParentIdIsNull();
  }

  public Category getSpecificCategory(String name){
    return catRepo.findByNameAndParentIdIsNull(name);
  }


  public Category getCategoryTree() {
    return Category.builder().name("Tree").parentId(null).children(this.getAll()).build();
  }

  public Category getCategoryById(Long id) {
    return catRepo.findById(id).orElseThrow( () -> new IllegalArgumentException("Nie znaleziono takiej kategorii"));

  }

  public void deleteCategory(Category category) {
    if (category.getParentId() == null) {
      catRepo.delete(category);
    } else {
      Category parent = getCategoryById(category.getParentId());
      parent.getChildren().remove(category);
      catRepo.save(parent);
      catRepo.delete(category);
    }
  }
}
