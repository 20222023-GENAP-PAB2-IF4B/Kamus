package masterous.if4b.kamus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

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
        kamusViewAdapter = new KamusViewAdapter(this::onItemKamusClick);
        binding.rvKamus.setLayoutManager(new LinearLayoutManager(this));
        binding.rvKamus.setAdapter(kamusViewAdapter);

        getAllData();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSearch = binding.etSearch.getText().toString();

                if (TextUtils.isEmpty(strSearch)) {
                    getAllData();
                } else {
                    kamusHelper.open();
                    ArrayList<Kamus> kamus = kamusHelper.getAllDataByTitle(strSearch);
                    //kamusHelper.close();
                    kamusViewAdapter.setData(kamus);
                }
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

    private void onItemKamusClick(Kamus kamus, int i) {
        Toast.makeText(this, "Item ke-" + i + " : " + kamus.getTitle(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllData();
    }

    private void getAllData() {
        kamusHelper.open();
        ArrayList<Kamus> kamusArrayList = kamusHelper.getAllData();
        //kamusHelper.close();
        kamusViewAdapter.setData(kamusArrayList);
    }
}