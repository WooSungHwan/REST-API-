package co.worker.board.configuration.enums;

import java.util.Arrays;

public enum AuthorityType {

    ROLE_USER(0, "user"),
    ROLE_ADMIN(10, "admin");

    private final int value;
    private final String name;

    AuthorityType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String getName(){ return name; }
    public int getValue() { return value; }

    public static AuthorityType valueOf(int type){
        return Arrays.stream(AuthorityType.values())
                .filter(t -> type == t.getValue())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("not Supported Type %s", type)));
    }

}
