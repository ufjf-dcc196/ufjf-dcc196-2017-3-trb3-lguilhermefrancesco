package br.com.oak.tasksonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskHelper db = TaskHelper.getInstance();
        db.initTasks(this);
        ListView list = (ListView)findViewById(R.id.lstViewTasks);
        list.setAdapter(db.getTasksCursor());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,CadTarefaActivity.class);
                intent.putExtra("idTarefa", l);

                startActivity(intent);
            }
        });

        Button btnNewTarefa = (Button)findViewById(R.id.bttNewTask);

        btnNewTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CadTarefaActivity.class);
                startActivity(intent);
            }
        });
    }
}
