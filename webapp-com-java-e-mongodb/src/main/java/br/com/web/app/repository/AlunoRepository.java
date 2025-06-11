package br.com.web.app.repository;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.web.app.model.Aluno;

@Repository
public class AlunoRepository {

	public void salvar(Aluno aluno) {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Aluno> alunos = database.getCollection("alunos", Aluno.class);
		alunos.insertOne(aluno);
		client.close();
	}
	
}