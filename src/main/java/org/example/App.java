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

                case "삭제?id=1" :
                    String[] arr = cmd.split("=");
                    long deleteId = Long.parseLong(arr[1]);
                    WiseSaying 삭제명언 = null;
                    for(int j=0; j<wiseSayingList.size() ; j++)
                    {
                        if(wiseSayingList.get(j).id == deleteId) {
                           삭제명언 = wiseSayingList.get(j);
                        }
                    }

                    if(삭제명언 !=null) {
                        System.out.printf("%d번 명언이 삭제되었습니다.", deleteId);
                        wiseSayingList.remove(삭제명언);
                    }
                    else
                        System.out.printf("%d번 명언은 존재하지 않습니다.",deleteId);

                    break;

                case "수정?id=2" :
                    String[] arrModify = cmd.split("=");
                    long modifyId = Long.parseLong(arrModify[1]);
                    WiseSaying 수정명언 = null;

                    for(int j=0; j<wiseSayingList.size() ; j++)
                    {
                        if(wiseSayingList.get(j).id == modifyId) {
                            수정명언 = wiseSayingList.get(j);
                        }
                    }

                    if(수정명언 !=null) {
                        System.out.printf("명언(기존) : %s", 수정명언.content);
                        System.out.print("명언 : ");
                        수정명언.content = sc.nextLine().trim();
                        System.out.printf("작가(기존) : %s", 수정명언.author);
                        System.out.print("작가 : ");
                        수정명언.author = sc.nextLine().trim();
                    }
                    else
                        System.out.printf("%d번 명언은 존재하지 않습니다.", modifyId);

                    break;


                default:
                    System.out.println("올바르지 않은 명령입니다.");
                    break;
               }
            }
        }
    }
