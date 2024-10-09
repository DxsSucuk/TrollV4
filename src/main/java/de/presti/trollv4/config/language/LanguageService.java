package de.presti.trollv4.config.language;

import de.presti.trollv4.api.RequestUtility;
import de.presti.trollv4.config.Config;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Objects;

/**
 * Utility used to work with Languages.
 */
@Slf4j
public class LanguageService {

    /**
     * A Hashmap containing the locale as key and the YamlConfiguration as value.
     */
    public static final HashMap<String, Language> languageResources = new HashMap<>();

    /**
     * Called to load every Language file into memory.
     */
    public static void initializeLanguages() {
        Path languagePath = getLanguagePath();

        try {
            for (File file : Objects.requireNonNull(languagePath.toFile().listFiles())) {
                if (!file.getName().endsWith(".yml") && !file.getName().endsWith(".yaml")) {
                    log.info("Skipping file {} because it's not a YAML file!", file.getName());
                    continue;
                }

                try {
                    loadLanguageFromInstance(new Language(YamlConfiguration.loadConfiguration(file)));
                } catch (Exception e) {
                    log.error("Couldn't load the language file {}!", file.getName(), e);
                }
            }
        } catch (Exception e) {
            log.error("Couldn't load the language files!", e);
        }
    }

    /**
     * Called to download every Language file from the GitHub Repository.
     */
    public static void downloadLanguages() {
        File languagePath = getLanguagePath().toFile();
        if (!languagePath.exists()) {
            if (languagePath.mkdirs()) {
                log.info("Created the language folder!");
            } else {
                log.error("Couldn't create the language folder!");
            }
        }

        try {
            RequestUtility.getJSON("https://api.github.com/repos/DxsSucuk/Trollv4/contents/languages").getAsJsonArray().forEach(jsonElement -> {
                String language = jsonElement.getAsJsonObject().get("name").getAsString().replace(".yml", "");
                String download = jsonElement.getAsJsonObject().get("download_url").getAsString();

                Path languageFile = new File(getLanguagePath().toFile(), language + ".yml").toPath();

                if (!languageFile.toAbsolutePath().startsWith(languagePath.toPath().toAbsolutePath())) {
                    log.info("Ignoring Language download, since Path Traversal has been detected!");
                    return;
                }

                log.info("Downloading Language: {}", language);

                byte[] contentByte = RequestUtility.getBytes(download);

                if (contentByte == null) return;

                try {
                    String content = IOUtils.toString(contentByte, StandardCharsets.UTF_8.toString());

                    if (Files.exists(languageFile)) {
                        StringReader stringReader = new StringReader(content);

                        log.info("Version comparing: {}!", language);
                        YamlConfiguration newLanguageYaml = YamlConfiguration.loadConfiguration(stringReader);
                        Language newLanguage = new Language(newLanguageYaml);
                        Language oldLanguage = new Language(YamlConfiguration.loadConfiguration(languageFile.toFile()));
                        if (oldLanguage.compareVersion(newLanguage)) {
                            log.info("Language file {} is outdated! Will update!", language);
                            if (!languageFile.toFile().delete()) {
                                log.info("Failed to delete old Language file {}!", language);
                            }

                            // Not using YamlConfiguration#save, since that method breaks the whole file somehow? Unsure why?
                            Files.write(languageFile, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

                            log.info("Updated Language file {}!", language);

                            if (languageResources.remove(oldLanguage.getLocale()) != null) {
                                log.info("Removed old Language of {} from memory!", oldLanguage.getLocale());
                            }
                        } else {
                            log.info("Language file {} is up to date!", language);
                        }
                    } else {
                        Files.write(languageFile, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                        log.info("Downloaded Language: {}", language);
                    }
                } catch (Exception e) {
                    log.error("Couldn't write the language file {}!", language, e);
                }
            });
        } catch (Exception exception) {
            log.error("An error occurred while downloading the language files!", exception);
        }

        initializeLanguages();
    }

    /**
     * Called to load a Language from a YamlConfiguration.
     *
     * @param local The local of the Language.
     * @return The Language.
     */
    public static @Nullable Language loadLanguageFromFile(@NonNull String local) {
        Path languageFile = new File(getLanguagePath().toFile(), local + ".yml").toPath();
        if (Files.exists(languageFile)) {
            try {
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(languageFile.toFile());
                Language language = new Language(yamlConfiguration);
                return loadLanguageFromInstance(language);
            } catch (Exception exception) {
                log.error("Error while getting Language File!", exception);
                return null;
            }
        } else {
            return null;
        }
    }

    public static @Nullable Language loadLanguageFromInstance(@Nullable Language language) {
        if (language == null) return null;

        languageResources.putIfAbsent(language.getLocale(), language);
        return language;
    }

    /**
     * Called to get a specific String from the default Language file.
     *
     * @param key       The key of the String.
     * @param parameter The Parameters to replace placeholders in the String.
     * @return The String.
     */
    public static @NonNull String getDefault(@NonNull String key, @Nullable Object... parameter) {
        return getDefault(key, null, parameter);
    }

    /**
     * Called to get a specific String from the default Language file.
     *
     * @param key       The key of the String.
     * @param parameter The Parameters to replace placeholders in the String.
     * @return The String.
     */
    public static @NonNull String getDefault(@NonNull String key, @Nullable Player p, @Nullable Object... parameter) {
        return getByLocale(getLocale(), key, p, parameter);
    }

    /**
     * Called to get a specific String from the Language file.
     *
     * @param locale     The locale of the Language file.
     * @param key        The key of the String.
     * @param parameters The Parameters to replace placeholders in the String.
     * @return The String.
     */
    public static @NonNull String getByLocale(@NonNull String locale, @NonNull String key, @Nullable Object... parameters) {
        return getByLocale(locale, key, null, parameters);
    }

    /**
     * Called to get a specific String from the Language file.
     *
     * @param locale     The locale of the Language file.
     * @param key        The key of the String.
     * @param player     The player.
     * @param parameters The Parameters to replace placeholders in the String.
     * @return The String.
     */
    public static @NonNull String getByLocale(@NonNull String locale, @NonNull String key, @Nullable Player player, @Nullable Object... parameters) {
        if (!languageResources.containsKey(locale) && !locale.equalsIgnoreCase("en_us")) return getByLocale("en_us", key, player, parameters);

        Language language = languageResources.get(locale);

        String resource = "Missing language resource!";

        if (language == null) return resource;
        if (player == null) return language.getResource(key, parameters);

        return language.getResource(key, player, parameters);
    }

    /**
     * Get the Path to the language folder.
     *
     * @return {@link Path}
     */
    public static Path getLanguagePath() {
        return new File("plugins/TrollV4", "languages").toPath();
    }

    /**
     * Get current Locale.
     *
     * @return the Locale that should be used.
     */
    public static String getLocale() {
        return Config.getConfig().getString("Language").toLowerCase();
    }

    /**
     * Clear out the language resource.
     */
    public static void clear() {
        languageResources.clear();
    }
}
