package br.com.mongo.executeapp;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal {

	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@localhost:27017/?authSource=admin");
		MongoClient client = new MongoClient(uri);
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> alunos = database.getCollection("alunos");
		Document aluno = alunos.find().first();
		System.out.println(aluno);
	}

}