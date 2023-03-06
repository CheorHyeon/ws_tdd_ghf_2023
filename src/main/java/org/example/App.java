package org.example;
import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class App {

    Scanner sc;
    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언앱 ==");

        long lastWiseSayingId = 0;

        while  (true) {
            System.out.println("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "종료":
                    System.out.println("프로그램이 종료되었습니다.");
                    return;
                case "등록" :
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.println("작가 : ");
                    String authorName = sc.nextLine().trim();

                    long id = ++lastWiseSayingId;
                    System.out.printf("%d번 명언이 등록되었습니다.", id);
                    break;

                default:
                    System.out.println("올바르지 않은 명령입니다.");
                    break;
               }
            }
        }
    }
