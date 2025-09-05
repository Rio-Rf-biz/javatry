package org.docksidestage.bizfw.basic.buyticket;

import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.NIGHT_ONLY_START_HOUR;

import java.time.LocalTime;

/**
 * @author Rio-Rf-biz
 */

// done iwata まあ、ここは大きな業務クラスなので、独立ファイルで作りましょう by jflute (2025/08/28)
// Step6で使っていそうなTicketクラスが存在したのでTicketCustomizedという名前にしました。
public class TicketCustomized {
    private TicketType ticketType;
    private boolean alreadyIn = false;
    private int ticketPrice;
    private int availableDays;
    private boolean nightOnly;

    public TicketCustomized(TicketType ticketType, int ticketPrice, int availableDays, boolean nightOnly) { // コンストラクタ
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.availableDays = availableDays;
        this.nightOnly = nightOnly;
    }

    public void doInPark() {
        if (nightOnly) {
            LocalTime now = LocalTime.now();
            // done iwata コメントで19時って書いちゃうと、値が変わった時に置き去りにされちゃう可能性 by jflute (2025/08/28)
            // e.g. 夜限定チケットは、その時刻以降に入場可能
            // done iwata [読み物課題] オートマティックおうむ返しコメントより背景や理由を by jflute (2025/08/28)
            // https://jflute.hatenadiary.jp/entry/20180625/repeatablecomment
            // 読みました！
            // 「0 を設定するというのは、業務的にどういう意味があるのか？」「なんのために、ここで 0 を設定しているのか？」の方が知りたいことです。
            // コメントは、人間のために書いています。その人間が何を知りたいのか？それを想像しながら書くものです。
            // ↑これらの部分が特に納得でした。
            if (now.getHour() < NIGHT_ONLY_START_HOUR) { // 夜限定チケットはその時刻以降に入場可能
                // done iwata 例外メッセージに時刻を出すのはわかりやすいですが、連動させてましょう by jflute (2025/08/28)
                throw new TicketBooth.NightOnlyException("Night-only ticket: available after " + NIGHT_ONLY_START_HOUR + ":00: now=" + now);
            }
        }
        if (availableDays > 0) { // 入場可能日数が残っている場合
            this.alreadyIn = true; // 入場済みにする
            --availableDays;
        } else {
            // done iwata [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2025/08/28)
            // https://jflute.hatenadiary.jp/entry/20170804/explossloss
            // ↑これを読むとどう直したらいいのかがわかる
            // 読みました！
            // 例外メッセージを誰がどんなシチュエーションで読むのかを考えずに実装していたなと気づきました。
            // 正しい日本語とかよりもまずは周辺の変数を出すべきということを理解しました。
            throw new TicketBooth.TicketUnavailableException("No more days available: availableDays = " + availableDays);
        }
    }
    // done iwata getterたちは、クラスの最後に定義のがわりと多いので移動をお願い by jflute (2025/08/28)
    public TicketType getTicketType() {
        return ticketType;
    }

    public int getDisplayPrice() {
        return ticketPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
