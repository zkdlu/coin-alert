package com.zkdlu.alert.service;

import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.composition.TextObject;
import com.zkdlu.alert.domain.Coin;
import com.zkdlu.alert.domain.CoinEvent;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class AlertService {
    private final SlackBot slackBot;
    private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance( Locale.KOREA );

    @EventListener
    public void alert(CoinEvent coinEvent) throws IOException {
        Coin coin = coinEvent.getCoin();

        var fields = getCoinFields(coin);

        List<LayoutBlock> layoutBlocks = Blocks.asBlocks(
                getHeader("코인 정보가 도착했어요. " + coin.getMarket()),
                Blocks.divider(),
                getFieldSection(fields)
        );

        slackBot.send(layoutBlocks);
    }

    private LayoutBlock getFieldSection(List<TextObject> fields) {
        return Blocks.section(s -> s.fields(fields));
    }

    private List<TextObject> getCoinFields(Coin coin) {
        return Arrays.asList(
                getField("고가", numberFormat.format(coin.getHighPrice())),
                getField("저가", numberFormat.format(coin.getLowPrice())),
                getField("현재 가격", numberFormat.format(coin.getTradePrice()))
        );
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

    @NotNull
    private TextObject getField(String title, String content) {
        return BlockCompositions.markdownText(
                "*" + title + "*\n" + content);
    }
}
