package org.docksidestage.javatry.basic.st6.dbms;

// #1on1: まだ見ぬ他の具象クラスを想像してみると抽象クラスは考えやすい (2025/10/30)
// データベースという言葉、広い意味を持ってて、広義のデータベース、狭義のデータベース
// 似た言葉として、プロジェクトって言葉も。
// ということで、今回は「データベース製品」なので、Database Management System。
// 略してDBMS。これだと、ほぼほぼ製品を指すので、MySQL, PostgreSQLという種類に合致する。
/**
 * @author Rio-Rf-biz
 */
public abstract class DatabaseManagementSystem {

    // TODO done iwata protectedだと外から呼べないので、ポリモーフィズムになってない。 by jflute (2025/10/30)
    public final String buildPagingQuery(int pageSize, int pageNumber){
        int offset = pageSize * (pageNumber - 1);
        return buildPagingQueryString(offset, pageSize);
    }

    protected abstract String buildPagingQueryString(int offset, int pageSize);

}
