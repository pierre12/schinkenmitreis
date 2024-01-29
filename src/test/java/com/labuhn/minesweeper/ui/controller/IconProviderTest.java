package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;

import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

public class IconProviderTest {

    private final IconProvider iconProvider = new IconProvider();

    @Test
    public void createMineImage(){
        ImageView mine = iconProvider.createMineImage(100,150);

        assertThat(mine.isPreserveRatio()).isEqualTo(true);
        assertThat(mine.getFitWidth()).isEqualTo(100);
        assertThat(mine.getFitHeight()).isEqualTo(150);
        assertThat(mine.getImage()).isNotNull();
    }

    @Test
    public void createMineMarkerImage(){
        ImageView mine = iconProvider.createMineMarkerImage(100,150);

        assertThat(mine.isPreserveRatio()).isEqualTo(true);
        assertThat(mine.getFitWidth()).isEqualTo(100);
        assertThat(mine.getFitHeight()).isEqualTo(150);
        assertThat(mine.getImage()).isNotNull();
    }

    @Test
    public void createQuestionMarkMarkerImage(){
        ImageView mine = iconProvider.createQuestionMarkMarkerImage(100,150);

        assertThat(mine.isPreserveRatio()).isEqualTo(true);
        assertThat(mine.getFitWidth()).isEqualTo(100);
        assertThat(mine.getFitHeight()).isEqualTo(150);
        assertThat(mine.getImage()).isNotNull();
    }


}
