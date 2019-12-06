package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.ImageSize;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlockType;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperToDtoTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void mapArticleDBEntityToDTOTest() {

        Article article = new Article();
        article.setTitle("My title");
        article.setAuthor("My author");
        article.setDescription("My description");
        article.setId(12345L);

        Set<ArticleBlock> blocks = new HashSet<>();

        TextBlock block1 = new TextBlock();
        block1.setSortIndex(1);
        block1.setText("Text1");
        blocks.add(block1);

        ImageBlock block2 = new ImageBlock();
        block2.setSortIndex(2);
        Image image = new Image();
        image.setUrl("sample url");
        block2.setImage(image);
        blocks.add(block2);

        article.setBlocks(blocks);

        ArticleDto articleDto = articleMapper.map(article);

        assert articleDto.getTitle().equals(article.getTitle());
        assert articleDto.getAuthor().equals(article.getAuthor());
        assert articleDto.getDescription().equals(article.getDescription());
        assert articleDto.getId().equals(article.getId());

        Set<Integer> sortIndexesDto = articleDto.getBlocks().stream()
                .map(ArticleBlockDto::getSortIndex)
                .collect(Collectors.toSet());

        Set<Integer> sortIndexesArticle = article.getBlocks().stream()
                .map(ArticleBlock::getSortIndex)
                .collect(Collectors.toSet());

        assert sortIndexesDto.equals(sortIndexesArticle);

    }

    @Test
    public void mapTextBlockDtoTest() {
        TextBlock textBlock = new TextBlock();

        textBlock.setText("Some sample text");

        Article article = new Article();
        article.setBlocks(Sets.newLinkedHashSet(textBlock));

        ArticleDto dto = articleMapper.map(article);

       com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock textBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock) dto.getBlocks().iterator().next();

        assert textBlock.getText().equals(textBlockDto.getText());
        assert textBlock.getSortIndex() == textBlockDto.getSortIndex();

    }


    @Test
    public void mapImageBlockDtoTest() {

        ImageBlock imageBlock = new ImageBlock();

        Image image = new Image();
        image.setUrl("imageurl");
        image.setId(12345L);
        image.setImageSize(ImageSize.SMALL);

        imageBlock.setImage(image);


        Article article = new Article();
        article.setBlocks(Sets.newLinkedHashSet(imageBlock));

        ArticleDto dto = articleMapper.map(article);

        com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock imageBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock) dto.getBlocks().iterator().next();

        ImageDto imageDto = imageBlockDto.getImage();

        assert imageBlockDto.getSortIndex() == imageBlock.getSortIndex();
        assert imageDto.getId().equals(image.getId());
        assert imageDto.getUrl().equals(image.getUrl());
        assert imageDto.getImageSize().equals(image.getImageSize());

    }


    @Test
    public void mapGalleryBlockDtoTest() {

        GalleryBlock galleryBlock = new GalleryBlock();

        Image image = new Image();
        image.setUrl("imageurl");
        image.setId(12345L);
        image.setImageSize(ImageSize.SMALL);

        galleryBlock.setImages(Lists.newArrayList(image));

        Article article = new Article();
        article.setBlocks(Sets.newLinkedHashSet(galleryBlock));

        ArticleDto dto = articleMapper.map(article);

        com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto galleryBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto) dto.getBlocks().iterator().next();

        ImageDto imageDto = galleryBlockDto.getImages().get(0);

        assert galleryBlock.getImages().size() == galleryBlockDto.getImages().size();
        assert imageDto.getId().equals(image.getId());
        assert imageDto.getUrl().equals(image.getUrl());
        assert imageDto.getImageSize().equals(image.getImageSize());
    }

    @Test
    public void mapVideoBlockDtoTest() {
        VideoBlock videoBlock = new VideoBlock();

        videoBlock.setType(VideoBlockType.VIMEO);
        videoBlock.setUrl("some/video/url");

        Article article = new Article();
        article.setBlocks(Sets.newLinkedHashSet(videoBlock));

        ArticleDto articleDto = articleMapper.map(article);

        com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock videoBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock) articleDto.getBlocks().iterator().next();

        assert videoBlock.getUrl().equals(videoBlockDto.getUrl());
        assert videoBlock.getType().equals(videoBlockDto.getType());
        assert videoBlock.getSortIndex() == videoBlockDto.getSortIndex();

    }




}
