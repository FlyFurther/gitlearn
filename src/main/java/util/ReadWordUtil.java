package util;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import java.io.FileInputStream;

/**
 * Created by hq on 17/6/14.
 */
public class ReadWordUtil {

    public static void main(String[] args) {
        readWordTable();
    }

    public static void readWordTable() {
        try{
            FileInputStream in = new FileInputStream("/Users/hq/Desktop/20170410095811383.doc");//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);
            //迭代文档中的表格
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                //迭代行，默认从0开始
                for (int i = 0; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);//取得单元格
                        //取得单元格的内容
                        for(int k=0;k<td.numParagraphs();k++){
                            Paragraph para =td.getParagraph(k);
                            String s = para.text();
                            if (s.contains("\r")) {
                                s = s.replace("\r", "");
                            }
                            System.out.print(s + "\t");
                        } //end for cell
                    } //end for row
                    System.out.println();
                }   //end for table
                System.out.println("++++++++++++++++++++++");
            } //end while
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
