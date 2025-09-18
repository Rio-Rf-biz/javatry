package org.docksidestage.bizfw.basic.buyticket;

import static org.docksidestage.bizfw.basic.buyticket.TicketBooth.NIGHT_ONLY_START_HOUR;

import java.time.LocalTime;

/**
 * @author Rio-Rf-biz
 */
// TODO done iwata 元々のTicketクラスであった、タグコメント (Attributeなど) もこっちへ by jflute (2025/09/12)
// done iwata まあ、ここは大きな業務クラスなので、独立ファイルで作りましょう by jflute (2025/08/28)
// Step6で使っていそうなTicketクラスが存在したのでTicketCustomizedという名前にしました。
/**
 * チケットを表すクラス
 * チケットの種類、価格、入場可能日数、夜限定かどうかなどの情報を持ち、入場に伴うチケットのステータス管理などを行う
 */
public class TicketCustomized {
    // TODO done iwata final付けられるものは付けちゃった方が何がimmutableなのか明示できる by jflute (2025/09/12)
    // 固定的な情報なのか、状態的な情報なのか、を明示するためにもfinalが使える。
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final TicketType ticketType;
    private boolean alreadyIn = false;
    private final int ticketPrice;
    private int availableDays;
    private final boolean nightOnly;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * コンストラクタ
     * @param ticketType 入場可能日数、夜限定などのチケットの種類
     * @param ticketPrice チケットの価格
     * @param availableDays 入場可能日数
     * @param nightOnly 夜限定チケットかどうか
     */
    public TicketCustomized(TicketType ticketType, int ticketPrice, int availableDays, boolean nightOnly) { // コンストラクタ
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.availableDays = availableDays;
        this.nightOnly = nightOnly;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    /**
     * 入場可能日数をデクリメントしてチケットのステータスを入場済みにする, 入場に伴うチケットのステータス管理用のメソッド.
     * @param currentTime 現在の時刻 (NotNull)
     * @throws NightOnlyException 夜限定チケットで入場可能時間前に入場しようとした場合
     * @throws TicketUnavailableException 入場可能日数が残っていない場合
     */
    public void doInPark(LocalTime currentTime) {
        if (nightOnly) {
            // done iwata コメントで19時って書いちゃうと、値が変わった時に置き去りにされちゃう可能性 by jflute (2025/08/28)
            // e.g. 夜限定チケットは、その時刻以降に入場可能
            // done iwata [読み物課題] オートマティックおうむ返しコメントより背景や理由を by jflute (2025/08/28)
            // https://jflute.hatenadiary.jp/entry/20180625/repeatablecomment
            // 読みました！
            // 「0 を設定するというのは、業務的にどういう意味があるのか？」「なんのために、ここで 0 を設定しているのか？」の方が知りたいことです。
            // コメントは、人間のために書いています。その人間が何を知りたいのか？それを想像しながら書くものです。
            // ↑これらの部分が特に納得でした。
            if (currentTime.getHour() < NIGHT_ONLY_START_HOUR) { // 夜限定チケットはその時刻以降に入場可能
                // done iwata 例外メッセージに時刻を出すのはわかりやすいですが、連動させてましょう by jflute (2025/08/28)
                throw new NightOnlyException("Night-only ticket: available after " + NIGHT_ONLY_START_HOUR + ":00: now=" + currentTime);
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
            // TODO done iwata Ticketが移動(独立)してきたから、例外クラスもこっちに連れてきちゃっていいんじゃないかな？ by jflute (2025/09/12)
            throw new TicketUnavailableException("No more days available: availableDays = " + availableDays);
        }
    }

    // ===================================================================================
    //                                                                            Exception
    //                                                                            ========
    /**
     * 夜限定チケットで入場可能時間前に入場しようとした場合の例外
     */
    public static class NightOnlyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public NightOnlyException(String msg) {
            super(msg);
        }
    }
    /**
     * 入場可能日数が残っていない場合の例外
     */
    public static class TicketUnavailableException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketUnavailableException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // done iwata getterたちは、クラスの最後に定義のがわりと多いので移動をお願い by jflute (2025/08/28)
    /**
     * ticketTypeを返すゲッター
     * @return チケットの種類
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * ticketPriceを返すゲッター
     * @return チケットの価格
     */
    public int getTicketPrice() {
        return ticketPrice;
    }

    /**
     * alreadyInを返すゲッター
     * @return 入場済みかどうか
     */
    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
