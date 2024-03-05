package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;

import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

public class IconProviderTest {

    private final IconCreator iconProvider = new IconCreator();

    @Test
    public void createMineImage(){
        ImageView mine = iconProvider.createImage(Icons.MINE, 100,150);

        assertThat(mine.isPreserveRatio()).isEqualTo(true);
        assertThat(mine.getFitWidth()).isEqualTo(100);
        assertThat(mine.getFitHeight()).isEqualTo(150);
        assertThat(mine.getImage()).isNotNull();
    }

}
