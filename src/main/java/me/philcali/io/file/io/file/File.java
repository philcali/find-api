package me.philcali.io.file.io.file;

import org.immutables.value.Value;

import java.nio.file.Path;

@Value.Immutable
public interface File {
    String name();

    Path path();

    long size();
}
