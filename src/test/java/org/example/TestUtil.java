package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtil {
    public static Scanner genScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        return new Scanner(in);
    }


    public static ByteArrayOutputStream setOutToByteArray() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        return output;
    }

    public static void clearSetOutToByteArray(ByteArrayOutputStream output) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try{
            output.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
