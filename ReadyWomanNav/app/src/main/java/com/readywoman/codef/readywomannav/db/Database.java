package com.readywoman.codef.readywomannav.db;

import android.provider.BaseColumns;

public class Database {

    //데이터베이스 호출 시 사용될 생성자
    public static final class CreateDB implements BaseColumns {
        public static final String _TABLENAME = "programTable";
        public static final String CLASSNAME = "className";
        public static final String CLASSTERM = "classTerm";
        public static final String CLASSTIME = "classTime";
        public static final String CLASSPRICE = "classPrice";
        public static final String CLASSSTATUS = "classStatus";
        public static final String CLASSCENTER = "classCenter";

        public static final String _CREATE =
                "create table " + _TABLENAME + "("
                        + _ID + " integer PRIMARY KEY autoincrement, "
                        + CLASSNAME + " text not null , "
                        + CLASSTERM + " text not null , "
                        + CLASSTIME + " text not null , "
                        + CLASSPRICE + " text not null , "
                        + CLASSSTATUS + " text not null , "
                        + CLASSCENTER + " text not null ); ";
    }

}
