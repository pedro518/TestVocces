package com.example.pedro.testvocces;

import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pedro.testvocces.db.UsersContract;
import com.example.pedro.testvocces.model.User;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class UserDetailActivity extends AppCompatActivity {

    private TextView tvVisits;
    private ImageView ivPicture;
    private TextView tvTitle;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvDob;
    private ImageView ivGender;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        tvVisits = findViewById(R.id.tvVisits);
        ivPicture = findViewById(R.id.ivPicture);
        tvTitle = findViewById(R.id.tvTitle);
        tvName = findViewById(R.id.tvName);
        ivGender = findViewById(R.id.ivGender);
        tvPhone = findViewById(R.id.tvPhone);
        tvDob = findViewById(R.id.tvDob);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.containsKey("user")){
                user = (User) extras.get("user");

                tvVisits.setText(user.getVisits()+"");
                //Registro la visita

                HashMap<String, String> params = new HashMap<>();
                params.put("visits", String.valueOf(user.getVisits()));
                UsersContract.updateParamsById(this, user.getId(), params);

                Glide.with(this).load(user.getPicture().getLarge()).into(ivPicture);

                String title = user.getName().getTitle().substring(0, 1).toUpperCase() + user.getName().getTitle().substring(1);
                tvTitle.setText(title);
                String first = user.getName().getFirst().substring(0, 1).toUpperCase() + user.getName().getFirst().substring(1);
                String last = user.getName().getLast().substring(0, 1).toUpperCase() + user.getName().getLast().substring(1);
                tvName.setText(first + " " + last);

                if("male".equals(user.getGender())){
                    ivGender.setImageResource(R.drawable.gender_male);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ivGender.setColorFilter(getColor(R.color.male));
                    } else {
                        ivGender.setColorFilter(getResources().getColor(R.color.male));
                    }
                } else {
                    ivGender.setImageResource(R.drawable.gender_female);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ivGender.setColorFilter(getColor(R.color.female));
                    } else {
                        ivGender.setColorFilter(getResources().getColor(R.color.female));
                    }
                }

                tvPhone.setText(user.getPhone());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
                tvDob.setText(sdf.format(user.getDob()));
            }
        }

    }


}
