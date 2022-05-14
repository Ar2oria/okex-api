package cc.w0rm.crypto.model.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookBO {
    private long ts;
    /**
     * 叫价深度（卖方）
     */
    private List<DeepBO> asks;

    /**
     * 出价深度（买方）
     */
    private List<DeepBO> bids;

    public static BookBO simple(){
        BookBO returnVal = new BookBO();
        returnVal.setTs(0);
        returnVal.setAsks(new ArrayList<>());
        returnVal.setBids(new ArrayList<>());
        return returnVal;
    }

}
