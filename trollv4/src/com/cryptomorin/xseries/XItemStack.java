/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Crypto Morin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.cryptomorin.xseries;

import com.google.common.base.Enums;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * <b>XItemStack</b> - YAML Item Serializer<br>
 * Using ConfigurationSection Example:
 * <pre>
 *     ConfigurationSection section = plugin.getConfig().getConfigurationSection("staffs.dragon-staff");
 *     ItemStack item = XItemStack.deserialize(section);
 * </pre>
 * ItemStack: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/ItemStack.html
 *
 * @author Crypto Morin
 * @version 4.0.1.1
 * @see XMaterial
 * @see XPotion
 * @see SkullUtils
 * @see XEnchantment
 * @see ItemStack
 */
public final class XItemStack {
    private XItemStack() {
    }

    /**
     * Writes an ItemStack object into a config.
     * The config file will not save after the object is written.
     *
     * @param item   the ItemStack to serialize.
     * @param config the config section to write this item to.
     * @since 1.0.0
     */
    @SuppressWarnings("deprecation")
    public static void serialize(@Nonnull ItemStack item, @Nonnull ConfigurationSection config) {
        Objects.requireNonNull(item, "Cannot serialize a null item");
        Objects.requireNonNull(config, "Cannot serialize item from a null configuration section.");
        ItemMeta meta = item.getItemMeta();

        if (meta.hasDisplayName()) config.set("name", ChatColor.stripColor(meta.getDisplayName()));
        if (meta.hasLore()) {
            List<String> lines = new ArrayList<>();
            for (String lore : meta.getLore()) lines.add(ChatColor.stripColor(lore));
            config.set("lore", lines);
        }
        if (item.getAmount() > 1) config.set("amount", item.getAmount());
        if (XMaterial.isNewVersion()) {
            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                if (damageable.hasDamage()) config.set("damage", damageable.getDamage());
            }
        } else if (item.getType().getMaxDurability() > 0) {
            config.set("damage", item.getDurability());
        }
        config.set("material", item.getType().name());
        if (meta.hasCustomModelData()) config.set("custom-model", meta.getCustomModelData());
        if (meta.isUnbreakable()) config.set("unbreakable", true);

        // Enchantments
        for (Map.Entry<Enchantment, Integer> enchant : meta.getEnchants().entrySet()) {
            String entry = "enchants." + XEnchantment.matchXEnchantment(enchant.getKey()).name();
            config.set(entry, enchant.getValue());
        }

        // Flags
        if (meta.getItemFlags().size() != 0) {
            List<String> flags = new ArrayList<>();
            for (ItemFlag flag : meta.getItemFlags()) flags.add(flag.name());
            config.set("flags", flags);
        }

        // Attributes - https://minecraft.gamepedia.com/Attribute
        if (XMaterial.supports(9) && meta.hasAttributeModifiers()) {
            for (Map.Entry<Attribute, AttributeModifier> attribute : meta.getAttributeModifiers().entries()) {
                String path = "attributes." + attribute.getKey().name() + '.';
                AttributeModifier modifier = attribute.getValue();

                config.set(path + "id", modifier.getUniqueId().toString());
                config.set(path + "name", modifier.getName());
                config.set(path + "amount", modifier.getAmount());
                config.set(path + "operation", modifier.getOperation().name());
                config.set(path + "slot", modifier.getSlot().name());
            }
        }

