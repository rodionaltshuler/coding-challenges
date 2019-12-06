package com.mhp.coding.challenges.mapping.models.dto;


import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ArticleDto {

    private Long id;

    private String title;

    private String description;

    private String author;

    private Collection<ArticleBlockDto> blocks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Collection<ArticleBlockDto> getBlocks() {
        List<ArticleBlockDto> sorted = new ArrayList<>(blocks);
        sorted.sort(Comparator.comparingInt(ArticleBlockDto::getSortIndex));
        return sorted;
    }

    public void setBlocks(Collection<ArticleBlockDto> blocks) {
        this.blocks = blocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
