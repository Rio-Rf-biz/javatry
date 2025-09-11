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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketCustomized;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.unit.PlainTestCase;

// #1on1: 話題: 決めの問題という言葉 by いわたさん
// 「逃げの決めの問題」と「合理的な決めの問題」が二つある by jflute
// 逃げの決めの問題: あまりよく考えず、考えることから逃げてるだけの決め
// 合理的な決めの問題: 考えれば答えは出るかもだけど、考える時間が掛かる、得られるメリットが薄いとかの決め
// ここは程度の問題になるので境目がグレーだけど、逃げにはならないようにしたいところ。

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 *
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 *
 * @author jflute
 * @author Rio-Rf-biz
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }
    // TicketBooth.javaを読んだ。seaにはquantityが入るので、そこにアタリをつけた。
    // quantityの初期値は10、buyOneDayPassport()内でquantityをgrepするとquantity--があるので、1減る。直前のifには入らず他に操作してる箇所はない。
    // なので、seaの中身は9になる。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000 -> 7400
    }
    // seaにはsalesProceedsが入るので、そこにアタリをつけた。
    // 初期値null、salesProceeds = handedMoney;が実行されるので、handedMoneyの値が入る。
    // 代入してるから初期値は見なくてもよかったか。
    //
    // 後の問題でone-day price分だけ減るように修正したので、10000ではなくone-day priceの7400が入るようになった。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
    }
    // seaにはsalesProceedsが入るので、そこにアタリをつけた。
    // 初期値null

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9 -> 10
    }
    // booth.getQuantity();が戻り値としてseaに入る。
    // 前の問題でbuyOneDayPassport()を実行すると、quantityが1減ったので9と予想。
    //
    // 次の問題でbuyOneDayPassportを変更した影響で答えは10になる。

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }
    // buyOneDayPassport内の--quantity;をお金が足りないif文の後に移動した。
    // log(sea);の値は、max quantityの10が入るようになった。

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }
    // 変更前はsalesProceeds = salesProceeds + handedMoney;だったので、10000が入っていた。
    // 変更後はsalesProceeds = ONE_DAY_PRICE;にしたので、one-day priceの7400が入るようになった。

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // uncomment after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        int change = booth.buyTwoDayPassport(money).getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money

        // and show two-day passport quantity here
        log(change);
    }
    // buyTwoDayPassportを作成した。
    // 戻り値としてお釣りを返すようにした。

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }
    // if (quantity <= 0) {}の部分にIDEの警告(処理の重複)が出ていたのでメソッドで切り出した。
    // 一時的にcheckQuantity()の前にquantity=0を追加して、修正前と修正後の実行結果を確認した。
    // 修正前の実行結果：org.docksidestage.bizfw.basic.buyticket.TicketBooth$TicketSoldOutException: Sold out
    // 修正後の実行結果：org.docksidestage.bizfw.basic.buyticket.TicketBooth$TicketSoldOutException: Sold out
    //
    // お金が足りているかの処理も共通しているのでメソッドで切り出した。
    // 修正前：org.docksidestage.bizfw.basic.buyticket.TicketBooth$TicketShortMoneyException: Short money: 5000
    // 修正後：org.docksidestage.bizfw.basic.buyticket.TicketBooth$TicketShortMoneyException: Short money: 5000
    //
    // 購入の処理が共通しているのでメソッドで切り出した。
    // 修正前：9, 7400
    // 修正後：9, 7400
    // done iwata [ざつだん] なるほど、エクササイズの "再利用しましょう" 以前にIDEで警告出るんですね^^ by jflute (2025/08/14)
    // #1on1: IDEの警告を見る習慣があるのは素晴らしい、見ないともったいない (2025/08/15)

    // done jflute 1on1にて、流れの再利用についてのお話 (2025/08/14)
    // #1on1: ↓の処理の順番がコピペされている: (2025/08/28)
    //assertQuantityValid(); // チケットの在庫を確認
    //assertHandedMoneyValid(handedMoney, ONE_DAY_PRICE); // お金が足りているか確認
    //Ticket ticket = new Ticket(ONE_DAY_PRICE, 1, false);
    //int change = doBuyPassport(handedMoney, ONE_DAY_PRICE);
    //return new TicketBuyResult(ticket, change);
    // done iwata ということで、全体の流れも再利用できるようにしましょう by jflute (2025/08/28)

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        TicketCustomized oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }
    // 結果が7400, false, trueとなることを確認

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBooth.TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        TicketCustomized twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }
    // claude codeと協力して作りました
    // TicketBuyResultクラスを作成して、フィールドにTicketを持たせるようにすることなどを教えてもらった
    // 結果が13200 + 6800 = 20000となることを確認

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBooth.TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        TicketCustomized twoDayPassport = buyResult.getTicket();
        twoDayPassport.doInPark();
        log(twoDayPassport.isAlreadyIn()); // should be true
        twoDayPassport.doInPark();
        log(twoDayPassport.isAlreadyIn()); // should be true
        //        twoDayPassport.doInPark();
        //        log(twoDayPassport.isAlreadyIn()); // ここは実行されない
    }
    // doInPark()に条件を追加する方針で。
    // org.docksidestage.bizfw.basic.buyticket.TicketBooth$TicketUnavailableException: No more days available
    // 上記の追加したエラーが表示されることを確認

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        TicketCustomized oneDayPassport = booth.buyOneDayPassport(10000).getTicket();
        showTicketIfNeeds(oneDayPassport);
        TicketBooth.TicketBuyResult buyResult = booth.buyTwoDayPassport(15000);
        TicketCustomized twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }
    // done iwata [いいね] 悩んだ過程と仕様が書かれているのがわかりやすい by jflute (2025/08/28)
    // Ticketの種別という意味なのでtwo-days-ticketの定義は値段で判断することにした。
    // 残りの使用可能日数と悩んだ
    //
    // logがohter, two-day passportとなることを確認

    // uncomment when you implement this exercise
    // done iwata コピーして持ってくると、本体の金額が変わった時に追従できないので、本体の定数を参照した方がいい by jflute (2025/08/28)

    // done iwata 修行++: 新しいチケット種別で、たまたまTwoDayと同じ金額のチケット追加されたら破綻する by jflute (2025/08/28)
    // ticketTypeフィールドを追加して、TicketType列挙型で種別を管理するようにした。
    // TODO iwata [いいね] Good, チケットの種類って、無限の組み合わせで作ることできてしまいますから... by jflute (2025/09/11)
    // 紛れがないように正確にってなったら、チケット種別そのものを表現して判定するしかないということです。
    private void showTicketIfNeeds(TicketCustomized ticket) {
        if (ticket.getTicketType() == TicketType.TWO_DAY) { // write determination for two-day passport
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        TicketBooth booth = new TicketBooth();
        int money = 24000;
        TicketBooth.TicketBuyResult buyResult = booth.buyFourDayPassport(money);
        int change = buyResult.getChange();
        log(change);
    }
    // おつりが1600円になることを確認

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        TicketBooth booth = new TicketBooth();
        int money = 10000;
        TicketBooth.TicketBuyResult buyResult = booth.buyNightOnlyTwoDayPassport(money);
        int change = buyResult.getChange();
        log(change);
        TicketCustomized nightTwoDayPassport = buyResult.getTicket();
        nightTwoDayPassport.doInPark();
    }
    // おつりが2600円になることを確認
    // org.docksidestage.bizfw.basic.buyticket.TicketBooth$NightOnlyException: Night-only ticket: available after 19:00
    // 作成した上記のエラーが出ることを確認

    // ===================================================================================
    //                                                                         Bonus Stage
    //                                                                         ===========
    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    // 良さそう
    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }
    // コメントを入れた

    // TODO iwata ちょっとエクササイズ追加でお願いします by jflute (2025/08/28)
    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                         Devil Stage
    //                                                                         ===========
    // TODO iwata ちょっとエクササイズ追加でお願いします2 by jflute (2025/08/28)
    /**
     * If your specification is to share inventory (quantity) between OneDay/TwoDay/...,
     * change the specification to separate inventory for each OneDay/TwoDay/.... <br>
     * (もし、OneDay/TwoDay/...で在庫(quantity)を共有する仕様になってたら、
     * OneDay/TwoDay/...ごとに在庫を分ける仕様に変えてみましょう)
     */
    public void test_class_moreFix_zonedQuantity() {
        // your confirmation code here
    }
}
