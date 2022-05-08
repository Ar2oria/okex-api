package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.db.domain.TaskDetail;
import cc.w0rm.crypto.model.dto.HistoryCandlesRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCandlesBO {
    private TaskDetail taskDetail;
    private HistoryCandlesRequestDTO requestDTO;
}
