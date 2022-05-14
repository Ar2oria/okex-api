package cc.w0rm.crypto.common;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class NumberUtil {

    public static <T> double[] convertToDoubleArray(List<T> dataList, BigDecimalToDouble<T> function) {
        if (CollectionUtils.isEmpty(dataList)) {
            return new double[0];
        }

        double[] arr = new double[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            arr[i] = function.convert(dataList.get(i));
        }
        return arr;
    }


}
