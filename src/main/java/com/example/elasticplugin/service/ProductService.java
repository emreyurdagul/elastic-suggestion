package com.example.elasticplugin.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.example.elasticplugin.model.Product;
import com.example.elasticplugin.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ElasticsearchOperations elasticsearchOperations;


    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        product.setProductName(product.getProductName().toUpperCase(Locale.ROOT));
        return productRepository.save(product);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> autoSuggestItemsByNameWithQuery(String name) {
        Float nonExistingBoost = null;

        Query query = QueryBuilders.matchQuery("product-name",name, Operator.Or, nonExistingBoost)._toQuery();
        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
        SearchHits<Product> result = elasticsearchOperations.search(nativeQuery, Product.class);
        return result.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
