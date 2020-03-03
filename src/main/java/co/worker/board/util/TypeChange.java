package co.worker.board.util;

import org.modelmapper.ModelMapper;

public class TypeChange {

    public static <R, T> T sourceToDestination(R source, T destinateion){
        new ModelMapper().map(source, destinateion);
        return destinateion;
    }

}
