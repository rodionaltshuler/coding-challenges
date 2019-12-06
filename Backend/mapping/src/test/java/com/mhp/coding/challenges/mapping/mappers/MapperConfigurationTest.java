package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperConfigurationTest {

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void mappingSetForAllBlockTypes() {

        Reflections reflections = new Reflections("com.mhp.coding.challenges");

        Set<Class<? extends ArticleBlock>> classes = reflections.getSubTypesOf(ArticleBlock.class);

        for (Class<? extends ArticleBlock> blockType : classes) {
            boolean mapped = modelMapper.getTypeMaps().stream()
                    .anyMatch(c -> c.getSourceType().equals(blockType));

            if (!mapped) {
                System.out.println();
            } else {
                System.out.println(blockType.getName() + " mapped!");
            }

            assertTrue("Block of type " + blockType.getName() + " is not mapped to corresponding DTO class ", mapped);
        }
    }


}

