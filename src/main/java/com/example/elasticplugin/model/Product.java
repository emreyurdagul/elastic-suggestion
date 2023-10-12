package com.example.elasticplugin.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;



@Data
@Document(indexName = "products")
@Setting(settingPath = "static/es-settings.json")
public class Product {

    @Id
    private String id;
    @Field(name = "product-name",type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String productName;
    @Field(name = "category",type = FieldType.Keyword)
    private String category;

}
