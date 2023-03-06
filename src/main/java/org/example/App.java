package org.example;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner sc;
    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언앱 ==");
        List<WiseSaying> wiseSayingList = new ArrayList<>();
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
                    wiseSayingList.add(new WiseSaying(id, content, authorName));
                    System.out.printf("%d번 명언이 등록되었습니다.", id);
                    break;
                case "목록" :
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("_".repeat(30));
                    for(int k = wiseSayingList.size()-1 ; k>=0 ; k--)
                    System.out.printf("%d / %s / %s\n", wiseSayingList.get(k).id, wiseSayingList.get(k).author, wiseSayingList.get(k).content);
                    break;

                default:
                    System.out.println("올바르지 않은 명령입니다.");
                    break;
               }
            }
        }
    }
