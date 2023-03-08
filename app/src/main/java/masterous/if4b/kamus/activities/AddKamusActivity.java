package masterous.if4b.kamus.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import masterous.if4b.kamus.databases.KamusHelper;
import masterous.if4b.kamus.databinding.ActivityAddKamusBinding;
import masterous.if4b.kamus.models.Kamus;

public class AddKamusActivity extends AppCompatActivity {
    private ActivityAddKamusBinding binding;
    private KamusHelper kamusHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddKamusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        kamusHelper = new KamusHelper(this);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etTitle.getText().toString();
                String description = binding.etDescription.getText().toString();

                boolean canAdd = true;
                if (TextUtils.isEmpty(title)) {
                    canAdd = false;
                    binding.etTitle.setError("Title harus diisi!");
                }
                if (TextUtils.isEmpty(description)) {
                    canAdd = false;
                    binding.etDescription.setError("Description harus diisi!");
                }

                if (canAdd) {
                    kamusHelper.open();
                    Kamus kamus = new Kamus(title, description);
                    kamusHelper.insertData(kamus);
                    kamusHelper.close();
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}