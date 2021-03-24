package common.parse.context;

import common.parse.entity.bean.ExcelErrorMessage;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExcelParseContext<T> extends WorkerPoolContext {

    /**
     * 解析Excel的错误信息
     */
    private final List<ExcelErrorMessage> errorMessages = Collections.synchronizedList(new LinkedList<>());

    /**
     * 解析成功的实体信息
     */
    private final List<T> entityList = Collections.synchronizedList(new LinkedList<>());

    /**
     * Excel 输入输出流
     */
    private final InputStream inputStream;

    /**
     * Excel对象
     */
    private Workbook workBook;

    /**
     * 用户上传的文件名称
     */
    private String uploadFilename;

    /**
     * 开始解析的行号
     */
    private int beginRowNum = 1;


    public ExcelParseContext(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public int getBeginRowNum() {
        return beginRowNum;
    }

    public void setBeginRowNum(int beginRowNum) {
        this.beginRowNum = beginRowNum;
    }

    public List<ExcelErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public Workbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(Workbook workBook) {
        this.workBook = workBook;
    }

    public void addEntity(T t) {
        entityList.add(t);
    }

    public void addErrorMsg(int row, int col, String msg) {
        errorMessages.add(new ExcelErrorMessage(row, col, msg));
    }

    public String getUploadFilename() {
        return uploadFilename;
    }

    public void setUploadFilename(String uploadFilename) {
        this.uploadFilename = uploadFilename;
    }
}
