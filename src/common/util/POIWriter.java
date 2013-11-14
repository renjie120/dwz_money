package common.util; 
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 对Excel2003进行写的类.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class POIWriter{
    /**
     * 工作簿
     */
    private HSSFWorkbook workbook;

    /**
     * 日志记录
     */
    private Log log = LogFactory.getLog("logger");

    /**
     * excel表
     */
    private HSSFSheet sheet;

    /**
     * excel文件流
     */
    private FileInputStream fis;

    /**
     * 消息
     */
    private StringBuffer msg = null;

    public POIWriter()
    {
            
    }
    /**
     * 从一个javaBean的集合向目标文件写数据
     * @param data 一个装有javaBean的list
     * @param targetFile 目标excel文件
     */
    public String setSheetFromTable(List data, String targetFile) {
            try {
                    HSSFWorkbook targetWorkbook = new HSSFWorkbook();
                    FileOutputStream fout = new FileOutputStream(targetFile,true);
                    setDataToWorksheet(data, targetWorkbook, 0);
                    targetWorkbook.write(fout);
                    fout.flush();
                    fout.close();
                    return "ok";
            } catch (Exception e) {
                    log.error("出现异常", e);
                    return "";
            }
    }

    /**
     * 将list的数据放进excel的一个工作表中去。
     * @param data 数据的来源list，是一个来自于数据库的装满了javabean的list
     * @param workbook 目的excel工作簿
     * @param sheetNum 目的excel工作簿的表格要填写数据的页码
     */
    public void setDataToWorksheet(List data,HSSFWorkbook workbook,int sheetNum)
    {
            HSSFRow title = null;
            HSSFSheet sheet = null;
            try{
            if(data.size()<1)
            {
                    return ;
            }
            sheet = workbook.createSheet();
            
            //下面设置cell的文字格式
            font = workbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setUnderline((byte)1);
            font.setColor(HSSFColor.BLUE.index);
            //下面设置标题行的样式                
            titleStyle = workbook.createCellStyle();
            titleStyle.setBorderBottom((short)1);
            titleStyle.setBorderLeft((short)1);
            titleStyle.setBorderRight((short)1);
            titleStyle.setBorderTop((short)1);
            titleStyle.setFont(font);
            titleStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            
            //取list中的第一个数据，进行属性名称的读取，准备放到excel表格中的第一行
            Object aData = data.get(0);
            PropertyDescriptor[] props = Introspector.getBeanInfo(
                            aData.getClass(), Object.class).getPropertyDescriptors();
            //在表格的第一行建立一个数据行，用来放置这些属性的名称
            title = sheet.createRow(0);
            //设置行高.注意值设置的很大。。
            title.setHeight((short)500);
            for(short temp = 0; temp < props.length; temp++)
            {        HSSFCell cell = title.createCell(temp);
                    cell.setCellStyle(titleStyle);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(props[temp].getShortDescription());
                    //设置各个列的宽度
                    sheet.setColumnWidth((short)temp, (short)5000);
            }
            for(int temp = 0;temp<data.size();temp++)
            {
                    //实际的数据是开始从第二行开始进行传递的
                    HSSFRow row = sheet.createRow(temp+1);
                    //取出javabean对象里面的各个属性的值
                    Object obj = data.get(temp);
                    String values[] = getPropertyOfBean(obj);
                    for(short cellNum=0;cellNum<values.length;cellNum++){
                            HSSFCell cell = row.createCell(cellNum);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(values[cellNum]);
                    }
            }
            }catch(Exception e)
            {
                    log.error("出现bug",e);
            }
    }

    HSSFFont font = null;
    HSSFCellStyle titleStyle = null;
    /**
     * 设置excel表格的样式
     *
     */
    private void setStyle()
    {
            
    }
    
    /**
     * 根据一个javabean对象，返回这个对象的属性值集合，使用到反射机制。
     * @return
     */
    private String[] getPropertyOfBean(Object obj) {
            String[] result = null;
            try {
                    PropertyDescriptor[] props = Introspector.getBeanInfo(
                                    obj.getClass(), Object.class).getPropertyDescriptors();
                    result = new String[props.length];
                    for (int temp = 0; temp < props.length; temp++) {
                            try {
                                    result[temp] = props[temp].getReadMethod().invoke(obj)
                                                    .toString();
                            } catch (Exception e) {
                                    log.error("出现异常", e);
                                    return null;
                            }
                    }
            } catch (Exception e1) {
                    log.error("出现异常", e1);
                    return null;
            }
            return result;
    }
}