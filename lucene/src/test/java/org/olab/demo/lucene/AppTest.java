package org.olab.demo.lucene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class AppTest extends TestCase {
    
    public void testString() throws IOException, ParseException {
        
        // Création de l'index (en mémoire)
        
        Directory directory = new RAMDirectory();
        IndexWriter writer = new IndexWriter(directory, new StandardAnalyzer(), true, MaxFieldLength.LIMITED);
        
        Document d1 = new Document();
        d1.add(new Field("description", "Bonjour, quel temps fait-il aujourd'hui?", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d1);
        
        Document d2 = new Document();
        d2.add(new Field("description", "Bonjour, je m'appelle bonjour.", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d2);
        
        Document d3 = new Document();
        d3.add(new Field("description", "Le soleil brille.", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d3);
        
        writer.close();
        
        // Recherche full text
        
        Analyzer analyzer = new StandardAnalyzer();
        IndexSearcher searcher = new IndexSearcher(directory);
        QueryParser parser = new QueryParser("description", analyzer);
        Query query = parser.parse("Bonjour");
        
        TopDocCollector collector = new TopDocCollector(10);
        searcher.search(query, collector);
        
        assertEquals(collector.getTotalHits(), 2);
        
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println("document n°" + docId + ", score=" + hits[i].score);
        }
    }

    public void testWord() throws IOException, ParseException, SAXException, TikaException {
        
        // Indexation en mémoire
        Directory directory = new RAMDirectory();
        IndexWriter writer = new IndexWriter(directory, new StandardAnalyzer(), true, MaxFieldLength.LIMITED);
        
        Document d1 = new Document();
        d1.add(new Field("body", convertToPlainText("src/test/resources/d1.doc"), Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d1);
        
        Document d2 = new Document();
        d2.add(new Field("body", convertToPlainText("src/test/resources/d2.doc"), Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d2);
        
        writer.close();
        
        // Recherche full text
        
        Analyzer analyzer = new StandardAnalyzer();
        IndexSearcher searcher = new IndexSearcher(directory);
        QueryParser parser = new QueryParser("body", analyzer);
        Query query = parser.parse("relationnelle");
        
        TopDocCollector collector = new TopDocCollector(10);
        searcher.search(query, collector);
        
        assertEquals(collector.getTotalHits(), 1);
    }
    
    public void testPdf() throws IOException, ParseException, SAXException, TikaException {
        // Indexation en mémoire
        Directory directory = new RAMDirectory();
        IndexWriter writer = new IndexWriter(directory, new StandardAnalyzer(), true, MaxFieldLength.LIMITED);
        
        Document d1 = new Document();
        d1.add(new Field("body", convertToPlainText("src/test/resources/d3.pdf"), Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(d1);
        
        writer.close();
        
        // Recherche full text
        
        Analyzer analyzer = new StandardAnalyzer();
        IndexSearcher searcher = new IndexSearcher(directory);
        QueryParser parser = new QueryParser("body", analyzer);
        Query query = parser.parse("exonérées");
        
        TopDocCollector collector = new TopDocCollector(10);
        searcher.search(query, collector);
        
        assertEquals(collector.getTotalHits(), 1);
    }

    private String convertToPlainText(String filename) throws FileNotFoundException, IOException, SAXException, TikaException {
        ContentHandler handler = new BodyContentHandler();
        InputStream stream = new FileInputStream(filename);
        try {
            Metadata metadata = new Metadata();
            new AutoDetectParser().parse(stream, handler, metadata);
        } finally {
            stream.close();
        }
        return handler.toString();
    }
}
