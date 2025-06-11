package br.com.web.app.repository;

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
import com.mongodb.client.MongoDatabase;

import br.com.web.app.codecs.AlunoCodec;
import br.com.web.app.model.Aluno;

@Repository
public class AlunoRepository {

	public void salvar(Aluno aluno) {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
	    AlunoCodec alunoCodec = new AlunoCodec(codec);

	    CodecRegistry registro = CodecRegistries.fromRegistries(
	        MongoClient.getDefaultCodecRegistry(), 
	        CodecRegistries.fromCodecs(alunoCodec));
	    
	    MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();
		
		MongoClient client = new MongoClient(
				new ServerAddress("localhost", 27017),
				MongoCredential.createCredential("admin", "admin", "admin".toCharArray()),
				opcoes);
		
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Aluno> alunos = database.getCollection("alunos", Aluno.class);
		alunos.insertOne(aluno);
		client.close();
	}
	
}