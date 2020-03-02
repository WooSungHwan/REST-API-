package co.worker.board.util;

public class Validate {
    private Validate(){}

    public static void isTrue(boolean isTrue, String msg){  //false일 때 에러를 낸다.
        if(!isTrue){
            throw new RuntimeException(msg);
        }
    }
}
