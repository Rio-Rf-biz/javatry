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

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

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
    // TODO iwata javadoc, @return も追加しましょう (日本語でいいですよ) by jflute (2025/08/14)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public int buyOneDayPassport(Integer handedMoney) {
        checkQuantity();
        checkHandedMoney(handedMoney, ONE_DAY_PRICE);
        return purchase(handedMoney, ONE_DAY_PRICE);
    }
    
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public int buyTwoDayPassport(Integer handedMoney) {
        checkQuantity();
        checkHandedMoney(handedMoney, TWO_DAY_PRICE);
        return purchase(handedMoney, TWO_DAY_PRICE);
    }

    // TODO iwata これでも全然問題ないのですが、よくcheckという言葉の曖昧さが話題になることがあります。 by jflute (2025/08/14)
    // checkQuantity()だと、QuantityがOKなのか？ダメなのか？どっちをチェックしているのか？どっちで例外が投げられるのか？
    // この辺はわかりにくくなるので、もうちょい明確になる動詞を使うケースもあります。
    // よく使われるのは assert という言葉で、assertの場合は期待することが目的語になるので、
    // 例えば、assertQuantityValid() とか assertQuantityExists() とか。
    // わざと曖昧にすることもあるので必ずしもcheckが悪いわけではないですが、
    // このくらいのプログラムであれば(privateメソッドであれば)、もうちょい具体的で良いかなとは思います。
    // ぜひ、IntelliJのrenameの機能を使ってリファクタリングしてみましょう。
    // #1on1: shift+shiftからのrenでrename探してenter
    // もう少し指が頑張れる人は、control+T がオススメ (リファクタリングメニュー)

    // TODO iwata [読み物課題] リファクタリングは思考のツール by jflute (2025/08/15)
    // https://jflute.hatenadiary.jp/entry/20121202/1354442627
    // TODO iwata [読み物課題] リファクタリングという行為が好きか？ by jflute (2025/08/15)
    // https://jflute.hatenadiary.jp/entry/20220328/loverefactor
    
    // #1on1: 指が早いというのは、単に作業が早くなるって単純な話だけではなく...
    // 試行錯誤が何回もできる、とか、サンクコストによる判断のブレを少なくすることにもつながる。

    private void checkQuantity() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    private void checkHandedMoney(Integer handedMoney, int price) {
        if(handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    // TODO iwata [いいね] publicメソッドと区別しているメソッド名にしてるのでGoodです。 by jflute (2025/08/14)
    // こっちもbuyだと同じ言葉を使うと区別がしづらいですからね...
    // TODO iwata 一方で、業務用語としてbuyとpurchaseの示す行為が明確に定義付けされているならOKですが... by jflute (2025/08/15)
    // そうでない場合は逆に紛らわしくなることもあります。
    // 例えばユーザー目線でもpurchaseという言葉を使う場合、buyとpurchaseが言葉ブレがあちらこちらで発生すると良くないです。
    // (もうここではbuyとpurchaseの言葉の使い方はこうである、って決めにするのであれば全然OKです)
    // ゆえに、buyのまま区別するやり方もあって...
    // publicのbuyメソッドに対して、privateのdoBuyPassport()メソッドみたいにdoをprefixとして付けるとか。
    // 他にも色々な区別の仕方はあるのですが、ぼくはけっこう「実処理」みたいなニュアンスで doXxx() はよく使います。
    // 会話上も言いやすく区別しやすいので。
    private int purchase(Integer handedMoney, int price) {
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        return handedMoney - price;
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
