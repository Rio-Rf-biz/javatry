/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

// done iwata @authorの追加をお願いします by jflute (2025/08/28)
/**
 * @author jflute
 * @auhtor Rio-Rf-biz
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final int MAX_QUANTITY = 10;
    public static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    public static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400; // 夜限定
    public static final int TWO_DAY_PRICE = 13200;
    public static final int FOUR_DAY_PRICE = 22400;
    public static final int NIGHT_ONLY_START_HOUR = 19;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // done iwata javadoc, @return も追加しましょう (日本語でいいですよ) by jflute (2025/08/14)
    // done iwata [いいね] チケットとお釣りという風に具体例があるのはめちゃわかりやすい by jflute (2025/08/28)
    // done iwata 一方で、チケットとお釣り以外の項目が増えた時に若干誤解しやすくなるので断定よりも... by jflute (2025/08/28)
    // 「チケットとお釣りなどを返す」みたいな感じでぼかすと良い。"など" を付けるだけで一部事例であることを示すことができる。
    // done iwata nullを戻すのかどうか？を示してくれると嬉しい (javatryルールとして) by jflute (2025/08/28)
    //  e.g. @return xxx (NotNull)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return チケットとお釣りなど (NotNull)
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        return (buyPassport(TicketType.ONE_DAY, handedMoney, ONE_DAY_PRICE, 1, false));
    }

    // done iwata twoDayの方にも、@returnを付けましょう by jflute (2025/08/28)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return チケットとお釣りなど (NotNull)
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        return (buyPassport(TicketType.TWO_DAY, handedMoney, TWO_DAY_PRICE, 2, false));
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        return (buyPassport(TicketType.FOUR_DAY, handedMoney, FOUR_DAY_PRICE, 4, false));
    }

    // #1on1: 流れを再利用して極力コピペをしないで済むようにする一方で... (2025/08/28)
    // コピペという作業は0にはできないので、コピペ修正の抜けを作らない工夫も必要。
    // o 指差し確認
    // o 別のテキストで小さなスコープにして、検索や一括置換をうまく使う
    // 
    // パッと出せる作業用のテキストスペースを準備しておくと良い話

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        return(buyPassport(TicketType.NIGHT_ONLY_TWO_DAY, handedMoney, NIGHT_ONLY_TWO_DAY_PRICE, 2, true));
    }

    // TODO r.iwata buyAnyPassportのようにするか悩みましたがbuyPassportの方がシンプルでわかりやすいと思ったのでそう名付けました、一応doBuyと区別はできている、doBuyの方を変えた方がいいんですかね (2025/09/03)
    private TicketBuyResult buyPassport(TicketType ticketType, Integer handedMoney, Integer ticketPrice, Integer availableDays, boolean nightOnly) {
        assertQuantityValid();
        assertHandedMoneyValid(handedMoney, ticketPrice);
        TicketCustomized ticket = new TicketCustomized(ticketType, ticketPrice, availableDays, nightOnly);
        int change = doBuyPassport(handedMoney, ticketPrice);
        return new TicketBuyResult(ticket, change);
    }

    // done iwata これでも全然問題ないのですが、よくcheckという言葉の曖昧さが話題になることがあります。 by jflute (2025/08/14)
    // checkQuantity()だと、QuantityがOKなのか？ダメなのか？どっちをチェックしているのか？どっちで例外が投げられるのか？
    // この辺はわかりにくくなるので、もうちょい明確になる動詞を使うケースもあります。
    // よく使われるのは assert という言葉で、assertの場合は期待することが目的語になるので、
    // 例えば、assertQuantityValid() とか assertQuantityExists() とか。
    // わざと曖昧にすることもあるので必ずしもcheckが悪いわけではないですが、
    // このくらいのプログラムであれば(privateメソッドであれば)、もうちょい具体的で良いかなとは思います。
    // ぜひ、IntelliJのrenameの機能を使ってリファクタリングしてみましょう。
    // #1on1: shift+shiftからのrenでrename探してenter
    // もう少し指が頑張れる人は、control+T がオススメ (リファクタリングメニュー)

    // done iwata [読み物課題] リファクタリングは思考のツール by jflute (2025/08/15)
    // https://jflute.hatenadiary.jp/entry/20121202/1354442627
    // 読みました
    // リファクタリングの行為自体 (書き変えること) に思考力をすべて取られては意味がない。エディターの機能は使いこなしていますか？リラックスしながらリファクタリングできると良い。
    //(直したほうが良いコードだと思っても、指が面倒だと思ったらやらなくなってしまいがちです)
    // うーんとパソコンの前でうなってもしょうがない。リファクタリングを始める。おもむろに部屋のお片付けをし始めるようなもの。
    //↑ 特に納得感があった部分を抜粋
    //
    // done iwata [読み物課題] リファクタリングという行為が好きか？ by jflute (2025/08/15)
    // https://jflute.hatenadiary.jp/entry/20220328/loverefactor
    // 読みました
    // ↑ リファクタリングへの解像度が上がりました。いつかやろう、では確かに忘れ去られてしまいそうですね。。
    // #1on1: 既存コードでちょいリファクタリングしたくなるようなコードがあった場合どうする？
    // pp 基本的にはチームの決め事に従う、決め事がなければ決める
    // o パターン1: 気にせずチケットブランチでがんがん修正
    // o パターン2: しっかりと別のブランチに分けて修正 (別タスク: 後回しになるかも!?)
    //    → 別タスクになって優先度が低いので結局年単位で放置されがち
    //    → 週1で1時間リファクタリングタイムを取るチームもあったり (理想的)
    // o パターン3: 1と2の間の子、差分が見づらくなければある程度はOK
    // o パターン4: 決め事もなければ決めようともしないので人に依る
    //    → 人に依る: 大抵は「なに勝手なことやってるの」と言われそうで怖いので見逃してスルーが多い
    //
    // jfluteのオススメ: 理想的にはパターン2でリファクタリングタイムを設ける。
    // だけど、リファクタリングタイムがなかなか成立しないので...パターン3である程度はOKに。
    // プルリク差分が見づらくなるくらいみんながリファクタリングに積極的なのであれば嬉しい悲鳴。
    // そのときにちょっと差分に配慮をしましょうって言えばいいだけかな。

    // #1on1: 指が早いというのは、単に作業が早くなるって単純な話だけではなく...
    // 試行錯誤が何回もできる、とか、サンクコストによる判断のブレを少なくすることにもつながる。

    private void assertQuantityValid() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    private void assertHandedMoneyValid(Integer handedMoney, int price) {
        if(handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    // done iwata [いいね] publicメソッドと区別しているメソッド名にしてるのでGoodです。 by jflute (2025/08/14)
    // こっちもbuyだと同じ言葉を使うと区別がしづらいですからね...
    // done iwata 一方で、業務用語としてbuyとpurchaseの示す行為が明確に定義付けされているならOKですが... by jflute (2025/08/15)
    // そうでない場合は逆に紛らわしくなることもあります。
    // 例えばユーザー目線でもpurchaseという言葉を使う場合、buyとpurchaseが言葉ブレがあちらこちらで発生すると良くないです。
    // (もうここではbuyとpurchaseの言葉の使い方はこうである、って決めにするのであれば全然OKです)
    // ゆえに、buyのまま区別するやり方もあって...
    // publicのbuyメソッドに対して、privateのdoBuyPassport()メソッドみたいにdoをprefixとして付けるとか。
    // 他にも色々な区別の仕方はあるのですが、ぼくはけっこう「実処理」みたいなニュアンスで doXxx() はよく使います。
    // 会話上も言いやすく区別しやすいので。
    private int doBuyPassport(Integer handedMoney, int price) {
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        return handedMoney - price;
    }

    public static class TicketBuyResult {
        // done iwata final付けて完全なimmutableにしてしまいましょう by jflute (2025/08/28)
        private final TicketCustomized ticket; // 購入したチケット
        private final int change; // お釣り

        public TicketBuyResult(TicketCustomized ticket, int change) {
            this.ticket = ticket;
            this.change = change;
        }

        public int getChange() {
            return change;
        }

        public TicketCustomized getTicket() {
            return ticket;
        }
    }

    public static class NightOnlyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public NightOnlyException(String msg) {
            super(msg);
        }
    }

    public static class TicketUnavailableException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketUnavailableException(String msg) {
            super(msg);
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
