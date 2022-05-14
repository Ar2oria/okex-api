package cc.w0rm.crypto.common;

import com.tictactec.ta.lib.Core;

public class FinanceUtil extends Core {

    private static final FinanceUtil FINANCE_UTIL = new FinanceUtil();

    private FinanceUtil() {
    }

    public static FinanceUtil create() {
        return FINANCE_UTIL;
    }

}
