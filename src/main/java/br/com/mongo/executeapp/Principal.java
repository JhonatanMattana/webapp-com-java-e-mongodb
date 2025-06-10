package br.com.mongo.executeapp;

import java.sql.Date;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Principal {

	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@localhost:27017/?authSource=admin");
		MongoClient client = new MongoClient(uri);
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> alunos = database.getCollection("alunos");
		Document aluno = alunos.find().first();
		System.out.println(aluno);
		
//		Document novoAluno = criarNovoAluno();
//		alunos.insertOne(novoAluno);

//		atualizarAluno(alunos);
		
		alunos.deleteOne(Filters.eq("nome", "João da Silva"));
		
		aluno = alunos.find().first();
		System.out.println(aluno);
		
		client.close();
	}

	private static void atualizarAluno(MongoCollection<Document> alunos) {
		alunos.updateOne(Filters.eq("nome", "João"),
			new Document("$set", new Document("nome", "João da Silva")));
	}

	private static Document criarNovoAluno() {
		Document novoAluno = new Document("nome", "João")
			.append("data_nascimento", new Date(2002, 10, 12))
			.append("curso", new Document("nome", "História"))
			.append("notas", Arrays.asList(10,9,8))
			.append("habilidades", Arrays.asList(new Document()
					.append("nome", "Inglês")
					.append("nível", "Básico"),
					new Document()
						.append("nome", "Espanhol")
						.append("nível", "Básico")));
		return novoAluno;
	}

}