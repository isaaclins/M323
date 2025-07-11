package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAgedBrie {

    private static final String AGED_BRIE = "Aged Brie";

    @Test
    void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

}
