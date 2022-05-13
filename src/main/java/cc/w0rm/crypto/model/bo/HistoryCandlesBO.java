package cc.w0rm.crypto.model.bo;

import cc.w0rm.crypto.db.domain.TaskDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCandlesBO {
    private TaskDetail taskDetail;
    private CandlesRequestBO request;
}
