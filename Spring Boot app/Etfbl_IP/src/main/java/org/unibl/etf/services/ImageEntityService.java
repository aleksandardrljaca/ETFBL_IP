package org.unibl.etf.services;

import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Image;


import java.io.IOException;

public interface ImageEntityService {
    Image insert(MultipartFile file) throws IOException;
    Image insertByPath(String path) throws IOException;
    byte[] getById(Integer id) throws NotFoundException;
    void deleteById(Integer id);
}
