package de.presti.trollv4.config.language;

import de.presti.trollv4.main.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * Class used to represent a Language.
 */
@Slf4j
public class Language {

    /**
     * The Locale-Tag of the Language.
     */
    private final String locale;

    /**
     * The Name of the Language.
     */
    private final String name;

    /**
     * The Author of the Language-File.
     */
    private final String author;

    /**
     * The Version of the Language-File.
     */
    private final String version;

    /**
     * All entries of the Language.
     */
    final HashMap<String, String> resources = new HashMap<>();

    /**
     * Constructor used to create a Language.
     * @param yamlConfiguration The YamlConfiguration of the Language.
     */
    public Language(@NonNull YamlConfiguration yamlConfiguration) {
        this.locale = yamlConfiguration.getString("language.locale");
        this.name = yamlConfiguration.getString("language.name");
        this.author = yamlConfiguration.getString("language.author");
        this.version = yamlConfiguration.getString("language.version");

        yamlConfiguration.getKeys(true).forEach(key -> {
            if (key.startsWith("language.")) return;

            resources.putIfAbsent(key, yamlConfiguration.getString(key));
        });
    }

    /**
     * Constructor used to create a Language.
     * @param locale The Locale-Tag of the Language.
     * @param name The Name of the Language.
     * @param author The Author of the Language-File.
     * @param version The Version of the Language-File.
     * @param resources All entries of the Language.
     */
    public Language(@NonNull String locale, @NonNull String name, @NonNull String author, @NonNull String version, @NonNull HashMap<String, String> resources) {
        this.locale = locale;
        this.name = name;
        this.author = author;
        this.version = version;
        this.resources.putAll(resources);
    }

    /**
     * Called to get the Locale-Tag of the Language.
     * @return The Locale-Tag.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Called to get the Name of the Language.
     * @return The Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Called to get the Author of the Language-File.
     * @return The Author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Called to get the Version of the Language-File.
     * @return The Version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Called to get the entry of the Language.
     * @param key The key of the entry.
     * @param parameter The parameter that should be used to replace placeholders.
     * @return The entry.
     */
    public String getResource(@NonNull String key, @Nullable Object... parameter) {
        if (!resources.containsKey(key)) {
            log.info("Missing Language-Entry: {}", key);
            return String.format("Couldn't find %s! (Regenerate message.yml)", key);
        }
        try {
            return String.format(resources.get(key), parameter).replaceAll("&", "§").replace("[VERSION]", Data.version);
        } catch (Exception e) {
            log.error("Error while formatting language resource! ({})", key, e);
            return String.format("Error when formatting %s! (Regenerate message.yml)", key);
        }
    }

    /**
     * Called to get the entry of the Language.
     * @param key The key of the entry.
     * @param parameter The parameter that should be used to replace placeholders.
     * @return The entry.
     */
    public String getResource(@NonNull String key, @NonNull Player player, @Nullable Object... parameter) {
        return getResource(key, parameter).replace("[PLAYER]", player.getName());
    }

    /**
     * Compare the current Language version with another Language.
     * @param language The Language to compare with.
     * @return The result of the comparison. True, if it should update | False, if it should not be updated.
     */
    public boolean compareVersion(Language language) {
        if (language == null) return false;
        if (language.getVersion() == null) return false;
        if (version == null) return true;
        if (language.getVersion().equals(version)) return false;

        String[] split = version.split("\\.");

        int mayor = Integer.parseInt(split[0]);
        int minor = Integer.parseInt(split[1]);
        int patch = Integer.parseInt(split[2]);

        String[] split2 = language.getVersion().split("\\.");
        int otherMayor = Integer.parseInt(split2[0]);
        int otherMinor = Integer.parseInt(split2[1]);
        int otherPatch = Integer.parseInt(split2[2]);

        if (otherMayor > mayor) return true;
        if (otherMayor == mayor && otherMinor > minor) return true;
        return otherMayor == mayor && otherMinor == minor && otherPatch > patch;
    }
}
