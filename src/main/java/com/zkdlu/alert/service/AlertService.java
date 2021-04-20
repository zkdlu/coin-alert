package com.zkdlu.alert.service;

import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlertService {
    private final SlackBot slackBot;

    @EventListener
    public void alert(String message) throws IOException {
        List<LayoutBlock> layoutBlocks = Blocks.asBlocks(
                getHeader(message),
                Blocks.divider(),
                getSection(message)
        );

        slackBot.send(layoutBlocks);
    }

    @NotNull
    private LayoutBlock getSection(String message) {
        return Blocks.section(s -> s.text(
                BlockCompositions.markdownText(message)));
    }

    @NotNull
    private LayoutBlock getHeader(String text) {
        return Blocks.header(h -> h.text(
                BlockCompositions.plainText(pt -> pt
                        .emoji(true)
                        .text(text))));
    }
}
