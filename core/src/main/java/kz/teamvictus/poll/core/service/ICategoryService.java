package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.Category;
import kz.teamvictus.utils.error.InternalException;
import org.springframework.boot.context.config.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    Category getCategoryById(Long id) throws InternalException;
    List<Category> getAllCategory() throws InternalException;
    Category updateCategory(Long id, Category category) throws InternalException;
    Category addCategory(Category category) throws InternalException;
    void deleteCategory(Long id) throws InternalException;
}
