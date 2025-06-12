package br.com.web.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.com.web.app.codecs.AlunoCodec;
import br.com.web.app.model.Aluno;

@Repository
public class AlunoRepository {
	private MongoClient clientAluno;
	private MongoDatabase database;
	
	private void criarConexaoMongoClientAluno() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	    AlunoCodec alunoCodec = new AlunoCodec(codec);

	    CodecRegistry registro = CodecRegistries.fromRegistries(
		        MongoClient.getDefaultCodecRegistry(), 
		        CodecRegistries.fromCodecs(alunoCodec));
	    
	    MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();
	    
	    this.clientAluno = new MongoClient(
				new ServerAddress("localhost", 27017),
				MongoCredential.createCredential("admin", "admin", "admin".toCharArray()),
				opcoes);

		this.database = this.clientAluno.getDatabase("test");
	}
	
	public void salvar(Aluno aluno) {
		criarConexaoMongoClientAluno();
		MongoCollection<Aluno> alunos = database.getCollection("alunos", Aluno.class);
		alunos.insertOne(aluno);
		this.clientAluno.close();
	}
	
	public List<Aluno> obterTodosAlunos() {
		criarConexaoMongoClientAluno();
		MongoCollection<Aluno> alunos = database.getCollection("alunos", Aluno.class);
		
		MongoCursor<Aluno> alunosIterator = alunos.find().iterator();
		List<Aluno> alunosEncontrados = new ArrayList<Aluno>();
		
		while (alunosIterator.hasNext()) {
			Aluno aluno = alunosIterator.next();
			alunosEncontrados.add(aluno);
		}
		this.clientAluno.close();
		
		return alunosEncontrados;
	}

}