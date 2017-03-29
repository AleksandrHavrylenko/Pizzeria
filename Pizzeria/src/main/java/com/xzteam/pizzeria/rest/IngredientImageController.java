package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@RestController
public class IngredientImageController {
    private static final Logger log = Logger.getLogger(IngredientImageController.class.getName());

    @Autowired
    private ImageService dishesImagesService;

    @RequestMapping(value = "/img/ingredients/{id}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @PathVariable Long id) {
        try {
            File file = dishesImagesService.getIngredientImage(id);
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            String msg = "IOError writing file to output stream " + ex.getMessage();
            log.warning(msg);
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/img/ingredients/{id}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable Long id) {
        dishesImagesService.removeIngredientImage(id);
    }


    @RequestMapping(value = "/img/ingredients", method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //TODO: Add id in headers
            File newFile = dishesImagesService.addIngredientImage(-1, file);
            InputStream is = new FileInputStream(newFile);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            String msg = "IOError writing file to output stream " + e.getMessage();
            log.warning(msg);
            throw e;
        }
    }

}
