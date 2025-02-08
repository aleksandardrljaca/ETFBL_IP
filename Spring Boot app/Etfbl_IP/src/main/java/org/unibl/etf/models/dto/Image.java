package org.unibl.etf.models.dto;

import lombok.Data;

@Data
public class Image {
    private Integer id;
    private String name;
    private byte[] imageData;

}
