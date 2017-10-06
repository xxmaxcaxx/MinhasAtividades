package com.example.logonrm.minhasatividades.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.logonrm.minhasatividades.R;
import com.example.logonrm.minhasatividades.model.Atividade;

import java.util.List;

/**
 * Created by logonrm on 06/10/2017.
 */

public class AtividadeAdapter
        extends RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder> {

    private List<Atividade> atividades;
    private Activity activity;
    private int lastPositionSelected​;

    public class AtividadeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{
        public TextView tvTitulo;
        public AtividadeViewHolder(View view){
            super(view);
            tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
            view.setOnClickListener(this);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view){
            activity.openContextMenu(view);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo){
            lastPositionSelected​ = getLayoutPosition();
            menu.setHeaderIcon(R.mipmap.ic_launcher);
            menu.setHeaderTitle(activity.getString(R.string.app_name));
            MenuInflater inflater= activity.getMenuInflater();
            inflater.inflate(R.menu.context_lista, menu);
        }
    }

    public int getLastPositionSelected​(){
        return lastPositionSelected​;
    }

    public AtividadeAdapter(Activity activity, List<Atividade> atividades){
        this.activity = activity;
        this.atividades = atividades;
    }

    @Override
    public AtividadeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_row, parent, false);
        return new AtividadeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AtividadeViewHolder holder, int position){
        Atividade movie = atividades.get(position);
        holder.tvTitulo.setText(movie.getTitulo());
    }

    @Override
    public int getItemCount(){
        return atividades.size();
    }

    public void add(Atividade atividade){
        atividades.add(atividade);
        notifyDataSetChanged();
    }

    public void addAll(List<Atividade> atividadeList){
        atividades.addAll(atividadeList);
        notifyDataSetChanged();
    }

    public Atividade getAtividadeSelected(){
        return atividades.get(lastPositionSelected​);
    }

    public void removeAtividadeSelected(){
        atividades.remove(atividades.get(lastPositionSelected​));
        notifyItemRemoved(lastPositionSelected​);
    }
}
