package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.rest.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Provide add, update, get, remove images from file system
 */
@Service
public class ImageService {

    private final static String SUB_PATH_DISH = "dishes/";
    private final static String SUB_PATH_INGREDIENT = "ingredients/";
    private final static String NO_IMG_RES_PATH = "classpath:/static/img/no-photo.jpg";
    private final List<String> formats = Arrays.asList(".jpg", ".jpeg", ".png");

    @Value("${myserver.imgpath}")
    private String basePath;

    public File getDishImage(long id) {
        return this.getImage(id, SUB_PATH_DISH);
    }

    public void removeDishImage(long id) {
        this.removeImage(id, SUB_PATH_DISH);
    }

    public File addDishImage(long id, MultipartFile file) throws Exception {
        return this.addImage(id, file, SUB_PATH_DISH);
    }

    public File getIngredientImage(long id) {
        return this.getImage(id, SUB_PATH_INGREDIENT);
    }

    public void removeIngredientImage(long id) {
        this.removeImage(id, SUB_PATH_INGREDIENT);
    }

    public File addIngredientImage(long id, MultipartFile file) throws Exception {
        return this.addImage(id, file, SUB_PATH_INGREDIENT);
    }

    private File getNotImageFile() {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resNoPhoto = loader.getResource(NO_IMG_RES_PATH);
        if (resNoPhoto.exists()) {
            try {
                return resNoPhoto.getFile();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    private File getImage(long id, String subPath) {
        String path = basePath + subPath + id;
        for (String format : formats) {
            File file = new File(path + format);
            if (file.exists()) {
                return file;
            }
        }
        return getNotImageFile();
    }

    private File addImage(long id, MultipartFile file, String subPath) throws Exception {
        String name = file.getOriginalFilename();
        String[] arr = name.split("\\.");
        String ext = '.' + arr[arr.length - 1];
        if (!formats.contains(ext)) {
            String msg = "It is not supported file format: " + ext;
            throw new BadRequestException(msg);
        }
        String saveName = id + ext;
        File newFile = new File(basePath + subPath + name);
        file.transferTo(newFile);
        return newFile;
    }

    @SuppressWarnings("all")
    private void removeImage(long id, String subPath) {
        String path = basePath + subPath + id;
        for (String format : formats) {
            File file = new File(path + format);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
