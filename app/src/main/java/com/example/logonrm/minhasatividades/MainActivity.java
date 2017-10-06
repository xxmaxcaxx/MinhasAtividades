package com.example.logonrm.minhasatividades;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logonrm.minhasatividades.adapter.AtividadeAdapter;
import com.example.logonrm.minhasatividades.dao.AtividadeDAO;
import com.example.logonrm.minhasatividades.model.Atividade;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMinhasAtividades;
    private AtividadeAdapter mAdapter;
    private FloatingActionMenu fMenu;

    private AtividadeDAO atividadeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fMenu = (FloatingActionMenu)findViewById(R.id.fMenu);
        atividadeDAO = new AtividadeDAO();
        inicializaLista();
        carregaMinhasAtividades();
    }

    private void inicializaLista(){
        rvMinhasAtividades = (RecyclerView) findViewById(R.id.rvMinhasAtividades);
        mAdapter = new AtividadeAdapter(this, new ArrayList<Atividade>());
        rvMinhasAtividades.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMinhasAtividades.setItemAnimator(new DefaultItemAnimator());
        rvMinhasAtividades.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvMinhasAtividades.setAdapter(mAdapter);
    }

    private void carregaMinhasAtividades() {
        mAdapter.addAll(atividadeDAO.findAll());
        mAdapter.notifyDataSetChanged();
    }

    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_apagar:
                mAdapter.getAtividadeSelected().delete();
                mAdapter.removeAtividadeSelected();
                Toast.makeText(this, "Game apagado com sucesso", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_editar:
                dialogAtividade(mAdapter.getAtividadeSelected());
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void novaAtividade(View v){
        fMenu.close(true);
        dialogAtividade(new Atividade());
    }

    private void dialogAtividade(final Atividade atividade){
        final boolean isInsert = atividade.getId() == null ? true : false;
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.new_atividade);

        dialog.setTitle("Nova Atividade");

        final EditText etTitulo = (EditText)dialog.findViewById(R.id.etTitulo);

        etTitulo.setText(atividade.getTitulo());

        Button btConfirmar = (Button) dialog.findViewById(R.id.btConfirmar);

        btConfirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                atividade.setTitulo(etTitulo.getText().toString());
                atividade.save();

                if(isInsert)
                    mAdapter.add(atividade);

                mAdapter.notifyDataSetChanged();

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Dado gravado com sucesso!",Toast.LENGTH_SHORT).show();
            }
        });

        Button btCancelar = (Button) dialog.findViewById(R.id.btCancelar);

        btCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
