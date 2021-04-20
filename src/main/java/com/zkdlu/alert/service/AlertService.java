package com.zkdlu.alert.service;

import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.zkdlu.alert.domain.Coin;
import com.zkdlu.alert.domain.CoinEvent;
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
    public void alert(CoinEvent coinEvent) throws IOException {
        Coin coin = coinEvent.getCoin();

        List<LayoutBlock> layoutBlocks = Blocks.asBlocks(
                getHeader("코인 정보가 도착했어요. " + coin.getMarket()),
                Blocks.divider(),
                getSection("현재 가격: " + coin.getTradePrice())
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
