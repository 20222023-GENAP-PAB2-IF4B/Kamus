package masterous.if4b.kamus.databases;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_KAMUS_NAME = "kamus";

    static final class KamusColumns implements BaseColumns {
        static String KAMUS_TITLE = "title";
        static String KAMUS_DESCRIPTION = "description";
    }
}
