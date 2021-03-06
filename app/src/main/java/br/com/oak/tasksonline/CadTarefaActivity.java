package br.com.oak.tasksonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class CadTarefaActivity extends AppCompatActivity {

    private int idTarefa;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tarefa);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        idTarefa = (int)i.getLongExtra("idTarefa",0);

        final TaskHelper db = TaskHelper.getInstance();

        if(idTarefa != 0)
        {

            task = db.SearchForTask(idTarefa);

            EditText txtTitulo = (EditText)findViewById(R.id.txtTitulo);
            txtTitulo.setText(task.getTitulo());

            EditText txtDescricao = (EditText)findViewById(R.id.txtDescricao);
            txtDescricao.setText(task.getDescricao());

            SeekBar skDificuldade = (SeekBar)findViewById(R.id.skDificuldade);
            skDificuldade.setProgress(task.getNivel() - 1);

            String[] estados = getResources().getStringArray(R.array.estados);

            for(int j = 0; j < estados.length; j++)
            {
                if(estados[j] == task.getStatus())
                {
                    Spinner spEstado = (Spinner)findViewById(R.id.spEstado);
                    spEstado.setSelection(j);
                }
            }
        }

        Button btnCancelar = (Button)findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSalvar = (Button)findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task tarefa = new Task();

                EditText txtTitulo = (EditText)findViewById(R.id.txtTitulo);
                tarefa.setTitulo(txtTitulo.getText().toString());

                EditText txtDescricao = (EditText)findViewById(R.id.txtDescricao);
                tarefa.setDescricao(txtDescricao.getText().toString());

                SeekBar skDificuldade = (SeekBar)findViewById(R.id.skDificuldade);
                tarefa.setNivel(skDificuldade.getProgress() + 1);

                Spinner spEstado = (Spinner)findViewById(R.id.spEstado);
                tarefa.setStatus(spEstado.getSelectedItem().toString());

                TaskHelper db = TaskHelper.getInstance();

                if(idTarefa == 0)
                    db.AddNovaTask(tarefa);
                else
                {
                    tarefa.setId(idTarefa);
                    db.UpdateTask(tarefa);
                }

                Toast.makeText(CadTarefaActivity.this, "A tarefa foi salva com sucesso.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        Button btnDelete = (Button)findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idTarefa != 0)
                {
                    db.DeleteTask(task);
                }
                finish();
            }
        });
    }
}
