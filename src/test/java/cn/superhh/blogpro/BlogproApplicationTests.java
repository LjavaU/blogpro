package cn.superhh.blogpro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogproApplicationTests {

    @Test
    public void contextLoads() {
        String sss="sdfaTFGFF";
    String s=  sss.replace('T',' ');
        System.out.println(s);
    }
    @Test
    public void test(){
      Path path=  Paths.get("c://lucene");
        System.out.println(path.toString());
    }

}
