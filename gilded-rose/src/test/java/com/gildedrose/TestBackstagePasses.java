package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBackstagePasses {

    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void testBackstagePassesIncreaseInQuality() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void testBackstagePassesQualityIncreasesByTwoWhenSellInIs10OrLess() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void testBackstagePassesQualityIncreasesByThreeWhenSellInIs5OrLess() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void testBackstagePassesQualityIsZeroWhenSellInIsZero() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

}
