package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケット種別ごとの在庫を管理するクラス。
 * @author Rio-Rf-biz
 */
public class TicketStock {

    private final TicketType ticketType;
    private int quantity;

    public TicketStock(TicketType ticketType, int initialQuantity) {
        this.ticketType = ticketType;
        this.quantity = initialQuantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void consume() {
        --quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
