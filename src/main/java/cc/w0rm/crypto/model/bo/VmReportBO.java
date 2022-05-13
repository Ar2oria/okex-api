package cc.w0rm.crypto.model.bo;

import lombok.Data;

@Data
public class VmReportBO {
    private VmTaskDetailBO vmTaskDetail;
    private VmLogDetailBO vmLogDetail;
}
