package springbootTest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hello.store.test.mongodb.Test1Repository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplicationTests.class)
public class DemoApplicationTests {
	
	@Autowired
	Test1Repository test1Repository;
	
  @Test
  public void contextLoads() {
	  
	  try {
		System.err.println();
		
	} catch (Exception e) {
		//		Runtime.getRuntime().exec("cmd.exe /c start D:/Users/MACHENIKE/HBuilderProjects/firstVue/static/xxx.docx");
		//      Auto-generated catch block cmd.exe /c start https://macsphere.mcmaster.ca/bitstream/11375/24789/2/kunj_finalthesis.pdf
		e.printStackTrace();
	}
	  
  }
  
  /**
   * 测试根据名称模糊查询
   */
  @Test
  public void testFindByTitleLike() {
//      List<Test1> res = test1Repository.findByNameLike("小");
//      System.err.println(res);
      
  }
  
  
}

