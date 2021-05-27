package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class UserPagerActivity extends AppCompatActivity {
    private ViewPager userViewPager;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);
        userViewPager = findViewById(R.id.UserViewPager);
        users = UserList.get(this).getUsers();
        int position = getIntent().getIntExtra("position",1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        userViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) { // фарагмент с текущим пользователем
               User user = users.get(position);
                Bundle args = new Bundle();
                args.putInt("position",position);
                UserFragment fragment = new UserFragment();
                fragment.setArguments(args);
                return fragment;
            }

            @Override
            public int getCount() { // возвращает кол-во эллементов списка
                return users.size();
            }
        });
        userViewPager.setCurrentItem(position);
    }
}