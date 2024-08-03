package seeds.StartupInvestor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespMainPost {
    private String title;
    private String description;
    private byte[] imgData;
    private int bookmarkCnt;
    private int likeCnt;
    private boolean bookmark;
    private boolean like;
}
