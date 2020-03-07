package com.zeto.kooteam.service.eventbus;

import com.google.common.eventbus.Subscribe;
import com.zeto.Zen;
import com.zeto.ZenData;
import com.zeto.kooteam.service.eventbus.model.MessageModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageListener {
    private static final String topic = "bridge";
    private static final String tag = "kooteam";

    private static final String apiURL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

    @Subscribe
    public void execute(MessageModel model) {
        ZenData data = ZenData.create("from", model.getFrom()).put("to", model.getTo()).put("content",
                model.getContent());
        // 保存消息记录
        Zen.getStorageEngine().execute("put/message", data, null);

    }
}
