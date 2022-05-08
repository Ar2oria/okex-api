package cc.w0rm.crypto.service;

import cc.w0rm.crypto.model.SaveCryptoConfig;

public interface CryptoService {

    void saveCryptoData(SaveCryptoConfig config) throws Exception;

}
