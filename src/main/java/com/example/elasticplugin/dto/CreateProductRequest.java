package com.example.elasticplugin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String productName;
    private String category;
}
