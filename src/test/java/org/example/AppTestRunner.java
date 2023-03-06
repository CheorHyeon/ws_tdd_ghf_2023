package org.example;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String cmd) {
        cmd.stripIndent().trim();
        if(cmd.isEmpty())
            cmd ="종료";
        else
            cmd += "\n종료";
        Scanner sc = TestUtil.genScanner(cmd); // 문자열 입력
        ByteArrayOutputStream output = TestUtil.setOutToByteArray(); // 화면출력금지 시작

        new App(sc).run();

        String rs = output.toString(); //그동안 출력되지 않던 문자열들 문자열 변환
        TestUtil.clearSetOutToByteArray(output); //화면 출력금지 끝

        return rs;

    }
}
