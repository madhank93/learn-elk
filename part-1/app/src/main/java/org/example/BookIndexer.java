package org.example;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class BookIndexer {
    public void bookCollection(String indexPath) throws IOException {

        //Storing the index in local file system.
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //Creating documents
        Document book1 = new Document();
        book1.add(new TextField("title", "Anna Karenina", Field.Store.YES));
        book1.add(new TextField("author", "Leo Tolstoy", Field.Store.YES));

        Document book2 = new Document();
        book1.add(new TextField("title", "The Sound and the Fury", Field.Store.YES));
        book1.add(new TextField("author", "William Faulkner", Field.Store.YES));

        Document book3 = new Document();
        book1.add(new TextField("title", "Absalom, Absalom!", Field.Store.YES));
        book1.add(new TextField("author", "William Faulkner", Field.Store.YES));

        System.out.println("Preparing to index");
        
        //Adding documents to index
        indexWriter.addDocument(book1);
        indexWriter.addDocument(book2);
        indexWriter.addDocument(book3);
        
        System.out.println("Books indexed successfully");

        indexWriter.close();
    }

    public static void main(String[] args) throws IOException {
        String indexPath = "booksIndex";
        BookIndexer indexer = new BookIndexer();
        indexer.bookCollection(indexPath);
    }
}