package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTests {
    //테스트유틸 시작
    @Test
    @DisplayName("스케너에 키보드가 아닌 문자열을 입력으로 설정")
    public void t1() {
        Scanner sc = TestUtil.genScanner("안녕");

        String cmd = sc.nextLine().trim();
        assertThat(cmd).isEqualTo("안녕");
    }
    @Test
    @DisplayName("출력을 모니터에 하지 않고 문자열로 얻기")
    public void t2() {
        // System.out에 대한 화면출력 금지 시작
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        System.out.print("안녕"); // 화면(x)

        // 그 동안 출력되지 않던 내용들을 문자열로 반환
        String rs = output.toString(); // rs에 집어넣을 수 있음

        //System.out에 대한 화면출력 금지 끝
        TestUtil.clearSetOutToByteArray(output); //원상복구

        assertEquals("안녕", rs);
    }

    @Test
    @DisplayName("프로그램 시작시 타이틀 출력 그리고 종료")
    public void t3() {
        Scanner sc = TestUtil.genScanner("종료"); // 문자열 입력
        ByteArrayOutputStream output = TestUtil.setOutToByteArray(); // 화면출력금지 시작

        new App(sc).run();

        String rs = output.toString(); //그동안 출력되지 않던 문자열들 문자열 변환
        TestUtil.clearSetOutToByteArray(output); //화면 출력금지 끝

        assertThat(rs)
                .contains("== 명언앱 ==")
                .contains("명령) ")
                .contains("프로그램이 종료되었습니다.")
                .doesNotContain("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("잘못된 명령어 입력에 대한 처리")
    public void t4() {
        Scanner sc = TestUtil.genScanner("""
        종료2
        종료
        """.stripIndent().trim()); // 문자열 입력
        ByteArrayOutputStream output = TestUtil.setOutToByteArray(); // 화면출력금지 시작

        new App(sc).run();

        String rs = output.toString(); //그동안 출력되지 않던 문자열들 문자열 변환
        TestUtil.clearSetOutToByteArray(output); //화면 출력금지 끝

        assertThat(rs)
                .contains("올바르지 않은 명령입니다.");
    }
    //테스트유틸 테스트 끝

    //앱 테스트 시작
}
