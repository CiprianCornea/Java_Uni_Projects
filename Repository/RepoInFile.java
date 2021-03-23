package Repository;

import Domain.Entity;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class RepoInFile<T extends Entity> extends RepoInMemory<T> {
    private final String fileName;
    private final Function<String, T> entityFactory;

    public RepoInFile(String fileName, Function<String, T> entityFactory) {
        super();
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        this.loadFromFile();
    }

    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter(this.fileName, false);
            for (T element : this.getAll()) {
                myWriter.write(element.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException exc) {
            throw new RuntimeException(
                    String.format("An error occurred while saving to file: %s", exc.getMessage()),
                    exc
            );
        }
    }

    private void loadFromFile() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(this.fileName));
            for (String line : allLines) {
                T t = entityFactory.apply(line);
                this.storage.put(t.getId(), t);
            }
        } catch (IOException exc) {
            // ignore missing file
        }
    }

    public void add(T element) {
        super.add(element);
        this.saveToFile();
    }

    public void delete(T element) {
        super.delete(element);
        this.saveToFile();
    }

    public void update(T toUpdate, T toReplace) {
        super.update(toUpdate, toReplace);
        this.saveToFile();
    }
}
