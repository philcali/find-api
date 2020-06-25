package me.philcali.io.file.io.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface FileService {
    Stream<File> find(Path path, Predicate<File> filter) throws FileServiceException;

    default Stream<File> find(final Path path) {
        return find(path, file -> true);
    }

}
