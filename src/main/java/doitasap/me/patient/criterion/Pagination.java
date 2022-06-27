package doitasap.me.patient.criterion;

import lombok.Data;

/**
 * 2022-06-26 오후 11:55
 * author DoitA$ap
 */
@Data
public class Pagination {
    private int pageNum = 1;
    private int rowCount = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    public int getOffset(){
        pageNum = Math.max(pageNum, 1);
        return (pageNum - 1) * rowCount;
    }
}
