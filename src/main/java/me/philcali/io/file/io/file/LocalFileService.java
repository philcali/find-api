package me.philcali.io.file.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LocalFileService implements FileService {

    @Override
    public Stream<File> find(final Path path, final Predicate<File> filter) throws FileServiceException {
        try {
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .map(this::convertFile)
                    .filter(filter);
        } catch (IOException ie) {
            throw new FileServiceException(ie);
        }
    }

    private File convertFile(final Path fullPath) {
        try {
            return ImmutableFile.builder()
                    .name(fullPath.getFileName().toString())
                    .path(fullPath)
                    .size(Files.size(fullPath))
                    .build();
        } catch (IOException ie) {
            throw new FileServiceException(ie);
        }
    }
}
