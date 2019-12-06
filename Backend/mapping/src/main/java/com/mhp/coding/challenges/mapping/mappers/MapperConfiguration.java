package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //DB entity to DTO
        modelMapper.addConverter((context -> modelMapper.map(context.getSource(), TextBlock.class
                )), com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock.class, ArticleBlockDto.class);

        modelMapper.addConverter((context -> modelMapper.map(context.getSource(),
                ImageBlock.class)), com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock.class, ArticleBlockDto.class);

        modelMapper.addConverter((context -> modelMapper.map(context.getSource(), GalleryBlockDto.class
                )),com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock.class , ArticleBlockDto.class);

        modelMapper.addConverter((context -> modelMapper.map(context.getSource(),VideoBlock.class)),
                com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock.class, ArticleBlockDto.class);

        return modelMapper;
    }
}
