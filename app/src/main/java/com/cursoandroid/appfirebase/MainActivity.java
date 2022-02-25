package com.cursoandroid.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.CDATASection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    /*
    private DatabaseReference referecia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

     */

    private ImageView imageFoto;
    private Button buttonUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUpload = findViewById(R.id.buttonUpload);
        imageFoto = findViewById(R.id.imageFoto);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //configura para imagem ser salva na memoria
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                //Reculpera bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                //Comprimi bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75,baos );
                //Converter o baos para pixel brutos em uma matriz de bytes
                byte[] dadosImagem = baos.toByteArray();
                //Define nos para o Storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                //dowloads
                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(imagemRef)
                        .into(imageFoto);



                //Delete imagem
               /* imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Ero ao deletar", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar", Toast.LENGTH_LONG).show();
                    }
                });

                */


                //Nome da imagem
               // String nomeArquivo = UUID.randomUUID().toString();
                //StorageReference imagemRef = imagens.child(nomeArquivo + "jpeg");

                //Retorna obj que ira controlar o upload
                /*UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, "Upload da imagem falhou:"+
                                    e.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload:", Toast.LENGTH_LONG).show();
                            }
                        });

                    }


                });

                 */

            }
        });




       // DatabaseReference usuarios = referecia.child("usuarios");
        //recuperar usuario pelo id
       // DatabaseReference usuarioPesquisa = usuarios.child("-MwhNwicEj9FcvsMVh08");

            //pesquisa pelo Query
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Marcelo");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);
        //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);

        //pesquisa utilizndo maior ou igual (>=)
       // Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(35);

        //Menor ou igual (<=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(22);

        //mesclar os dois valores
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(22);

        //filtrar por palavras
        /*
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("A").endAt("T" + "\uf8ff");


        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               /* Usuario dadosUsuario = snapshot.getValue(Usuario.class);

                Log.i("Dados Usuarios: ", "nome " + dadosUsuario.getNome()
                + "idade " + dadosUsuario.getIdade()
                + "Sobrenome " + dadosUsuario.getSobrenome());
                  */
                //Log.i("Dados Usuarios ", snapshot.getValue().toString());

            /*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*
        Usuario usuario = new Usuario();
        usuario.setNome("Rodrigo");
        usuario.setSobrenome("Matos");
        usuario.setIdade(35);
        usuarios.push().setValue(usuario);

         */


        // deslogar usuario
        //usuario.signOut();
        //Logar usuario

        /*
        usuario.signInWithEmailAndPassword("jamilton2@gmail.com" , "sa1234")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("SignInUser", "Sucesso ao logar usuario!");
                        }else{
                            Log.i("SignInUser", "Erro ao logar usuario!");
                        }
                    }
                });/*



        // Verificar usuarios Logados
        if (usuario.getCurrentUser()!= null){
            Log.i("CurrentUser", " usuario logado ");
        }else{
            Log.i("CurrentUser", " usuario nao logado");
        }
         /*
        // Cadastrar usuarios//

        usuario.createUserWithEmailAndPassword(
                "jamilton2@gmail.com" , "sa1234")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar usuario!");
                        }else{
                            Log.i("CreateUser", "Erro ao cadastrar usuario!");
                        }

                    }
                });

         */

        /*
        referecia.child("usuarios2").child("001").child("nome").setValue("Sarah");

        DatabaseReference usuarios = referecia.child("usuarios").child("001");
        DatabaseReference produto = referecia.child("produto");




        Salvar dados no Firebase
       Usuario usuario = new Usuario();
       usuario.setNome("Maria");
       usuario.setSobrenome("Silva");
       usuario.setIdade(45);


        Produtos produtos = new Produtos();
        produtos.setDescricao("Acer Aspire");
        produtos.setMarca("Acer");
        produtos.setPreco(1234.00);

       //produto.child("002").setValue(produtos);

        //recuperar os dados
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */






    }
}