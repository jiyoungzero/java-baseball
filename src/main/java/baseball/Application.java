package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* 기능목록정리
      1. 숫자야구게임 시작문구 출력
      2. 정답인 랜덤 숫자 3개 지정
      3. 입력값 받기(Integer[]로 형변환
      4. 스트라이크, 볼, 낫띵인지 판단하는 함수 / response를 보내주는 함수
      5. 재시작(1), 종료(2) 구현
      6. 예외처리 / IllegalArgumentException 처리
      7. 테스트 코드 작성 후 검증
* */
public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        while(true) {
            if (playGame(generateRandomNum()).equals("2")) {
                break;
            }

        }
    }
    public static List<Integer> generateRandomNum() {
        List<Integer> computer = new ArrayList<>();
        while(computer.size() < 3){
            int randomNum = Randoms.pickNumberInRange(1,9);
            if(!computer.contains(randomNum)){
                computer.add(randomNum);
            }

        }
        System.out.println(computer.get(0));
        System.out.println(computer.get(1));
        System.out.println(computer.get(2));
        return computer;
    }

    public static String playGame(List<Integer> computer){
        System.out.println("숫자 야구 게임을 시작합니다.");
        while(true){
            GameStatus gameStatus = new GameStatus();

            System.out.println("숫자를 입력해주세요 : ");
            String input = Console.readLine();
            String[] inputList = input.split("");

            // 입력값 검증
            validateCase(inputList);

            int[] inputNums = Arrays.stream(inputList).mapToInt(Integer::valueOf).toArray();

            saveNumberStatus(inputNums, computer,gameStatus);
            String response = responseNumberStatus(gameStatus);
            System.out.println(response);

            if(gameStatus.strike == 3){
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료 \n"+
                        "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                break;
            }
        }
        return Console.readLine();
    }
    private static void saveNumberStatus(int[] inputNums, List<Integer> computer, GameStatus gameStatus){
        for(int i =0 ; i<inputNums.length; i++){
            if(!computer.contains(inputNums[i])){
                gameStatus.nothing = true;
            }
            else{ // 스트라이크 or 볼인 경우
                if(i == computer.indexOf(inputNums[i])){
                    gameStatus.strike++;
                }
                else {
                    gameStatus.ball++;
                }
            }
        }
    }
    private static String responseNumberStatus(GameStatus gameStatus){
        StringBuilder sb = new StringBuilder();

        if(gameStatus.nothing){
            return "낫띵";
        }
        else{
            if(gameStatus.ball > 0){
                sb.append(gameStatus.ball).append("볼 ");
            }
            if(gameStatus.strike > 0){
                sb.append(gameStatus.strike).append("스트라이크 ");
            }
        }
        return sb.toString();
    }
    public static class GameStatus{
        Boolean nothing = false;
        Integer strike = 0;
        Integer ball = 0;
    }

    private static void validateCase(String[] inputList){
        if(inputList.length != 3) throw new IllegalArgumentException();
        if(!Pattern.matches("[1-9]", Arrays.toString(inputList)))
            throw new IllegalArgumentException();
    }





}
