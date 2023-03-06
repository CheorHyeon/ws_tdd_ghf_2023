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


    //테스트유틸 테스트 끝

    //앱 테스트 시작
    @Test
    @DisplayName("프로그램 시작시 타이틀 출력 그리고 종료")
    public void t3()
    {
        String rs = AppTestRunner.run("");

        assertThat(rs)
                .contains("== 명언앱 ==")
                .contains("명령) ")
                .contains("프로그램이 종료되었습니다.")
                .doesNotContain("올바르지 않은 명령입니다.");
    }
    @Test
  @DisplayName("잘못된 명령어 입력에 대한 처리")
  public void t4() {
      String rs = AppTestRunner.run("종료2");
       assertThat(rs)
             .contains("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("등록화면에서 명언과 작가를 입력받고, 명언을 생성한다.")
    public void t5() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """.stripIndent().trim());
        assertThat(rs)
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("1번 명언이 등록되었습니다.");
    }
    @Test
    @DisplayName("명언이 등록될 때 마다 생성되는 명언의 번호가 1씩 증가한다.")
    public void t6() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                """.stripIndent().trim());
        assertThat(rs)
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .doesNotContain("3번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 출력")
    public void t7() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """.stripIndent().trim());

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("______________________________")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("1번 명언 삭제")
    public void t8() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                """.stripIndent().trim());

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("______________________________")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("1번 명언 삭제")
    public void t9() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                """.stripIndent().trim());

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("______________________________")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 명언삭제에 대한 예외처리")
    public void t10() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                삭제?id=1
                """.stripIndent().trim());

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("______________________________")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("명언 수정")
    public void t11() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                삭제?id=1
                삭제?id=1
                수정?id=2
                현재와 자신을 사랑하라.
                홍길동
                목록
                """.stripIndent().trim());

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("______________________________")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.")
                .contains("명언(기존) : 과거에 집착하지 마라.")
                .contains("작가(기존) : 작자미상")
                .contains("2 / 홍길동 / 현재와 자신을 사랑하라.");
    }

}
