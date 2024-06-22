package com.bilgeadam.utility;

import java.util.UUID;

public class CodeGenerator {

    //random bir activation kod olusturacğız
    // asdsaf8-fasfas9-gakasd6-44asfafg

    public static String generateCode() {
        String uuid= UUID.randomUUID().toString();
        String[] data=uuid.split("-");
        String code = "";
        for (int i = 0; i < data.length; i++) {
            code+=data[i].charAt(0);
        }
        return code;
    }
}
