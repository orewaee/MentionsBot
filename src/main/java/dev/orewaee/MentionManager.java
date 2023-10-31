package dev.orewaee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringJoiner;

public class MentionManager {
    private static MentionManager instance;

    private final Path directory = Path.of("mentions");

    public MentionManager() {
        try {
            if (!Files.exists(directory)) Files.createDirectories(directory);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static MentionManager getInstance() {
        if (instance == null)
            instance = new MentionManager();

        return instance;
    }

    public String getMentionsByChatId(Long chatId) {
        StringJoiner mentions = new StringJoiner(", ");

        Path path = directory.resolve(chatId + ".txt");

        try {
            if (!Files.exists(path)) Files.createFile(path);

            Scanner scanner = new Scanner(path);

            while (scanner.hasNextLine())
                mentions.add("@" + scanner.nextLine());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return mentions + "";
    }
}
