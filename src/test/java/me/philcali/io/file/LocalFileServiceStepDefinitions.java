package me.philcali.io.file;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.philcali.io.file.io.file.File;
import me.philcali.io.file.io.file.FileService;
import me.philcali.io.file.io.file.Filters;
import me.philcali.io.file.io.file.LocalFileService;
import org.junit.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LocalFileServiceStepDefinitions {
    private FileService service;
    private List<Path> resultingPaths;

    @Given("{} has created the LocalFileService")
    @When("{} creates the LocalFileService")
    public void createsTheService(final String user) {
        service = new LocalFileService();
    }

    @Then("{} can interact with a FileService")
    public void assertServiceIsLocalFileService(final String user) {
        Assert.assertTrue(service instanceof FileService);
    }

    @ParameterType(name = "directory", value = "\"([^\"]+)\"")
    public Path createPath(final String path) {
        return Paths.get(path);
    }

    @ParameterType(name = "regex", value = "\"([^\"]+)\"")
    public Predicate<File> createRegexFilter(final String regex) {
        return Filters.regex(regex);
    }

    @ParameterType(name = "glob", value = "\"([^\"]+)\"")
    public Predicate<File> createGlobFilter(final String glob) {
        return Filters.glob(glob);
    }

    @When("{} invokes find on {directory}")
    public void serviceCallsFind(final String user, final Path directory) {
        resultingPaths = service.find(directory).map(File::path).collect(Collectors.toList());
    }

    @When("{} invokes find on {directory} with a regex filter of {regex}")
    public void serviceCallsFind(final String user, final Path directory, final Predicate<File> regex) {
        resultingPaths = service.find(directory, regex).map(File::path).collect(Collectors.toList());
    }

    @When("{} invokes find on {directory} with a glob filter of {glob}")
    public void serviceCallsFindGlob(final String user, final Path directory, final Predicate<File> glob) {
        resultingPaths = service.find(directory, glob).map(File::path).collect(Collectors.toList());
    }

    @Then("the file list contains:")
    public void returningList(final List<String> paths) {
        Assert.assertEquals(paths.stream().map(this::createPath).collect(Collectors.toList()), resultingPaths);
    }
}
