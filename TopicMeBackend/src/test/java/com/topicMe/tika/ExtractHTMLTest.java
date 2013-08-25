package com.topicMe.tika;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ExtractHTMLTest {

	InputStream is = null;

	FileOutputStream fos = null;
	File outFile;

	@AfterMethod
	public void tearDownMethod(){
		try {

			if (is != null) {
				is.close();
			}

			if (fos != null) {
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testExtractHTML() throws IOException, SAXException,
			TikaException {

		File file = new File("C:\\Programming\\testResources\\html\\tika\\temp");

		for (File f : file.listFiles()) {

			if (f.isFile()) {
				System.out.println(" File Name: " + f.getAbsolutePath());
				URL url = f.toURI().toURL();
				InputStream input = url.openStream();
				LinkContentHandler linkHandler = new LinkContentHandler();
				ContentHandler textHandler = new BodyContentHandler(System.out);
				ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
				TeeContentHandler teeHandler = new TeeContentHandler(
						linkHandler, textHandler, toHTMLHandler);
				Metadata metadata = new Metadata();
				ParseContext parseContext = new ParseContext();
				HtmlParser parser = new HtmlParser();
				parser.parse(input, teeHandler, metadata, parseContext);
				// System.out.println("title:\n" + metadata.get("title"));

				// printLinkList(linkHandler.getLinks());
				System.out.println("text:\n" + textHandler.toString());
				// System.out.println("html:\n" + toHTMLHandler.toString());
			}
		}

	}

	@Test
	public void testExtractPDF() {

		try {
			is = new BufferedInputStream(new FileInputStream(new File(
					"C:/Users/Khoa/Documents/ebooks/Tika in Action.pdf")));

			outFile = new File(
					"C:/Programming/testResources/parsed_result/Tika_In_Action.txt");
			if (!outFile.exists()) {
				outFile.createNewFile();
			}

			fos = new FileOutputStream(outFile, false);
			Parser parser = new AutoDetectParser();
			ContentHandler handler = new BodyContentHandler(fos);

			Metadata metadata = new Metadata();

			parser.parse(is, handler, metadata, new ParseContext());
			
			 for (String name : metadata.names()) {
				 String value = metadata.get(name);
				
				 if (value != null) {
				 System.out.println("Metadata Name:  " + name);
				 System.out.println("Metadata Value: " + value);
				 }
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {

			try {

					if (is != null) {
						is.close();
					}
	
					if (fos != null) {
						fos.flush();
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Test
	public void testExtractWebPage() throws Exception{
		
		String urlString = "http://docs.oracle.com/cd/E16764_01/integration.1111/e10224/bp_workflow.htm#BABGJDEE";
		
		outFile = new File(
				"C:/Programming/testResources/parsed_result/" + urlString.substring(urlString.lastIndexOf("/")+1));
		
		if (!outFile.exists()) {
			outFile.createNewFile();
		}

		fos = new FileOutputStream(outFile, false);
		
		URL url = new URL(urlString);
		
		is = url.openStream();
		
		LinkContentHandler linkHandler = new LinkContentHandler();
		ContentHandler textHandler = new BodyContentHandler(fos);
		ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
		TeeContentHandler teeHandler = new TeeContentHandler(
				linkHandler, textHandler, toHTMLHandler);
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext();
		HtmlParser parser = new HtmlParser();
		parser.parse(is, teeHandler, metadata, parseContext);
		// System.out.println("title:\n" + metadata.get("title"));

		// printLinkList(linkHandler.getLinks());
		System.out.println("text:\n" + textHandler.toString());
		// System.out.println("html:\n" + toHTMLHandler.toString());
	}
	
	
	@Test
	public void testExtractWebPage2() throws Exception{
		
		String urlString = "http://www.casact.net/pubs/forum/06wforum/06w55.pdf";
		
		outFile = new File(
				"C:/Programming/testResources/parsed_result/" + urlString.substring(urlString.lastIndexOf("/")+1));
		
		if (!outFile.exists()) {
			outFile.createNewFile();
		}

		fos = new FileOutputStream(outFile, false);
		
		URL url = new URL(urlString);
		
		InputStream input = url.openStream();
		
		Parser parser = new AutoDetectParser();
		ContentHandler handler = new BodyContentHandler(fos);

		Metadata metadata = new Metadata();
		parser.parse(input, handler, metadata, new ParseContext());
	}
	
	private void printLinkList(List<Link> list) {

		for (Link link : list) {
			System.out.println(" Text " + link.getText());
			System.out.println(" REL " + link.getRel());
			System.out.println(" URI " + link.getUri());
			System.out.println(" Type " + link.getType());
		}
	}
}
