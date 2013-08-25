package example.lucene;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


public class Indexer {
	private IndexWriter indexWriter;
	
	/*Location of directory where index files are stored */
	private String indexDirectory ;
	
	/*Location of data directory */
	private String dataDirectory ;
	
	public static final Version luceneVersion = Version.LUCENE_40;
	
	public Indexer(String indexDirectory, String dataDirectory){
		this.indexDirectory = indexDirectory ;
		this.dataDirectory = dataDirectory ;
	}
	
	public Indexer(String indexDirectory){
		this.indexDirectory = indexDirectory ;
	}
	
	/**
	 * This method reads data directory and loads all properties files.
	 * It extracts  various fields and writes them to the index using IndexWriter.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 */
	void indexData() throws FileNotFoundException, IOException, ParseException{

		//Directory directory =  FSDirectory.open(new File(indexDirectory));
		
		Directory directory = new RAMDirectory();
	
		Analyzer standardAnalyzer = new StandardAnalyzer(luceneVersion);
		
		
		IndexWriterConfig indexWriteConfig =  new IndexWriterConfig(luceneVersion, standardAnalyzer);
	
		//Create the instance of deletion policy
//			IndexDeletionPolicy deletionPolicy = 
//									new KeepOnlyLastCommitDeletionPolicy(); 
		indexWriter = new IndexWriter(directory, indexWriteConfig);
	    Document doc = new Document();
	    String text = "With the seller flying back to Ankara that evening the situation was getting hopeless. What was " +
	    		" the solution? It turned out to be nothing more than an old-fashioned verbal agreement sealed with a " +
	    		" walked out with the seller’s bank information on a piece of paper and the portfolio of images under" +
	    		" his arm. Needless to say, we transferred the funds the next day, and we remain grateful and" +
	    		" impressed by this unknown person’s trust in one of us. It recalls something that might have" +
	    		 " happened a long time ago." ;
	    
	    String fiedlName = "fieldName"; 

	    doc.add(new Field(fiedlName, text, TextField.TYPE_STORED));
	    indexWriter.addDocument(doc);
	    indexWriter.close();
	    
	    // Now search the index:
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	   
	    QueryParser parser = new QueryParser(luceneVersion,fiedlName, standardAnalyzer);
	    Query query = parser.parse("flying");
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	   
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      System.out.println(hitDoc.get(fiedlName));
	    }
	    ireader.close();
	    directory.close();
	}
	
	
	
}
