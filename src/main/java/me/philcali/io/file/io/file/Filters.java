package me.philcali.io.file.io.file;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Filters {
    private Filters() {
    }

    public static Predicate<File> regex(final String pattern) {
        final Pattern regex = Pattern.compile(pattern);
        return file -> regex.matcher(file.path().toString()).find();
    }

    public static Predicate<File> glob(final String pattern) {
        String globPattern = pattern;
        final Pattern replacer = Pattern.compile("(\\*\\*?|\\?)");
        final Matcher matcher = replacer.matcher(pattern);
        while (matcher.find()) {
            final String content = matcher.group(1);
            switch (content) {
                case "*":
                    globPattern = globPattern.replaceFirst("\\*", "[^/]+");
                    break;
                case "**":
                    globPattern = globPattern.replaceFirst("\\*\\*", ".+?");
                    break;
                case "?":
                    globPattern = globPattern.replaceFirst("\\?", "\\\\w");
                    break;
                default:
                    break;
            }
        }
        System.out.println(globPattern);
        return regex(globPattern);
    }
}