        if (meta instanceof SkullMeta) {
            config.set("skull", SkullUtils.getSkinValue(meta));
        } else if (meta instanceof BannerMeta) {
            BannerMeta banner = (BannerMeta) meta;
            ConfigurationSection patterns = config.createSection("patterns");

            for (Pattern pattern : banner.getPatterns()) {
                patterns.set(pattern.getPattern().name(), pattern.getColor().name());
            }
        } else if (meta instanceof LeatherArmorMeta) {
            LeatherArmorMeta leather = (LeatherArmorMeta) meta;
            Color color = leather.getColor();
            config.set("color", color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
        } else if (meta instanceof PotionMeta) {
            PotionMeta potion = (PotionMeta) meta;
            List<String> effects = new ArrayList<>();
            for (PotionEffect effect : potion.getCustomEffects())
                effects.add(effect.getType().getName() + ", " + effect.getDuration() + ", " + effect.getAmplifier());

            config.set("effects", effects);
            PotionData potionData = potion.getBasePotionData();
            config.set("base-effect", potionData.getType().name() + ", " + potionData.isExtended() + ", " + potionData.isUpgraded());

            if (potion.hasColor()) {
                config.set("color", potion.getColor().asRGB());
            }
        } else if (meta instanceof FireworkMeta) {
            FireworkMeta firework = (FireworkMeta) meta;
            config.set("power", firework.getPower());
            int i = 0;

            for (FireworkEffect fw : firework.getEffects()) {
                ConfigurationSection fwc = config.getConfigurationSection("firework." + i);
                fwc.set("type", fw.getType().name());

                List<String> colors = new ArrayList<>();
                for (Color color : fw.getColors()) colors.add(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
                fwc.set("colors", colors);

                colors.clear();
                for (Color color : fw.getFadeColors()) colors.add(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
                fwc.set("fade-colors", colors);
            }
            //} else if (meta instanceof MapMeta) {
        } else if (XMaterial.supports(14)) {
            if (meta instanceof CrossbowMeta) {
                CrossbowMeta crossbow = (CrossbowMeta) meta;
                int i = 0;
                for (ItemStack projectiles : crossbow.getChargedProjectiles()) {
                    serialize(projectiles, config.getConfigurationSection("projectiles." + i));
                    i++;
                }
            } else if (meta instanceof TropicalFishBucketMeta) {
                TropicalFishBucketMeta tropical = (TropicalFishBucketMeta) meta;
                config.set("pattern", tropical.getPattern().name());
                config.set("color", tropical.getBodyColor().name());
                config.set("pattern-color", tropical.getPatternColor().name());
            } else if (meta instanceof SuspiciousStewMeta) {
                SuspiciousStewMeta stew = (SuspiciousStewMeta) meta;
                List<String> effects = new ArrayList<>();
                for (PotionEffect effect : stew.getCustomEffects()) {
                    effects.add(effect.getType().getName() + ", " + effect.getDuration() + ", " + effect.getAmplifier());
                }

                config.set("effects", effects);
            }
        }
    }

    /**
     * Deserialize an ItemStack from the config.
     *
     * @param config the config section to deserialize the ItemStack object from.
     * @return a deserialized ItemStack.
     * @since 1.0.0
     */
    @SuppressWarnings("deprecation")
    @Nullable
    public static ItemStack deserialize(@Nonnull ConfigurationSection config) {
        Objects.requireNonNull(config, "Cannot deserialize item to a null configuration section.");

        // Material
        String material = config.getString("material");
        if (material == null) return null;
        Optional<XMaterial> matOpt = XMaterial.matchXMaterial(material);
        if (!matOpt.isPresent()) return null;

        // Build
        ItemStack item = matOpt.get().parseItem();
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();

        // Amount
        int amount = config.getInt("amount");
        if (amount > 1) item.setAmount(amount);

        // Durability - Damage
        if (XMaterial.isNewVersion()) {
            if (meta instanceof Damageable) {
                int damage = config.getInt("damage");
                if (damage > 0) ((Damageable) meta).setDamage(damage);
            }
        } else {
            int damage = config.getInt("damage");
            if (damage > 0) item.setDurability((short) damage);
        }

        // Special Items
        if (matOpt.get() == XMaterial.PLAYER_HEAD) {
            String skull = config.getString("skull");
            if (skull != null) SkullUtils.applySkin(meta, skull);
        } else if (meta instanceof BannerMeta) {
            BannerMeta banner = (BannerMeta) meta;
            ConfigurationSection patterns = config.getConfigurationSection("patterns");

            if (patterns != null) {
                for (String pattern : patterns.getKeys(false)) {
                    PatternType type = PatternType.getByIdentifier(pattern);
                    if (type == null) type = Enums.getIfPresent(PatternType.class, pattern.toUpperCase(Locale.ENGLISH)).or(PatternType.BASE);
                    DyeColor color = Enums.getIfPresent(DyeColor.class, patterns.getString(pattern).toUpperCase(Locale.ENGLISH)).or(DyeColor.WHITE);

                    banner.addPattern(new Pattern(color, type));
                }
            }
        } else if (meta instanceof LeatherArmorMeta) {
            LeatherArmorMeta leather = (LeatherArmorMeta) meta;
            String colorStr = config.getString("color");
            if (colorStr != null) {
                leather.setColor(parseColor(colorStr));
            }
        } else if (meta instanceof PotionMeta) {
            PotionMeta potion = (PotionMeta) meta;
            for (String effects : config.getStringList("effects")) {
                PotionEffect effect = XPotion.parsePotionEffectFromString(effects);
                potion.addCustomEffect(effect, true);
            }

            String baseEffect = config.getString("base-effect");
            if (!Strings.isNullOrEmpty(baseEffect)) {
                String[] split = StringUtils.split(baseEffect, ',');
                PotionType type = Enums.getIfPresent(PotionType.class, split[0].trim().toUpperCase(Locale.ENGLISH)).or(PotionType.UNCRAFTABLE);
                boolean extended = split.length != 1 && Boolean.parseBoolean(split[1].trim());
                boolean upgraded = split.length > 2 && Boolean.parseBoolean(split[2].trim());

                PotionData potionData = new PotionData(type, extended, upgraded);
                potion.setBasePotionData(potionData);
            }

            if (config.contains("color")) {
                potion.setColor(Color.fromRGB(config.getInt("color")));
            }
        } else if (meta instanceof BlockStateMeta) {
            BlockStateMeta bsm = (BlockStateMeta) meta;
            BlockState state = bsm.getBlockState();

            if (state instanceof CreatureSpawner) {
                CreatureSpawner spawner = (CreatureSpawner) state;
                spawner.setSpawnedType(Enums.getIfPresent(EntityType.class, config.getString("spawner").toUpperCase(Locale.ENGLISH)).orNull());
                spawner.update(true);
                bsm.setBlockState(spawner);
            } else if (state instanceof Banner) {
                Banner banner = (Banner) state;
                ConfigurationSection patterns = config.getConfigurationSection("patterns");

                if (patterns != null) {
                    for (String pattern : patterns.getKeys(false)) {
                        PatternType type = PatternType.getByIdentifier(pattern);
                        if (type == null) type = Enums.getIfPresent(PatternType.class, pattern.toUpperCase(Locale.ENGLISH)).or(PatternType.BASE);
                        DyeColor color = Enums.getIfPresent(DyeColor.class, patterns.getString(pattern).toUpperCase(Locale.ENGLISH)).or(DyeColor.WHITE);

                        banner.addPattern(new Pattern(color, type));
                    }

                    banner.update(true);
                    bsm.setBlockState(banner);
                }
            }
        } else if (meta instanceof FireworkMeta) {
            FireworkMeta firework = (FireworkMeta) meta;
            firework.setPower(config.getInt("power"));

            ConfigurationSection fireworkSection = config.getConfigurationSection("firework");
            if (fireworkSection != null) {
                FireworkEffect.Builder builder = FireworkEffect.builder();
                for (String fws : fireworkSection.getKeys(false)) {
                    ConfigurationSection fw = config.getConfigurationSection("firework." + fws);

                    builder.flicker(fw.getBoolean("flicker"));
                    builder.trail(fw.getBoolean("trail"));
                    builder.with(Enums.getIfPresent(FireworkEffect.Type.class, fw.getString("type").toUpperCase(Locale.ENGLISH)).or(FireworkEffect.Type.STAR));

                    List<Color> colors = new ArrayList<>();
                    for (String colorStr : fw.getStringList("colors")) {
                        colors.add(parseColor(colorStr));
                    }
                    builder.withColor(colors);

                    colors.clear();
                    for (String colorStr : fw.getStringList("fade-colors")) {
                        colors.add(parseColor(colorStr));
                    }
                    builder.withFade(colors);

                    firework.addEffect(builder.build());
                }
            }
            //} else if (meta instanceof MapMeta) {
            // TODO This is a little bit too complicated.
            //MapMeta map = (MapMeta) meta;
        } else if (XMaterial.supports(14)) {
            if (meta instanceof CrossbowMeta) {
                CrossbowMeta crossbow = (CrossbowMeta) meta;
                for (String projectiles : config.getConfigurationSection("projectiles").getKeys(false)) {
                    ItemStack projectile = deserialize(config.getConfigurationSection("projectiles." + projectiles));
                    crossbow.addChargedProjectile(projectile);
                }
            } else if (meta instanceof TropicalFishBucketMeta) {
                TropicalFishBucketMeta tropical = (TropicalFishBucketMeta) meta;
                DyeColor color = Enums.getIfPresent(DyeColor.class, config.getString("color")).or(DyeColor.WHITE);
                DyeColor patternColor = Enums.getIfPresent(DyeColor.class, config.getString("pattern-color")).or(DyeColor.WHITE);
                TropicalFish.Pattern pattern = Enums.getIfPresent(TropicalFish.Pattern.class, config.getString("pattern")).or(TropicalFish.Pattern.BETTY);

                tropical.setBodyColor(color);
                tropical.setPatternColor(patternColor);
                tropical.setPattern(pattern);
            }
            // Apparently Suspicious Stew was never added in 1.14
        } else if (XMaterial.supports(15)) {
            if (meta instanceof SuspiciousStewMeta) {
                SuspiciousStewMeta stew = (SuspiciousStewMeta) meta;
                for (String effects : config.getStringList("effects")) {
                    PotionEffect effect = XPotion.parsePotionEffectFromString(effects);
                    stew.addCustomEffect(effect, true);
                }
            }
        }

        // Display Name
        String name = config.getString("name");
        if (!Strings.isNullOrEmpty(name)) {
            String translated = ChatColor.translateAlternateColorCodes('&', name);
            meta.setDisplayName(translated);
        }

        // Unbreakable
        if (XMaterial.supports(11)) meta.setUnbreakable(config.getBoolean("unbreakable"));

        // Custom Model Data
        if (XMaterial.supports(14)) {
            int modelData = config.getInt("model-data");
            if (modelData != 0) meta.setCustomModelData(modelData);
        }

        // Lore
        List<String> lores = config.getStringList("lore");
        if (!lores.isEmpty()) {
            List<String> translatedLore = new ArrayList<>();
            String lastColors = "";

            for (String lore : lores) {
                if (lore.isEmpty()) {
                    translatedLore.add(" ");
                    continue;
                }

                for (String singleLore : StringUtils.splitPreserveAllTokens(lore, '\n')) {
                    if (singleLore.isEmpty()) {
                        translatedLore.add(" ");
                        continue;
                    }
                    singleLore = lastColors + ChatColor.translateAlternateColorCodes('&', singleLore);
                    translatedLore.add(singleLore);

                    lastColors = ChatColor.getLastColors(singleLore);
                }
            }

            meta.setLore(translatedLore);
        } else {
            String lore = config.getString("lore");
            if (!Strings.isNullOrEmpty(lore)) {
                List<String> translatedLore = new ArrayList<>();
                String lastColors = "";

                for (String singleLore : StringUtils.splitPreserveAllTokens(lore, '\n')) {
                    if (singleLore.isEmpty()) {
                        translatedLore.add(" ");
                        continue;
                    }
                    singleLore = lastColors + ChatColor.translateAlternateColorCodes('&', singleLore);
                    translatedLore.add(singleLore);

                    lastColors = ChatColor.getLastColors(singleLore);
                }

                meta.setLore(translatedLore);
            }
        }

        // Enchantments
        ConfigurationSection enchants = config.getConfigurationSection("enchants");
        if (enchants != null) {
            for (String ench : enchants.getKeys(false)) {
                Optional<XEnchantment> enchant = XEnchantment.matchXEnchantment(ench);
                enchant.ifPresent(xEnchantment -> meta.addEnchant(xEnchantment.parseEnchantment(), enchants.getInt(ench), true));
            }
        }

        // Flags
        List<String> flags = config.getStringList("flags");
        for (String flag : flags) {
            flag = flag.toUpperCase(Locale.ENGLISH);
            if (flag.equals("ALL")) {
                meta.addItemFlags(ItemFlag.values());
                break;
            }

            ItemFlag itemFlag = Enums.getIfPresent(ItemFlag.class, flag).orNull();
            if (itemFlag != null) meta.addItemFlags(itemFlag);
        }

        // Atrributes - https://minecraft.gamepedia.com/Attribute
        if (XMaterial.supports(9)) {
            ConfigurationSection attributes = config.getConfigurationSection("attributes");
            if (attributes != null) {
                for (String attribute : attributes.getKeys(false)) {
                    Attribute attributeInst = Enums.getIfPresent(Attribute.class, attribute.toUpperCase(Locale.ENGLISH)).orNull();
                    if (attributeInst == null) continue;

                    String attribId = attributes.getString("id");
                    UUID id = attribId != null ? UUID.fromString(attribId) : UUID.randomUUID();

                    AttributeModifier modifier = new AttributeModifier(
                            id,
                            attributes.getString("name"),
                            attributes.getInt("amount"),
                            Enums.getIfPresent(AttributeModifier.Operation.class, attributes.getString("operation"))
                                    .or(AttributeModifier.Operation.ADD_NUMBER),
                            Enums.getIfPresent(EquipmentSlot.class, attributes.getString("slot")).or(EquipmentSlot.HAND));

                    meta.addAttributeModifier(attributeInst, modifier);
                }
            }
        }

        item.setItemMeta(meta);
        return item;
    }

    /**
     * Parses RGB color codes from a string.
     * This only works for 1.13 and above.
     *
     * @param str the RGB string.
     * @return a color based on the RGB.
     * @since 1.1.0
     */
    @Nonnull
    public static Color parseColor(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) return Color.BLACK;
        String[] rgb = StringUtils.split(StringUtils.deleteWhitespace(str), ',');
        if (rgb.length < 3) return Color.WHITE;
        return Color.fromRGB(NumberUtils.toInt(rgb[0], 0), NumberUtils.toInt(rgb[1], 0), NumberUtils.toInt(rgb[2], 0));
    }

    /**
     * Adds a list of items to the player's inventory and drop the items that did not fit.
     *
     * @param player the player to give the items to.
     * @param items  the items to give.
     * @return the items that did not fit and were dropped.
     * @since 2.0.1
     */
    @Nonnull
    public static List<ItemStack> giveOrDrop(@Nonnull Player player, @Nullable ItemStack... items) {
        return giveOrDrop(player, false, items);
    }

    /**
     * Adds a list of items to the player's inventory and drop the items that did not fit.
     *
     * @param player the player to give the items to.
     * @param items  the items to give.
     * @param split  same as {@link #addItems(Inventory, boolean, ItemStack...)}
     * @return the items that did not fit and were dropped.
     * @since 2.0.1
     */
    @Nonnull
    public static List<ItemStack> giveOrDrop(@Nonnull Player player, boolean split, @Nullable ItemStack... items) {
        if (items == null || items.length == 0) return new ArrayList<>();
        List<ItemStack> leftOvers = addItems(player.getInventory(), split, items);
        World world = player.getWorld();
        Location location = player.getLocation();

        for (ItemStack drop : leftOvers) world.dropItemNaturally(location, drop);
        return leftOvers;
    }

    /**
     * Optimized version of {@link Inventory#addItem(ItemStack...)}
     * https://hub.spigotmc.org/stash/projects/SPIGOT/repos/craftbukkit/browse/src/main/java/org/bukkit/craftbukkit/inventory/CraftInventory.java
     *
     * @param inventory the inventory to add the items to.
     * @param split     if it should check for the inventory stack size {@link Inventory#getMaxStackSize()} or
     *                  item's max stack size {@link ItemStack#getMaxStackSize()} when putting items. This is useful when
     *                  you're adding stacked tools such as swords that you'd like to split them to other slots.
     * @param items     the items to add.
     * @return items that didn't fit in the inventory.
     * @since 4.0.0
     */
    @Nonnull
    public static List<ItemStack> addItems(@Nonnull Inventory inventory, boolean split, @Nonnull ItemStack... items) {
        Objects.requireNonNull(inventory, "Cannot add items to null inventory");
        Objects.requireNonNull(items, "Cannot add null items to inventory");

        List<ItemStack> leftOvers = new ArrayList<>(items.length);
        int lastEmpty = 0;
        int invSize = inventory.getSize();

        for (ItemStack item : items) {
            int partialIndex = 0;

            while (true) {
                int firstPartial = firstPartial(inventory, item, partialIndex);
                if (firstPartial == -1) {
                    if (lastEmpty != -1) lastEmpty = firstEmpty(inventory, lastEmpty);
                    if (lastEmpty == -1) {
                        leftOvers.add(item);
                        break;
                    }
                    partialIndex = lastEmpty;

                    int maxSize = split ? item.getMaxStackSize() : inventory.getMaxStackSize();
                    int amount = item.getAmount();
                    if (amount <= maxSize) {
                        inventory.setItem(lastEmpty, item);
                        break;
                    } else {
                        ItemStack copy = item.clone();
                        copy.setAmount(maxSize);
                        inventory.setItem(lastEmpty, copy);
                        item.setAmount(amount - maxSize);
                    }
                    if (++lastEmpty == invSize) lastEmpty = -1;
                } else {
                    ItemStack partialItem = inventory.getItem(firstPartial);
                    int maxAmount = partialItem.getMaxStackSize();
                    int partialAmount = partialItem.getAmount();
                    int amount = item.getAmount();
                    int sum = amount + partialAmount;

                    if (sum <= maxAmount) {
                        partialItem.setAmount(sum);
                        inventory.setItem(firstPartial, partialItem);
                        break;
                    } else {
                        partialItem.setAmount(maxAmount);
                        inventory.setItem(firstPartial, partialItem);
                        item.setAmount(sum - maxAmount);
                    }
                    if (sum >= maxAmount) partialIndex = firstPartial + 1;
                }
            }
        }

        return leftOvers;
    }

    /**
     * Gets the item index in the inventory that matches the given item argument.
     * The matched item must be {@link ItemStack#isSimilar(ItemStack)} and has not
     * reached its {@link ItemStack#getMaxStackSize()} for the inventory.
     *
     * @param inventory  the inventory to match the item from.
     * @param item       the item to match.
     * @param beginIndex the index which to start the search from in the inventory.
     * @return the index of the matched item, otherwise -1
     * @throws IndexOutOfBoundsException if the beginning index is less than 0 or greater than the inventory storage size.
     * @since 4.0.0
     */
    public static int firstPartial(@Nonnull Inventory inventory, @Nullable ItemStack item, int beginIndex) {
        if (item != null) {
            ItemStack[] items = inventory.getStorageContents();
            int len = items.length;
            if (beginIndex < 0 || beginIndex >= len) throw new IndexOutOfBoundsException("Begin Index: " + beginIndex + ", Size: " + len);

            for (; beginIndex < len; beginIndex++) {
                ItemStack cItem = items[beginIndex];
                if (cItem != null && cItem.getAmount() < cItem.getMaxStackSize() && cItem.isSimilar(item)) return beginIndex;
            }
        }
        return -1;
    }

    /**
     * Stacks up the items in the given item collection that are {@link ItemStack#isSimilar(ItemStack)}.
     *
     * @param items the items to stack.
     * @return stacked up items.
     * @since 4.0.0
     */
    @Nonnull
    public static List<ItemStack> stack(@Nonnull Collection<ItemStack> items) {
        Objects.requireNonNull(items, "Cannot stack null items");
        List<ItemStack> stacked = new ArrayList<>();

        for (ItemStack item : items) {
            if (item == null) continue;

            boolean add = true;
            for (ItemStack stack : stacked) {
                if (stack.isSimilar(item)) {
                    stack.setAmount(stack.getAmount() + item.getAmount());
                    add = false;
                    break;
                }
            }

            if (add) stacked.add(item.clone());
        }
        return stacked;
    }

    /**
     * Gets the first empty slot in the inventory from an index.
     *
     * @param inventory  the inventory to search from.
     * @param beginIndex the item index to start our search from in the inventory.
     * @return first empty item index, otherwise -1
     * @throws IndexOutOfBoundsException if the beginning index is less than 0 or greater than the inventory storage size.
     * @since 4.0.0
     */
    public static int firstEmpty(@Nonnull Inventory inventory, int beginIndex) {
        ItemStack[] items = inventory.getStorageContents();
        int len = items.length;
        if (beginIndex < 0 || beginIndex >= len) throw new IndexOutOfBoundsException("Begin Index: " + beginIndex + ", Size: " + len);

        for (; beginIndex < len; beginIndex++) {
            if (items[beginIndex] == null) return beginIndex;
        }
        return -1;
    }
}
