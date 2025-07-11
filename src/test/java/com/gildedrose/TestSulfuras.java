package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSulfuras {

    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    void testSulfurasSellInDoesNotDecrease() {
        Item[] items = new Item[] { new Item(SULFURAS, 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void testSulfurasQualityDoesNotDecrease() {
        Item[] items = new Item[] { new Item(SULFURAS, 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

}
