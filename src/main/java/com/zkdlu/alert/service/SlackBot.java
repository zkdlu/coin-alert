package com.zkdlu.alert.service;

import com.slack.api.Slack;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.webhook.WebhookPayloads;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class SlackBot {
    @Value("${slack}")
    private String webhook;

    public String send(List<LayoutBlock> slackMessage) throws IOException {
        return Slack.getInstance().send(webhook, WebhookPayloads
                .payload(p -> p
                        .text(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .blocks(slackMessage)
                )).getMessage();
    }

    public String callback() {
        return "아직 콜백을 지원하지 않습니다.";
    }
}
