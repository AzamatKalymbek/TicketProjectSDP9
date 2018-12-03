package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.Category;
import kz.teamvictus.poll.core.repository.CategoryJpaRepo;
import kz.teamvictus.poll.core.service.ICategoryService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

    @Autowired
    private CategoryJpaRepo categoryJpaRepo;

    @Override
    public Category getCategoryById(Long id) throws InternalException {
        try {
            return categoryJpaRepo.getOne(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getCategoryById", e);
        }
    }

    @Override
    public List<Category> getAllCategory() throws InternalException {
        try {
            return categoryJpaRepo.findAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllCategory", e);
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) throws InternalException {
        try {
            Category currentCategory = categoryJpaRepo.getOne(id);
            currentCategory.setIsDisabled(category.getIsDisabled());
            currentCategory.setName(category.getName());
            currentCategory.setId(category.getId());
            return categoryJpaRepo.saveAndFlush(currentCategory);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateCategory", e);
        }
    }

    @Override
    public Category addCategory(Category category) throws InternalException {
        try {
            return categoryJpaRepo.saveAndFlush(category);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addCategory", e);
        }
    }

    @Override
    public void deleteCategory(Long id) throws InternalException {
        try {
            Category currentCategory = categoryJpaRepo.getOne(id);
            currentCategory.setIsDisabled(true);
            categoryJpaRepo.saveAndFlush(currentCategory);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:deleteCategory", e);
        }
    }
}
