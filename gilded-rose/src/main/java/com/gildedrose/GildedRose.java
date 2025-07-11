
package com.gildedrose;

public class GildedRose {

    // Item name constants
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualitySingle(items[i]);
        }
    }

    private void decreaseItemQuality(Item item, int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }

    private void decreaseItemQuality(Item item) {
        decreaseItemQuality(item, 1);
    }

    private void increaseItemQuality(Item item, int amount) {
        item.quality = Math.min(MAX_QUALITY, item.quality + amount);
    }

    private void increaseItemQuality(Item item) {
        increaseItemQuality(item, 1);
    }

    private void updateQualitySingle(Item item) {
        // Update sell-in first (except for Sulfuras)
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        // Update quality based on item type
        if (item.name.equals(SULFURAS)) {
            // Sulfuras never changes quality, do nothing
        } else if (item.name.equals(AGED_BRIE)) {
            increaseItemQuality(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            increaseItemQuality(item);

            if (item.sellIn < 10) {
                increaseItemQuality(item);
            }

            if (item.sellIn < 5) {
                increaseItemQuality(item);
            }
        } else {
            // Default item behavior
            decreaseItemQuality(item);
        }

        // Handle post-expiration behavior
        if (item.sellIn < 0) {
            if (item.name.equals(AGED_BRIE)) {
                increaseItemQuality(item);
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                item.quality = 0;
            } else if (!item.name.equals(SULFURAS)) {
                decreaseItemQuality(item);
            }
        }
    }
}
