package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> ans = new ArrayList<>();
        int startingIndex = 0;
        for (int i = 0; i < source.length(); i++) {
            for (String str : delimiters) {
                if (checkWhetherToSplit(source, str, i)) {
                    if (startingIndex < i) {
                        ans.add(source.substring(startingIndex, i));
                    }
                    startingIndex = i + str.length();
                    i = startingIndex - 1;
                    break;
                }
            }
        }
        if (startingIndex < source.length()) {
            ans.add(source.substring(startingIndex));
        }

        return ans;
    }

    private boolean checkWhetherToSplit(String source, String delimiter, int startingIndex) {
        if (startingIndex < 0 || startingIndex >= source.length()) {
            throw new StringIndexOutOfBoundsException("you cant cut like this");
        }

        if (startingIndex + delimiter.length() > source.length()) return false;

        return source.startsWith(delimiter, startingIndex);
    }
}
