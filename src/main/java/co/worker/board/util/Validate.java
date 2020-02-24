package co.worker.board.util;

public class Validate {
    private Validate(){}

    public static void isTrue(boolean isTrue, String msg){
        if(!isTrue){
            throw new RuntimeException(msg);
        }
    }
}
