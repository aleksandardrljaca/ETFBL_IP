package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Image;
import org.unibl.etf.services.ImageEntityService;

import java.io.IOException;
@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {
    private final ImageEntityService imageEntityService;

    public ImageController(ImageEntityService imageEntityService) {
        this.imageEntityService = imageEntityService;
    }
    @PostMapping()
    public Image insert(@RequestPart MultipartFile file) throws IOException {
        return imageEntityService.insert(file);
    }
    @GetMapping("/{id}")
    public byte[] getImageByID(@PathVariable Integer id) throws NotFoundException {
        return imageEntityService.getById(id);
    }
}
