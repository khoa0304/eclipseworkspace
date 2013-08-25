package example.lucene;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class LunceneIndexWriterTest {

	
	Version LUCENE_VERSION = Version.LUCENE_40;
	
	@Test
	public void testWriteIndex() throws FileNotFoundException, IOException,
			ParseException {
		String indexDirectory = "C:/LuncenTestIndex";
		Indexer index = new Indexer(indexDirectory);
		index.indexData();
	}

	@Test
	public void testMemIndex() throws IOException, ParseException {

		Analyzer analyzer = new StandardAnalyzer(LUCENE_VERSION);

		// Store the index in memory:
		//Directory directory = new RAMDirectory();
		// To store an index on disk, use this instead:
		Directory directory = FSDirectory.open(new File("C:/LuncenTestIndex"));
		IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION, analyzer);
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter iwriter = new IndexWriter(directory, config);
		Document doc = new Document();
	
		String text = "With the seller flying back to Ankara that evening the situation was getting hopeless. What was " +
		    		" the solution? It turned out to be nothing more than an old-fashioned verbal agreement sealed with a " +
		    		" walked out with the seller’s bank information on a piece of paper and the portfolio of images under" +
		    		" his arm. Needless to say, we transferred the funds the next day, and we remain grateful and" +
		    		" impressed by this unknown person’s trust in one of us. It recalls something that might have" +
		    		 " happened a long time ago." ;
		
		TextField textField = new TextField("fieldname", text, Field.Store.YES);
		
		doc.add(textField);
		iwriter.addDocument(doc);
		iwriter.close();

		// Now search the index:
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser(LUCENE_VERSION,
				"fieldname", analyzer);
	    TermQuery tq = new TermQuery(new Term("fieldname", "ankara"));
		//Query query = parser.parse("text");
		ScoreDoc[] hits = isearcher.search(tq, null, 1000).scoreDocs;
		// Iterate through the results:
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			List<IndexableField> list = hitDoc.getFields();
			for(IndexableField index : list){
				System.out.println(index.stringValue());
			}
			System.out.println(hitDoc.get("fieldname"));
		}
		ireader.close();
		directory.close();
	}
	
	
	
}
