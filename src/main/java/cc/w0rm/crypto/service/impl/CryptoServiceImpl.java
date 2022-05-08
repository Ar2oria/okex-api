package cc.w0rm.crypto.service.impl;

import cc.w0rm.crypto.client.okex.OkexClient;
import cc.w0rm.crypto.model.bo.SaveCryptoConfig;
import cc.w0rm.crypto.model.bo.Task;
import cc.w0rm.crypto.model.dto.CandlesDTO;
import cc.w0rm.crypto.service.CryptoService;
import cc.w0rm.crypto.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CryptoServiceImpl implements CryptoService {

    private static final Map<String, Task> taskMap = new ConcurrentHashMap<>();

    private OkexClient okexClient = new OkexClient();
    private DbService dbService = new DbServiceImpl();


    @Override
    public synchronized void saveCryptoData(SaveCryptoConfig config) throws Exception {
        String taskName = config.getInstId() + "-" + config.getBar();
        if(taskMap.containsKey(taskName)){
            Task task = taskMap.get(taskName);
            task.remove();
        }

        Task task = Task.createTask(config, requestDTO -> {
            if (Objects.isNull(requestDTO)){
                return;
            }
            List<CandlesDTO> candlesList = okexClient.queryHistoryCandles(requestDTO);
            if (CollectionUtils.isEmpty(candlesList)){
                return;
            }
            dbService.saveHistoryCandles(config, candlesList);
        });


        taskMap.put(taskName, task);
    }


}
