package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.services.DishService;
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
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class DishImageController {
    private static final Logger log = Logger.getLogger(DishImageController.class.getName());
    @Autowired
    private DishService dishService;
    @Autowired
    private ImageService dishesImagesService;

    @RequestMapping(value = "/img/dishes/{id}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @PathVariable Long id) {
        try {
            File file = dishesImagesService.getDishImage(id);
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            String msg = "IOError writing file to output stream " + ex.getMessage();
            log.warning(msg);
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/img/dishes/{id}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable Long id) {
        dishesImagesService.removeDishImage(id);
    }

    @RequestMapping(value = "/img/dishes/random", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
        long id = dishService.getAll().get(new Random().nextInt(dishService.getAll().size())).getId();
        try {
            File file = dishesImagesService.getDishImage(id);
            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            String msg = "IOError writing file to output stream " + e.getMessage();
            log.warning(msg);
        }
    }

    @RequestMapping(value = "/img/dishes", method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //TODO: Add id in headers
            File newFile = dishesImagesService.addDishImage(-1, file);
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
