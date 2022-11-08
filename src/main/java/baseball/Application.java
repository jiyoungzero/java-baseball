package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 기능목록정리
      1. 숫자야구게임 시작문구 출력
      2. 정답인 랜덤 숫자 3개 지정
      3. 입력값 받기(Integer[]로 형변환
      4. 스트라이크, 볼, 낫띵인지 판단하는 함수
      5. 재시작(1), 종료(2) 구현
      6. 예외처리 / IllegalArgumentException 처리
      7. 테스트 코드 작성 후 검증
* */
public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    }
    public static List<Integer> generateRandomNum() {
        List<Integer> computer = new ArrayList<>();
        while(computer.size() < 3){
            int randomNum = Randoms.pickNumberInRange(1,9);
            if(!computer.contains(randomNum)){
                computer.add(randomNum);
            }

        }
        return computer;
    }

    public static void playGame(List<Integer> computer){
        System.out.println("숫자 야구 게임을 시작합니다.");
        while(true){
            System.out.println("숫자를 입력해주세요 : ");
            String input = Console.readLine();
            String[] inputList = input.split("");
            int[] inputNums = Arrays.stream(inputList).mapToInt(Integer::valueOf).toArray();

            printNumberStatus(inputNums, computer);
        }
    }
    private static void printNumberStatus(int[] inputList, List<Integer> computer){

    }

    public static class GameStatus{
        Boolean nothing = false;
        Integer strike = 0;
        Integer ball = 0;
    }





}
