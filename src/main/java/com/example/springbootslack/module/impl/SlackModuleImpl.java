package com.example.springbootslack.module.impl;

import com.example.springbootslack.module.SlackModule;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.stereotype.Service;

@Service
public class SlackModuleImpl implements SlackModule {
    //개인이 발급 받은 OAuth token값을 입력한다. 토큰의 경우 Config를 별도로 관리하는 것을 추천하나 간단한 작업을 위해 클래스 내 변수 선언
    Slack slack;
    static String token = "Virtual-Token-String";

    @Override
    public void sendService(String message) {
        sendService("DEFAULT_CHANNEL_NAME", message);
    }

    @Override
    public void sendService(String channelName, String message) {
        try {
            channelName = channelName != null && !channelName.isEmpty() ? channelName : "DEFAULT_CHANNEL_NAME";
            slack = Slack.getInstance();
            MethodsClient client = slack.methods(token);
            ChatPostMessageRequest req = ChatPostMessageRequest.builder()
                    .channel(channelName)
                    .text(message)
                    .build();

            client.chatPostMessage(req);
            // response를 받아서 별도로 처리하려면 ChatPostMessageResponse로 받아서 가공
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
