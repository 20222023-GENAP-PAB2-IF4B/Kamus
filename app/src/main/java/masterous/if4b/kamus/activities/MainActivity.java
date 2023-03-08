package masterous.if4b.kamus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import masterous.if4b.kamus.R;
import masterous.if4b.kamus.adapters.KamusViewAdapter;
import masterous.if4b.kamus.databases.KamusHelper;
import masterous.if4b.kamus.databinding.ActivityMainBinding;
import masterous.if4b.kamus.models.Kamus;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private KamusHelper kamusHelper;
    private KamusViewAdapter kamusViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        kamusHelper = new KamusHelper(this);
        kamusViewAdapter = new KamusViewAdapter();
        binding.rvKamus.setLayoutManager(new LinearLayoutManager(this));
        binding.rvKamus.setAdapter(kamusViewAdapter);

        getAllData();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddKamusActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }

    private void getAllData() {
        kamusHelper.open();
        ArrayList<Kamus> kamusArrayList = kamusHelper.getAllData();
        kamusHelper.close();
        kamusViewAdapter.setData(kamusArrayList);
    }
}