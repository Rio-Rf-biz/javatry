package org.docksidestage.bizfw.basic.buyticket;

// #1on1: enumについて、Java5 (2005年くらい) から導入
// なかった時代は、素のクラスでenumを表現していた。
//public class TicketType (extends Enum) {
//    public static final TicketType ONE_DAY = new TicketType(1);
//    public static final TicketType TWO_DAY = new TicketType(2);
//    ...
//    private TicketType(int days) {
//    }
//}
// ↑これでほぼほぼenumと同じような使い方ができる。
// つまり、enumの仕組み的な本質はこんな感じ。(インスタンス限定クラスと言える)
/**
 * @author Rio-Rf-biz
 */
public enum TicketType {
    ONE_DAY,
    TWO_DAY,
    NIGHT_ONLY_TWO_DAY,
    FOUR_DAY
}
