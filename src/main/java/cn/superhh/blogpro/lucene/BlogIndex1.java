package cn.superhh.blogpro.lucene;

import cn.superhh.blogpro.pojo.Blog;
import cn.superhh.blogpro.util.DateUtil;

import cn.superhh.blogpro.util.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;



import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Component
public class BlogIndex1 {

    private Directory index=null;
    public List<Blog> search(String q) throws IOException, ParseException, InvalidTokenOffsetsException {
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        Query query= new QueryParser("content",analyzer).parse(q);
        Query query1=new QueryParser("title",analyzer).parse(q);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query1, BooleanClause.Occur.SHOULD);

        index= FSDirectory.open(Paths.get("C://lucene", new String[0]));
        IndexReader indexReader= DirectoryReader.open(index);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        TopDocs hits = indexSearcher.search(booleanQuery.build(), 100);

        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Blog> blogList = new LinkedList();
        for (ScoreDoc scoreDoc : hits.scoreDocs)
        {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setId(Integer.valueOf(Integer.parseInt(doc.get("id"))));
            blog.setReleaseDateStr(doc.get("releaseDate"));
            String title = doc.get("title");
            String content = StringEscapeUtils.escapeHtml(doc.get("content"));
            if (title != null)
            {
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                } else {
                    blog.setTitle(hTitle);
                }
            }
            if (content != null)
            {
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isEmpty(hContent))
                {
                    if (content.length() <= 200) {
                        blog.setContent(content);
                    } else {
                        blog.setContent(content.substring(0, 200));
                    }
                }
                else {
                    blog.setContent(hContent);
                }
            }
            blogList.add(blog);
        }
        return blogList;
    }
        /*
                 ScoreDoc[] scoreDocs= indexSearcher.search(booleanQuery.build(),5).scoreDocs;
                 System.out.println("数组长度为:"+scoreDocs.length);
               List<Blog> blogList= showSearchResults(indexSearcher,scoreDocs,query,analyzer);
                   indexReader.close();
             return blogList;*/

    private static List<Blog> showSearchResults(IndexSearcher indexSearcher, ScoreDoc[] scoreDocs, Query query, SmartChineseAnalyzer analyzer) throws IOException, InvalidTokenOffsetsException {
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        List<Blog> bloglist=new LinkedList<>();
        for(int i=0;i<scoreDocs.length;i++) {
            ScoreDoc scoreDocs1 = scoreDocs[i];
            int index = scoreDocs1.doc;
            Document document = indexSearcher.doc(index);

            Blog blog = new Blog();
            if((document.get("id"))!=null ) {
                blog.setId(Integer.valueOf(Integer.parseInt(document.get("id"))));
                blog.setReleaseDateStr(document.get("releaseDate"));

                String title = document.get("title");
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(document.get("title")));
                String fieldContent = highlighter.getBestFragment(tokenStream, document.get("title"));
                blog.setTitle(fieldContent);


                TokenStream tokenStream1 = analyzer.tokenStream("content", new StringReader(document.get("content")));
                String fieldContent2 = highlighter.getBestFragment(tokenStream1, document.get("content"));
                blog.setContent(fieldContent2);

                bloglist.add(blog);
            }


        }
        return bloglist;
        }



    /**
     * 获得indexwriter 对象
     * @return
     * @throws IOException
     */
    public IndexWriter getWriter() throws IOException {
             SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
         index= FSDirectory.open(Paths.get("C://lucene", new String[0]));
            IndexWriterConfig indexWriterConfig=new IndexWriterConfig(analyzer);
            IndexWriter indexWriter=new IndexWriter(index,indexWriterConfig);
            return indexWriter;
    }
    /**
     * 创建索引
     *
     * @throws IOException
     */
     public   void  crateIndex(Blog blog) throws IOException {
              IndexWriter indexWriter=getWriter();
                add(blog,indexWriter);
               indexWriter.close();

    }

    /**
     * 添加索引
     * @param blog
     * @param indexWriter
     * @throws IOException
     */
    private   void add(Blog blog, IndexWriter indexWriter) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        indexWriter.addDocument(doc);

    }

    /**
     * 更新索引
     * @param blog
     * @throws IOException
     */
    public void updateIndex(Blog blog) throws IOException {
       IndexWriter indexWriter=getWriter();
        Document doc = new Document();
        doc.add(new TextField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new TextField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        indexWriter.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        indexWriter.close();
    }

    /**
     * 刪除索引
     * @param ids
     * @throws IOException
     */
   public void deleteIndex(int[] ids) throws IOException {
       IndexWriter indexWriter=getWriter();
       for(int i=0;i<ids.length;i++){
           indexWriter.deleteDocuments(new Term("id",String.valueOf(ids[i])));
           indexWriter.commit();
           indexWriter.close();
       }
   }
}
