package cc.w0rm.crypto.service;

import cc.w0rm.crypto.model.bo.VmReportBO;
import cc.w0rm.crypto.model.bo.VmTaskBO;

public interface VmService {
    VmReportBO startVm(VmTaskBO vmTaskConfig) throws Exception;
}
